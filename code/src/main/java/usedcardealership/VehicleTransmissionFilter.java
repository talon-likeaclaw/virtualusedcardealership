/**
 * Filter for Vehicle transmission field.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

import java.util.*;

public class VehicleTransmissionFilter extends VehicleFilter {

  /**
   * Filters the provided list of Vehicle by specified transmission
   * 
   * @param vehicles - the list of Vehicle objects to filter
   * @param param    - the transmission to filter by
   * @return a list of Vehicle objects with the matching transmission field
   */
  @Override
  public List<Vehicle> filter(List<Vehicle> vehicles, Object param) {
    String transmission = (String) param;
    List<Vehicle> filteredTransmissions = new ArrayList<>();
    for (Vehicle v : vehicles) {
      if (transmission != null && v.getTransmission().equals(transmission)) {
        filteredTransmissions.add(v);
      }
    }
    return filteredTransmissions;
  }
}