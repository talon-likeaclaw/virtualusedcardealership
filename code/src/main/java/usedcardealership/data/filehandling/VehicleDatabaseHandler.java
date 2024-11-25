package usedcardealership.data.filehandling;

import java.sql.*;
import java.util.*;
import usedcardealership.data.vehicle.*;

public class VehicleDatabaseHandler implements IDataHandler<Vehicle> {
    private final Connection connection;

    public VehicleDatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Vehicle> load() {
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

    @Override
    public void save(List<Vehicle> vehicles) {
        String query = "INSERT INTO vehicles (type, make, model, year, price, color, transmission, drive_type, horsepower, weight, kilometerage, damage, is_electric, engine_cc, handlebar_type, num_doors, num_seats, has_sunroof, sleep_capacity, has_bathroom, is_convertible, cargo_capacity, has_thirdrow_seating, has_sliding_doors, bed_length, towing_capacity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            for (Vehicle v : vehicles) {
                setCommonFields(pstmt, v);
                setTypeSpecificFields(pstmt, v);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
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
        pstmt.setString(1, v.getType());
        pstmt.setString(2, v.getMake());
        pstmt.setString(3, v.getModel());
        pstmt.setInt(4, v.getYear());
        pstmt.setDouble(5, v.getPrice());
        pstmt.setString(6, v.getColor());
        pstmt.setString(7, v.getTransmission());
        pstmt.setString(8, v.getDriveType());
        pstmt.setInt(9, v.getHorsepower());
        pstmt.setDouble(10, v.getWeight());
        pstmt.setDouble(11, v.getKilometerage());
        pstmt.setDouble(12, v.getDamage());
        pstmt.setBoolean(13, v.isElectric());
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
            pstmt.setDouble(14, motorcycle.getEngineCC());
            pstmt.setString(15, motorcycle.getHandleType());
            pstmt.setNull(16, Types.INTEGER);
            pstmt.setNull(17, Types.INTEGER);
            pstmt.setNull(18, Types.BOOLEAN);
            pstmt.setNull(19, Types.INTEGER);
            pstmt.setNull(20, Types.BOOLEAN);
            pstmt.setNull(21, Types.BOOLEAN);
            pstmt.setNull(22, Types.DOUBLE);
            pstmt.setNull(23, Types.BOOLEAN);
            pstmt.setNull(24, Types.BOOLEAN);
            pstmt.setNull(25, Types.DOUBLE);
            pstmt.setNull(26, Types.DOUBLE);
        } else if (v instanceof Car) {
            Car car = (Car) v;
            pstmt.setNull(14, Types.DOUBLE);
            pstmt.setNull(15, Types.VARCHAR);
            pstmt.setInt(16, car.getNumDoors());
            pstmt.setInt(17, car.getNumSeats());
            pstmt.setBoolean(18, car.hasSunRoof());
            pstmt.setNull(19, Types.INTEGER);
            pstmt.setNull(20, Types.BOOLEAN);
            pstmt.setBoolean(21, car.isConvertible());
            pstmt.setNull(22, Types.DOUBLE);
            pstmt.setNull(23, Types.BOOLEAN);
            pstmt.setNull(24, Types.BOOLEAN);
            pstmt.setNull(25, Types.DOUBLE);
            pstmt.setNull(26, Types.DOUBLE);
        } else if (v instanceof RV) {
            RV rv = (RV) v;
            pstmt.setNull(14, Types.DOUBLE);
            pstmt.setNull(15, Types.VARCHAR);
            pstmt.setInt(16, rv.getNumDoors());
            pstmt.setInt(17, rv.getNumSeats());
            pstmt.setBoolean(18, rv.hasSunRoof());
            pstmt.setInt(19, rv.getSleepCapacity());
            pstmt.setBoolean(20, rv.hasBathroom());
            pstmt.setNull(21, Types.BOOLEAN);
            pstmt.setNull(22, Types.DOUBLE);
            pstmt.setNull(23, Types.BOOLEAN);
            pstmt.setNull(24, Types.BOOLEAN);
            pstmt.setNull(25, Types.DOUBLE);
            pstmt.setNull(26, Types.DOUBLE);
        } else if (v instanceof SUV) {
            SUV suv = (SUV) v;
            pstmt.setNull(14, Types.DOUBLE);
            pstmt.setNull(15, Types.VARCHAR);
            pstmt.setInt(16, suv.getNumDoors());
            pstmt.setInt(17, suv.getNumSeats());
            pstmt.setBoolean(18, suv.hasSunRoof());
            pstmt.setNull(19, Types.INTEGER);
            pstmt.setNull(20, Types.BOOLEAN);
            pstmt.setNull(21, Types.BOOLEAN);
            pstmt.setNull(22, Types.DOUBLE);
            pstmt.setBoolean(23, suv.hasThirdRowSeating());
            pstmt.setNull(24, Types.BOOLEAN);
            pstmt.setNull(25, Types.DOUBLE);
            pstmt.setNull(26, Types.DOUBLE);
        } else if (v instanceof PickupTruck) {
            PickupTruck truck = (PickupTruck) v;
            pstmt.setNull(14, Types.DOUBLE);
            pstmt.setNull(15, Types.VARCHAR);
            pstmt.setNull(16, Types.INTEGER);
            pstmt.setInt(17, truck.getNumSeats());
            pstmt.setNull(18, Types.BOOLEAN);
            pstmt.setNull(19, Types.INTEGER);
            pstmt.setNull(20, Types.BOOLEAN);
            pstmt.setNull(21, Types.BOOLEAN);
            pstmt.setDouble(22, truck.getCargoCapacity());
            pstmt.setNull(23, Types.BOOLEAN);
            pstmt.setNull(24, Types.BOOLEAN);
            pstmt.setDouble(25, truck.getBedLength());
            pstmt.setDouble(26, truck.getTowingCapacity());
        } else if (v instanceof Van) {
            Van van = (Van) v;
            pstmt.setNull(14, Types.DOUBLE);
            pstmt.setNull(15, Types.VARCHAR);
            pstmt.setInt(16, van.getNumDoors());
            pstmt.setInt(17, van.getNumSeats());
            pstmt.setBoolean(18, van.hasSunRoof());
            pstmt.setNull(19, Types.INTEGER);
            pstmt.setNull(20, Types.BOOLEAN);
            pstmt.setNull(21, Types.BOOLEAN);
            pstmt.setDouble(22, van.getCargoCapacity());
            pstmt.setNull(23, Types.BOOLEAN);
            pstmt.setBoolean(24, van.hasSlidingDoors());
            pstmt.setNull(25, Types.DOUBLE);
            pstmt.setNull(26, Types.DOUBLE);
        } else {
            throw new IllegalArgumentException("Unkown vehicle type: " + v.getType());
        }
    }
}