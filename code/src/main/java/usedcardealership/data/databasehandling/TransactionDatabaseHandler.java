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

    /**
     * Constructor for TransactionDatabaseHandler
     * 
     * @param connection the JDBC Connection object
     */
    public TransactionDatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    /**
     * Loads all of the Transactions from the databse using a SELECT query
     * 
     * @return List<Transaction> - the List of Transactions loaded from the database
     * @throws SQLException if there is a database error
     */
    @Override
    public List<Transaction> load() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT t.id, t.type, t.date, t.price, t.tax, c.id AS customer_id, "
                + "c.first_name, c.last_name, c.birthday, c.phone_number, c.address, c.account_balance, "
                + "v.type AS vehicle_type, v.* "
                + "FROM transactions t "
                + "JOIN customers c ON t.customer_id = c.id "
                + "JOIN vehicles v ON t.vehicle_id = v.id";
        try (Statement stmt = this.connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Parse Customer object
                Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("birthday"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getDouble("account_balance"),
                        new ArrayList<>());

                // Parse Vehicle object
                String vehicleType = rs.getString("vehicle_type");
                Vehicle vehicle = VehicleHelper.parseVehicleFromResultSet(vehicleType, rs);

                // Parse Transaction object with Customer and Vehicle
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

    /**
     * Writes an List of Transactions to the databse with an INSERT statement
     * 
     * @param transactions - The List of Transactions to INSERT into the database
     * @throws SQLException if there is a database error
     */
    @Override
    public void save(List<Transaction> transactions) {
        String query = "INSERT INTO transactions (id, type, date, price, tax, customer_id, vehicle_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            for (Transaction transaction : transactions) {
                pstmt.setInt(1, transaction.getID());
                pstmt.setString(2, transaction.getType());
                pstmt.setDate(3, java.sql.Date.valueOf(transaction.getDate()));
                pstmt.setDouble(4, transaction.getPrice());
                pstmt.setDouble(5, transaction.getTax());
                pstmt.setInt(6, transaction.getCustomer().getID());
                pstmt.setInt(7, transaction.getVehicle().getID());
                pstmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
