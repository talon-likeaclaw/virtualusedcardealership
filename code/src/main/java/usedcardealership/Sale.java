/**
 * Manages Dealership sales (removing Vehicles from the database)
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/6/2024
 */
package usedcardealership;

public class Sale {
    
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
    public Sale(int id, String type, String date, double price, double tax, Customer customer, Vehicle vehicle){
        super(id, type, date, price, tax, customer, vehicle);
    }
    /** 
     * Gives user a test drive of the vehicle they wish to buy
     * 
     * @return void
    */
    public void requestTestDrive(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /** 
     * Gets user's trade in value if they exchange pieces/vehicle for a vehicle
     * 
     * @return double
     */
    public double getTradeInValue(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /** 
     * Makes user a trade offer
     * 
     * @return void
     */
    public void makeOffer(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /** 
     * Dealership sells vehicle
     * 
     * @return void
     */
    public void sellVehicle(Vehicle vehicle){
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
