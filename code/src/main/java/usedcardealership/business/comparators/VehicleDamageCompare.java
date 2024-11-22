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
 * @return int: positive if vehicle1's damage is greater or equals, negative if it's smaller
 */
public class VehicleDamageCompare extends VehicleCompare{
    @Override
    public int compare(Vehicle vehicle1, Vehicle vehicle2){
            if(vehicle1.getDamage() >= vehicle2.getDamage()){
                return 1;
            }
            return -1;
    }
}
