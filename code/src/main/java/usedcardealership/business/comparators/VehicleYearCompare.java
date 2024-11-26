/**
 * Compare interface
 * 
 * @author Juan Badel Sebastian - 2338127
 * @version 11/18/2024
 */
package usedcardealership.business.comparators;

import usedcardealership.data.vehicle.Vehicle;

/**
 * Compares two Vehicles based on their damage.
 * 
 * @param vehicle1 vehicle that gets compared to
 * @param vehicle2 vehicle to compare with
 * @return int: positive if vehicle1's year is greater, negative if it's smaller, 0 if equal
 */
public class VehicleYearCompare extends VehicleCompare {
    @Override
    public int compare(Vehicle vehicle1, Vehicle vehicle2) {
        if (vehicle1 == null || vehicle2 == null) {
            throw new IllegalArgumentException("Vehicles cannot be null.");
        }
        return vehicle1.getYear() - vehicle2.getYear();
    }
}
