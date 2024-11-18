/**
 * Manages all Transactions
 * 
 * author Juan Sebastian Badel - 2338127
 * version 11/6/2024
 */
package usedcardealership;

public class Transaction {
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
    public Transaction(){};
    public Transaction(int id, String type, String date, double price, double tax, Customer customer, Vehicle vehicle) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.price = price;
        this.tax = 1.15;
        this.customer = customer;
        this.vehicle = vehicle;
    }

    public int getID() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String getDate() {
        return this.date;
    }

    public double getPrice() {
        return this.price;
    }

    public double getTax() {
        return this.tax;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    //Adding necessary setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /** 
     * Calculates the total (negative or positive) of the transaction 
     * car value - depreciation + tax
     * 
     * @return double
     */
    // public double calculateTotal() {
    //     double depreciation = this.getVehicle().calculateDepreciation();
    //     return (this.getPrice() - depreciation) * this.getTax();
    // }
}
