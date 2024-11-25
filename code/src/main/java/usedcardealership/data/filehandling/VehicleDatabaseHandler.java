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
}