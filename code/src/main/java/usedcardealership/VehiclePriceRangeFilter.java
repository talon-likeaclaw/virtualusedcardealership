/**
 * Filter for Vehicle price range.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

public class VehiclePriceRangeFilter extends VehicleFilter {
    private double minPrice;
    private double maxPrice;

    /**
     * Constructs a VehiclePriceRangeFilter object
     * 
     * @param minPrice the minimum price to filter by
     * @param maxPrice the maximum price to filter by
     */
    public VehiclePriceRangeFilter(double minPrice, double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
    
    /**
     * Checks if Vehcile's price (current value) is within specified range
     * 
     * @param vehicles - the Vehicle object to check
     * @return true if within price range, false otherwise
     */
    @Override public boolean filter(Vehicle vehicle) {
        double totalPrice = vehicle.calculateTotalPrice();
        return totalPrice >= minPrice && totalPrice <= maxPrice;
    }
}
