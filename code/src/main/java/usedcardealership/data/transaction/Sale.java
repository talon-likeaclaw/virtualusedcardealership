/**
 * Manages Dealership sales (removing Vehicles from the database)
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/6/2024
 */
package usedcardealership.data.transaction;

import java.time.*;
import usedcardealership.data.customer.*;
import usedcardealership.data.vehicle.*;

public class Sale extends Transaction{
    
    /**
     * @param id        the unique identifier for the sale
     * @param date      the date of the sale
     * @param price     the sale price of the vehicle
     * @param customer  the Customer purchasing the vehicle
     * @param vehicle   the Vehicle being sold
     * 
     * Constructor: uses parent constructor
    */
    public Sale(int id, LocalDate date, double price, Customer customer, Vehicle vehicle){
        super(id, "Sale", date, price, customer, vehicle);
    }
}
