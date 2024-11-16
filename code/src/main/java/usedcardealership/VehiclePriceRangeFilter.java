/**
 * Filter for Vehicle price range.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

import java.util.*;

public class VehiclePriceRangeFilter extends VehicleFilter {

  /**
   * Filters the provided list of Vehicle by specified price range
   * 
   * @param vehicles - the list of Vehicle objects to filter
   * @param param    - the price range to filter by in array form
   * @return a list of Vehicle objects with the matching price range
   */
  @Override
  public List<Vehicle> filter(List<Vehicle> vehicles, Object param) {
    double[] priceRange = (double[]) param; // Expecting [minPrice, maxPrice]
    double minPrice = priceRange[0];
    double maxPrice = priceRange[1];
    List<Vehicle> inPriceRange = new ArrayList<>();
    for (Vehicle v : vehicles) {
      if (v.calculateTotalPrice() >= minPrice && v.calculateTotalPrice() <= maxPrice) {
        inPriceRange.add(v);
      }
    }
    return inPriceRange;
  }
}
