/**
 * Vehicle filter abstract class
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

import java.util.*;

public abstract class VehicleFilter implements IFilter<Vehicle, Object> {

    /**
     * Abstract method to filter a list of Vehicle objects based on parameter
     * 
     * @param vehicles - the list of Vehicle objects to filter
     * @param param    - the param to filter by
     * @return a list of Vehicle objects with the matching parameter
     */
    @Override
    public abstract List<Vehicle> filter(List<Vehicle> vehicles, Object param);
}