/**
 * Filter for Vehicle type field.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

import java.util.*;

public class VehicleTypeFilter extends VehicleFilter {

  /**
   * Filters the provided list of Vehicle by specified type.
   * 
   * @param vehicles - the list of Vehicle objects to filter
   * @param param    - the type to filter by
   * @return a list of Vehicle objects with the matching type field
   */
  @Override
  public List<Vehicle> filter(List<Vehicle> vehicles, Object param) {
    String type = (String) param;
    List<Vehicle> filteredTypes = new ArrayList<>();
    for (Vehicle v : vehicles) {
      if (type != null && v.getType().equals(type)) {
        filteredTypes.add(v);
      }
    }
    return filteredTypes;
  }
}