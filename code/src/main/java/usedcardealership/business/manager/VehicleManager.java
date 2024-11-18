/**
 * Manages all Vehicles, inventory and database.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/10/2024
 */

package usedcardealership.business.manager;

import java.util.*;

import usedcardealership.business.filter.*;
import usedcardealership.data.vehicle.*;

public class VehicleManager {
  private List<Vehicle> inventory;
  private List<Vehicle> database;

  /**
   * VehicleManager Constructor
   * Initializes the dealership's inventory and database of vehicles.
   * 
   * @param inventory list of vehicles dealership currently has in inventory.
   * @param database  list of vehicles dealership has seen before.
   */
  public VehicleManager(List<Vehicle> inventory, List<Vehicle> database) {
    this.inventory = inventory;
    this.database = database;
  }

  public List<Vehicle> getInventory() {
    return this.inventory;
  }

  public List<Vehicle> getDatabase() {
    return this.database;
  }

  /**
   * Prints each vehicle in the specified list in short form.
   * 
   * @param vehicleList the list of vehicles to print.
   */
  public void printVehiclesShort(List<Vehicle> vehicleList) {
    for (Vehicle v : vehicleList) {
      System.out.println(v.getImportantDetails());
    }
  }

  /**
   * Prints each vehicle in the specified list in short form.
   * 
   * @param vehicleList the list of vehicles to print.
   */
  public void printVehiclesFull(List<Vehicle> vehicleList) {
    for (Vehicle v : vehicleList) {
      System.out.println(v);
    }
  }

  /**
   * Method for adding a vehicle to the dealership's inventory or database.
   * Only adds if it doesn't already exitst.
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
   * Method that searches for vehicles in the inventory based on criteria.
   * 
   * @param criteria a IFilter sub-type object to determine filtering criteria.
   * @return List<Vehicle> list of filtered vehicles based on criteria input.
   */
  public List<Vehicle> searchInventory(IFilter<Vehicle> criteria) {
    return searchList(criteria, this.inventory);
  }

  /**
   * Method that searches for a vehicles in the database based on criteria.
   * 
   * @param criteria a IFilter sub-type object
   * @return List<Vehicle> list of filtered vehicles based on criteria input.
   */
  public List<Vehicle> searchDatabase(IFilter<Vehicle> criteria) {
    return searchList(criteria, this.database);
  }

  /**
   * Helper method for searching within a list for specific criteria
   * 
   * @param v
   */
  public List<Vehicle> searchList(IFilter<Vehicle> criteria, List<Vehicle> list) {
    List<Vehicle> result = new ArrayList<>();
    for (Vehicle v : list) {
      if (criteria.filter(v)) {
        result.add(v);
      }
    }
    return result;
  }

  /**
   * Method that allows us to update a vehicles mutable fields in Lists
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
   * @param updatedVehicle the vehicle with updated data.
   */
  private void updateVehicleInList(List<Vehicle> vehicleList, Vehicle updatedVehicle) {
    for (int i = 0; i < vehicleList.size(); i++) {
      if (vehicleList.get(i).equals(updatedVehicle)) {
        // Check the type of updatedVehicle and call correct copy constructor
        if (updatedVehicle instanceof Car) {
          vehicleList.set(i, new Car((Car) updatedVehicle));
        } else if (updatedVehicle instanceof SUV) {
          vehicleList.set(i, new SUV((SUV) updatedVehicle));
        } else if (updatedVehicle instanceof Van) {
          vehicleList.set(i, new Van((Van) updatedVehicle));
        } else if (updatedVehicle instanceof PickupTruck) {
          vehicleList.set(i, new PickupTruck((PickupTruck) updatedVehicle));
        } else if (updatedVehicle instanceof Motorcycle) {
          vehicleList.set(i, new Motorcycle((Motorcycle) updatedVehicle));
        } else if (updatedVehicle instanceof RV) {
          vehicleList.set(i, new RV((RV) updatedVehicle));
        } else {
          throw new IllegalArgumentException("Unsupported vehicle type.");
        }
        break;
      }
    }
  }
}
