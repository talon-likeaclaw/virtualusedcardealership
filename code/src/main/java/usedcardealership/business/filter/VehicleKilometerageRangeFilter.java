/**
 * Filter for Vehicle kilometerage range.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership.business.filter;

import usedcardealership.data.vehicle.*;

public class VehicleKilometerageRangeFilter extends VehicleFilter {
    private double minKilometerage;
    private double maxKilometerage;

    /**
     * Constructs a VehicleKilometerageRangeFilter object
     * 
     * @param minKilometerage the minimum kilometerage to filter by
     * @param maxKilometerage the maximum kilometerage to filter by
     */
    public VehicleKilometerageRangeFilter(double minKilometerage, double maxKilometerage) {
        if (minKilometerage < 0) {
            throw new IllegalArgumentException("Minimum kilometerage cannot be negative.");
        }
        if (minKilometerage > maxKilometerage) {
            throw new IllegalArgumentException("Minimum kilometerage cannot be greater than maximum kilometerage.");
        }
        this.minKilometerage = minKilometerage;
        this.maxKilometerage = maxKilometerage;
    }

    /**
     * Checks if Vehcile's kilometerage is within specified range
     * 
     * @param vehicles - the Vehicle object to check
     * @return true if within kilometerage range, false otherwise
     */
    @Override
    public boolean filter(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        return vehicle.getKilometerage() >= minKilometerage && vehicle.getKilometerage() <= maxKilometerage;
    }
}
