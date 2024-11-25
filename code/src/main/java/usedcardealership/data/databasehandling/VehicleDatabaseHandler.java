package usedcardealership.data.databasehandling;

import java.sql.*;
import java.util.*;

import usedcardealership.data.IDataHandler;
import usedcardealership.data.filehandling.VehicleHelper;
import usedcardealership.data.vehicle.*;

public class VehicleDatabaseHandler implements IDataHandler<Vehicle> {
    private final Connection connection;

    public VehicleDatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Vehicle> load() {
        List<Vehicle> vehicles = new ArrayList<>();
        // Only load vehicles not associated with customer_id
        // Have to have singular vehicles table with customer_vehicles bridging table
        String query = "SELECT * FROM vehicles WHERE id NOT IN (SELECT vehicle_id FROM customers_vehicles)";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String type = rs.getString("type");
                Vehicle vehicle = VehicleHelper.parseVehicleFromResultSet(type, rs);
                if (vehicle != null) {
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> loadDatabase() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String type = rs.getString("type");
                Vehicle vehicle = VehicleHelper.parseVehicleFromResultSet(type, rs);
                if (vehicle != null) {
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    /**
     * Clears all relevant tables
     */
    private void clearAllTables() {
        String deleteCustomersVehiclesQuery = "DELETE FROM customers_vehicles";
        String deleteTransactionsQuery = "DELETE FROM transactions";
        String deleteVehiclesQuery = "DELETE FROM vehicles";
        String deleteCustomersQuery = "DELETE FROM customers";

        try (PreparedStatement pstmt1 = connection.prepareStatement(deleteCustomersVehiclesQuery);
                PreparedStatement pstmt2 = connection.prepareStatement(deleteTransactionsQuery);
                PreparedStatement pstmt3 = connection.prepareStatement(deleteVehiclesQuery);
                PreparedStatement pstmt4 = connection.prepareStatement(deleteCustomersQuery)) {

            pstmt1.executeUpdate();
            pstmt2.executeUpdate();
            pstmt3.executeUpdate();
            pstmt4.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(List<Vehicle> vehicles) {
        clearAllTables();
        String query = "INSERT INTO vehicles (id, type, make, model, year, price, color, transmission, drive_type, horsepower, weight, kilometerage, damage,"
                + " is_electric, engine_cc, handlebar_type, num_doors, num_seats, has_sunroof, sleep_capacity, has_bathroom, is_convertible, cargo_capacity, has_thirdrow"
                + "_seating, has_sliding_doors, bed_length, towing_capacity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            for (Vehicle v : vehicles) {
                setCommonFields(pstmt, v);
                setTypeSpecificFields(pstmt, v);
                pstmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets common fields shared by all Vehicle types
     *
     * @param pstmt   The PreparedStatement object
     * @param vehicle The Vehicle object
     * @throws SQLException
     */
    private void setCommonFields(PreparedStatement pstmt, Vehicle v) throws SQLException {
        pstmt.setInt(1, v.getID());
        pstmt.setString(2, v.getType());
        pstmt.setString(3, v.getMake());
        pstmt.setString(4, v.getModel());
        pstmt.setInt(5, v.getYear());
        pstmt.setDouble(6, v.getPrice());
        pstmt.setString(7, v.getColor());
        pstmt.setString(8, v.getTransmission());
        pstmt.setString(9, v.getDriveType());
        pstmt.setInt(10, v.getHorsepower());
        pstmt.setDouble(11, v.getWeight());
        pstmt.setDouble(12, v.getKilometerage());
        pstmt.setDouble(13, v.getDamage());
        pstmt.setBoolean(14, v.isElectric());
    }

    /**
     * Sets type-specific fields based on Vehicle type
     * 
     * @param pstmt the PreparedStatement object
     * @param v     the Vehicle object
     * @throws SQLException
     */
    private void setTypeSpecificFields(PreparedStatement pstmt, Vehicle v) throws SQLException {
        if (v instanceof Motorcycle) {
            Motorcycle motorcycle = (Motorcycle) v;
            pstmt.setDouble(15, motorcycle.getEngineCC());
            pstmt.setString(16, motorcycle.getHandleType());
            pstmt.setNull(17, Types.INTEGER);
            pstmt.setNull(18, Types.INTEGER);
            pstmt.setNull(19, Types.BOOLEAN);
            pstmt.setNull(20, Types.INTEGER);
            pstmt.setNull(21, Types.BOOLEAN);
            pstmt.setNull(22, Types.BOOLEAN);
            pstmt.setNull(23, Types.DOUBLE);
            pstmt.setNull(24, Types.BOOLEAN);
            pstmt.setNull(25, Types.BOOLEAN);
            pstmt.setNull(26, Types.DOUBLE);
            pstmt.setNull(27, Types.DOUBLE);
        } else if (v instanceof Car) {
            Car car = (Car) v;
            pstmt.setNull(15, Types.DOUBLE);
            pstmt.setNull(16, Types.VARCHAR);
            pstmt.setInt(17, car.getNumDoors());
            pstmt.setInt(18, car.getNumSeats());
            pstmt.setBoolean(19, car.hasSunRoof());
            pstmt.setNull(20, Types.INTEGER);
            pstmt.setNull(21, Types.BOOLEAN);
            pstmt.setBoolean(22, car.isConvertible());
            pstmt.setNull(23, Types.DOUBLE);
            pstmt.setNull(24, Types.BOOLEAN);
            pstmt.setNull(25, Types.BOOLEAN);
            pstmt.setNull(26, Types.DOUBLE);
            pstmt.setNull(27, Types.DOUBLE);
        } else if (v instanceof RV) {
            RV rv = (RV) v;
            pstmt.setNull(15, Types.DOUBLE);
            pstmt.setNull(16, Types.VARCHAR);
            pstmt.setInt(17, rv.getNumDoors());
            pstmt.setInt(18, rv.getNumSeats());
            pstmt.setBoolean(19, rv.hasSunRoof());
            pstmt.setInt(20, rv.getSleepCapacity());
            pstmt.setBoolean(21, rv.hasBathroom());
            pstmt.setNull(22, Types.BOOLEAN);
            pstmt.setNull(23, Types.DOUBLE);
            pstmt.setNull(24, Types.BOOLEAN);
            pstmt.setNull(25, Types.BOOLEAN);
            pstmt.setNull(26, Types.DOUBLE);
            pstmt.setNull(27, Types.DOUBLE);
        } else if (v instanceof SUV) {
            SUV suv = (SUV) v;
            pstmt.setNull(15, Types.DOUBLE);
            pstmt.setNull(16, Types.VARCHAR);
            pstmt.setInt(17, suv.getNumDoors());
            pstmt.setInt(18, suv.getNumSeats());
            pstmt.setBoolean(19, suv.hasSunRoof());
            pstmt.setNull(20, Types.INTEGER);
            pstmt.setNull(21, Types.BOOLEAN);
            pstmt.setNull(22, Types.BOOLEAN);
            pstmt.setNull(23, Types.DOUBLE);
            pstmt.setBoolean(24, suv.hasThirdRowSeating());
            pstmt.setNull(25, Types.BOOLEAN);
            pstmt.setNull(26, Types.DOUBLE);
            pstmt.setNull(27, Types.DOUBLE);
        } else if (v instanceof PickupTruck) {
            PickupTruck truck = (PickupTruck) v;
            pstmt.setNull(15, Types.DOUBLE);
            pstmt.setNull(16, Types.VARCHAR);
            pstmt.setInt(17, truck.getNumDoors());
            pstmt.setInt(18, truck.getNumSeats());
            pstmt.setNull(19, Types.BOOLEAN);
            pstmt.setNull(20, Types.INTEGER);
            pstmt.setNull(21, Types.BOOLEAN);
            pstmt.setNull(22, Types.BOOLEAN);
            pstmt.setDouble(23, truck.getCargoCapacity());
            pstmt.setNull(24, Types.BOOLEAN);
            pstmt.setNull(25, Types.BOOLEAN);
            pstmt.setDouble(26, truck.getBedLength());
            pstmt.setDouble(27, truck.getTowingCapacity());
        } else if (v instanceof Van) {
            Van van = (Van) v;
            pstmt.setNull(15, Types.DOUBLE);
            pstmt.setNull(16, Types.VARCHAR);
            pstmt.setInt(17, van.getNumDoors());
            pstmt.setInt(18, van.getNumSeats());
            pstmt.setBoolean(19, van.hasSunRoof());
            pstmt.setNull(20, Types.INTEGER);
            pstmt.setNull(21, Types.BOOLEAN);
            pstmt.setNull(22, Types.BOOLEAN);
            pstmt.setDouble(23, van.getCargoCapacity());
            pstmt.setNull(24, Types.BOOLEAN);
            pstmt.setBoolean(25, van.hasSlidingDoors());
            pstmt.setNull(26, Types.DOUBLE);
            pstmt.setNull(27, Types.DOUBLE);
        } else {
            throw new IllegalArgumentException("Unkown vehicle type: " + v.getType());
        }
    }
}