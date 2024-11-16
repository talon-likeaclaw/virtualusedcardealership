/**
 * Vehicle filter abstract class
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

public abstract class VehicleFilter implements IFilter<Vehicle> {

    /**
     * Abstract method check a Vehicle object matches filter criteria
     * 
     * @param vehicle - the Vehicle object to check
     * @return true if the Vehicle matches the filter criteria, false otherwise
     */
    @Override
    public abstract boolean filter(Vehicle vehicle);
}