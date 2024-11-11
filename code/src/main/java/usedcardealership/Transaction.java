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
     * Initialize all fields using parameters
     */
    public Transaction(int id, String type, String date, double price, double tax, Customer customer, Vehicle vehicle){
        throw new UnsupportedOperationException("Not written yet");
    }
    public int getID() {
        throw new UnsupportedOperationException("Not written yet");
    }
    public String getType() {
        throw new UnsupportedOperationException("Not written yet");
    }
    public String getDate() {
        throw new UnsupportedOperationException("Not written yet");
    }
    public double getPrice() {
        throw new UnsupportedOperationException("Not written yet");
    }
    public double getTax() {
        throw new UnsupportedOperationException("Not written yet");
    }
    public Customer getCustomer() {
        throw new UnsupportedOperationException("Not written yet");
    }
    public Vehicle getVehicle() {
        throw new UnsupportedOperationException("Not written yet");
    }
    /** 
     * Calculates the total (negative or positive) of the transaction 
     * car value - depreciation + tax
     * 
     * @return double
     */
    public double calculateTotal() {
        throw new UnsupportedOperationException("Not written yet");
    }
}
