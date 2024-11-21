/**
 * Filter for Vehicle type field.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership.business.filter;

import usedcardealership.data.vehicle.*;

public class VehicleTypeFilter extends VehicleFilter {
    private String type;

    /**
     * Constructs a VehicleTypeFilter with specified type
     * 
     * @param type - the type to filter by
     */
    public VehicleTypeFilter(String type) {
        this.type = type.toLowerCase();
    }

    /**
     * Determines if Vehicle matches filter's type crieria
     * 
     * @param vehicle - the Vehicle object to check
     * @return true if Vehicle's type matches, false otherwise
     */
    @Override
    public boolean filter(Vehicle vehicle) {
        return vehicle.getType().toLowerCase().equals(this.type);
    }
}
