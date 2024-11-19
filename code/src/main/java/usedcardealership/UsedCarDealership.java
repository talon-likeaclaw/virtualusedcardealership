package usedcardealership;

import java.util.*;
import usedcardealership.data.filehandling.*;
import usedcardealership.data.vehicle.*;
import usedcardealership.data.customer.*;
import usedcardealership.data.transaction.*;
import usedcardealership.business.manager.*;

public class UsedCarDealership {

    public static void main(String[] args) {
        DealershipManager dealership = initialize();
        mainMenuView(dealership);
        shutdown();
    }

    private static void mainMenuView(DealershipManager dealership) {
        System.out.println("Welcome to " + dealership.getName() + "!");
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
        System.out.println("Initializing dealership database and inventory.");
        String vehicleDatabasePath = "resources/database.csv";
        String vehicleInventoryPath = "resources/inventory.csv";
        List<Vehicle> database = initializeListVehicle(vehicleDatabasePath);
        List<Vehicle> inventory = initializeListVehicle(vehicleInventoryPath);

        // Load customers
        System.out.println("Initializing customers.");
        String customerPath = "resources/customers.csv";
        List<Customer> customers = initializeListCustomer(customerPath);

        // Load transactions
        System.out.println("Initializing transactions.\n");
        String transactionPath = "resources/transactions.csv";
        List<Transaction> transactions = initializeListTransaction(transactionPath);

        // Initialize the DealershipManager
        DealershipManager dealership = new DealershipManager(
                dealershipName, dealershipAccountBalance, transactions, inventory, database, customers);

        System.out.println("Dealership successfully initilized.\n");
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
     * Initilizes a CustomerFileHanlder and loads from file path
     * 
     * @param filePath the file path to load from
     * @return a list of customers loaded from file
     */
    private static List<Customer> initializeListCustomer(String filePath) {
            CustomerFileHandler customerLoader = new CustomerFileHandler(filePath);
            return customerLoader.load();
    }

    /**
     * Initilizes a TransactionFileHanlder and loads from file path
     * 
     * @param filePath the file path to load from
     * @return a list of transactions loaded from file
     */
    private static List<Transaction> initializeListTransaction(String filePath) {
            TransactionFileHandler transactionLoader = new TransactionFileHandler(filePath);
            return transactionLoader.load();
    }

    private static void shutdown() {
    }
}
