/**
 * Filter for Vehicle year range.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership.business.filter;

import usedcardealership.data.vehicle.*;

public class VehicleYearRangeFilter extends VehicleFilter {
    private int minYear;
    private int maxYear;

    /**
     * Constructs a VehicleYearRangeFilter object
     * 
     * @param minYear the minimum year to filter by
     * @param maxYear the maximum year to filter by
     */
    public VehicleYearRangeFilter(int minYear, int maxYear) {
        if (minYear < 1800) {
            throw new IllegalArgumentException("Minimum year cannot be less than 1800.");
        }
        if (minYear > maxYear) {
            throw new IllegalArgumentException("Minimum year cannot be greater than maximum year.");
        }
        this.minYear = minYear;
        this.maxYear = maxYear;
    }

    /**
     * Checks if Vehicle's year is within specified range
     * 
     * @param vehicles - the Vehicle object to check
     * @return true if within year range, false otherwise
     */
    @Override
    public boolean filter(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        return vehicle.getYear() >= minYear && vehicle.getYear() <= maxYear;
    }
}
