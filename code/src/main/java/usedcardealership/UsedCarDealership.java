package usedcardealership;

import usedcardealership.interaction.*;
import usedcardealership.data.filehandling.*;
import usedcardealership.data.vehicle.*;
import usedcardealership.data.customer.*;
import usedcardealership.data.databasehandling.*;
import usedcardealership.data.transaction.*;
import usedcardealership.data.coupons.*;
import usedcardealership.business.manager.*;

import java.sql.*;
import java.util.*;

public class UsedCarDealership {
    public static void main(String[] args) {
        run();
    }

    /**
     * Lets the user choose between CSV or Database loading
     * then runs the Application with the loaded data
     */
    private static void run() {
        boolean inPage = true;
        while (inPage) {
            PrettyUtils.wipe();
            PrettyUtils.printCyan(PrettyUtils.returnCars());
            PrettyUtils.printCyan("Welcome to the Virtual Used Car Dealership!\n");
            String menu = PrettyUtils.returnYellow("Initialization Options:") + "\n" +
                    PrettyUtils.returnYellow("1:") + " Load From CSV\n" +
                    PrettyUtils.returnYellow("2:") + " Load from Database";
            try {
                int choice = Prompter.promptOption(menu, 2);
                if (choice == 0) {
                    PrettyUtils.printRed("\nIllegal input! Input must be a number from the list.");
                    Prompter.promptEnter();
                    continue;
                }
                switch (choice) {
                    case 1:
                        // The user decided to load from the CSV
                        try {
                            DealershipManager dealership = initialize();
                            if (dealership != null) {
                                inPage = false;
                                mainMenuView(dealership);
                                shutdown(dealership);
                            }
                            break;
                        } catch (Exception e) {
                            PrettyUtils.printRed("An unexpected error occured. Exiting the program.");
                            PrettyUtils.printRed(e.getMessage());
                            Prompter.promptEnter();
                        }
                    case 2:
                        // The user decided to load from the Database
                        try {
                            DealershipManager dealership = initializeFromDb();
                            if (dealership != null) {
                                inPage = false;
                                mainMenuView(dealership);
                                shutdownFromDb(dealership);
                            } else {
                                DealershipManager fallbackDealership = initialize();
                                if (fallbackDealership != null) {
                                    inPage = false;
                                    mainMenuView(fallbackDealership);
                                    shutdown(fallbackDealership);
                                }
                            }
                            break;
                        } catch (Exception e) {
                            PrettyUtils.printRed("An unexpected error occured. Exiting the program.");
                            PrettyUtils.printRed(e.getMessage());
                            Prompter.promptEnter();
                        }
                }
            } catch (Exception e) {
                PrettyUtils.printRed(e.getMessage());
                Prompter.promptEnter();
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
            List<Vehicle> database = VehicleManager.initializeListVehicle(vehicleDatabasePath);
            String vehicleInventoryPath = "resources/inventory.csv";
            List<Vehicle> inventory = VehicleManager.initializeListVehicle(vehicleInventoryPath);

            // Load customers
            String customerPath = "resources/customers.csv";
            CustomerFileHandler customerLoader = new CustomerFileHandler(customerPath);
            List<Customer> customers = customerLoader.load();

            // Load transactions
            String transactionPath = "resources/transactions.csv";
            TransactionFileHandler transactionLoader = new TransactionFileHandler(transactionPath);
            List<Transaction> transactions = transactionLoader.load();

            // Initialize and return the DealershipManager
            DealershipManager dealership = new DealershipManager(
                    dealershipName, dealershipAccountBalance, transactions, inventory, database, customers);
            dealership.initializeCurrentCustomer(customers, dealership);
            return dealership;
        } catch (Exception e) {
            PrettyUtils.printRed("Error occured when reading files.");
            PrettyUtils.printRed(e.getMessage());
            Prompter.promptEnter();
            return null;
        }
    }

    /**
     * Initalized from the database connection
     * 
     * @return the DealershipManager object
     */
    private static DealershipManager initializeFromDb() {
        String dealershipName = "Talon & Juan's Used Car Emporium";
        double dealershipAccountBalance = 567234.54;
        String jdbcUrl = "jdbc:postgresql://localhost:5432/usedcardealership";
        String dbUser = "postgres";
        String dbPassword = "postgres";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Load vehicles
            VehicleDatabaseHandler vehicleHandler = new VehicleDatabaseHandler(connection);
            List<Vehicle> inventory = vehicleHandler.load();
            List<Vehicle> database = vehicleHandler.loadDatabase();

            // Load customers
            CustomerDatabaseHandler customerHandler = new CustomerDatabaseHandler(connection);
            List<Customer> customers = customerHandler.load();

            TransactionDatabaseHandler transactionHandler = new TransactionDatabaseHandler(connection);
            List<Transaction> transactions = transactionHandler.load();

            DealershipManager dealership = new DealershipManager(dealershipName, dealershipAccountBalance, transactions,
                    inventory, database, customers);
            dealership.initializeCurrentCustomer(customers, dealership);
            return dealership;
        } catch (SQLException e) {
            PrettyUtils.printRed("\nFailed to connect to the database loading from CSV instead.");
            Prompter.promptEnter();
            return null;
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
     * Shutdown the DealershipManager and saves all vehicles, customers,
     * transactions
     * 
     * @param dealership the DealershipManager object
     */
    private static void shutdownFromDb(DealershipManager dealership) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/usedcardealership";
        String dbUser = "postgres";
        String dbPassword = "postgres";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Save inventory
            List<Vehicle> database = dealership.getDatabase();
            VehicleDatabaseHandler vehicleInventorySaver = new VehicleDatabaseHandler(connection);
            vehicleInventorySaver.save(database);

            // Save customers
            List<Customer> customers = dealership.getCustomers();
            CustomerDatabaseHandler customerSaver = new CustomerDatabaseHandler(connection);
            customerSaver.save(customers);

            // Save transactions
            List<Transaction> transactions = dealership.getTransactionManager().getTransactions();
            TransactionDatabaseHandler transactionSaver = new TransactionDatabaseHandler(connection);
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
                        CustomerManager.viewAccountView(dealership);
                        break;
                    case 3:
                        CustomerManager.sellVehicleView(dealership);
                        break;
                    case 4:
                        CustomerManager.viewShoppingCart(dealership);
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
                VehicleManager.displayAvailableCriteria(dealership, filterType);
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
            List<Vehicle> filteredVehicles = dealership.applyRangeFilter(filterType, min, max);

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
            VehicleManager.selectVehiclesFromList(dealership, filteredVehicles);
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
        List<Vehicle> filteredVehicles = dealership.applyFilter(filterType, criteria);
        // If no vehicles print warning and prompt enter
        return handleFilteredVehicles(dealership, filteredVehicles);
    }

    /**
     * Displays options to proceed to checkout or continue shopping.
     * Prints the current shopping cart contents for the user.
     * 
     * @param dealer the DealershipManager handling the dealership state.
     * @return true to continue, false to stop prompting
     */
    public static boolean checkoutPrompter(DealershipManager dealer) {
        boolean inPage = true;
        while (inPage) {
            PrettyUtils.wipe();
            // Displaying shopping cart
            System.out.println(dealer.getCurrentCart());
            PrettyUtils.printYellow("Would you like to:");
            String menu = PrettyUtils.returnYellow("1:") + " Go to checkout\n" +
                    PrettyUtils.returnYellow("2: ") + "Remove items from the cart\n" +
                    PrettyUtils.returnYellow("0:") + " Keep shopping";
        int choice = Prompter.promptOption(menu, 2);
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
                PrettyUtils.printRed("You may only select 0, 1 or 2");
        }
        // Continue prompting for valid input
        return true; 
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
                default:
                    PrettyUtils.printRed("You may only select 0 or 1");
            }
        }
        return true;
    }

    /**
     * Handles the checkout process. Displays the shopping cart contents and checks
     * if
     * the cart is empty before proceeding to checkout logic.
     * 
     * @param dealer the DealershipManager handling the dealership state.
     */
    private static void checkoutView(DealershipManager dealer) {
        PrettyUtils.wipe();
    
        List<Vehicle> productsList = dealer.getCurrentCart().getProductsList();
    
        if (productsList.isEmpty()) {
            PrettyUtils.printRed("Please fill your Shopping Cart to visit checkout.");
            Prompter.promptEnter();
        } else {
            // maybe not like this
            TransactionManager.checkoutLogic(dealer, productsList);
            Prompter.promptEnter();
        }
    }
}