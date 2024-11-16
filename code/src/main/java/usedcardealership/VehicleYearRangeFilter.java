/**
 * Filter for Vehicle year range.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

public class VehicleYearRangeFilter extends VehicleFilter {
    private double minYear;
    private double maxYear;

    /**
     * Constructs a VehicleYearRangeFilter object
     * 
     * @param minYear the minimum year to filter by
     * @param maxYear the maximum year to filter by
     */
    public VehicleYearRangeFilter(double minYear, double maxYear) {
        this.minYear = minYear;
        this.maxYear = maxYear;
    }

    /**
     * Checks if Vehcile's year is within specified range
     * 
     * @param vehicles - the Vehicle object to check
     * @return true if within year range, false otherwise
     */
    @Override
    public boolean filter(Vehicle vehicle) {
        return vehicle.getYear() >= minYear && vehicle.getYear() <= maxYear;
    }
}
