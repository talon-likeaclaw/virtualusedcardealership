package usedcardealership;

import usedcardealership.interaction.*;
import usedcardealership.data.filehandling.*;
import usedcardealership.data.vehicle.*;
import usedcardealership.data.customer.*;
import usedcardealership.data.transaction.*;
import usedcardealership.business.comparators.*;
import usedcardealership.business.filter.*;
import usedcardealership.business.manager.*;

import java.util.*;

public class UsedCarDealership {
    public static void main(String[] args) {
        DealershipManager dealership = initialize();
        mainMenuView(dealership);
        shutdown(dealership);
    }

    /**
     * Main menu that allows user to choose what to do
     * 
     * @param dealership the DealershipManager object
     */
    private static void mainMenuView(DealershipManager dealership) {
        boolean inPage = true;
        while (inPage) {
            wipe();
            System.out.println("Welcome to " + dealership.getName() + "!");
            System.out.println("\nWould you like to:");
            switch (Prompter.promptOption(
                    "1: Browse Vehicles\n2: View Account and Owned Vehicles\n3: Sell Vehicle to Dealership\n4: View Shopping Cart\n0: Exit",
                    4)) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    chooseVehicleFilterView(dealership);
                    break;
                case 2:
                    viewAccountView(dealership);
                    break;
                case 3:
                    sellVehicleView(dealership);
                    break;
                case 4:
                    // TODO: viewShoppingCart()
                    break;
                default:
                    System.out.println("I hope you're proud of yourself, you broke");
            }
        }
    }

    /**
     * View to let customer choose how to filter Vehicles
     * 
     * @param dealership the DealershipManager object
     */
    private static void chooseVehicleFilterView(DealershipManager dealership) {
        boolean inPage = true;
        while (inPage) {
            wipe();
            System.out.println("Filter by:");
            System.out.println(
                    "Type\nMake\nColor\nYear Range\nDrive Type\nPrice Range\nKilometrage Range\nTransmission Type");
            System.out.println(Prompter.getPrompt("filter"));
            String input = Prompter.promptString();
            if (input == null) {
                inPage = false;
                break;
            } else {
                input.trim().toLowerCase();
            }
            switch (input) {
                case "type":
                    genericFilterView(dealership, "type");
                    break;
                case "make":
                    genericFilterView(dealership, "make");
                    break;
                case "color":
                    genericFilterView(dealership, "color");
                    break;
                case "year range":
                    genericFilterView(dealership, "year");
                    break;
                case "drive type":
                    genericFilterView(dealership, "drive");
                    break;
                case "price range":
                    genericFilterView(dealership, "price");
                    break;
                case "kilometrage range":
                    genericFilterView(dealership, "kilo");
                    break;
                case "transmission type":
                    genericFilterView(dealership, "trans");
                    break;
                default:
                    System.out.println("\nInvalid filter name. Please try again.");
                    Prompter.promptEnter();
            }
        }
    }

    /**
     * Generic view for allowing user to choose from filtered criteria
     * 
     * @param dealership the DealershipManager object
     * @param filterType the method we are filtering by (type, make, color, etc)
     */
    private static void genericFilterView(DealershipManager dealership, String filterType) {
        boolean inPage = true;
        while (inPage) {
            wipe();
            if (!filterType.equals("price") && !filterType.equals("year") && !filterType.equals("kilo")) {
                // Display all availble criteria and prompt user to choose one
                displayAvailableCriteria(dealership, filterType);
            }
            String filterPrompt = Prompter.getPrompt(filterType);
            System.out.println(filterPrompt);
            if (filterType.equals("price") || filterType.equals("year") || filterType.equals("kilo")) {
                inPage = handleRangeFiltering(dealership, filterType);
            } else {
                inPage = handleStringFiltering(dealership, filterType);
            }
        }
    }

    /**
     * Handles logic necessary for range filtering
     * 
     * @param dealership the DealershipManager object
     * @param filterType the method we are filtering by
     * @return boolean representing if we are still in the page or not
     */
    private static boolean handleRangeFiltering(DealershipManager dealership, String filterType) {
        System.out.println("Enter the range in the format `min-max`.");
        String rangeInput = Prompter.promptString();
        // If range null or invalid format return false
        if (rangeInput == null) {
            return false;
        } else if (!rangeInput.contains("-")) {
            System.out.println("\nInvalid input! Returning to filter menu.");
            Prompter.promptEnter();
            return false;
        }
        // Split input by "-"
        String[] range = rangeInput.split("-");
        try {
            String min = range[0];
            String max = range[1];

            // Get filtered list by range
            List<Vehicle> filteredVehicles = applyRangeFilter(dealership, filterType, min, max);

            // If no vehicles
            return handleFilteredVehicles(dealership, filteredVehicles);
        } catch (NumberFormatException e) {
            System.out.println("Invalid range input! Returning to menu.");
            Prompter.promptEnter();
            return false;
        }
    }

    /**
     * Checks to see if vehicle list is empty or not and opens vehicle selection
     * view
     * 
     * @param dealership       the DealershipManager object
     * @param filteredVehicles the list of filtered vehicles
     * @return true if list is not empty false if list is empty.
     */
    private static boolean handleFilteredVehicles(DealershipManager dealership, List<Vehicle> filteredVehicles) {
        if (filteredVehicles.size() == 0) {
            System.out.println("\nNo vehicles match your criteria!");
            Prompter.promptEnter();
            return false;
        } else {
            selectVehiclesFromList(dealership, filteredVehicles);
            return true;
        }
    }

    /**
     * Handles logic necessary for string filtering
     * 
     * @param dealership the DealershipManager object
     * @param filterType the method we are filtering by
     * @return boolean representing if we are still in the page or not
     */
    private static boolean handleStringFiltering(DealershipManager dealership, String filterType) {
        String criteria = Prompter.promptString();
        // If criteria null go back
        if (criteria == null) {
            return false;
        }
        // Apply user input filter to get list of filtered vehicles
        List<Vehicle> filteredVehicles = applyFilter(dealership, filterType, criteria);
        // If no vehicles print warning and prompt enter
        return handleFilteredVehicles(dealership, filteredVehicles);
    }

    /**
     * Displays the all of the unique available criteria to choose from
     * 
     * @param dealership the DealershipManager object
     * @param filterType the method we are filtering by
     */
    private static void displayAvailableCriteria(DealershipManager dealership, String filterType) {
        // Create String hash set for unique values only
        HashSet<String> criteriaSet = new HashSet<>();
        // Depending on filterType, print unique values to choose from
        switch (filterType) {
            case "type":
                for (Vehicle v : dealership.getInventory()) {
                    criteriaSet.add(v.getType());
                }
                break;
            case "make":
                for (Vehicle v : dealership.getInventory()) {
                    criteriaSet.add(v.getMake());
                }
                break;
            case "color":
                for (Vehicle v : dealership.getInventory()) {
                    criteriaSet.add(v.getColor());
                }
                break;
            case "drive":
                for (Vehicle v : dealership.getInventory()) {
                    criteriaSet.add(v.getDriveType());
                }
                break;
            case "trans":
                for (Vehicle v : dealership.getInventory()) {
                    criteriaSet.add(v.getTransmission());
                }
                break;
            default:
                System.out.println("No available criteria to display for this filter.");
                Prompter.promptEnter();
                return;
        }
        // If there are no options to choose from print warning
        if (criteriaSet.size() == 0) {
            System.out.println("No options available.");
            Prompter.promptEnter();
        } else {
            // Convert HashSet to List for sorting
            List<String> sortedCriteria = new ArrayList<>(criteriaSet);
            // Sort alphabetically
            Collections.sort(sortedCriteria);
            System.out.println("Available options:");
            for (String criteria : sortedCriteria) {
                System.out.println(criteria);
            }
        }
    }

    /**
     * Applies the filter to the dealership inventory using searchInventory
     * Automatically sorts vehicle list by ID
     * 
     * @param dealership the DealershipManager object
     * @param filterType the method we are filtering by
     * @param criteria   the user input criteria to pass into the filter
     * @return a list of Vehicles that are filtered by user input
     */
    private static List<Vehicle> applyFilter(DealershipManager dealership, String filterType, String criteria) {
        switch (filterType) {
            case "type":
                return dealership.getVehicleManager().sortVehiclesById(
                        dealership.getVehicleManager().searchInventory(new VehicleTypeFilter(criteria)));
            case "make":
                return dealership.getVehicleManager().sortVehiclesById(
                        dealership.getVehicleManager().searchInventory(new VehicleMakeFilter(criteria)));
            case "color":
                return dealership.getVehicleManager().sortVehiclesById(
                        dealership.getVehicleManager().searchInventory(new VehicleColorFilter(criteria)));
            case "drive":
                return dealership.getVehicleManager().sortVehiclesById(
                        dealership.getVehicleManager().searchInventory(new VehicleDriveFilter(criteria)));
            case "trans":
                return dealership.getVehicleManager().sortVehiclesById(
                        dealership.getVehicleManager().searchInventory(new VehicleTransmissionFilter(criteria)));
            default:
                return new ArrayList<>();
        }
    }

    /**
     * Applies the range filter to the dealership inventory using searchInventory
     * Automatically sorts vehicle list by ID
     * 
     * @param dealership the DealershipManager object
     * @param filterType the method we are filtering by
     * @param min        the user input min range
     * @param max        the user input max range
     * @return a list of Vehicles that are filter by the crtieria
     */
    private static List<Vehicle> applyRangeFilter(DealershipManager dealership, String filterType, String min,
            String max) {
        switch (filterType) {
            case "year":
                return dealership.getVehicleManager().sortVehiclesById(dealership.getVehicleManager()
                        .searchInventory(new VehicleYearRangeFilter(Integer.parseInt(min), Integer.parseInt(max))));
            case "price":
                return dealership.getVehicleManager().sortVehiclesById(dealership.getVehicleManager()
                        .searchInventory(
                                new VehiclePriceRangeFilter(Double.parseDouble(min), Double.parseDouble(max))));
            case "kilo":
                return dealership.getVehicleManager().sortVehiclesById(dealership.getVehicleManager().searchInventory(
                        new VehicleKilometerageRangeFilter(Double.parseDouble(min), Double.parseDouble(max))));
            default:
                return new ArrayList<>();
        }
    }

    /**
     * Allows customer to select vehicle from list by ID
     * 
     * @param dealership the DealershipManager object
     * @param vehicles   the list of vehicles to select from
     */
    private static void selectVehiclesFromList(DealershipManager dealership, List<Vehicle> vehicles) {
        boolean inPage = true;
        while (inPage) {
            wipe();
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
            // Print out prompt and get user input for sorting
            System.out.println(Prompter.getPrompt("id-sort"));
            String input = Prompter.promptString();
            // If null go back else trim and set to lowercase
            if (input == null) {
                inPage = false;
                break;
            } else {
                input = input.trim().toLowerCase();
            }
            try {
                // Check if input is numeric (assumes it's a vehicle ID)
                int vehicleID = Integer.parseInt(input);
                Vehicle selectedVehicle = dealership.getVehicleManager().getVehicleById(vehicleID);
                if (selectedVehicle != null) {
                    vehicleDetailsMenu(dealership, vehicleID);
                } else {
                    System.out.println("\nInvalid Vehicle ID!");
                    Prompter.promptEnter();
                }
            } catch (NumberFormatException e) {
                // Get sorting type and order
                String[] sortingInfo = input.split(" ");
                String sortType = sortingInfo[0];
                // Default to ascending if no "desc"
                boolean ascending = sortingInfo.length < 2 || !sortingInfo[1].equals("desc"); 
                switch (sortType) {
                    case "id":
                        dealership.getVehicleManager().sortVehicles(vehicles, new VehicleIdCompare(), ascending);
                        System.out.println("\nSorting by ID " + (ascending ? "ascending." : "descending."));
                        break;
                    case "year":
                        dealership.getVehicleManager().sortVehicles(vehicles, new VehicleYearCompare(), ascending);
                        System.out.println("\nSorting by Year " + (ascending ? "ascending." : "descending."));
                        break;
                    case "kilometrage":
                        dealership.getVehicleManager().sortVehicles(vehicles, new VehicleKilometerageCompare(), ascending);
                        System.out.println("\nSorting by Kilometrage " + (ascending ? "ascending." : "descending."));
                        break;
                    case "damage":
                        dealership.getVehicleManager().sortVehicles(vehicles, new VehicleDamageCompare(), ascending);
                        System.out.println("\nSorting by Damage " + (ascending ? "ascending." : "descending."));
                        break;
                    default:
                        System.out.println("\nInvalid option. Please enter a valid vehicle ID or sorting type.");
                }
                Prompter.promptEnter();
            }
        }
    }


    /**
     * Menu that asks user if they want to purchase vehicle or go back
     * 
     */
    private static void vehicleDetailsMenu(DealershipManager dealership, int vehicleId) {
        boolean inPage = true;
        int testDriveCount = 0;
        Vehicle vehicle = dealership.getVehicleManager().getVehicleById(vehicleId);
        while (inPage) {
            wipe();
            System.out.println(vehicle.getFullDetails());
            System.out.println("\nWould you like to:");
            switch (Prompter.promptOption(
                    "1: Test Drive Vehicle\n2: Add Vehicle to Cart\n0: Return to Vehicle List", 2)) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    if (testDriveCount < 1) {
                        try {
                            dealership.getVehicleManager().getVehicleById(vehicleId).testDrive();
                            Prompter.promptEnter();
                            testDriveCount++;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("\nYou just test drove this vehicle!");
                        Prompter.promptEnter();
                        wipe();
                    }
                    break;
                case 2:
                    // TODO: Implement method to add vehicle to cart
                    break;
            }
        }
    }

    /**
     * Initializes the DealershipManager by loading all data from files
     * 
     * @return the DealershipManager object that was initialized
     */
    private static DealershipManager initialize() {
        String dealershipName = "Talon & Juan's Used Car Emporium";
        double dealershipAccountBalance = 567234.54;

        // Load vehicles
        String vehicleDatabasePath = "resources/database.csv";
        List<Vehicle> database = initializeListVehicle(vehicleDatabasePath);
        String vehicleInventoryPath = "resources/inventory.csv";
        List<Vehicle> inventory = initializeListVehicle(vehicleInventoryPath);

        // Load customers
        String customerPath = "resources/customers.csv";
        CustomerFileHandler customerLoader = new CustomerFileHandler(customerPath);
        List<Customer> customers = customerLoader.load();
        // TODO: Select a random customer from the list to assign the the currentUser on
        // init
        // Thought it would be cool if each time you start up the program you are a
        // random customer

        // Load transactions
        String transactionPath = "resources/transactions.csv";
        TransactionFileHandler transactionLoader = new TransactionFileHandler(transactionPath);
        List<Transaction> transactions = transactionLoader.load();

        // Initialize and return the DealershipManager
        DealershipManager dealership = new DealershipManager(
                dealershipName, dealershipAccountBalance, transactions, inventory, database, customers);
        initializeCurrentCustomer(customers, dealership);
        return dealership;
    }

    /**
     * Initilizes a VehicleFileLoader and loads from file path
     * 
     * @param filePath the file path to load from
     * @return a list of vehicles loaded from file
     */
    private static List<Vehicle> initializeListVehicle(String filePath) {
        VehicleFileHandler vehicleLoader = new VehicleFileHandler(filePath);
        return vehicleLoader.load();
    }

    /**
     * Shuts down the program, saving the dealerships database, inventory,
     * customerlist, and transactionhistory to csv files
     * 
     * @param dealership the DealershipManager object
     */
    private static void shutdown(DealershipManager dealership) {
        // Save inventory and database
        String vehicleDatabasePath = "resources/database.csv";
        String vehicleInventoryPath = "resources/inventory.csv";
        List<Vehicle> database = dealership.getDatabase();
        List<Vehicle> inventory = dealership.getInventory();
        VehicleFileHandler vehicleDatabaseSaver = new VehicleFileHandler(vehicleDatabasePath);
        VehicleFileHandler vehicleInventorySaver = new VehicleFileHandler(vehicleInventoryPath);
        vehicleDatabaseSaver.save(database);
        vehicleInventorySaver.save(inventory);

        // Save customers
        String customerPath = "resources/customers.csv";
        List<Customer> customers = dealership.getCustomers();
        CustomerFileHandler customerSaver = new CustomerFileHandler(customerPath);
        customerSaver.save(customers);

        // Save transactions
        String transactionPath = "resources/transactions.csv";
        List<Transaction> transactions = dealership.getTransactionManager().getTransactions();
        TransactionFileHandler transactionSaver = new TransactionFileHandler(transactionPath);
        transactionSaver.save(transactions);

        System.out.println("\nShutting down. Please come again! :)");
        Prompter.close();
    }

    /**
     * Wipes the console screen
     */
    public static void wipe() {
        System.out.print("\033[H\033[2J");
    }

    /**
     * Initializes the current customer for the dealership by randomly selecting a customer 
     * from the provided list of customers.
     * 
     * @param customers   the list of customers available
     * @param dealership  the dealership manager object to set the current customer
     */
    private static void initializeCurrentCustomer(List<Customer> customers, DealershipManager dealership) {
        Random rand = new Random();
        dealership.setCurrentCustomer(customers.get(rand.nextInt(customers.size())));
    }

    /**
     * Displays the account details of the current customer and allows navigation back to the main menu.
     * 
     * @param dealer  the dealership manager containing the current customer's information
     */
    private static void viewAccountView(DealershipManager dealer) {
        wipe();
        System.out.println(dealer.getCurrentCustomer());
        boolean inPage = true;
        while (inPage) {
            switch (Prompter.promptOption(
                    "\n0: Exit",
                    0)) {
                case 0:
                    inPage = false;
                    break;
                default:
                    System.out.println("You may only select 0");
            }
        }
    }

    /**
     * Allows the current customer to view and sell their vehicles. The user can:
     * - View a list of owned vehicles.
     * - Select a vehicle by its ID to sell.
     * - Confirm whether to proceed with the sale.
     * 
     * @param dealer  the dealership manager containing the current customer's vehicles
     */
    private static void sellVehicleView(DealershipManager dealer) {
        boolean inPage = true;
        while (inPage) {
            wipe();
            List<Vehicle> vehicles = dealer.getCurrentCustomer().getVehicles();
            List<Integer> ids = new ArrayList<Integer>();  
            System.out.println("You own: \n");
            for (int i = 0; i < vehicles.size(); i++) {
                System.out.println(vehicles.get(i));
                ids.add(vehicles.get(i).getID());
            }
            switch (Prompter.promptOption("1: Select Vehicle by ID\n0: Exit", 1)) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    System.out.println("\nWhich vehicle will you sell to us?");
                    int vehicleID = Prompter.promptVehicleId();
                    if (!(ids.contains(vehicleID))) {
                        System.out.println("\nInvalid Vehicle ID!");
                        Prompter.promptEnter();
                    } else {
                        wipe();
                        System.out.println("\nAre you sure you want to sell vehicle "+ vehicleID + "?");
                        boolean confirmed = Prompter.promptYesNo();
                        if (confirmed) {
                            manageVehicleTransaction(dealer, vehicleID);
                            Prompter.promptEnter();
                        } else {
                            System.out.println("\nVehicle selection cancelled.");
                            Prompter.promptEnter();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * Manages the vehicle transaction process, offering a vehicle to the customer, 
     * confirming the offer, and processing the sale if accepted.
     *
     * @param dealer the DealershipManager responsible for managing the dealership operations
     * @param vehicleID the ID of the vehicle being offered for sale
    */
    private static void manageVehicleTransaction(DealershipManager dealer, int vehicleID){
        Vehicle vehicle = dealer.getCurrentCustomer().getVehicleById(vehicleID);
        Customer customer = dealer.getCurrentCustomer();

        if (vehicle == null || customer == null) {
            System.out.println("Error: Vehicle or Customer not found!");
            return;
        }
        System.out.println("The dealership offers you $" + vehicle.calculateTotalPrice() + " for the vehicle.");

        System.out.println("Do you accept this offer? (Y/N)");
        boolean confirmed = Prompter.promptYesNo();
        wipe();
        if (confirmed) {
            dealer.processCustomerVehicleSale(vehicle, customer, "purchase");
            List<Transaction> transactions = dealer.getTransactionManager().getTransactions();

            System.out.println("Sale successful!");
            System.out.println("Updated Account Balance: " + customer.getAccountBalance());
            System.out.println("\nReceipt: \n" + transactions.get(transactions.size() - 1));
        } else {
            System.out.println("Sale cancelled.");
        }

    } 

}
