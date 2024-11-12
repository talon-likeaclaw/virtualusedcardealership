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

        // TODO: Create helper method(s) for each Vehicle type
        Vehicle vehicle = createVehicle(type, fields);
        vehicles.add(vehicle);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return vehicles;
  }

  @Override
  public void save(List<Vehicle> vehicles) {
  }
}
