/**
 * Filter for Vehicle drive type field.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

public class VehicleDriveFilter extends VehicleFilter {
    private String driveType;

    /**
     * Constructs a VehicleDriveFilter with specified drive type
     * 
     * @param driveType - the driveType to filter by
     */
    public VehicleDriveFilter(String driveType) {
        this.driveType = driveType;
    }

    /**
     * Determines if Vehicle matches filter's drive type crieria
     * 
     * @param vehicle - the Vehicle object to check
     * @return true if Vehicle's drive type matches, false otherwise
     */
    @Override
    public boolean filter(Vehicle vehicle) {
        return vehicle.getDriveType().equals(this.driveType);
    }
}
