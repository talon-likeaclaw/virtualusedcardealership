/**
 * Manages all Transactions
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/6/2024
 */
package usedcardealership;

public abstract class Transaction {
    private int id;
    private String type;
    private String date;
    private double price;
    private double tax;
    private Customer customer;
    private Vehicle vehicle;


    /**
     * @param id
     * @param type
     * @param date
     * @param price
     * @param tax
     * @param customer
     * @param vehicle
     * 
     * Sets all fields using parameters
     */
    public Transaction(int id, String type, String date, double price, double tax, Customer customer, Vehicle vehicle){
        throw new UnsupportedOperationException("Not written yet");
    }
    /**
     * @return int
     */
    public int getID() {
        throw new UnsupportedOperationException("Not written yet");
    }

    /**
     * @return String
     */
    public String getType() {
        throw new UnsupportedOperationException("Not written yet");
    }

    /**
     * @return String
     */
    public String getDate() {
        throw new UnsupportedOperationException("Not written yet");
    }

    
    /** 
     * @return double
     */
    public double getPrice() {
        throw new UnsupportedOperationException("Not written yet");
    }

    
    /** 
     * @return double
     */
    public double getTax() {
        throw new UnsupportedOperationException("Not written yet");
    }

    
    /** 
     * @return Customer
     */
    public Customer getCustomer() {
        throw new UnsupportedOperationException("Not written yet");
    }

    
    /** 
     * @return Vehicle
     */
    public Vehicle getVehicle() {
        throw new UnsupportedOperationException("Not written yet");
    }

    
    /** 
     * @return double
     */
    public double calculateTotal() {
        throw new UnsupportedOperationException("Not written yet");
    }
}
