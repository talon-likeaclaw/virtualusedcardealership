/**
 * Compare abstract class
 * 
 * @author Juan Badel Sebastian - 2338127
 * @version 11/18/2024
 */
package usedcardealership.business.comparators;

import usedcardealership.data.vehicle.*;

/**
 * Compares two Vehicles, used for sorting.
 * 
 * @param vehicle1 vehicle that gets compared to
 * @param vehicle2 vehicle to compare with
 * @return int
 */
public abstract class VehicleCompare implements ICompare<Vehicle> {

    @Override
    public abstract int compare(Vehicle vehicle1, Vehicle vehicle2);
}
