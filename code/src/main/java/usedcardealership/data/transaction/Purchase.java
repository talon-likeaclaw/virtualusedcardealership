/**
 * Manages Dealership purchases (adding Vehicles to the database)
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/6/2024
 */
package usedcardealership.data.transaction;

import java.time.LocalDate;

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
    public Purchase(int id, LocalDate date, double price, Customer customer, Vehicle vehicle){
        super(id, "Dealership Purchase", date, price, customer, vehicle);
    }
}
