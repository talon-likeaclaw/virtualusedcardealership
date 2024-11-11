/**
 * Represents a customer who wishes to Sell to the Dealership
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/6/2024
 */
package usedcardealership;

import java.util.List;

public class Seller {
    public Seller(int id, String firstName, String lastName, String birthday, String phoneNumber, String address, double accountBalance, List<Vehicle> vehicles){
        super(id, firstName, lastName, birthday, phoneNumber, address, accountBalance, vehicles);
    }
    /**
     * Customer offers sale Vehicle to Dealership
     * 
     * @param vehicle
     * @return void
     */
    public void sellVehicle(Vehicle vehicle){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /**
     * Customer negociates a Price
     * 
     * @return void
    */
    public void negotiatePrice(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /**
     * Completes sale
     * 
     * @return void
    */
    public void finalizeSale(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
