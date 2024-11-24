/**
 * Filter for Vehicle drive type field.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership.business.filter;

import usedcardealership.data.vehicle.*;

public class VehicleDriveFilter extends VehicleFilter {
    private String driveType;

    /**
     * Constructs a VehicleDriveFilter with specified drive type
     * 
     * @param driveType - the driveType to filter by
     */
    public VehicleDriveFilter(String driveType) {
        if (driveType == null || driveType.isBlank()) {
            throw new IllegalArgumentException("Drive type cannot be null or empty.");
        }
        this.driveType = driveType.toLowerCase();
    }

    /**
     * Determines if Vehicle matches filter's drive type crieria
     * 
     * @param vehicle - the Vehicle object to check
     * @return true if Vehicle's drive type matches, false otherwise
     */
    @Override
    public boolean filter(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        return vehicle.getDriveType().toLowerCase().equals(this.driveType);
    }
}
