/**
 * Filter for Vehicle color field.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership.business.filter;

import usedcardealership.data.vehicle.*;

public class VehicleColorFilter extends VehicleFilter {
    private String color;

    /**
     * Constructs a VehicleColorFilter with specified color
     * 
     * @param color - the color to filter by
     */
    public VehicleColorFilter(String color) {
        if (color == null || color.length() == 0) {
            throw new IllegalArgumentException("Color cannot be null or empty.");
        }
        this.color = color.toLowerCase();
    }

    /**
     * Determines if Vehicle matches filter's color crieria
     * 
     * @param vehicle - the Vehicle object to check
     * @return true if Vehicle's color matches, false otherwise
     */
    @Override
    public boolean filter(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        return vehicle.getColor().toLowerCase().equals(this.color);
    }
}