/**
 * Filter for Vehicle color field.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

public class VehicleColorFilter extends VehicleFilter {
    private String color;

    /**
     * Constructs a VehicleColorFilter with specified color
     * 
     * @param color - the color to filter by
     */
    public VehicleColorFilter(String color) {
        this.color = color;
    }

    /**
     * Determines if Vehicle matches filter's color crieria
     * 
     * @param vehicle - the Vehicle object to check
     * @return true if Vehicle's color matches, false otherwise
     */
    @Override
    public boolean filter(Vehicle vehicle) {
        return vehicle.getColor().equals(this.color);
    }
}
