package usedcardealership;

import usedcardealership.interaction.*;
import usedcardealership.data.filehandling.*;
import usedcardealership.data.vehicle.*;
import usedcardealership.data.customer.*;
import usedcardealership.data.transaction.*;
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
            switch (Prompter.promptOption(
                    "1: Type\n" +
                            "2: Make\n" +
                            "3: Color\n" +
                            "4: Year Range\n" +
                            "5: Drive Type\n" +
                            "6: Price Range\n" +
                            "7: Kilometrage Range\n" +
                            "8: Transmission Type\n" +
                            "0: Exit",
                    8)) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    genericFilterView(dealership, "type");
                    break;
                case 2:
                    genericFilterView(dealership, "make");
                    break;
                case 3:
                    genericFilterView(dealership, "color");
                    break;
                case 4:
                    genericFilterView(dealership, "year");
                    break;
                case 5:
                    genericFilterView(dealership, "drive");
                    break;
                case 6:
                    genericFilterView(dealership, "price");
                    break;
                case 7:
                    genericFilterView(dealership, "kilo");
                    break;
                case 8:
                    genericFilterView(dealership, "trans");
                    break;
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
            String filterPrompt = getFilterPrompt(filterType);
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
     * Checks to see if vehicle list is empty or not and opens vehicle selection view
     * 
     * @param dealership the DealershipManager object
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
                // TODO: Sort alphabetically
                for (Vehicle v : dealership.getInventory()) {
                    criteriaSet.add(v.getType());
                }
                break;
            case "make":
                // TODO: Sort alphabetically
                for (Vehicle v : dealership.getInventory()) {
                    criteriaSet.add(v.getMake());
                }
                break;
            case "color":
                // TODO: Sort alphabetically
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
            // Print unique available options to choose from
            System.out.println("Available options:");
            for (String criteria : criteriaSet) {
                System.out.println(criteria);
            }
        }
    }

    /**
     * Returns a unique prompt depending on the filter type
     * 
     * @param filterType the method we are filtering by
     * @return the prompt from the particular type of filter
     */
    private static String getFilterPrompt(String filterType) {
        switch (filterType) {
            case "type":
                return "\nEnter vehicle type or press Enter to go back:";
            case "make":
                return "\nEnter vehicle make or press Enter to go back:";
            case "color":
                return "\nEnter vehicle color or press Enter to go back:";
            case "year":
                return "\nEnter vehicle year range or press Enter to go back:";
            case "drive":
                return "\nEnter vehicle drive type or press Enter to go back:";
            case "price":
                return "\nEnter vehicle price range or press Enter to go back:";
            case "kilo":
                return "\nEnter vehicle kilometrage range or press Enter to go back:";
            case "trans":
                return "\nEnter vehicle transmission type or press Enter to go back:";
            default:
                return "\nEnter filter criteria or press Enter to go back:";
        }
    }

    /**
     * Applies the filter to the dealership inventory using searchInventory
     * 
     * @param dealership the DealershipManager object
     * @param filterType the method we are filtering by
     * @param criteria   the user input criteria to pass into the filter
     * @return a list of Vehicles that are filtered by user input
     */
    private static List<Vehicle> applyFilter(DealershipManager dealership, String filterType, String criteria) {
        switch (filterType) {
            case "type":
                return dealership.getVehicleManager().searchInventory(new VehicleTypeFilter(criteria));
            case "make":
                return dealership.getVehicleManager().searchInventory(new VehicleMakeFilter(criteria));
            case "color":
                return dealership.getVehicleManager().searchInventory(new VehicleColorFilter(criteria));
            case "drive":
                return dealership.getVehicleManager().searchInventory(new VehicleDriveFilter(criteria));
            case "trans":
                return dealership.getVehicleManager().searchInventory(new VehicleTransmissionFilter(criteria));
            default:
                return new ArrayList<>();
        }
    }

    /**
     * Applies the range filter to the dealership inventory using searchInventory
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
                return dealership.getVehicleManager()
                        .searchInventory(new VehicleYearRangeFilter(Integer.parseInt(min), Integer.parseInt(max)));
            case "price":
                return dealership.getVehicleManager()
                        .searchInventory(new VehiclePriceRangeFilter(Double.parseDouble(min), Double.parseDouble(max)));
            case "kilo":
                return dealership.getVehicleManager().searchInventory(
                        new VehicleKilometerageRangeFilter(Double.parseDouble(min), Double.parseDouble(max)));
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
            // TODO: sort vehicles numerically by ID
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
            // TODO: possibly could add type based menu option for sorting by filter type
            // For example: price, kilometrage, year, id, etc
            System.out.println("\nWould you like to:");
            switch (Prompter.promptOption(
                    "1: Select Vehicle by ID\n0: Exit", 1)) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    int vehicleID = selectVehicle(vehicles);
                    if (vehicleID == -1) {
                        System.out.println("\nInvalid Vehicle ID!");
                        Prompter.promptEnter();
                    } else {
                        vehicleDetailsMenu(dealership, vehicleID);
                    }
                    break;
            }
        }
    }

    /**
     * Allows the user to select a vehicle by ID
     * 
     * @return the selected ID of the vehicle they want more details on
     */
    private static int selectVehicle(List<Vehicle> vehicles) {
        if (vehicles.size() == 1) {
            return vehicles.get(0).getID();
        }
        int chosenId = Prompter.promptVehicleId();
        for (Vehicle v : vehicles) {
            if (chosenId == v.getID()) {
                return chosenId;
            }
        }
        return -1;
    }

    /**
     * Menu that asks user if they want to purchase vehicle or go back
     * 
     */
    private static void vehicleDetailsMenu(DealershipManager dealership, int vehicleId) {
        boolean inPage = true;
        int testDriveCount = 0;
        Vehicle vehicle = dealership.getVehicleById(vehicleId);
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
        List<Transaction> transactions = dealership.getTransactions();
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
                        System.out.println("\nYou selected vehicle: " + vehicleID);
                        System.out.println("\nDo you want to sell this vehicle?");
                        boolean confirmed = Prompter.promptYesNo();
                        if (confirmed) {
                            // TODO: handle sale or add to shopping cart, handle removing from the customer's vehicleList
                            System.out.println("\nThe vehicle has been marked for sale.");
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

}
