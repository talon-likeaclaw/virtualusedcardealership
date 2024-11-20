package usedcardealership;

import java.util.*;
import usedcardealership.interaction.*;
import usedcardealership.data.filehandling.*;
import usedcardealership.data.vehicle.*;
import usedcardealership.data.customer.*;
import usedcardealership.data.transaction.*;
import usedcardealership.business.manager.*;

public class UsedCarDealership {
    private static Prompter prompter;

    public static void main(String[] args) {
        prompter = new Prompter();
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
            System.out.println("\nPlease select an option:");
            switch (prompter.promptOption(
                    "1: Browse Vehicles\n2: View Account and Owned Vehicles\n3: Sell Vehicle to Dealership\n0: Exit",
                    3)) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    browseVehiclesView(dealership);
                    break;
                case 2:
                    // TODO: viewAccountView()
                    break;
                case 3:
                    // TODO: sellVehicleView()
                    break;
            }
        }
    }

    /**
     * Menu that allows user to choose between vehicle type
     * 
     * @param dealership the DealershipManager object
     */
    private static void browseVehiclesView(DealershipManager dealership) {
        boolean inPage = true;
        while (inPage) {
            wipe();
            System.out.println("Select Vehicle Type:");
            switch (prompter.promptOption(
                    "1: All\n2: Car\n3: SUV\n4: Van\n5: RV\n6: Motorcycle\n7: Pickup Truck\n0: Main Menu",
                    7)) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    viewAllVehicles(dealership);
                    break;
                case 2:
                    // TODO: viewCars()
                    break;
                case 3:
                    // TODO: viewSUVs()
                    break;
                case 4:
                    // TODO: viewVans()
                    break;
                case 5:
                    // TODO: viewRVs()
                    break;
                case 6:
                    // TODO: viewMotorcycles()
                    break;
                case 7:
                    // TODO: viewTrucks()
                    break;
            }
        }
    }

    /**
     * Gets and views a list of all of the vehicles available in inventory
     * 
     * @param dealership the DealershipManager object
     */
    private static void viewAllVehicles(DealershipManager dealership) {
        wipe();
        List<Vehicle> vehicles = dealership.getInventory();
        // TODO: sort vehicles by ID
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
        vehicleViewMenu();
    }

    /**
     * Gets and views a list of all of the Cars available in inventory
     * 
     * @param dealership the DealershipManager object
     */
    private static void viewCars(DealershipManager dealership) {
        // TODO: dealership.getCars();
    }

    /**
     * Gets and views a list of all of the SUVs available in inventory
     * 
     * @param dealership the DealershipManager object
     */
    private static void viewSUVs(DealershipManager dealership) {
        // TODO: dealership.getSUVs();
    }

    /**
     * Gets and views a list of all of the Trucks available in inventory
     * 
     * @param dealership the DealershipManager object
     */
    private static void viewTrucks(DealershipManager dealership) {
        // TODO: dealership.getSUVs();
    }

    /**
     * Gets and views a list of all of the Vans available in inventory
     * 
     * @param dealership the DealershipManager object
     */
    private static void viewVans(DealershipManager dealership) {
        // TODO: dealership.getVans();
    }

    /**
     * Gets and views a list of all of the RVs available in inventory
     * 
     * @param dealership the DealershipManager object
     */
    private static void viewRVs(DealershipManager dealership) {
        // TODO: dealership.getRVs();
    }

    /**
     * Gets and views a list of all of the Motorcycles available in inventory
     * 
     * @param dealership the DealershipManager object
     */
    private static void viewMotorcycles(DealershipManager dealership) {
        // TODO: dealership.getMotorcycless();
    }

    /**
     * Menu that allows user to select a vehicle or exit
     * 
     */
    private static void vehicleViewMenu() {
        boolean inPage = true;
        while (inPage) {
            System.out.println("\nPlease select an option:");
            switch (prompter.promptOption(
                    "1: Select Vehicle\n0: Exit", 1)) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    // TODO: int vehilceID = selectVehicle();
                    // TODO: viewVehicleDetails(vehicleID);
                    break;
            }
        }
    }

    /**
     * Allows the user to select a vehicle by ID
     * 
     * @param dealership the DealershipManager object
     * @return the selected ID of the vehicle they want more details on
     */
    private static int selectVehicle(DealershipManager dealership) {
        // TODO: Implement logic to select vehicle by ID to enter detailed view
        return 0;
    }

    /**
     * Gets and prints the Vehicle's full details
     * 
     * @param vehicleID the ID of the Vehicle to print details for
     */
    private static void viewVehicleDetails(int vehicleID) {
        // TODO: Print vehicle's full details
        // TODO: Create a getFullDetails method for each Vehicle type
        // vehicleDetailsMenu():
    }

    /**
     * Menu that asks user if they want to purchase vehicle or go back
     * 
     */
    private static void vehicleDetailsMenu() {
        // TODO: Create a menu that allows for purchase, go back, or go to main menu
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
        prompter.close();
    }

    /**
     * Wipes the console screen
     */
    public static void wipe() {
        System.out.print("\033[H\033[2J");
    }

}
