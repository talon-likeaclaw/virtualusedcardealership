/**
 * Manages the Dealership
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/19/2024
 */

package usedcardealership.business.manager;

import usedcardealership.data.customer.*;
import usedcardealership.data.transaction.*;
import usedcardealership.data.vehicle.*;

import java.util.*;

public class DealershipManager {
  private String name;
  private double accountBalance;
  private TransactionManager transactionManager;
  private VehicleManager vehicleManager;
  private CustomerManager customerManager;
  private Customer currentCustomer;
  private ShoppingCart currentCart;

  /**
   * DealershipManager Constructor
   * Initializes the dealership's name and account balance.
   * 
   * @param name           dealership's name.
   * @param accountBalance dealership's financial account balanace.
   * @param transactions   list of transactions for TransactionManager.
   * @param inventory      list of vehicles in dealership's inventory.
   * @param database       list of vehicles in dealership's database.
   * @param customers      list of customers for CustomerManager.
   */
  public DealershipManager(String name, double accountBalance, List<Transaction> transactions,
      List<Vehicle> inventory, List<Vehicle> database, List<Customer> customers) {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("Dealership name cannot be null or empty.");
    }
    if (accountBalance < 0) {
      throw new IllegalArgumentException("Account balance cannot be negative.");
    }
    if (transactions == null) {
      throw new IllegalArgumentException("Transactions name cannot be null or empty.");
    }
    if (inventory == null) {
      throw new IllegalArgumentException("Inventory list cannot be null.");
    }
    if (database == null) {
      throw new IllegalArgumentException("Database list cannot be null.");
    }
    if (customers == null) {
      throw new IllegalArgumentException("Customers list cannot be null.");
    }
    this.name = name;
    this.accountBalance = accountBalance;
    this.transactionManager = new TransactionManager(transactions);
    this.vehicleManager = new VehicleManager(inventory, database);
    this.customerManager = new CustomerManager(customers);
    this.currentCart = new ShoppingCart();
  }

  public String getName() {
    return this.name;
  }

  public double getBalance() {
    return this.accountBalance;
  }

  public List<Customer> getCustomers() {
    return this.customerManager.getCustomers();
  }

  public List<Transaction> getTransactions() {
    return this.transactionManager.getTransactions();
  }

  public List<Vehicle> getInventory() {
    return this.vehicleManager.getInventory();
  }

  public List<Vehicle> getDatabase() {
    return this.vehicleManager.getDatabase();
  }

  public VehicleManager getVehicleManager() {
    return this.vehicleManager;
  }

  public Vehicle getVehicleById(int vehicleId) {
    if (vehicleId <= 0) {
      throw new IllegalArgumentException("Vehicle ID must be a positive integer.");
    }
    for (Vehicle v : this.getInventory()) {
      if (v.getID() == vehicleId) {
        return v;
      }
    }
    return null;
  }

  /**
   * Method for adding or removing money from the dealership's account balance.
   * No validation necessary as the dealership can go into debt (for fun).
   * 
   * @param balanceChange the amount that the account balance will change
   *                      (negative or postive).
   */
  public void updateAccountBalance(double balanceChange) {
    this.accountBalance += balanceChange;
  }


  public Customer getCurrentCustomer(){
    return this.currentCustomer;
  }
  public void setCurrentCustomer(Customer customer) {
    if (customer == null) {
      throw new IllegalArgumentException("Customer cannot be null.");
    }
    this.currentCustomer = customer;
  }

}
