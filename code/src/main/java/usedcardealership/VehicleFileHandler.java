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

  public VehicleFileHandler(String filePath) throws IOException {
    this.filePath = Paths.get(filePath);
  }

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
      double engineCC = Double.parseDouble(fields[14]);
      String handlebarType = fields[15];
      return new Motorcycle(id, make, model, year, price, color, transmission, driveType,
          horsepower, weight, kilometerage, damage, isElectric, engineCC, handlebarType);
    } else if (type == "RV") {
      int numSeats = Integer.parseInt(fields[14]);
      int numDoors = Integer.parseInt(fields[15]);
      boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
      int sleepCapacity = Integer.parseInt(fields[17]);
      boolean hasBathroom = Boolean.parseBoolean(fields[18]);
      return new RV(id, make, model, year, price, color, transmission, driveType, horsepower, weight,
          kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, sleepCapacity, hasBathroom);
    } else if (type == "Car") {
      int numSeats = Integer.parseInt(fields[14]);
      int numDoors = Integer.parseInt(fields[15]);
      boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
      boolean isConvertible = Boolean.parseBoolean(fields[17]);
      return new Car(id, make, model, year, price, color, transmission, driveType, horsepower,
          weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, isConvertible);
    } else if (type == "SUV") {
      int numSeats = Integer.parseInt(fields[14]);
      int numDoors = Integer.parseInt(fields[15]);
      boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
      boolean hasThirdRowSeating = Boolean.parseBoolean(fields[17]);
      return new SUV(id, make, model, year, price, color, transmission, driveType, horsepower, weight,
          kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, hasThirdRowSeating);
    } else if (type == "PickupTruck") {
      int numSeats = Integer.parseInt(fields[14]);
      int numDoors = Integer.parseInt(fields[15]);
      boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
      double cargoCapacity = Double.parseDouble(fields[17]);
      double bedLength = Double.parseDouble(fields[18]);
      double towingCapacity = Double.parseDouble(fields[19]);
      return new PickupTruck(id, make, model, year, price, color, transmission, driveType, horsepower, weight,
          kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, cargoCapacity, bedLength, towingCapacity);
    } else if (type == "Van") {
      int numSeats = Integer.parseInt(fields[14]);
      int numDoors = Integer.parseInt(fields[15]);
      boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
      double cargoCapacity = Double.parseDouble(fields[17]);
      boolean hasSlidingDoors = Boolean.parseBoolean(fields[18]);
      return new Van(id, make, model, year, price, color, transmission, driveType, horsepower, weight,
          kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, cargoCapacity, hasSlidingDoors);
    } else {
      return null;
    }
  }

  @Override
  public void save(List<Vehicle> vehicles) {
  }
}
