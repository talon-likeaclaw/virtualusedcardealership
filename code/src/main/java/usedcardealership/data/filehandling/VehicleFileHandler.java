/**
 * Class for handling reading and writing Vehicles from/to files
 *
 * @param <Vehicle> the input vehicle list
 * @author Talon Dunbar
 * @version 11/19/2024
 */

package usedcardealership.data.filehandling;

import java.nio.file.*;
import java.io.*;
import java.util.*;

import usedcardealership.data.IDataHandler;
import usedcardealership.data.vehicle.*;

public class VehicleFileHandler implements IDataHandler<Vehicle> {
  private final Path filePath;

  /**
   * Constructs a VehicleFileHandler with the specified file path.
   * 
   * @param String filePath - The path to the file containing Vehicle data.
   */
  public VehicleFileHandler(String filePath) {
    this.filePath = Paths.get(filePath);
  }

  /**
   * Loads Vehicles from the file and returns them as a list.
   * 
   * @return List<Vehicle> - A list of Vehicles loaded from the file.
   */
  @Override
  public List<Vehicle> load() {
    List<Vehicle> vehicles = new ArrayList<>();
    try {
      List<String> allLines = Files.readAllLines(this.filePath);
      for (String line : allLines) {
        String[] vehicleFields = line.split(",");
        Vehicle vehicle = VehicleHelper.parseVehicle(vehicleFields);
        if (vehicle != null) {
          vehicles.add(vehicle);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return vehicles;
  }

  /**
   * Converts each vehicle in List to CSV format and writes to filePath.
   * 
   * @param List<Vehicle> vehicles - the List of Vehicles to save to file.
   */
  @Override
  public void save(List<Vehicle> vehicles) {
    List<String> lines = new ArrayList<>();

    for (Vehicle vehicle : vehicles) {
      lines.add(VehicleHelper.convertVehicleToCSV(vehicle));
    }

    try {
      Files.write(this.filePath, lines);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
