/**
 * Manages all Vehicles, inventory and database.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/4/2024
 */

package usedcardealership;
import java.util.*;

public class VehicleManager {
  private List<Vehicle> inventory;
  private List<Vehicle> database;


  /**
   * VehicleManager Constructor
   * Initializes the dealership's inventory and database of vehicles.
   * 
   * @param inventory list of vehicles dealership currently has in inventory.
   * @param database list of vehicles dealership has seen before.
   */
  public VehicleManager(List<Vehicle> inventory, List<Vehicle> database) {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Method for adding a vehicle to the dealership's inventory or database.
   * 
   * @param v the vehicle that is being added.
   * @return void
   */
  public void addVehicle(Vehicle v) {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Method that removes a vehicle from the dealership's inventory.
   * 
   * @param v the vehcile that is being removed.
   * @return void
   */
  public void removeVehicle(Vehicle v) {
    throw new UnsupportedOperationException("Not written yet");
  }

  /** 
   * Method that searches for a vehicle in the inventory or database.
   * 
   * @param v vehicle that we are searching for.
   * @return void
   */
  public void searchVehicle(Vehicle v) {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Method that allows us to update a vehicles mutable fields.
   * 
   * @param v vehicle that we are updating the mutable fields of.
   * @return void
   */
  public void updateVehicle(Vehicle v) {
    throw new UnsupportedOperationException("Not written yet");
  }
}
