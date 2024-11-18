/**
 * Compare interface
 * 
 * @author Juan Badel Sebastian - 2338127
 * @version 11/18/2024
 */
package usedcardealership.comparators;
import usedcardealership.data.vehicle.Vehicle;

/**
 * Compares two Vehicles based on their Type.
 * 
 * @param vehicle1 vehicle that gets compared to
 * @param vehicle2 vehicle to compare with
 * @return int: positive if vehicle1's Type is lexicographically greator, 0 if both vehicles are equal, otherwise negative
 */
public class VehicleTypeCompare extends VehicleCompare{
    @Override
    public int compareTo(Vehicle vehicle1, Vehicle vehicle2){
        return vehicle1.getType().compareTo(vehicle2.getType());
    }
}
