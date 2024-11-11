/**
 * Represents a Customer
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/6/2024
 */
package usedcardealership;

import java.util.List;

public abstract class Customer {
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
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /** 
     * Overrides toString method
     * 
     * @return String
     */
    public String toString(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public int getID(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public String getFirstName(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public String getLastName(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public String getBirthday(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public String getPhoneNumber(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public String getAddress(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public double getAccountBalance(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public List<Vehicle> getVehicles(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /**
     * Updates the customers balance after making a transaction
     * 
     * @param fee
     * @return void
     */
    public void updateAccountBalance(double amount){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void setPhoneNumber(String number){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void setAddress(String address){
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
