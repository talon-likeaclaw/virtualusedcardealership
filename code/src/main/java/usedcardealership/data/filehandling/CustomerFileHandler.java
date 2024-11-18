/**
 * Class for handling reading and writing Customers from/to files
 *
 * @param <Customer> the input Customer list
 * @author Talon Dunbar
 * @version 11/12/2024
 */

package usedcardealership.data.filehandling;

import java.nio.file.*;
import java.io.*;
import java.util.*;
import usedcardealership.data.customer.*;
import usedcardealership.data.vehicle.*;

public class CustomerFileHandler implements IDataHandler<Customer> {
    private final Path filePath;

    /**
     * Constructs a CustomerFileHandler with the specified file path.
     * 
     * @param String filePath - The path to the file containing Customer data.
     * @throws IOException
     */
    public CustomerFileHandler(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads Customers from the file and returns them as a list of Customer objects.
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

                Customer customer = new Customer(id, firstName, lastName, birthday, phoneNumber, address,
                        accountBalance, vehicles);
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * Saves the list of Customers to the file in CSV format.
     * 
     * @param customers List of Customer objects to save.
     */
    @Override
    public void save(List<Customer> customers) {
        List<String> lines = new ArrayList<>();

        for (Customer customer : customers) {
            // Convert Customer information to CSV format
            String customerLine = String.join(",",
                    String.valueOf(customer.getID()),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getBirthday(),
                    customer.getPhoneNumber(),
                    customer.getAddress(),
                    String.valueOf(customer.getAccountBalance()));

            // Convert each vehicle in the customer's list to CSV format
            List<String> vehicleStrings = new ArrayList<>();
            for (Vehicle vehicle : customer.getVehicles()) {
                vehicleStrings.add(VehicleHelper.convertVehicleToCSV(vehicle));
            }
            // Add [ and ] to outside of vehicle list and delimit each vehicle with #
            String vehiclesField = "[" + String.join("#", vehicleStrings) + "]";
            // Add vehicles to customer CSV string and add to List<String> lines
            customerLine += "," + vehiclesField;
            lines.add(customerLine);
        }
        try {
            Files.write(this.filePath, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses a nested CSV vehicleList into a list of Vehicle objects.
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
