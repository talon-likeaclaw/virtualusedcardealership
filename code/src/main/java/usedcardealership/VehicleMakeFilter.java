/**
 * Filter for Vehicle make field.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

import java.util.*;

public class VehicleMakeFilter extends VehicleFilter {

    /**
     * Filters the provided list of Vehicle by specified make
     * 
     * @param vehicles - the list of Vehicle objects to filter
     * @param param    - the make to filter by
     * @return a list of Vehicle objects with the matching make field
     */
    @Override
    public List<Vehicle> filter(List<Vehicle> vehicles, Object param) {
        String make = (String) param;
        List<Vehicle> filteredMakes = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (make != null && v.getMake().equals(make)) {
                filteredMakes.add(v);
            }
        }
        return filteredMakes;
    }
}
