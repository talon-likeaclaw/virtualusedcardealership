package usedcardealership;
import java.util.*;

public class VehicleMakeFilter extends VehicleFilter {
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
