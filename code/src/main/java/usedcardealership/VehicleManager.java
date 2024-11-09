/**
 * Manages all Vehicles, inventory and database.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/9/2024
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
    this.inventory = inventory;
    this.database = database;
  }

  /**
   * Method for adding a vehicle to the dealership's inventory or database.
   * 
   * @param v the vehicle that is being added.
   * @return void
   */
  public void addVehicle(Vehicle v) {
    // TODO: Need to override Vehicle equals method
    if (!inventory.contains(v)) {
      inventory.add(v);
    }
    if (!database.contains(v)) {
      database.add(v);
    }
  }

  /**
   * Method that removes a vehicle from the dealership's inventory.
   * 
   * @param v the vehcile that is being removed.
   * @return void
   */
  public void removeVehicle(Vehicle v) {
    inventory.remove(v);
  }

  /** 
   * Method that searches for a vehicle in the inventory.
   * 
   * @param criteria a IFilter sub-type object to determine filtering criteria.
   * @return List<Vehicle> list of filtered vehicles based on criteria input.
   */
  public List<Vehicle> searchInventory(IFilter criteria) {
    List<Vehicle> result = new ArrayList<>();
    for (Vehicle v : inventory) {
      // TODO: Need to create IFilter Strategy with matches overrides for criteria
      if (criteria.matches(v))  {
        result.add(v);
      }
    }
    return result;
  }

  /** 
   * Method that searches for a vehicle in the database.
   * 
   * @param criteria a IFilter sub-type object
   * @return List<Vehicle> list of filtered vehicles based on criteria input.
   */
  public List<Vehicle> searchDatabase(IFilter criteria) {
    List<Vehicle> result = new ArrayList<>();
    for (Vehicle v : database) {
      // TODO: Need to create IFilter Strategy with matches overrides for criteria
      if (criteria.matches(v))  {
        result.add(v);
      }
    }
    return result;
  }

  /**
   * Method that allows us to update a vehicles mutable fields.
   * 
   * @param v vehicle with updated data.
   * @return void
   */
  public void updateVehicle(Vehicle v) {
    for (int i = 0; i < inventory.size(); i++) {
      if (inventory.get(i).equals(v)) {
        inventory.set(i, v);
        break;
      }
    }
    for (int i = 0; i < database.size(); i++) {
      if (database.get(i).equals(v)) {
        database.set(i, v);
        break;
      }
    }
  }
}
