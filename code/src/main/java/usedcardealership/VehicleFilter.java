/**
 * Vehicle filter abstract class
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

public abstract class VehicleFilter implements IFilter<Vehicle> {

    /**
     * Abstract method to filter a list of Vehicle objects based on parameter
     * 
     * @param vehicle - the Vehicle object to check
     * @return true if the Vehicle mathces the filter criteria, false otherwise
     */
    @Override
    public abstract boolean filter(Vehicle vehicle);
}