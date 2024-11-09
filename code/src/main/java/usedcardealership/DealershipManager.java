/**
 * Manages the Dealership
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/9/2024
 */

package usedcardealership;
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
   * @param name dealership's name.
   * @param accountBalance dealership's finacial account balanace.
   */
  public DealershipManager(String name, double accountBalance) {
    this.name = name;
    this.accountBalance = accountBalance;

    // TODO: Need to implement data handling strategy
    List<Transaction> transactions = loadTransactions(dataHandler);
    List<Vehicle> vehicleInventory = loadVehicles(dataHandler);
    List<Vehicle> vehicleDatabase = loadVehicles(dataHandler);
    List<Customer> customers = loadCustomers(dataHandler);


    this.transactionManager = new TransactionManager(transactions);
    this.vehicleManager = new VehicleManager(vehicleInventory, vehicleDatabase);
    this.customerManager = new CustomerManager(customers);
  }

  /**
   * Getter for dealership's name.
   * 
   * @return String representing dealership's name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Getter for dealership account balance.
   * 
   * @return double representing dealership financial account balance.
   */
  public double getBalance() {
    return this.accountBalance;
  }

  /**
   * Method for adding or removing money from the dealership's account balance.
   * 
   * @param balanceChange the amount that the account balance will change (negative or postive).
   * @return void
   */
  public void updateAccountBalance(double balanceChange) {
    this.accountBalance += balanceChange;
  }

}
