/**
 * Represents a Customer who buys from Dealership
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/6/2024
 */
package usedcardealership.data.customer;

import java.util.*;
import usedcardealership.data.vehicle.*;

public class Buyer extends Customer{
    private ShoppingCart cart;
    /**
     * @param id
     * @param firstName
     * @param firstName
     * @param birthday
     * @param phoneNumber
     * @param address
     * @param accountBalance
     * @param vehicles
     * 
     * Initialize all fields using parameters
     */
    public Buyer(int id, String firstName, String lastName, String birthday, String phoneNumber, String address, double accountBalance, List<Vehicle> vehicles){
        super(id, firstName, lastName, birthday, phoneNumber, address, accountBalance, vehicles);
        this.cart = new ShoppingCart();
    }
    /** 
     * Gives customer a test drive of the vehicle they wish to buy
     * 
     * @return void
    */
    // public void requestTestDrive(int index){
    //     this.vehicles.get(index).requestTestDrive();
    // }
    /** 
     * Gets a trade in value Buyer exchanges pieces/vehicle for a vehicle
     * 
     * @return double
     */
    public double getTradeInValue(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /** 
     * Makes Dealership a trade offer
     * 
     * @return void
     */
    public double makeOffer(double offer){
        return offer;
    }
    /** 
     * adds a vehicle to cart
     * 
     * @return void
     */
    public void addVehicleToCart(Vehicle vehicle){
        this.cart.addVehicle(vehicle);
    }
        /** 
     * adds a vehicle to cart
     * 
     * @return void
     */
    public void removeVehicleFromCart(int index){
        this.cart.removeVehicle(index);
    }
    /** 
     * buys a vehicle
     * 
     * @return void
     */
    public void buyVehiclesInCart(){
        //...
    }
}
