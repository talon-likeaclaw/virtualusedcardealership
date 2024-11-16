/**
 * Filter for Vehicle year range.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

import java.util.*;

public class VehicleYearRangeFilter extends VehicleFilter {

  /**
   * Filters the provided list of Vehicle by specified year range
   * 
   * @param vehicles - the list of Vehicle objects to filter
   * @param param    - the year range to filter by in array form
   * @return a list of Vehicle objects with the matching year range
   */
  @Override
  public List<Vehicle> filter(List<Vehicle> vehicles, Object param) {
    int[] yearRange = (int[]) param; // Expecting [minYear, maxYear]
    double minYear = yearRange[0];
    double maxYear = yearRange[1];
    List<Vehicle> inYearRange = new ArrayList<>();
    for (Vehicle v : vehicles) {
      if (v.getYear() >= minYear && v.getYear() <= maxYear) {
        inYearRange.add(v);
      }
    }
    return inYearRange;
  }
}
