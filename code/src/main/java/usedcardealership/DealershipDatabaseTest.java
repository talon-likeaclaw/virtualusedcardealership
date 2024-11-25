package usedcardealership;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import usedcardealership.data.filehandling.CustomerDatabaseHandler;
import usedcardealership.data.filehandling.CustomerFileHandler;
import usedcardealership.data.filehandling.VehicleDatabaseHandler;
import usedcardealership.data.filehandling.VehicleFileHandler;
import usedcardealership.data.vehicle.Vehicle;
import usedcardealership.data.customer.*;
import usedcardealership.interaction.PrettyUtils;

public class DealershipDatabaseTest {

  public static void main(String[] args) {
    String jdbcUrl = "jdbc:postgresql://localhost:5432/usedcardealership";
    String dbUser = "postgres";
    String dbPassword = "postgres";

    try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
      PrettyUtils.printGreen("Connected to the database successfully!");

      VehicleDatabaseHandler dbHandler = new VehicleDatabaseHandler(connection);
      List<Vehicle> test = dbHandler.load();
      for (Vehicle v : test) {
        System.out.println(v);
      }

      CustomerDatabaseHandler custDbHandler = new CustomerDatabaseHandler(connection);
      List<Customer> cust = custDbHandler.load();
      for (Customer c : cust) {
        System.out.println(c);
        System.out.println(c.getVehicles());
      }

      PrettyUtils.printGreen("Inventory successfully saved to the database!");

    } catch (SQLException e) {
      PrettyUtils.printRed("Failed to connect to the database or execute operations.");
      e.printStackTrace();
    }
  }
}
