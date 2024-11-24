/**
 * Manages all Transactions
 * 
 * author Juan Sebastian Badel - 2338127
 * version 11/6/2024
 */
package usedcardealership.data.transaction;

import usedcardealership.data.customer.*;
import usedcardealership.data.vehicle.*;
import java.time.*;

public class Transaction {
    private int id;
    private String type;
    private LocalDate date;
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
     */
    public Transaction(int id, String type, LocalDate localDate, double price, Customer customer, Vehicle vehicle) {
        if (id <= 0) {
            throw new IllegalArgumentException("Transaction ID must be a positive integer.");
        }
        if (type == null || type.length() == 0) {
            throw new IllegalArgumentException("Transaction type cannot be null or empty.");
        }
        if (localDate == null) {
            throw new IllegalArgumentException("Transaction date cannot be null.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Transaction price cannot be negative.");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        this.id = id;
        this.type = type;
        this.date = localDate;
        this.price = price;
        this.tax = 1.15;
        this.customer = customer;
        this.vehicle = vehicle;
    }

    public Transaction() {
    }

    public int getID() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public LocalDate getDate() {
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

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        this.date = date;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    public String toString() {
        return "******************************\nTransaction ID: " + this.id + "\nType: " + this.type + "\nDate: "
                + this.date + "\nPrice: " + this.price + "\n\nCustomer:\n" + this.customer + "\n\nVehicle:"
                + this.vehicle + "\n******************************";
    }

    /**
     * Calculates the total (negative or positive) of the transaction
     * car value - depreciation + tax
     * 
     * @return double
     */
    public double calculateTotal() {
        return this.getVehicle().calculateTotalPrice() * this.getTax();
    }
}
