/**
 * Filter for Vehicle make field.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

public class VehicleMakeFilter extends VehicleFilter {
    private String make;

    /**
     * Constructs a VehicleMakeFilter with specified make
     * 
     * @param make - the make to filter by
     */
    public VehicleMakeFilter(String make) {
        this.make = make;
    }

    /**
     * Determines if Vehicle matches filter's make crieria
     * 
     * @param vehicle - the Vehicle object to check
     * @return true if Vehicle's make matches, false otherwise
     */
    @Override
    public boolean filter(Vehicle vehicle) {
        return vehicle.getMake().equals(this.make);
    }
}
