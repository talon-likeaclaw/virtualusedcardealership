/**
 * Manages the Dealership
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/4/2024
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
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Getter for dealership's name.
   * 
   * @return String representing dealership's name.
   */
  public String getName() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Getter for dealership account balance.
   * 
   * @return double representing dealership financial account balance.
   */
  public double getBalance() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Method for adding or removing money from the dealership's account balance.
   * 
   * @param balanceChange the amount that the account balance will change (negative or postive).
   * @return void
   */
  public void updateAccountBalance(double balanceChange) {
    throw new UnsupportedOperationException("Not written yet");
  }
}
