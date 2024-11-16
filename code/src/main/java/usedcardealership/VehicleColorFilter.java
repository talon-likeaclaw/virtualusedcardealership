/**
 * Filter for Vehicle color field.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

import java.util.*;

public class VehicleColorFilter extends VehicleFilter {

  /**
   * Filters the provided list of Vehicle by specified color
   * 
   * @param vehicles - the list of Vehicle objects to filter
   * @param param    - the color to filter by
   * @return a list of Vehicle objects with the matching color field
   */
  @Override
  public List<Vehicle> filter(List<Vehicle> vehicles, Object param) {
    String color = (String) param;
    List<Vehicle> filteredColors = new ArrayList<>();
    for (Vehicle v : vehicles) {
      if (color != null && v.getColor().equals(color)) {
        filteredColors.add(v);
      }
    }
    return filteredColors;
  }
}