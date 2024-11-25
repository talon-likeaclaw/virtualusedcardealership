package usedcardealership.data.databasehandling;

import java.sql.*;
import java.util.*;

import usedcardealership.data.IDataHandler;
import usedcardealership.data.customer.Customer;
import usedcardealership.data.filehandling.VehicleHelper;
import usedcardealership.data.vehicle.Vehicle;

public class CustomerDatabaseHandler implements IDataHandler<Customer> {
    private final Connection connection;

    public CustomerDatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Customer> load() {
        String customerQuery = "SELECT * FROM customers";
        // Use bridging table to get vehicles for customer vehicle loading
        String vehicleQuery = "SELECT v.* FROM vehicles v "
                + "JOIN customers_vehicles cv ON v.id = cv.vehicle_id "
                + "WHERE cv.customer_id = ?";
        List<Customer> customers = new ArrayList<>();

        try (PreparedStatement customerStmt = connection.prepareStatement(customerQuery);
                ResultSet customerRs = customerStmt.executeQuery()) {

            while (customerRs.next()) {
                int customerId = customerRs.getInt("id");
                String firstName = customerRs.getString("first_name");
                String lastName = customerRs.getString("last_name");
                String birthday = customerRs.getString("birthday");
                String phoneNumber = customerRs.getString("phone_number");
                String address = customerRs.getString("address");
                double accountBalance = customerRs.getDouble("account_balance");

                List<Vehicle> vehicles = new ArrayList<>();
                try (PreparedStatement vehicleStmt = connection.prepareStatement(vehicleQuery)) {
                    vehicleStmt.setInt(1, customerId);
                    try (ResultSet vehicleRs = vehicleStmt.executeQuery()) {
                        while (vehicleRs.next()) {
                            String type = vehicleRs.getString("type");
                            vehicles.add(VehicleHelper.parseVehicleFromResultSet(type, vehicleRs));
                        }
                    }
                }

                Customer customer = new Customer(customerId, firstName, lastName, birthday, phoneNumber, address,
                        accountBalance, vehicles);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void save(List<Customer> customers) {
        String queryCustomer = "INSERT INTO customers (id, first_name, last_name, birthday, phone_number, address, account_balance) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String queryVehicles = "INSERT INTO customers_vehicles (customer_id, vehicle_id) VALUES (?, ?)";

        try (PreparedStatement pstmtCustomer = connection.prepareStatement(queryCustomer);
                PreparedStatement pstmtVehicles = connection.prepareStatement(queryVehicles)) {

            for (Customer customer : customers) {
                // Insert customer
                pstmtCustomer.setInt(1, customer.getID());
                pstmtCustomer.setString(2, customer.getFirstName());
                pstmtCustomer.setString(3, customer.getLastName());
                pstmtCustomer.setDate(4, java.sql.Date.valueOf(customer.getBirthday()));
                pstmtCustomer.setString(5, customer.getPhoneNumber());
                pstmtCustomer.setString(6, customer.getAddress());
                pstmtCustomer.setDouble(7, customer.getAccountBalance());
                pstmtCustomer.execute();

                // Insert vehicles associated with the customer
                for (Vehicle vehicle : customer.getVehicles()) {
                    pstmtVehicles.setInt(1, customer.getID());
                    pstmtVehicles.setInt(2, vehicle.getID());
                    pstmtVehicles.execute();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
