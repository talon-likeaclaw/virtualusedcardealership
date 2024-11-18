package usedcardealership;

import java.io.IOException;
import java.util.*;
import usedcardealership.business.filter.*;
import usedcardealership.business.manager.*;
import usedcardealership.data.filehandling.*;
import usedcardealership.data.vehicle.*;

public class UsedCarDealership {
    public static void main(String[] args) {
        testVehicleFileHandling();
    }

    public static void testVehicleFileHandling() {
        String databasePath = "resources/database.csv";
        String inventoryPath = "resources/inventory.csv";
        String testWritePath = "resources/testWrite.csv";
        try {
            VehicleFileHandler vehicleDatabaseHandler = new VehicleFileHandler(databasePath);
            VehicleFileHandler vehicleInventoryHandler = new VehicleFileHandler(inventoryPath);
            VehicleFileHandler vehicleWriteHandler = new VehicleFileHandler(testWritePath);
            List<Vehicle> database = vehicleDatabaseHandler.load();
            List<Vehicle> inventory = vehicleInventoryHandler.load();
            VehicleManager manager = new VehicleManager(inventory, database);
            // manager.printVehicles(inventory); // WOOOOOO IT WORKS!!
            Car mitsubishiLancer = new Car(
                    "Car",
                    999, // id
                    "Mitsubishi", // make
                    "Lancer", // model
                    2018, // year
                    18000.0, // price
                    "White", // color
                    "Manual", // transmission
                    "FWD", // driveType
                    168, // horsepower
                    2900.0, // weight in lbs
                    45000.0, // mileage in kilometers
                    4.5, // damage percentage
                    false, // isElectric
                    5, // numSeats
                    4, // numDoors
                    false, // hasSunRoof
                    false // isConvertible
            );
            manager.addVehicle(mitsubishiLancer);
            // Test filters
            IFilter<Vehicle> colorFilter = new VehicleColorFilter("Yellow");
            printGreen("\nVehicles that are Yellow:");
            manager.printVehiclesFull(manager.searchInventory(colorFilter));
            IFilter<Vehicle> driveFilter = new VehicleDriveFilter("AWD");
            printGreen("\nVehicles that are All Wheel Drive:");
            manager.printVehiclesFull(manager.searchInventory(driveFilter));
            IFilter<Vehicle> kilometerageFilter = new VehicleKilometerageRangeFilter(10000, 20000);
            printGreen("\nVehicles between 10,000km and 20,000km:");
            manager.printVehiclesFull(manager.searchInventory(kilometerageFilter));
            IFilter<Vehicle> makeFilter = new VehicleMakeFilter("Hyundai");
            printGreen("\nVehicles made by Hyundai:");
            manager.printVehiclesFull(manager.searchInventory(makeFilter));
            IFilter<Vehicle> priceFilter = new VehiclePriceRangeFilter(10000, 20000);
            printGreen("\nVehicles between $10,000 and $20,000:");
            manager.printVehiclesFull(manager.searchInventory(priceFilter));
            IFilter<Vehicle> transmissionFilter = new VehicleTransmissionFilter("Manual");
            printGreen("\nVehicles that have a Manual transmission:");
            manager.printVehiclesFull(manager.searchInventory(transmissionFilter));
            IFilter<Vehicle> typeFilter = new VehicleTypeFilter("PickupTruck");
            printGreen("\nVehicles that are PickupTrucks");
            manager.printVehiclesFull(manager.searchInventory(typeFilter));
            IFilter<Vehicle> yearFilter = new VehicleYearRangeFilter(2020, 2021);
            printGreen("\nVehicles that are from years 2020 and 2021:");
            manager.printVehiclesFull(manager.searchInventory(yearFilter));

            vehicleWriteHandler.save(manager.getInventory()); // I can't believe it works!!!

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testVehicles() {
        // MOTORCYCLE
        Motorcycle harleyDavidsonStreet = new Motorcycle(
                "Motorcycle",
                101, // id
                "Harley-Davidson", // make
                "Street 750", // model
                2020, // year
                7500.0, // price
                "Black", // color
                "Manual", // transmission
                "RWD", // driveType
                53, // horsepower
                233.0, // weight in lbs
                15000.0, // mileage in kilometers
                5.0, // damage percentage
                false, // isElectric
                749.0, // engineCC
                "Cruiser" // handlebarType
        );
        System.out.println(harleyDavidsonStreet);
        System.out.println();

        // RV
        RV winnebagoVoyage = new RV(
                "RV",
                202, // id
                "Winnebago", // make
                "Voyage", // model
                2022, // year
                75000.0, // price
                "White", // color
                "Automatic", // transmission
                "4WD", // driveType
                300, // horsepower
                12000.0, // weight in lbs
                20000.0, // mileage in kilometers
                10.0, // damage percentage
                false, // isElectric
                6, // numSeats
                2, // numDoors
                false, // hasSunRoof
                4, // sleepCapacity
                true // hasBathroom
        );
        System.out.println(winnebagoVoyage);
        System.out.println();

        // CAR
        Car mitsubishiLancer = new Car(
                "Car",
                505, // id
                "Mitsubishi", // make
                "Lancer", // model
                2018, // year
                18000.0, // price
                "White", // color
                "Manual", // transmission
                "FWD", // driveType
                168, // horsepower
                2900.0, // weight in lbs
                45000.0, // mileage in kilometers
                4.5, // damage percentage
                false, // isElectric
                5, // numSeats
                4, // numDoors
                false, // hasSunRoof
                false // isConvertible
        );
        mitsubishiLancer.addDamage(25.7); // Got into a little crash
        System.out.println(mitsubishiLancer);
        System.out.println();

        // SUV
        SUV hyundaiKona = new SUV(
                "SUV",
                404, // id
                "Hyundai", // make
                "Kona", // model
                2022, // year
                21000.0, // price
                "White", // color
                "Automatic", // transmission
                "AWD", // driveType
                147, // horsepower
                3200.0, // weight in lbs
                10000.0, // mileage in kilometers
                2.5, // damage percentage
                false, // isElectric
                5, // numSeats
                4, // numDoors
                false, // hasSunRoof
                false // hasThirdRowSeating
        );
        hyundaiKona.addKilometerage(30000);
        System.out.println(hyundaiKona);
        System.out.println();

        // VAN
        Van fordTransit = new Van(
                "Van",
                606, // id
                "Ford", // make
                "Transit", // model
                2020, // year
                35000.0, // price
                "White", // color
                "Automatic", // transmission
                "RWD", // driveType
                275, // horsepower
                5000.0, // weight in lbs
                40000.0, // kilometerage in kilometers
                3.0, // damage percentage
                false, // isElectric
                2, // numSeats
                4, // numDoors
                false, // hasSunRoof
                487.3, // cargoCapacity in cubic feet
                true // hasSlidingDoors
        );
        System.out.println(fordTransit);
        System.out.println();

        // PICKUP TRUCK
        PickupTruck f150Lightning = new PickupTruck(
                "PickupTruck",
                707, // id
                "Ford", // make
                "F-150 Lightning", // model
                2022, // year
                45000.0, // price
                "Blue", // color
                "Automatic", // transmission
                "AWD", // driveType
                563, // horsepower
                6400.0, // weight in lbs
                5000.0, // kilometerage in kilometers
                2.0, // damage percentage
                true, // isElectric
                5, // numSeats
                4, // numDoors
                false, // hasSunRoof
                52.8, // cargoCapacity in cubic feet
                5.5, // bedLength in feet
                10000.0 // towingCapacity in lbs
        );
        System.out.println(f150Lightning);
        System.out.println();

        // VehicleManager Testing
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(mitsubishiLancer);
        vehicles.add(hyundaiKona);

        printGreen("Before winning the lottery:\n");
        VehicleManager testVehicles = new VehicleManager(vehicles, vehicles);
        testVehicles.printVehiclesShort(testVehicles.getInventory());

        printGreen("After winning the lottery:\n");
        testVehicles.addVehicle(harleyDavidsonStreet);
        testVehicles.addVehicle(winnebagoVoyage);
        testVehicles.addVehicle(fordTransit);
        testVehicles.addVehicle(f150Lightning);
        testVehicles.printVehiclesShort(testVehicles.getInventory());

    }

    /**
     * Prints message in color
     * 
     * @param color   Color of message
     * @param message Message to be printed
     */
    private static void printColor(String color, String message) {
        final String ANSI_RESET = "\u001B[0m";

        System.out.println(color + message + ANSI_RESET);
    }

    /**
     * Prints in greem
     * 
     * @param message String message to be printed in red
     */
    private static void printGreen(String message) {
        final String ANSI_GREEN = "\u001B[32m";
        printColor(ANSI_GREEN, message);
    }

}
