package usedcardealership.data.databasehandling;

import java.sql.*;
import java.util.*;

import usedcardealership.data.IDataHandler;
import usedcardealership.data.customer.Customer;
import usedcardealership.data.filehandling.VehicleHelper;
import usedcardealership.data.transaction.Transaction;
import usedcardealership.data.vehicle.Vehicle;

public class TransactionDatabaseHandler implements IDataHandler<Transaction> {
    private Connection connection;

    public TransactionDatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Transaction> load() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT t.id, t.type, t.date, t.price, t.tax, c.id AS customer_id, "
                + "c.first_name, c.last_name, c.birthday, c.phone_number, c.address, c.account_balance, "
                + "v.type AS vehicle_type, v.* "
                + "FROM transactions t "
                + "JOIN customers c ON t.customer_id = c.id "
                + "JOIN vehicles v ON t.vehicle_id = v.id";

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Parse Customer
                Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("birthday"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getDouble("account_balance"),
                        new ArrayList<>());

                // Parse Vehicle
                String vehicleType = rs.getString("vehicle_type");
                Vehicle vehicle = VehicleHelper.parseVehicleFromResultSet(vehicleType, rs);

                // Parse Transaction
                Transaction transaction = new Transaction(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getDate("date").toLocalDate(),
                        rs.getDouble("price"),
                        customer,
                        vehicle);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
