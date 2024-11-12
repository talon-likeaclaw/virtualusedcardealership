/**
 * Class for handling reading and writing Vehicles from/to files
 *
 * @param <Vehicle> the input vehicle list
 * @author Talon Dunbar
 * @version 11/12/2024
 */

package usedcardealership;

import java.nio.file.*;
import java.io.*;
import java.util.*;

public class VehicleFileHandler implements IDataHandler<Vehicle> {
  private final Path filePath;

  /**
   * Constructs a VehicleFileHandler with the specified file path.
   * 
   * @param String filePath - The path to the file containing vehicle data.
   * @throws IOException
   */
  public VehicleFileHandler(String filePath) throws IOException {
    this.filePath = Paths.get(filePath);
  }

  /**
   * Loads vehicles from the file and returns them as a list.
   * 
   * @return List<Vehicle> - A list of vehicles loaded from the file.
   */
  @Override
  public List<Vehicle> load() {
    List<Vehicle> vehicles = new ArrayList<>();
    try {
      List<String> allLines = Files.readAllLines(filePath);
      for (String line : allLines) {
        String[] fields = line.split(",");
        String type = fields[0];

        Vehicle vehicle = createVehicle(type, fields);
        vehicles.add(vehicle);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return vehicles;
  }

  /**
   * Creates a Vehicle based on the type and field data.
   * 
   * @param String   type - The type of the vehicle.
   * @param String[] fields - The field data for the vehicle.
   * @return Vehicle - The created Vehicle instance or null if type not valid.
   */
  private Vehicle createVehicle(String type, String[] fields) {
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

    if (type == "Motorcycle") {
      return createMotorcycle(fields, id, make, model, year, price, color, transmission, driveType,
          horsepower, weight, kilometerage, damage, isElectric);
    } else if (type == "RV") {
      return createRV(fields, id, make, model, year, price, color, transmission, driveType,
          horsepower, weight, kilometerage, damage, isElectric);
    } else if (type == "Car") {
      return createCar(fields, id, make, model, year, price, color, transmission, driveType,
          horsepower, weight, kilometerage, damage, isElectric);
    } else if (type == "SUV") {
      return createSUV(fields, id, make, model, year, price, color, transmission, driveType,
          horsepower, weight, kilometerage, damage, isElectric);
    } else if (type == "PickupTruck") {
      return createPickupTruck(fields, id, make, model, year, price, color, transmission, driveType,
          horsepower, weight, kilometerage, damage, isElectric);
    } else if (type == "Van") {
      return createVan(fields, id, make, model, year, price, color, transmission, driveType,
          horsepower, weight, kilometerage, damage, isElectric);
    } else {
      return null;
    }
  }

  /**
   * Helper method to create a Motorcycle.
   * 
   * @param fields       String array of all fields
   * @param id           the Motorcycle's unique identifier
   * @param make         the company that makes the Motorcycle
   * @param model        the name of the Motorcycle's model
   * @param year         the year the Motorcycle was released
   * @param price        the initial price of the Motorcycle
   * @param color        the color of the Motorcycle's paint
   * @param transmission the type of transmission (Auto, Manual)
   * @param driveType    the drive type of the Motorcycle
   * @param horsepower   the Motorcycle's engine's horsepower
   * @param weight       the weight of the Motorcycle
   * @param kilometerage the number of kilometers the Motorcycle has on the gauge
   * @param damage       the damage of the Motorcycle (00.00 - 100.00)
   * @param isElectric   if the Motorcycle is electric of no
   * @return Motorcycle - The created Motorcycle instance.
   */
  private Motorcycle createMotorcycle(
      String[] fields,
      int id,
      String make,
      String model,
      int year,
      double price,
      String color,
      String transmission,
      String driveType,
      int horsepower,
      double weight,
      double kilometerage,
      double damage,
      boolean isElectric) {
    double engineCC = Double.parseDouble(fields[14]);
    String handlebarType = fields[15];
    return new Motorcycle(id, make, model, year, price, color, transmission, driveType, horsepower,
        weight, kilometerage, damage, isElectric, engineCC, handlebarType);
  }

  /**
   * Helper method to create a RV.
   * 
   * @param fields       String array of all fields
   * @param id           the RV's unique identifier
   * @param make         the company that makes the RV
   * @param model        the name of the RV's model
   * @param year         the year the RV was released
   * @param price        the initial price of the RV
   * @param color        the color of the RV's paint
   * @param transmission the type of transmission (Auto, Manual)
   * @param driveType    the drive type of the RV
   * @param horsepower   the RV's engine's horsepower
   * @param weight       the weight of the RV
   * @param kilometerage the number of kilometers the RV has on the gauge
   * @param damage       the damage of the RV (00.00 - 100.00)
   * @param isElectric   if the RV is electric of no
   * @return RV - The created RV instance.
   */
  private RV createRV(
      String[] fields,
      int id,
      String make,
      String model,
      int year,
      double price,
      String color,
      String transmission,
      String driveType,
      int horsepower,
      double weight,
      double kilometerage,
      double damage,
      boolean isElectric) {
    int numSeats = Integer.parseInt(fields[14]);
    int numDoors = Integer.parseInt(fields[15]);
    boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
    int sleepCapacity = Integer.parseInt(fields[17]);
    boolean hasBathroom = Boolean.parseBoolean(fields[18]);
    return new RV(id, make, model, year, price, color, transmission, driveType, horsepower,
        weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, sleepCapacity, hasBathroom);
  }

  /**
   * Helper method to create a Car.
   * 
   * @param fields       String array of all fields
   * @param id           the Car's unique identifier
   * @param make         the company that makes the Car
   * @param model        the name of the Car's model
   * @param year         the year the Car was released
   * @param price        the initial price of the Car
   * @param color        the color of the Car's paint
   * @param transmission the type of transmission (Auto, Manual)
   * @param driveType    the drive type of the Car
   * @param horsepower   the Car's engine's horsepower
   * @param weight       the weight of the Car
   * @param kilometerage the number of kilometers the Car has on the gauge
   * @param damage       the damage of the Car (00.00 - 100.00)
   * @param isElectric   if the Car is electric of no
   * @return Car - The created Car instance.
   */
  private Car createCar(
      String[] fields,
      int id,
      String make,
      String model,
      int year,
      double price,
      String color,
      String transmission,
      String driveType,
      int horsepower,
      double weight,
      double kilometerage,
      double damage,
      boolean isElectric) {
    int numSeats = Integer.parseInt(fields[14]);
    int numDoors = Integer.parseInt(fields[15]);
    boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
    boolean isConvertible = Boolean.parseBoolean(fields[17]);
    return new Car(id, make, model, year, price, color, transmission, driveType, horsepower,
        weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, isConvertible);
  }

  /**
   * Helper method to create a SUV.
   * 
   * @param fields       String array of all fields
   * @param id           the SUV's unique identifier
   * @param make         the company that makes the SUV
   * @param model        the name of the SUV's model
   * @param year         the year the SUV was released
   * @param price        the initial price of the SUV
   * @param color        the color of the SUV's paint
   * @param transmission the type of transmission (Auto, Manual)
   * @param driveType    the drive type of the SUV
   * @param horsepower   the SUV's engine's horsepower
   * @param weight       the weight of the SUV
   * @param kilometerage the number of kilometers the SUV has on the gauge
   * @param damage       the damage of the SUV (00.00 - 100.00)
   * @param isElectric   if the SUV is electric of no
   * @return SUV - The created SUV instance.
   */
  private SUV createSUV(
      String[] fields,
      int id,
      String make,
      String model,
      int year,
      double price,
      String color,
      String transmission,
      String driveType,
      int horsepower,
      double weight,
      double kilometerage,
      double damage,
      boolean isElectric) {
    int numSeats = Integer.parseInt(fields[14]);
    int numDoors = Integer.parseInt(fields[15]);
    boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
    boolean hasThirdRowSeating = Boolean.parseBoolean(fields[17]);
    return new SUV(id, make, model, year, price, color, transmission, driveType, horsepower,
        weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, hasThirdRowSeating);
  }

  /**
   * Helper method to create a PickupTruck.
   * 
   * @param fields       String array of all fields
   * @param id           the PickupTruck's unique identifier
   * @param make         the company that makes the PickupTruck
   * @param model        the name of the PickupTruck's model
   * @param year         the year the PickupTruck was released
   * @param price        the initial price of the PickupTruck
   * @param color        the color of the PickupTruck's paint
   * @param transmission the type of transmission (Auto, Manual)
   * @param driveType    the drive type of the PickupTruck
   * @param horsepower   the PickupTruck's engine's horsepower
   * @param weight       the weight of the PickupTruck
   * @param kilometerage the number of kilometers the PickupTruck has on the gauge
   * @param damage       the damage of the PickupTruck (00.00 - 100.00)
   * @param isElectric   if the PickupTruck is electric of no
   * @return PickupTruck - The created PickupTruck instance.
   */
  private PickupTruck createPickupTruck(
      String[] fields,
      int id,
      String make,
      String model,
      int year,
      double price,
      String color,
      String transmission,
      String driveType,
      int horsepower,
      double weight,
      double kilometerage,
      double damage,
      boolean isElectric) {
    int numSeats = Integer.parseInt(fields[14]);
    int numDoors = Integer.parseInt(fields[15]);
    boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
    double cargoCapacity = Double.parseDouble(fields[17]);
    double bedLength = Double.parseDouble(fields[18]);
    double towingCapacity = Double.parseDouble(fields[19]);
    return new PickupTruck(id, make, model, year, price, color, transmission, driveType, horsepower,
        weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof,
        cargoCapacity, bedLength, towingCapacity);
  }

  /**
   * Helper method to create a Van.
   * 
   * @param fields       String array of all fields
   * @param id           the Van's unique identifier
   * @param make         the company that makes the Van
   * @param model        the name of the Van's model
   * @param year         the year the Van was released
   * @param price        the initial price of the Van
   * @param color        the color of the Van's paint
   * @param transmission the type of transmission (Auto, Manual)
   * @param driveType    the drive type of the Van
   * @param horsepower   the Van's engine's horsepower
   * @param weight       the weight of the Van
   * @param kilometerage the number of kilometers the Van has on the gauge
   * @param damage       the damage of the Van (00.00 - 100.00)
   * @param isElectric   if the Van is electric of no
   * @return Van - The created Van instance.
   */
  private Van createVan(
      String[] fields,
      int id,
      String make,
      String model,
      int year,
      double price,
      String color,
      String transmission,
      String driveType,
      int horsepower,
      double weight,
      double kilometerage,
      double damage,
      boolean isElectric) {
    int numSeats = Integer.parseInt(fields[14]);
    int numDoors = Integer.parseInt(fields[15]);
    boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
    double cargoCapacity = Double.parseDouble(fields[17]);
    boolean hasSlidingDoors = Boolean.parseBoolean(fields[18]);
    return new Van(id, make, model, year, price, color, transmission, driveType, horsepower,
        weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, cargoCapacity, hasSlidingDoors);
  }

  @Override
  public void save(List<Vehicle> vehicles) {
  }
}
