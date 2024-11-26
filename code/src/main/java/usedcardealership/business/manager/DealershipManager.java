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
import usedcardealership.data.coupons.*;
import usedcardealership.interaction.*;
import usedcardealership.business.filter.*;

import java.util.*;

public class DealershipManager {
  private String name;
  private double accountBalance;
  private TransactionManager transactionManager;
  private VehicleManager vehicleManager;
  private CustomerManager customerManager;
  private Customer currentCustomer;
  private ShoppingCart currentCart;
  private CouponManager couponManager;

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
      List<Vehicle> inventory, List<Vehicle> database, List<Customer> customers, List<Coupon> coupons) {
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
    this.couponManager = new CouponManager(coupons);
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

    public TransactionManager getTransactionManager() {
        return this.transactionManager;
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

  public ShoppingCart getCurrentCart() {
    return this.currentCart;
  }

  public CouponManager getCouponManager() {
    return this.couponManager;
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

    public Customer getCurrentCustomer() {
        return this.currentCustomer;
    }

    public void setCurrentCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        this.currentCustomer = customer;
    }

    /**
     * Applies the filter to the dealership inventory using searchInventory
     * Automatically sorts vehicle list by ID
     * 
     * @param filterType the method we are filtering by
     * @param criteria   the user input criteria to pass into the filter
     * @return a list of Vehicles that are filtered by user input
     */
    public List<Vehicle> applyFilter(String filterType, String criteria) {
        if (criteria == null || criteria.length() == 0) {
            PrettyUtils.printRed("Invalid filter criteria");
            return new ArrayList<>();
        }
        switch (filterType) {
            case "type":
                return this.vehicleManager
                        .sortVehiclesById(this.vehicleManager.searchInventory(new VehicleTypeFilter(criteria)));
            case "make":
                return this.vehicleManager
                        .sortVehiclesById(this.vehicleManager.searchInventory(new VehicleMakeFilter(criteria)));
            case "color":
                return this.vehicleManager
                        .sortVehiclesById(this.vehicleManager.searchInventory(new VehicleColorFilter(criteria)));
            case "drive":
                return this.vehicleManager
                        .sortVehiclesById(this.vehicleManager.searchInventory(new VehicleDriveFilter(criteria)));
            case "trans":
                return this.vehicleManager
                        .sortVehiclesById(this.vehicleManager.searchInventory(new VehicleTransmissionFilter(criteria)));
            default:
                return new ArrayList<>();
        }
    }

    /**
     * Applies the range filter to the dealership inventory using searchInventory
     * Automatically sorts vehicle list by ID
     * 
     * @param filterType the method we are filtering by
     * @param min        the user input min range
     * @param max        the user input max range
     * @return a list of Vehicles that are filter by the crtieria
     */
    public List<Vehicle> applyRangeFilter(String filterType, String min, String max) {
        try {
            double minValue = Double.parseDouble(min);
            double maxValue = Double.parseDouble(max);
            switch (filterType) {
                case "year":
                    return this.vehicleManager.sortVehiclesById(this.vehicleManager
                            .searchInventory(new VehicleYearRangeFilter(Integer.parseInt(min), Integer.parseInt(max))));
                case "price":
                    return this.vehicleManager.sortVehiclesById(
                            this.vehicleManager.searchInventory(new VehiclePriceRangeFilter(minValue, maxValue)));
                case "kilo":
                    return this.vehicleManager.sortVehiclesById(this.vehicleManager
                            .searchInventory(new VehicleKilometerageRangeFilter(minValue, maxValue)));
                default:
                    return new ArrayList<>();
            }
        } catch (NumberFormatException e) {
            PrettyUtils.printRed("Invalid range values. Please enter numeric values.");
            return new ArrayList<>();
        }
    }

    /**
     * Processes a customer's vehicle sale to the dealership.
     * Handles the transaction, adds the vehicle to the dealership inventory,
     * and removes the vehicle from the customer's owned vehicles.
     * 
     * @param vehicle         vehicle being sold by the customer
     * @param customer        customer selling the vehicle
     * @param transactionType type of transaction
     */
    public void processCustomerVehicleTransaction(Vehicle vehicle, Customer customer, String transactionType) {
        if (vehicle == null || customer == null || transactionType == null || transactionType.length() == 0) {
            throw new IllegalArgumentException("Vehicle, customer, or transaction type cannot be null");
        }
        if (!customer.getVehicles().contains(vehicle) && transactionType == "purchase") {
            throw new IllegalArgumentException("Customer does not own the vehicle being sold.");
        }
        this.getTransactionManager().handleTransaction(vehicle, customer, transactionType);
        updateCustomerAndInventory(vehicle, customer, transactionType);
    }

    public void updateCustomerAndInventory(Vehicle vehicle, Customer customer, String transactionType) {
        if (transactionType == "purchase") {
            this.getVehicleManager().addVehicle(vehicle);
        } else if (transactionType == "sale") {
            this.getVehicleManager().removeVehicle(vehicle);
        }
    }

    /**
     * Initializes the current customer for the dealership by randomly selecting a
     * customer
     * from the provided list of customers.
     * 
     * @param customers  the list of customers available
     * @param dealership the dealership manager object to set the current customer
     */
    public void initializeCurrentCustomer(List<Customer> customers, DealershipManager dealership) {
        if (customers == null || customers.size() == 0) {
            PrettyUtils.printRed("No customers found to initialize.");
            return;
        }
        Random rand = new Random();
        dealership.setCurrentCustomer(customers.get(rand.nextInt(customers.size())));
    }

    /**
     * Manages the vehicle transaction process, offering a vehicle to the customer,
     * confirming the offer, and processing the sale if accepted.
     *
     * @param dealer    the DealershipManager responsible for managing the
     *                  dealership operations
     * @param vehicleID the ID of the vehicle being offered for sale
     */
    public void manageVehicleSale(DealershipManager dealer, int vehicleID) {
        Vehicle vehicle = dealer.getCurrentCustomer().getVehicleById(vehicleID);
        Customer customer = dealer.getCurrentCustomer();

        if (vehicle == null || customer == null) {
            PrettyUtils.printRed("Error: Vehicle or Customer not found!");
            return;
        }
        System.out.println("\nThe dealership offers you "
                + PrettyUtils.returnYellow("$" + String.format("%.2f", vehicle.calculateTotalPrice()))
                + " for the vehicle.");

        System.out.println("\nDo you accept this offer? " + PrettyUtils.returnYellow("(Y/N)") + ".");
        boolean confirmed = Prompter.promptYesNo();
        PrettyUtils.wipe();
        if (confirmed) {
            dealer.processCustomerVehicleTransaction(vehicle, customer, "purchase");
            List<Transaction> transactions = dealer.getTransactionManager().getTransactions();

            PrettyUtils.printGreen("Sale successful!");
            System.out.println("Updated Account Balance: "
                    + PrettyUtils.returnYellow("$" + String.format("%.2f", customer.getAccountBalance())));
            System.out.println(
                    "\n" + PrettyUtils.returnYellow("Receipt:") + "\n" + transactions.get(transactions.size() - 1));
        } else {
            PrettyUtils.printRed("Sale cancelled.");
        }
    }
}
