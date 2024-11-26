/**
 * Class for handling reading and writing Coupons from/to database
 *
 * @param <Coupon> the input Coupon list
 * @author Talon Dunbar
 * @version 11/25/2024
 */
package usedcardealership.data.databasehandling;

import java.sql.*;
import java.util.*;

import usedcardealership.data.IDataHandler;
import usedcardealership.data.coupons.*;
import usedcardealership.interaction.*;

public class CouponDatabaseHandler implements IDataHandler<Coupon> {
    private final Connection connection;

    /**
     * Constructor for CouponDatabaseHandler
     * 
     * @param connection the JDBC Connection object
     */
    public CouponDatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    /**
     * Loads a List of Coupons from the database using a SELECT query
     * 
     * @return List<Coupon> - the List of Coupons loaded from the database
     * @throws SQLException if there is a database error
     */
    @Override
    public List<Coupon> load() {
        String couponQuery = "SELECT * FROM coupons";
        List<Coupon> coupons = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(couponQuery);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String type = rs.getString("type");
                String code = rs.getString("code");
                double discount = rs.getDouble("discount");
                if ("Numeric".equalsIgnoreCase(type)) {
                    coupons.add(new NumericCoupon(code, discount));
                } else if ("Percentage".equalsIgnoreCase(type)) {
                    coupons.add(new PercentageCoupon(code, discount));
                } else {
                    PrettyUtils.printRed("Unknown coupon type: " + type);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coupons;
    }

    public void save(List<Coupon> coupons) {
        throw new UnsupportedOperationException("Not necessary");
    }
}