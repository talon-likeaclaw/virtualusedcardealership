/**
 * Shopping Cart, holds Vehicles customer wishes to buy;
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/6/2024
 */
package usedcardealership.data.customer;

import java.util.*;
import usedcardealership.data.vehicle.*;

public class ShoppingCart {
    private List<Vehicle> productsList;

    public ShoppingCart(){
        this.productsList = new ArrayList<Vehicle>();
    }
    /**
     * Adds a vehicle taken as input to productsList
     * 
     * @param vehicle
     * @return void
     */
    public List<Vehicle> getProductsList(){
        return this.productsList;
    }
    //Only way i see we could make a deep copy
    /**
     *public void addVehicle(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            this.productsList.add(new Car((Car) vehicle));
        } else if (vehicle instanceof Truck) {
            this.productsList.add(new Truck((Truck) vehicle));
        } else if (vehicle instanceof Motorcycle) {
            this.productsList.add(new Motorcycle((Motorcycle) vehicle));
        } .......
    }

     * @param vehicle
     */
    public void addVehicle(Vehicle vehicle){
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        this.productsList.add(vehicle);
    }
    /**
     * Removes vehicle from the productsList at selected index
     * 
     * @return void
     */
    public void removeVehicle(int index){
        if (index < 0 || index >= productsList.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        this.productsList.remove(index);
    }

    /**
     * Removes a vehicle from the cart based on its ID.
     * 
     * @param vehicleId the ID of the vehicle to remove.
     * @return true if the vehicle was found and removed, false if no vehicle with the given ID was found.
     */
    public boolean removeVehicleById(int vehicleId) {
        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getID() == vehicleId) {
                productsList.remove(i);
                return true; // Successfully removed the vehicle
            }
        }
        return false; // Vehicle not found
    }

    /**
     * Checks if a vehicle is already in the shopping cart based on its ID.
     * 
     * @param vehicle the vehicle to check
     * @return true if the vehicle is already in the cart, false otherwise
     */
    public boolean isVehicleInCart(Vehicle vehicle) {
        for (Vehicle v : productsList) {
            if (v.getID() == vehicle.getID()) {
                return true;  // Vehicle is already in the cart
            }
        }
        return false;  // Vehicle is not in the cart
    }

    
    /**
     * Removes every element from the productsList
     * 
     * @return void
     */
    public void emptyCart(){
        this.productsList.clear();
    }
    
    @Override
    public String toString() {
        if (productsList.isEmpty()) {
            return "Your shopping cart is empty.";
        }

        StringBuilder cartContents = new StringBuilder("Shopping Cart:\n");
        for (int i = 0; i < productsList.size(); i++) {
            cartContents.append(i + 1).append(". ").append(productsList.get(i).toString()).append("\n");
        }
        return cartContents.toString();
    }
}
