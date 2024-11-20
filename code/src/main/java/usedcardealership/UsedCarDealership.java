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
        System.out.println("Welcome to " + dealership.getName() + "!");
        System.out.println("\nPlease select an option:");
        while (inPage) {
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
        System.out.println("\nSelect Vehicle Type:");
        while (inPage) {
            switch (prompter.promptOption(
                    "1: Car\n2: SUV\n3: Pickup Truck\n4: Van\n5: RV\n6: Motorcycle\n7: All\n0: Return to Main Menu",
                    7)) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    // TODO: viewCars()
                    break;
                case 2:
                    // TODO: viewSUVs()
                    break;
                case 3:
                    // TODO: viewTrucks()
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
                    // TODO: viewAllVehicles()
                    break;
            }
        }
    }

    /**
     *  Gets and views a list of all of the Cars available in inventory
     * 
     * @param dealership the DealershipManager object
     */
    private static void viewCars(DealershipManager dealership) {
        // TODO: dealership.getCars();
    }

    /**
     *  Gets and views a list of all of the SUVs available in inventory
     * 
     * @param dealership the DealershipManager object
     */
    private static void viewSUVs(DealershipManager dealership) {
        // TODO: dealership.getSUVs();
    }
    
    /**
     *  Gets and views a list of all of the Trucks available in inventory
     * 
     * @param dealership the DealershipManager object
     */
    private static void viewTrucks(DealershipManager dealership) {
        // TODO: dealership.getSUVs();
    }

    /**
     *  Gets and views a list of all of the Vans available in inventory
     * 
     * @param dealership the DealershipManager object
     */
    private static void viewVans(DealershipManager dealership) {
        // TODO: dealership.getVans();
    }

    /**
     *  Gets and views a list of all of the RVs available in inventory
     * 
     * @param dealership the DealershipManager object
     */
    private static void viewRVs(DealershipManager dealership) {
        // TODO: dealership.getRVs();
    }

    /**
     *  Gets and views a list of all of the Motorcycles available in inventory
     * 
     * @param dealership the DealershipManager object
     */
    private static void viewMotorcycles(DealershipManager dealership) {
        // TODO: dealership.getMotorcycless();
    }

    /**
     *  Gets and views a list of all of the vehicles available in inventory
     * 
     * @param dealership the DealershipManager object
     */
    private static void viewAllVehicles(DealershipManager dealership) {
        // TODO: dealership.getAllVehicles();
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
}
