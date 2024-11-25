package usedcardealership.data.filehandling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usedcardealership.data.customer.Customer;
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

    @Override
    public void save(List<Customer> customers) {
        String customerInsert = "INSERT INTO customers (id, first_name, last_name, birthday, phone_number, address, account_balance) "
                + "VALUES (?, ?, ?, ?::DATE, ?, ?, ?)";
        String customerVehicleInsert = "INSERT INTO customers_vehicles (customer_id, vehicle_id) VALUES (?, ?)";

        try {
            connection.setAutoCommit(false);

            for (Customer customer : customers) {
                try (PreparedStatement customerStmt = connection.prepareStatement(customerInsert)) {
                    customerStmt.setInt(1, customer.getID());
                    customerStmt.setString(2, customer.getFirstName());
                    customerStmt.setString(3, customer.getLastName());
                    customerStmt.setString(4, customer.getBirthday());
                    customerStmt.setString(5, customer.getPhoneNumber());
                    customerStmt.setString(6, customer.getAddress());
                    customerStmt.setDouble(7, customer.getAccountBalance());
                    customerStmt.executeUpdate();
                }

                // Insert customer-vehicle relationships
                try (PreparedStatement vehicleStmt = connection.prepareStatement(customerVehicleInsert)) {
                    for (Vehicle vehicle : customer.getVehicles()) {
                        vehicleStmt.setInt(1, customer.getID());
                        vehicleStmt.setInt(2, vehicle.getID());
                        vehicleStmt.addBatch();
                    }
                    vehicleStmt.executeBatch();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
