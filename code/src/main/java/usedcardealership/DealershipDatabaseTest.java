package usedcardealership;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import usedcardealership.data.filehandling.VehicleDatabaseHandler;
import usedcardealership.data.filehandling.VehicleFileHandler;
import usedcardealership.data.vehicle.Vehicle;

public class DealershipDatabaseTest {

  public static void main(String[] args) {
    String jdbcUrl = "jdbc:postgresql://localhost:5432/usedcardealership";
    String dbUser = "postgres";
    String dbPassword = "postgres";

    String inventoryFilePath = "resources/inventory.csv";

    try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
      System.out.println("Connected to the database successfully!");

      VehicleFileHandler fileHandler = new VehicleFileHandler(inventoryFilePath);

      VehicleDatabaseHandler dbHandler = new VehicleDatabaseHandler(connection);
      List<Vehicle> inventory = fileHandler.load();
      dbHandler.save(inventory);
      List<Vehicle> test = dbHandler.load();
      for (Vehicle v : test) {
        System.out.println(v);
      }

      System.out.println("Inventory successfully saved to the database!");

    } catch (SQLException e) {
      System.err.println("Failed to connect to the database or execute operations.");
      e.printStackTrace();
    }
  }
}
