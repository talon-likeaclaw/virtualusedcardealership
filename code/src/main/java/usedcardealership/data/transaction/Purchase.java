/**
 * Manages Dealership purchases (adding Vehicles to the database)
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/6/2024
 */
package usedcardealership.data.transaction;

import usedcardealership.data.customer.*;
import usedcardealership.data.vehicle.*;

public class Purchase extends Transaction{
    /**
     * @param id
     * @param type
     * @param date
     * @param price
     * @param tax
     * @param customer
     * @param vehicle
     * 
     * Constructor: uses parent constructor
     */
    public Purchase(int id, String type, String date, double price, double tax, Customer customer, Vehicle vehicle){
        super(id, "Purchase", date, price, tax, customer, vehicle);
    }
    public Purchase(){}
    /**
     * Initializes the transaction
     * 
     * @return void
     */
    public void initializePurchase(){
        //?
    }
    /**
     * Verifies the purchase
     * 
     * @return void
     */
    public void verifyPurchase(){
        //?
    }
}
