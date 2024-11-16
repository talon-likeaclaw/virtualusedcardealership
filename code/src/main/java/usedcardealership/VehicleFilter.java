package usedcardealership;

import java.util.*;

public abstract class VehicleFilter implements IFilter<Vehicle, Object> {
  @Override
  public abstract List<Vehicle> filter(List<Vehicle> vehicles, Object param);
}