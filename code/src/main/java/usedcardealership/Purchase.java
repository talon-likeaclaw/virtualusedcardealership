/**
 * Manages Dealership purchases (adding Vehicles to the database)
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/6/2024
 */
package usedcardealership;

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
        super(id, type, date, price, tax, customer, vehicle);
    }
    /**
     * Initializes the transaction
     * 
     * @return void
     */
    public void initializePurchase(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /**
     * Verifies the purchase
     * 
     * @return void
     */
    public void verifyPurchase(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
