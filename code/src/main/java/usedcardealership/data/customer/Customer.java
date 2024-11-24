/**
 * Represents a Customer
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/6/2024
 */
package usedcardealership.data.customer;

import java.util.*;
import usedcardealership.data.vehicle.Vehicle;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String birthday;
    private String phoneNumber;
    private String address;
    private double accountBalance;
    private List<Vehicle> vehicles;

    /**
     * @param id
     * @param firstName
     * @param lastName
     * @param birthday
     * @param phoneNumber
     * @param address
     * @param accountBalance
     * @param vehicles
     * 
     * Initialize all fields using parameters
     */
    public Customer(int id, String firstName, String lastName, String birthday, String phoneNumber, String address,
            double accountBalance, List<Vehicle> vehicles) {
        if (id <= 0) {
            throw new IllegalArgumentException("Customer ID must be a positive integer.");
        }
        if (firstName == null || firstName.length() == 0) {
            throw new IllegalArgumentException("First name cannot be null or empty.");
        }
        if (lastName == null || lastName.length() == 0) {
            throw new IllegalArgumentException("Last name cannot be null or empty.");
        }
        if (birthday == null || birthday.length() == 0) {
            throw new IllegalArgumentException("Birthday cannot be null or empty.");
        }
        if (phoneNumber == null || phoneNumber.length() == 0) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }
        if (address == null || address.length() == 0) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        if (vehicles == null) {
            throw new IllegalArgumentException("Vehicle list cannot be null.");
        }
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.accountBalance = accountBalance;
        this.vehicles = vehicles;
    }

    public Customer(Customer c) {
        this(c.id,
                c.firstName,
                c.lastName,
                c.birthday,
                c.phoneNumber,
                c.address,
                c.accountBalance,
                c.vehicles);
    }

    /**
     * Overrides toString method
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Customer ID: " + id + "\n" +
                "Name: " + firstName + " " + lastName + "\n" +
                "Birthday: " + birthday + "\n" +
                "Phone: " + phoneNumber + "\n" +
                "Address: " + address + "\n" +
                "Account Balance: $" + accountBalance;
    }

    public int getID() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public double getAccountBalance() {
        return this.accountBalance;
    }

    public List<Vehicle> getVehicles() {
        return this.vehicles;
    }

    /**
     * Updates the customers balance after making a transaction
     * Account balance can go into negative for fun (debt)
     * 
     * @param amount (positive or negative)
     * @return void
     */
    public void updateAccountBalance(double amount) {
        this.accountBalance += amount;
    }

    public void setPhoneNumber(String number) {
        if (number == null || number.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }
        this.phoneNumber = number;
    }

    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        this.address = address;
    }

    /**
     * Overrides toString method
     * 
     * @return String
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer c = (Customer) o;
        return (this.id == c.id);
    }
}
