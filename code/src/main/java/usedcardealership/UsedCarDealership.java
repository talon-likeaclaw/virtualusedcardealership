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
        shutdown();
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
                    // viewAccountView()
                    break;
                case 3:
                    // sellVehicleView()
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
                    // viewCars()
                    break;
                case 2:
                    // viewSUVs()
                    break;
                case 3:
                    // viewTrucks()
                    break;
                case 4:
                    // viewVans()
                    break;
                case 5:
                    // viewRVs()
                    break;
                case 6:
                    // viewMotorcycles()
                    break;
                case 7:
                    // viewAllVehicles()
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
        String vehicleInventoryPath = "resources/inventory.csv";
        List<Vehicle> database = initializeListVehicle(vehicleDatabasePath);
        List<Vehicle> inventory = initializeListVehicle(vehicleInventoryPath);

        // Load customers
        String customerPath = "resources/customers.csv";
        List<Customer> customers = initializeListCustomer(customerPath);

        // Load transactions
        String transactionPath = "resources/transactions.csv";
        List<Transaction> transactions = initializeListTransaction(transactionPath);

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
