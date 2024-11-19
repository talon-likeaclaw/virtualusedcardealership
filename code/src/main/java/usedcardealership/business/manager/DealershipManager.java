/**
 * Manages the Dealership
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/10/2024
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
    this.name = name;
    this.accountBalance = accountBalance;

    // TODO: Need to implement data handling Strategy
    // Still not 100% about the implementation yet, estimating still until more clear
    this.transactionManager = new TransactionManager(transactions);
    this.vehicleManager = new VehicleManager(inventory, database);
    this.customerManager = new CustomerManager(customers);
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

  /**
   * Method for adding or removing money from the dealership's account balance.
   * 
   * @param balanceChange the amount that the account balance will change
   *                      (negative or postive).
   */
  public void updateAccountBalance(double balanceChange) {
    this.accountBalance += balanceChange;
  }
}
