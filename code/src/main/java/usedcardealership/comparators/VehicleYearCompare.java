/**
 * Compare interface
 * 
 * @author Juan Badel Sebastian - 2338127
 * @version 11/18/2024
 */
package usedcardealership.comparators;
import usedcardealership.data.vehicle.Vehicle;

/**
 * Compares two Vehicles based on their damage.
 * 
 * @param vehicle1 vehicle that gets compared to
 * @param vehicle2 vehicle to compare with
 * @return int: positive if vehicle1's year is greater or equals, negative if it's smaller
 */
public class VehicleYearCompare extends VehicleCompare{
    @Override
    public int compareTo(Vehicle vehicle1, Vehicle vehicle2){
        return vehicle1.getYear() - vehicle2.getYear();
    }
}
