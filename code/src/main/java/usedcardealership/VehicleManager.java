/**
 * Manages all Vehicles, inventory and database.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/10/2024
 */

package usedcardealership;
import usedcardealership.Vehicle;
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
   */
  public void addVehicle(Vehicle v) {
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
   * @param v the vehicle that is being removed.
   */
  public void removeVehicle(Vehicle v) {
    inventory.remove(v);
  }

  /** 
   * Method that searches for vehciles in the inventory based on specific criteria.
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
   * Method that searches for a vehicles in the database based on specific criteria.
   * 
   * @param criteria a IFilter sub-type object
   * @return List<Vehicle> list of filtered vehicles based on criteria input.
   */
  public List<Vehicle> searchDatabase(IFilter criteria) {
    List<Vehicle> result = new ArrayList<>();
    for (Vehicle v : database) {
      if (criteria.matches(v))  {
        result.add(v);
      }
    }
    return result;
  }

  /**
   * Method that allows us to update a vehicles mutable fields in both inventory and database.
   * 
   * @param v vehicle with updated data.
   */
  public void updateVehicle(Vehicle v) {
    updateVehicleInList(inventory, v);
    updateVehicleInList(database, v);
  }

  /**
   * Helper method that updates a vehicle's information within a given list.
   * 
   * @param vehicleList the list of vehicles.
   * @param updatedVehicle the vehicle with updated data.
   */
  private void updateVehicleInList(List<Vehicle> vehicleList, Vehicle updatedVehicle) {
    for (int i = 0; i < vehicleList.size(); i++) {
      if (vehicleList.get(i).equals(updatedVehicle)) {
        // TODO: Create copy constructor in Vehicle
        vehicleList.set(i, new Vehicle(updatedVehicle));
        break;
      }
    }
  }
}
