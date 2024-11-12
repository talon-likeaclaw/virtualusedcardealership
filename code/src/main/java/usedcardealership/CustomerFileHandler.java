/**
 * Class for handling reading and writing Customers from/to files
 *
 * @param <Customer> the input vehicle list
 * @author Talon Dunbar
 * @version 11/12/2024
 */

package usedcardealership;

import java.nio.file.*;
import java.io.*;
import java.util.*;

public class CustomerFileHandler implements IDataHandler<Customer> {
    private final Path filePath;

    /**
     * Constructs a CustomerFileHandler with the specified file path.
     * 
     * @param String filePath - The path to the file containing Customer data.
     * @throws IOException
     */
    public CustomerFileHandler(String filePath, VehicleManager vehicleManager) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads Customers from the file and returns them as a list.
     * 
     * @return List<Customer> - A list of Customers loaded from the file.
     */
    @Override
    public List<Customer> load() {
        List<Customer> customers = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(this.filePath);
            for (String line : allLines) {
                String[] customerFields = line.split(",");
                int id = Integer.parseInt(customerFields[0]);
                String firstName = customerFields[1];
                String lastName = customerFields[2];
                String birthday = customerFields[3];
                String phoneNumber = customerFields[4];
                String address = customerFields[5];
                double accountBalance = Double.parseDouble(customerFields[6]);
                String vehiclesList = customerFields[7];
                List<Vehicle> vehicles = parseVehicles(vehiclesList);

                Customer customer = new Customer(id, firstName, lastName, birthday, phoneNumber, address, accountBalance, vehicles);
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * Parses a nested CSV vehcileList into a list of Vehicle objects.
     * 
     * @param vehicleList - A string representing Customer's vehicles delimiter #.
     * @return List<Vehicle> - The list of Vehicle objects.
     */
    private List<Vehicle> parseVehicles(String vehicleList) {
        List<Vehicle> vehicles = new ArrayList<>();

        // Get each individual vehicle.
        vehicleList = vehicleList.replace("[", "").replace("]", "");
        String[] customerVehicles = vehicleList.split("#");

        // Create list of Vehicle objects.
        for (String vehicleData : customerVehicles) {
            String[] vehicleFields = vehicleData.split(",");
            Vehicle vehicle = VehicleHelper.parseVehicle(vehicleFields);
            if (vehicle != null) {
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }
}
