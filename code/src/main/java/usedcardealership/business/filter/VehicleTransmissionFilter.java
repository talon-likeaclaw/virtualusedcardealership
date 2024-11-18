/**
 * Filter for Vehicle transmission field.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership.business.filter;

import usedcardealership.data.vehicle.*;

public class VehicleTransmissionFilter extends VehicleFilter {
    private String transmission;

    /**
     * Constructs a VehicleTransmissionFilter with specified transmission
     * 
     * @param transmission - the transmission to filter by
     */
    public VehicleTransmissionFilter(String transmission) {
        this.transmission = transmission;
    }

    /**
     * Determines if Vehicle matches filter's transmission crieria
     * 
     * @param vehicle - the Vehicle object to check
     * @return true if Vehicle's transmission matches, false otherwise
     */
    @Override
    public boolean filter(Vehicle vehicle) {
        return vehicle.getTransmission().equals(this.transmission);
    }
}
