/**
 * Filter for Vehicle drive type field.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

import java.util.*;

public class VehicleDriveFilter extends VehicleFilter {

  /**
   * Filters the provided list of Vehicle by specified drive type
   * 
   * @param vehicles - the list of Vehicle objects to filter
   * @param param    - the drive type to filter by
   * @return a list of Vehicle objects with the matching drive type field
   */
  @Override
  public List<Vehicle> filter(List<Vehicle> vehicles, Object param) {
    String driveType = (String) param;
    List<Vehicle> filteredDriveTypes = new ArrayList<>();
    for (Vehicle v : vehicles) {
      if (driveType != null && v.getDriveType().equals(driveType)) {
        filteredDriveTypes.add(v);
      }
    }
    return filteredDriveTypes;
  }
}