/**
 * Represents a Customer who buys from Dealership
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/6/2024
 */
package usedcardealership;

import java.util.List;

public class Buyer extends Customer{
    
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
    }
    /** 
     * Gives customer a test drive of the vehicle they wish to buy
     * 
     * @return void
    */
    public void requestTestDrive(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
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
    public void makeOffer(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /** 
     * buys a vehicle
     * 
     * @return void
     */
    public void buyVehicle(Vehicle vehicle){
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
