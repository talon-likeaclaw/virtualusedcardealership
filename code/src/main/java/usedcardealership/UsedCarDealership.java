package usedcardealership;

import java.util.*;
import java.util.concurrent.TimeUnit;

import usedcardealership.interaction.*;
import usedcardealership.data.filehandling.*;
import usedcardealership.data.vehicle.*;
import usedcardealership.data.customer.*;
import usedcardealership.data.transaction.*;
import usedcardealership.business.manager.*;

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
            System.out.println("\nPlease select an option:");
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
                    // TODO: viewAccountView()
                    break;
                case 3:
                    // TODO: sellVehicleView()
                    break;
                case 4:
                    // TODO: viewShoppingCart()
                    break;
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
            System.out.println("**************");
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
                    vehicleTypeView(dealership);
                    break;
                case 2:
                    vehicleMakeView(dealership);
                    break;
                case 3:
                    vehicleColorView(dealership);
                    break;
                case 4:
                    // TODO: vehicleYearRangeView(dealership);
                    break;
                case 5:
                    // TODO: vehicleDriveView(dealership);
                    break;
                case 6:
                    // TODO: vehiclePriceRangeView(dealership);
                    break;
                case 7:
                    // TODO: vehicleKilometrageRangeView(dealership);
                    break;
                case 8:
                    // TODO: vehicleTransmissionView(dealership);
                    break;
            }
        }
    }

    /**
     * Menu that allows user to choose between vehicle type
     * 
     * @param dealership the DealershipManager object
     */
    private static void vehicleTypeView(DealershipManager dealership) {
        boolean inPage = true;
        while (inPage) {
            wipe();
            System.out.println("Select Vehicle Type:");
            switch (Prompter.promptOption(
                    "1: All\n2: Car\n3: SUV\n4: Van\n5: RV\n6: Motorcycle\n7: Pickup Truck\n0: Return to Main Menu",
                    7)) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    printVehiclesByType(dealership, "All");
                    break;
                case 2:
                    printVehiclesByType(dealership, "Car");
                    break;
                case 3:
                    printVehiclesByType(dealership, "SUV");
                    break;
                case 4:
                    printVehiclesByType(dealership, "Van");
                    break;
                case 5:
                    printVehiclesByType(dealership, "RV");
                    break;
                case 6:
                    printVehiclesByType(dealership, "Motorcycle");
                    break;
                case 7:
                    printVehiclesByType(dealership, "Truck");
                    break;
            }
        }
    }

    /**
     * View that allows user to choose to input a make or exit
     * 
     * @param dealership the DealershipManager object
     */
    private static void vehicleMakeView(DealershipManager dealership) {
        boolean inPage = true;
        List<Vehicle> vehicles = dealership.getInventory();
        while (inPage) {
            wipe();
            System.out.println("Vehicle Makes Currently Available:");
            HashSet<String> makes = new HashSet<>();
            // Get all unique existing makes
            for (Vehicle v : vehicles) {
                makes.add(v.getMake());
            }
            // TODO: Sort vehicles by make
            // Print all unique existing makes
            for (String s : makes) {
                System.out.println(s);
            }
            switch (Prompter.promptOption(
                    "\n1: Input Make\n0: Exit", 1)) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    String make = Prompter.promptVehicleMake();
                    printVehiclesByMake(dealership, make);
                    break;
            }
        }
    }

    /**
     * View that allows user to choose to input a make or exit
     * 
     * @param dealership
     */
    private static void vehicleColorView(DealershipManager dealership) {
        boolean inPage = true;
        List<Vehicle> vehicles = dealership.getInventory();
        while (inPage) {
            wipe();
            System.out.println("Vehicle Colors Currently Available:");
            HashSet<String> colors = new HashSet<>();
            // Get all unique existing colors
            for (Vehicle v : vehicles) {
                colors.add(v.getColor());
            }
            // TODO: Sort vehicles by color
            // Print all unique existing colors
            for (String s : colors) {
                System.out.println(s);
            }
            switch (Prompter.promptOption(
                    "\n1: Input Color\n0: Exit", 1)) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    String color = Prompter.promptVehicleColor();
                    printVehiclesByColor(dealership, color);
                    break;
            }
        }
    }

    /**
     * Prints the list of vehicles by chosen type
     * 
     * @param dealership  the DealershipManager object
     * @param vehicleType the type of vehicle to get a list of
     */
    private static void printVehiclesByType(DealershipManager dealership, String vehicleType) {
        List<Vehicle> vehicles = new ArrayList<>();

        if (vehicleType.equals("All")) {
            vehicles = dealership.getInventory();
        } else {
            switch (vehicleType) {
                case "Car":
                    vehicles = dealership.getCars();
                    break;
                case "SUV":
                    vehicles = dealership.getSUVs();
                    break;
                case "Van":
                    vehicles = dealership.getVans();
                    break;
                case "RV":
                    vehicles = dealership.getRVs();
                    break;
                case "Motorcycle":
                    vehicles = dealership.getMotorcycles();
                    break;
                case "Truck":
                    vehicles = dealership.getTrucks();
                    break;
            }
        }
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available in inventory.");
        } else {
            selectVehiclesFromList(dealership, vehicles);
        }
    }

    /**
     * Prints vehicles of a specified make
     * 
     * @param dealership the DealershipManager object
     * @param make       the make of vehicle to print from inventory
     */
    private static void printVehiclesByMake(DealershipManager dealership, String make) {
        List<Vehicle> vehicles = dealership.getVehiclesByMake(make);
        if (vehicles.size() == 0) {
            System.out.println("\nInvalid vehicle make!");
            Prompter.promptEnter();
        } else {
            selectVehiclesFromList(dealership, vehicles);
        }
    }

    /**
     * Prints vehicles of a specified color
     * 
     * @param dealership the DealershipManager object
     * @param color      the color of vehicle to print from inventory
     */
    private static void printVehiclesByColor(DealershipManager dealership, String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles = dealership.getVehiclesByColor(color);
        if (vehicles.size() == 0) {
            System.out.println("\nInvalid vehicle color!");
            Prompter.promptEnter();
        } else {
            selectVehiclesFromList(dealership, vehicles);
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
            // TODO: sort vehicles by ID
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
            System.out.println("\nPlease select an option:");
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
                        viewVehicleDetails(dealership, vehicleID);
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
     * Gets and prints the Vehicle's full details
     * 
     * @param vehicleID the ID of the Vehicle to print details for
     */
    private static void viewVehicleDetails(DealershipManager dealership, int vehicleID) {
        Vehicle vehicle = dealership.getVehicleById(vehicleID);
        wipe();
        System.out.println(vehicle.getFullDetails());
        vehicleDetailsMenu(dealership);
    }

    /**
     * Menu that asks user if they want to purchase vehicle or go back
     * 
     * @param dealership the DealershipManager object
     */
    private static void vehicleDetailsMenu(DealershipManager dealership) {
        boolean inPage = true;
        while (inPage) {
            System.out.println("\nWould you like to:");
            switch (Prompter.promptOption(
                    "1: Add Vehicle to Cart\n0: Return to Vehicle List", 1)) {
                case 0:
                    inPage = false;
                    break;
                case 1:
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

}
