/**
 * Filter for Vehicle kilometerage range.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

import java.util.*;

public class VehicleKilometerageRangeFilter extends VehicleFilter {

  /**
   * Filters the provided list of Vehicle by specified kilometerage range
   * 
   * @param vehicles - the list of Vehicle objects to filter
   * @param param    - the kilometerage range to filter by in array form
   * @return a list of Vehicle objects with the matching kilometerage range
   */
  @Override
  public List<Vehicle> filter(List<Vehicle> vehicles, Object param) {
    double[] kilometerageRange = (double[]) param; // Expecting [minKilometerage, maxKilometerage]
    double minKilometerage = kilometerageRange[0];
    double maxKilometerage = kilometerageRange[1];
    List<Vehicle> inKilometerageRange = new ArrayList<>();
    for (Vehicle v : vehicles) {
      if (v.getKilometerage() >= minKilometerage && v.getKilometerage() <= maxKilometerage) {
        inKilometerageRange.add(v);
      }
    }
    return inKilometerageRange;
  }
}
