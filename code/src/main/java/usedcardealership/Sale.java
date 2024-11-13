/**
 * Manages Dealership sales (removing Vehicles from the database)
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/6/2024
 */
package usedcardealership;

import java.time.*;

public class Sale extends Transaction{
    
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

    /* 
    * Applies a discount to the sale price.
    * 
    * @param discount the discount amount to apply to the sale price
    */
    public void applyDiscount(double discount) {
        if (discount >= this.getPrice()) {
            this.setPrice(0);
        } else {
            this.setPrice(this.getPrice() - discount);
        }
    }

   /**
    * Initializes the sale by performing necessary setup operations
    * such as updating sale status or preparing sale details.
    */

    public void initializeSale() {
        if (this.getDate() == null) {
            this.setDate(LocalDate.now().toString());  // Set current date as a string
        }
        //?
    }

   /**
    * Verifies the sale details to ensure all necessary information
    * and conditions are met before finalizing the sale.
    */
    public void verifySale() {
        if (this.getCustomer().getAccountBalance() < this.getVehicle().calculateTotalPrice()) {
            //check this later
            throw new RuntimeException("Can't afford, sorry");
        }
    }
}
