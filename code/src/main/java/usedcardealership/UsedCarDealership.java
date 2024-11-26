package usedcardealership;

import usedcardealership.interaction.*;
import usedcardealership.data.filehandling.*;
import usedcardealership.data.vehicle.*;
import usedcardealership.data.customer.*;
import usedcardealership.data.transaction.*;
import usedcardealership.data.coupons.*;
import usedcardealership.business.comparators.*;
import usedcardealership.business.filter.*;
import usedcardealership.business.manager.*;

import java.util.*;

public class UsedCarDealership {
    public static void main(String[] args) {
        try {
            DealershipManager dealership = initialize();
            if (dealership != null) {
                mainMenuView(dealership);
            }
            shutdown(dealership);
        } catch (Exception e) {
            PrettyUtils.printRed("An unexpected error occured. Exiting the program.");
            PrettyUtils.printRed(e.getMessage());
            Prompter.promptEnter();
        }
    }

    /**
     * Main menu that allows user to choose what to do
     * 
     * @param dealership the DealershipManager object
     */
    private static void mainMenuView(DealershipManager dealership) {
        boolean inPage = true;
        while (inPage) {
            // Print welcome message
            PrettyUtils.wipe();
            PrettyUtils.printCyan(PrettyUtils.returnCars());
            PrettyUtils.printCyan("Welcome to " + dealership.getName() + "!");
            // Print menu
            PrettyUtils.printYellow("\nWould you like to:");
            String menu = PrettyUtils.returnYellow("1:") + " Browse Vehicles\n" +
                    PrettyUtils.returnYellow("2:") + " View Account and Owned Vehicles\n" +
                    PrettyUtils.returnYellow("3:") + " Sell Vehicle to Dealership\n" +
                    PrettyUtils.returnYellow("4:") + " View Shopping Cart\n" +
                    PrettyUtils.returnYellow("0:") + " Exit";
            try {
                // Prompt user for input
                int choice = Prompter.promptOption(menu, 4);
                if (choice == -1) {
                    // Invalid input, restart loop
                    continue;
                }
                switch (choice) {
                    case 0:
                        // User decided to exit
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
                        viewShoppingCart(dealership);
                        break;
                    default:
                        PrettyUtils.printRed("I hope you're proud of yourself, you broke\n");
                }
            } catch (Exception e) {
                PrettyUtils.printRed("An error occuyred while processing your choice.");
                System.out.println(e.getMessage());
                Prompter.promptEnter();
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
            PrettyUtils.wipe();
            PrettyUtils.printYellow("Filter Types:");
            System.out.println(
                    "- Type\n- Make\n- Color\n- Year Range\n- Drive Type\n- Price Range\n- Kilometrage Range\n- Transmission Type");
            System.out.println(Prompter.getPrompt("filter"));
            try {
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
                        PrettyUtils.printRed("\nInvalid filter name. Please try again.");
                        Prompter.promptEnter();
                }
            } catch (Exception e) {
                PrettyUtils.printRed("An error occured while processing your choice.");
                PrettyUtils.printRed(e.getMessage());
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
            PrettyUtils.wipe();
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
        System.out.println("Enter the range in the format " + PrettyUtils.returnYellow("`min-max`") + ".");
        String rangeInput = Prompter.promptString();
        // If range null or invalid format return false
        if (rangeInput == null) {
            return false;
        } else if (!rangeInput.contains("-")) {
            PrettyUtils.printRed("\nInvalid input! Returning to filter menu.");
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
        } catch (Exception e) {
            PrettyUtils.printRed("\nInvalid range input! Returning to menu.");
            PrettyUtils.printRed(e.getMessage());
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
            PrettyUtils.printRed("\nNo vehicles match your criteria!");
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
                PrettyUtils.printRed("No available criteria to display for this filter.");
                Prompter.promptEnter();
                return;
        }
        // If there are no options to choose from print warning
        if (criteriaSet.size() == 0) {
            PrettyUtils.printRed("No options available.");
            Prompter.promptEnter();
        } else {
            // Convert HashSet to List for sorting
            List<String> sortedCriteria = new ArrayList<>(criteriaSet);
            // Sort alphabetically
            Collections.sort(sortedCriteria);
            PrettyUtils.printYellow("Available options:");
            for (String criteria : sortedCriteria) {
                System.out.println("- " + criteria);
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
        if (criteria == null || criteria.length() == 0) {
            PrettyUtils.printRed("Invalid filter criteria");
            return new ArrayList<>();
        }
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
        try {
            double minValue = Double.parseDouble(min);
            double maxValue = Double.parseDouble(max);
            switch (filterType) {
                case "year":
                    return dealership.getVehicleManager().sortVehiclesById(dealership.getVehicleManager()
                            .searchInventory(new VehicleYearRangeFilter(Integer.parseInt(min), Integer.parseInt(max))));
                case "price":
                    return dealership.getVehicleManager().sortVehiclesById(dealership.getVehicleManager()
                            .searchInventory(
                                    new VehiclePriceRangeFilter(minValue, maxValue)));
                case "kilo":
                    return dealership.getVehicleManager()
                            .sortVehiclesById(dealership.getVehicleManager().searchInventory(
                                    new VehicleKilometerageRangeFilter(minValue, maxValue)));
                default:
                    return new ArrayList<>();
            }
        } catch (NumberFormatException e) {
            PrettyUtils.printRed("Invalid range values. Please enter numeric values.");
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
            PrettyUtils.wipe();
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
                    vehicleDetailsMenu(dealership, vehicleID, vehicles);
                } else {
                    PrettyUtils.printRed("\nInvalid Vehicle ID!");
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
                    case "price":
                        dealership.getVehicleManager().sortVehicles(vehicles, new VehiclePriceCompare(), ascending);
                        System.out.println("\nSorting by Price " + (ascending ? "ascending." : "descending."));
                        break;
                    case "year":
                        dealership.getVehicleManager().sortVehicles(vehicles, new VehicleYearCompare(), ascending);
                        System.out.println("\nSorting by Year " + (ascending ? "ascending." : "descending."));
                        break;
                    case "kilometrage":
                        dealership.getVehicleManager().sortVehicles(vehicles, new VehicleKilometerageCompare(),
                                ascending);
                        System.out.println("\nSorting by Kilometrage " + (ascending ? "ascending." : "descending."));
                        break;
                    case "damage":
                        dealership.getVehicleManager().sortVehicles(vehicles, new VehicleDamageCompare(), ascending);
                        System.out.println("\nSorting by Damage " + (ascending ? "ascending." : "descending."));
                        break;
                    default:
                        PrettyUtils.printRed("\nInvalid option. Please enter a valid vehicle ID or sorting type.");
                }
                Prompter.promptEnter();
            }
        }
    }

    /**
     * Menu that asks user if they want to purchase vehicle or go back
     * 
     */
    // TODO: Add a straight to checkoout option 
    private static void vehicleDetailsMenu(DealershipManager dealership, int vehicleId, List<Vehicle> vehicles) {
        boolean inPage = true;
        int testDriveCount = 0;
        Vehicle vehicle = dealership.getVehicleManager().getVehicleById(vehicleId);
        while (inPage) {
            PrettyUtils.wipe();
            System.out.println(vehicle.getFullDetails());
            PrettyUtils.printYellow("\nWould you like to:");
            String menu = PrettyUtils.returnYellow("1:") + " Test Drive Vehicle\n" +
                    PrettyUtils.returnYellow("2:") + " Add Vehicle to Cart\n" +
                    PrettyUtils.returnYellow("0:") + " Return to Vehicle List";
            int choice = Prompter.promptOption(menu, 2);
            if (choice == -1) {
                continue;
            }
            switch (choice) {
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
                        PrettyUtils.printRed("\nYou just test drove this vehicle!");
                        Prompter.promptEnter();
                        PrettyUtils.wipe();
                    }
                    break;
                case 2:
                    addVehicleToCart(dealership, vehicle, vehicles);
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
        try {
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

            // Load transactions
            String transactionPath = "resources/transactions.csv";
            TransactionFileHandler transactionLoader = new TransactionFileHandler(transactionPath);
            List<Transaction> transactions = transactionLoader.load();

            //Load coupons
            String couponPath = "resources/coupons.csv";
            CouponFileHandler couponLoader = new CouponFileHandler(couponPath);
            List<Coupon> coupons = couponLoader.load();

            // Initialize and return the DealershipManager
            DealershipManager dealership = new DealershipManager(
                    dealershipName, dealershipAccountBalance, transactions, inventory, database, customers, coupons);
            initializeCurrentCustomer(customers, dealership);
            return dealership;
        } catch (Exception e) {
            PrettyUtils.printRed("Error occured when reading files.");
            PrettyUtils.printRed(e.getMessage());
            Prompter.promptEnter();
            return null;
        }
    }

    /**
     * Initilizes a VehicleFileLoader and loads from file path
     * 
     * @param filePath the file path to load from
     * @return a list of vehicles loaded from file
     */
    private static List<Vehicle> initializeListVehicle(String filePath) {
        try {
            VehicleFileHandler vehicleLoader = new VehicleFileHandler(filePath);
            return vehicleLoader.load();
        } catch (Exception e) {
            PrettyUtils.printRed("Error loading vehicles from file: " + filePath);
            PrettyUtils.printRed(e.getMessage());
            Prompter.promptEnter();
            return new ArrayList<>();
        }
    }

    /**
     * Shuts down the program, saving the dealerships database, inventory,
     * customerlist, and transactionhistory to csv files
     * 
     * @param dealership the DealershipManager object
     */
    private static void shutdown(DealershipManager dealership) {
        try {
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

            PrettyUtils.printCyan("\nShutting down. Please come again! :)");
        } catch (Exception e) {
            PrettyUtils.printRed("Error saving data. Your changes may not have been saved.");
            PrettyUtils.printRed(e.getMessage());
            Prompter.promptEnter();
        } finally {
            Prompter.close();
        }
    }

    /**
     * Initializes the current customer for the dealership by randomly selecting a
     * customer
     * from the provided list of customers.
     * 
     * @param customers  the list of customers available
     * @param dealership the dealership manager object to set the current customer
     */
    private static void initializeCurrentCustomer(List<Customer> customers, DealershipManager dealership) {
        if (customers == null || customers.size() == 0) {
            PrettyUtils.printRed("No customers found to initialize.");
            return;
        }
        Random rand = new Random();
        dealership.setCurrentCustomer(customers.get(rand.nextInt(customers.size())));
    }

    /**
     * Displays the account details of the current customer and allows navigation
     * back to the main menu.
     * 
     * @param dealer the dealership manager containing the current customer's
     *               information
     */
    private static void viewAccountView(DealershipManager dealer) {
        boolean inPage = true;
        while (inPage) {
            PrettyUtils.wipe();
            PrettyUtils.printYellow("Account Details:");
            System.out.println(dealer.getCurrentCustomer());
            List<Vehicle> vehicles = dealer.getCurrentCustomer().getVehicles();
            List<Integer> ids = new ArrayList<Integer>();
            PrettyUtils.printYellow("\nCurrent Vehicles:");
            for (int i = 0; i < vehicles.size(); i++) {
                System.out.println(vehicles.get(i));
                ids.add(vehicles.get(i).getID());
            }
            PrettyUtils.printYellow("\nWould you like to:");
            String menu = PrettyUtils.returnYellow("1:") + " Sell Vehicle\n" +
                    PrettyUtils.returnYellow("0:") + " Exit";
            int choice = Prompter.promptOption(menu, 1);
            if (choice == -1) {
                continue;
            }
            switch (choice) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    sellVehicleView(dealer);
                default:
                    PrettyUtils.printRed("You may only select 0");
            }
        }
    }

    /**
     * Allows the current customer to view and sell their vehicles. The user can:
     * - View a list of owned vehicles.
     * - Select a vehicle by its ID to sell.
     * - Confirm whether to proceed with the sale.
     * 
     * @param dealer the dealership manager containing the current customer's
     *               vehicles
     */
    private static void sellVehicleView(DealershipManager dealer) {
        boolean inPage = true;
        while (inPage) {
            PrettyUtils.wipe();
            List<Vehicle> vehicles = dealer.getCurrentCustomer().getVehicles();
            List<Integer> ids = new ArrayList<Integer>();
            int choice;
            if (vehicles.size() > 0) {
                PrettyUtils.printYellow("Current Vehicles:");
                for (int i = 0; i < vehicles.size(); i++) {
                    System.out.println(vehicles.get(i));
                    ids.add(vehicles.get(i).getID());
                }
                PrettyUtils.printYellow("Would you like to:");
                String menu = PrettyUtils.returnYellow("1:") + " Select Vehicle to Sell\n" +
                        PrettyUtils.returnYellow("0:") + " Exit";
                choice = Prompter.promptOption(menu, 1);
            } else {
                PrettyUtils.printRed("You currently do not own any vehicles.");
                Prompter.promptEnter();
                choice = 0;
            }
            if (choice == -1) {
                // Invalid input; loop restarts automatically
                continue;
            }
            switch (choice) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    System.out.println("\nWhich vehicle will you sell to us?");
                    try {
                        int vehicleID = Prompter.promptVehicleId();
                        if (vehicleID == -1) {
                            Prompter.promptEnter();
                            break;
                        }
                        if (!(ids.contains(vehicleID))) {
                            PrettyUtils.printRed("\nInvalid Vehicle ID!");
                            Prompter.promptEnter();
                        } else {
                            PrettyUtils.wipe();
                            System.out.println("\nAre you sure you want to sell vehicle " + PrettyUtils.returnYellow("[" + vehicleID + "]") + "?");
                            boolean confirmed = Prompter.promptYesNo();
                            if (confirmed) {
                                manageVehicleSale(dealer, vehicleID);
                                Prompter.promptEnter();
                            } else {
                                PrettyUtils.printRed("\nVehicle selection cancelled.");
                                Prompter.promptEnter();
                            }
                        }
                        break;
                    } catch (NumberFormatException e) {
                        PrettyUtils.printRed("Invalid input! Please enter a numeric vehicle ID.");
                        Prompter.promptEnter();
                    } catch (IllegalArgumentException e) {
                        PrettyUtils.printRed(e.getMessage());
                        Prompter.promptEnter();
                    }
                default:
                    break;
            }
        }
    }

    /**
     * Manages the vehicle transaction process, offering a vehicle to the customer,
     * confirming the offer, and processing the sale if accepted.
     *
     * @param dealer    the DealershipManager responsible for managing the
     *                  dealership operations
     * @param vehicleID the ID of the vehicle being offered for sale
    */
    private static void manageVehicleSale(DealershipManager dealer, int vehicleID){
        Vehicle vehicle = dealer.getCurrentCustomer().getVehicleById(vehicleID);
        Customer customer = dealer.getCurrentCustomer();

        if (vehicle == null || customer == null) {
            PrettyUtils.printRed("Error: Vehicle or Customer not found!");
            return;
        }
        System.out.println("\nThe dealership offers you " + PrettyUtils.returnYellow("$" + String.format("%.2f", vehicle.calculateTotalPrice())) + " for the vehicle.");

        System.out.println("\nDo you accept this offer? " + PrettyUtils.returnYellow("(Y/N)") + ".");
        boolean confirmed = Prompter.promptYesNo();
        PrettyUtils.wipe();
        if (confirmed) {
            dealer.processCustomerVehicleTransaction(vehicle, customer, "purchase");
            List<Transaction> transactions = dealer.getTransactionManager().getTransactions();

            PrettyUtils.printGreen("Sale successful!");
            System.out.println("Updated Account Balance: " + PrettyUtils.returnYellow("$" + String.format("%.2f", customer.getAccountBalance())));
            System.out.println("\n" + PrettyUtils.returnYellow("Receipt:") + "\n" + transactions.get(transactions.size() - 1));
        } else {
            PrettyUtils.printRed("Sale cancelled.");
        }

    }

    /**
     * Displays the shopping cart and allows the user to proceed to checkout or exit the view.
     * 
     * @param dealer the DealershipManager handling the current dealership state.
     */
    private static void viewShoppingCart(DealershipManager dealer) {
        boolean inPage = true;
        while (inPage) {
            PrettyUtils.wipe();
            PrettyUtils.printYellow("Your ShoppingCart:");
            inPage = checkoutPrompter(dealer);
        }
    }

    /**
     * Adds a vehicle to the shopping cart, removes it from available inventory,
     * and provides options for further actions.
     * 
     * @param dealer the DealershipManager handling the dealership state.
     * @param vehicle the Vehicle to be added to the shopping cart.
     * @param vehicles the list of available vehicles to update upon addition to the cart.
     */
    private static void addVehicleToCart(DealershipManager dealer, Vehicle vehicle, List<Vehicle> vehicles) {
        PrettyUtils.wipe();
        //TODO move this to dealership
        dealer.getCurrentCart().addVehicle(vehicle);
        dealer.getVehicleManager().removeVehicle(vehicle);
        vehicles.remove(vehicle);

        PrettyUtils.printGreen("\nVehicle added to ShoppingCart");
        boolean inPage = true;
        while (inPage) {
            inPage = checkoutPrompter(dealer);
        }
    }

    /**
     * Displays options to proceed to checkout or continue shopping.
     * Prints the current shopping cart contents for the user.
     * 
     * @param dealer the DealershipManager handling the dealership state.
     * @return {@code true} to continue prompting; {@code false} to exit the prompt loop.
     */
    private static boolean checkoutPrompter(DealershipManager dealer) {
        // Displaying shopping cart
        System.out.println(dealer.getCurrentCart());

        PrettyUtils.printYellow("\nWould you like to:");
        String menu = PrettyUtils.returnYellow("1:") + " Go to checkout\n" +
                    PrettyUtils.returnYellow("2: ") + "Remove items from the cart" +
                    PrettyUtils.returnYellow("0:") + " Keep shopping";
        int choice = Prompter.promptOption(menu, 1);
        if (choice == -1) {
            return true;
        }
        switch (choice) {
            case 0:
                return false;
            case 1:
                checkoutView(dealer);
                return false;
            case 2:
                removeFromCart(dealer);
                return false;
            default:
                PrettyUtils.printRed("You may only select 0 or 1");
        }
        // Continue prompting for valid input
        return true; 
    }

    public static void removeFromCart(DealershipManager dealer){
        int choice = Prompter.promptOption("Select which vehicle to remove", 1);
        ShoppingCart cart = dealer.getCurrentCart();
    }

    /**
     * Handles the checkout process. Displays the shopping cart contents and checks if
     * the cart is empty before proceeding to checkout logic.
     * 
     * @param dealer the DealershipManager handling the dealership state.
     */
    private static void checkoutView(DealershipManager dealer) {
        PrettyUtils.wipe();
        List<Vehicle> productsList = dealer.getCurrentCart().getProductsList();
        if (productsList.isEmpty()) {
            PrettyUtils.printRed("Please, fill out your ShoppingCart to visit checkout.");
            Prompter.promptEnter();
        } else {
            checkoutLogic(dealer, productsList);
            Prompter.promptEnter();
        }
    }

    /**
     * Validates the customer's purchase, ensures sufficient funds, and processes the transaction.
     * Displays the receipt and updates the account balance upon successful checkout.
     * 
     * @param dealer the DealershipManager handling the dealership state.
     * @param productsList the list of vehicles in the shopping cart.
     */
    private static void checkoutLogic(DealershipManager dealer, List<Vehicle> productsList) {
        int allVehiclesPrice = 0;
        for (Vehicle v : productsList) {
            System.out.println(v);
            allVehiclesPrice += v.calculateTotalPrice();
        }
        Customer customer = dealer.getCurrentCustomer();

        if (productsList == null || customer == null) {
            System.out.println("Error: Vehicle list or Customer not found!");
            return;
        }
        System.out.println("Total: " + allVehiclesPrice + "$");

        System.out.println("Finalize your purchase? (Y/N)");
        boolean confirmed = Prompter.promptYesNo();
        PrettyUtils.wipe();
        if (confirmed) {
            String receipt = "Receipt:";
            for (Vehicle vehicle : productsList) {
                dealer.processCustomerVehicleTransaction(vehicle, customer, "sale");
                List<Transaction> transactions = dealer.getTransactionManager().getTransactions();
                System.out.println("Congrats on your new " + vehicle.getModel() + "!");
                receipt += "\n" + transactions.get(transactions.size() - 1);
            }

            System.out.println("Updated Account Balance: " + customer.getAccountBalance());
            dealer.getCurrentCart().emptyCart();
            viewReceipt(receipt);

            // Fix pressing 0 after
            mainMenuView(dealer);

        } else {
            System.out.println("Sale cancelled.");
        }
    }

    /**
     * Displays the receipt to the user if they confirm they want to view it.
     * 
     * @param receipt the receipt content to be displayed.
     */
    public static void viewReceipt(String receipt) {
        System.out.println("Do you want to see the receipt?");
        boolean confirmed = Prompter.promptYesNo();
        PrettyUtils.wipe();
        if (confirmed) {
            System.out.println(receipt);
        }
        Prompter.promptEnter();
    }



}
