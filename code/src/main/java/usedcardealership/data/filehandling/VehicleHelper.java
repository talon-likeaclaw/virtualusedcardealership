/**
 * Class for static methods to help with vehicle loading/writing.
 * Reduces code duplication Vehicle and Customer loading.
 *
 * @author Talon Dunbar
 * @version 11/19/2024
 */

package usedcardealership.data.filehandling;

import usedcardealership.data.vehicle.*;

public class VehicleHelper {
    /**
     * Parses a CSV fields array into a specific Vehicle type based on type field.
     * 
     * @param fields String[] containing the Vehicle's data.
     * @return a Vehicle instance or null if the type is not valid.
     */
    public static Vehicle parseVehicle(String[] fields) {
        if (fields[0].equals("[]")) {
            return null;
        }
        // Fill common fields.
        String type = fields[0];
        int id = Integer.parseInt(fields[1]);
        String make = fields[2];
        String model = fields[3];
        int year = Integer.parseInt(fields[4]);
        double price = Double.parseDouble(fields[5]);
        String color = fields[6];
        String transmission = fields[7];
        String driveType = fields[8];
        int horsepower = Integer.parseInt(fields[9]);
        double weight = Double.parseDouble(fields[10]);
        double kilometerage = Double.parseDouble(fields[11]);
        double damage = Double.parseDouble(fields[12]);
        boolean isElectric = Boolean.parseBoolean(fields[13]);

        // Check for each type of Vehicle and fill fields/return Vehicle subtype.
        switch (type) {
            case "Motorcycle":
                return parseMotorcycle(fields, id, make, model, year, price, color, transmission, driveType, horsepower,
                        weight, kilometerage, damage, isElectric);
            case "RV":
                return parseRV(fields, id, make, model, year, price, color, transmission, driveType, horsepower, weight,
                        kilometerage, damage, isElectric);
            case "Car":
                return parseCar(fields, id, make, model, year, price, color, transmission, driveType, horsepower,
                        weight, kilometerage, damage, isElectric);
            case "SUV":
                return parseSUV(fields, id, make, model, year, price, color, transmission, driveType, horsepower,
                        weight, kilometerage, damage, isElectric);
            case "PickupTruck":
                return parsePickupTruck(fields, id, make, model, year, price, color, transmission, driveType,
                        horsepower, weight, kilometerage, damage, isElectric);
            case "Van":
                return parseVan(fields, id, make, model, year, price, color, transmission, driveType, horsepower,
                        weight, kilometerage, damage, isElectric);
            default:
                return null;
        }
    }

    /**
     * Parses a Motorcycle from the given fields array.
     */
    private static Motorcycle parseMotorcycle(String[] fields, int id, String make, String model, int year,
            double price, String color, String transmission, String driveType, int horsepower,
            double weight, double kilometerage, double damage, boolean isElectric) {
        double engineCC = Double.parseDouble(fields[14]);
        String handlebarType = fields[15];
        return new Motorcycle("Motorcycle", id, make, model, year, price, color, transmission, driveType, horsepower,
                weight, kilometerage, damage, isElectric, engineCC, handlebarType);
    }

    /**
     * Parses an RV from the given fields array.
     */
    private static RV parseRV(String[] fields, int id, String make, String model, int year, double price,
            String color, String transmission, String driveType, int horsepower, double weight,
            double kilometerage, double damage, boolean isElectric) {
        int numSeats = Integer.parseInt(fields[14]);
        int numDoors = Integer.parseInt(fields[15]);
        boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
        int sleepCapacity = Integer.parseInt(fields[17]);
        boolean hasBathroom = Boolean.parseBoolean(fields[18]);
        return new RV("RV", id, make, model, year, price, color, transmission, driveType, horsepower, weight,
                kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, sleepCapacity, hasBathroom);
    }

    /**
     * Parses a Car from the given fields array.
     */
    private static Car parseCar(String[] fields, int id, String make, String model, int year, double price,
            String color, String transmission, String driveType, int horsepower, double weight,
            double kilometerage, double damage, boolean isElectric) {
        int numSeats = Integer.parseInt(fields[14]);
        int numDoors = Integer.parseInt(fields[15]);
        boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
        boolean isConvertible = Boolean.parseBoolean(fields[17]);
        return new Car("Car", id, make, model, year, price, color, transmission, driveType, horsepower, weight,
                kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, isConvertible);
    }

    /**
     * Parses an SUV from the given fields array.
     */
    private static SUV parseSUV(String[] fields, int id, String make, String model, int year, double price,
            String color, String transmission, String driveType, int horsepower, double weight,
            double kilometerage, double damage, boolean isElectric) {
        int numSeats = Integer.parseInt(fields[14]);
        int numDoors = Integer.parseInt(fields[15]);
        boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
        boolean hasThirdRowSeating = Boolean.parseBoolean(fields[17]);
        return new SUV("SUV", id, make, model, year, price, color, transmission, driveType, horsepower, weight,
                kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, hasThirdRowSeating);
    }

    /**
     * Parses a PickupTruck from the given fields array.
     */
    private static PickupTruck parsePickupTruck(String[] fields, int id, String make, String model, int year,
            double price, String color, String transmission, String driveType, int horsepower,
            double weight, double kilometerage, double damage, boolean isElectric) {
        int numSeats = Integer.parseInt(fields[14]);
        int numDoors = Integer.parseInt(fields[15]);
        boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
        double cargoCapacity = Double.parseDouble(fields[17]);
        double bedLength = Double.parseDouble(fields[18]);
        double towingCapacity = Double.parseDouble(fields[19]);
        return new PickupTruck("PickupTruck", id, make, model, year, price, color, transmission, driveType, horsepower,
                weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, cargoCapacity,
                bedLength, towingCapacity);
    }

    /**
     * Parses a Van from the given fields array.
     */
    private static Van parseVan(String[] fields, int id, String make, String model, int year, double price,
            String color, String transmission, String driveType, int horsepower, double weight,
            double kilometerage, double damage, boolean isElectric) {
        int numSeats = Integer.parseInt(fields[14]);
        int numDoors = Integer.parseInt(fields[15]);
        boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
        double cargoCapacity = Double.parseDouble(fields[17]);
        boolean hasSlidingDoors = Boolean.parseBoolean(fields[18]);
        return new Van("Van", id, make, model, year, price, color, transmission, driveType, horsepower, weight,
                kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, cargoCapacity, hasSlidingDoors);
    }

    /**
     * Converts a Vehicle object to a CSV string based on its type and getters.
     * 
     * @param Vehicle vehicle - The Vehicle to convert.
     * @return String - A CSV string representation of the vehicle.
     */
    public static String convertVehicleToCSV(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        return String.join(",", vehicle.toCSVFields());
    }
}
