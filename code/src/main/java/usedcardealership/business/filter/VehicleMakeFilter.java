/**
 * Filter for Vehicle make field.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership.business.filter;

import usedcardealership.data.vehicle.*;

public class VehicleMakeFilter extends VehicleFilter {
    private String make;

    /**
     * Constructs a VehicleMakeFilter with specified make
     * 
     * @param make - the make to filter by
     */
    public VehicleMakeFilter(String make) {
        if (make == null || make.length() == 0) {
            throw new IllegalArgumentException("Make cannot be null or empty.");
        }
        this.make = make.toLowerCase();
    }

    /**
     * Determines if Vehicle matches filter's make crieria
     * 
     * @param vehicle - the Vehicle object to check
     * @return true if Vehicle's make matches, false otherwise
     */
    @Override
    public boolean filter(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        return vehicle.getMake().toLowerCase().equals(this.make);
    }
}
