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
    public Customer(int id, String firstName, String lastName, String birthday, String phoneNumber, String address, double accountBalance, List<Vehicle> vehicles){
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
    public String toString(){
        return "Customer ID: " + id + "\n" +
               "Name: " + firstName + " " + lastName + "\n" +
               "Birthday: " + birthday + "\n" +
               "Phone: " + phoneNumber + "\n" +
               "Address: " + address + "\n" +
               "Account Balance: $" + accountBalance;
    }
    
    public int getID(){
        return this.id;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getBirthday(){
        return this.birthday;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getAddress(){
        return this.address;
    }

    public double getAccountBalance(){
        return this.accountBalance;
    }

    public List<Vehicle> getVehicles(){
        return this.vehicles;
    }

    /**
     * Updates the customers balance after making a transaction
     * 
     * @param amount (positive or negative)
     * @return void
     */
    public void updateAccountBalance(double amount){
        this.accountBalance += amount;
    }

    public void setPhoneNumber(String number){
        this.phoneNumber = number;
    }

    public void setAddress(String address){
        this.address = address;
    }

     /** 
     * Overrides toString method
     * 
     * @return String
     */
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Customer)){
            return false;
        }
        Customer c = (Customer) o;
        return (this.id == c.id);
    }
}
