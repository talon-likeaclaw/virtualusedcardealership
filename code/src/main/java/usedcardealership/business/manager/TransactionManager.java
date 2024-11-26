/**
 * Manages all Transactions, has a List<Transaction>
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/4/2024
 */
package usedcardealership.business.manager;

import java.util.*;
import java.time.*;
import usedcardealership.data.customer.*;
import usedcardealership.data.transaction.*;
import usedcardealership.data.vehicle.*;
import usedcardealership.data.coupons.*;
import usedcardealership.interaction.*;

public class TransactionManager {
    private List<Transaction> transactionHistory;

    /**
     * Constructor, Initializes the field transactionHistory
     * 
     * @param transactionHistory a List<Transaction>
     */
    public TransactionManager(List<Transaction> transactionHistory) {
        if (transactionHistory == null) {
            throw new IllegalArgumentException("Transaction history cannot be null.");
        }
        this.transactionHistory = transactionHistory;
    }

    public List<Transaction> getTransactions() {
        return this.transactionHistory;
    }

    /**
     * Adds a transaction to the transaction history
     * 
     * @param transaction a Transaction object
     */
    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null.");
        }
        this.transactionHistory.add(transaction);
    }

    /**
     * Handles a transaction for either purchasing or selling a vehicle.
     * Creates the appropriate transaction object (Purchase or Sale),
     * Updates the transaction history, and processes transaction adequatly.
     * 
     * @param vehicle         the Vehicle object involved in the transaction
     * @param customer        the Customer object involved in the transaction
     * @param transactionType the type of transaction, either "purchase" or "sale"
     * @throws IllegalArgumentException if the transactionType is invalid
     */
    public void handleTransaction(Vehicle vehicle, Customer customer, String transactionType) {
        if (vehicle == null || customer == null || transactionType == null || transactionType.length() == 0) {
            throw new IllegalArgumentException("Vehicle, customer, and transaction type cannot be null.");
        }
        int newId = transactionHistory.size() + 1;
        LocalDate currentDate = LocalDate.now();
        double price = vehicle.calculateTotalPrice();

        Transaction transaction;
        if ("purchase".equalsIgnoreCase(transactionType)) {
            transaction = new Purchase(newId, currentDate, price, customer, vehicle);
        } else if ("sale".equalsIgnoreCase(transactionType)) {
            transaction = new Sale(newId, currentDate, price, customer, vehicle);
        } else {
            throw new IllegalArgumentException("Invalid transaction type: " + transactionType);
        }

        addTransaction(transaction);
        processTransaction(transaction, customer);
    }

    /**
     * Calls the adequate function depending on the type of transaction
     * 
     * @return void
     */
    public void processTransaction(Transaction transaction, Customer customer) {
        if (transaction == null || customer == null) {
            throw new IllegalArgumentException("Transaction and customer cannot be null.");
        }
        if (transaction instanceof Purchase) {
            processPurchase((Purchase) transaction, customer);
        } else if (transaction instanceof Sale) {
            processSale((Sale) transaction, customer);
        } else {
            throw new IllegalArgumentException("Unsupported transaction type.");
        }
    }

    /**
     * Processes the dealership purchase of a vehicle from a customer by updating
     * the customer's account balance
     * and removing the sold vehicle from their list of owned vehicles.
     * 
     * @param sale     the Transaction representing the sale
     * @param customer the Customer who is selling the vehicle
     * @throws IllegalArgumentException if the customer does not own the vehicle
     *                                  being sold
     */
    private void processPurchase(Purchase purchase, Customer customer) {
        Vehicle vehicle = purchase.getVehicle();
        if (!customer.getVehicles().contains(vehicle)) {
            throw new IllegalArgumentException("Customer does not own the vehicle being sold");
        }
        customer.updateAccountBalance(customer.getAccountBalance() + purchase.getPrice());
        customer.getVehicles().remove(vehicle);
    }

    /**
     * Processes the purchase of a vehicle by adding the vehicle to the customer's
     * list
     * of owned vehicles and deducting the purchase price from their account
     * balance.
     * 
     * @param purchase the Purchase transaction to process
     * @param customer the Customer making the purchase
     * @throws IllegalArgumentException if the customer's balance is insufficient
     */
    private void processSale(Sale sale, Customer customer) {
        Vehicle vehicle = sale.getVehicle();
        double price = sale.getPrice();

        // Check if the customer has enough balance
        if (customer.getAccountBalance() < price) {
            throw new IllegalArgumentException("Insufficient balance for the sale.");
        }

        // Deduct the price and add the vehicle to the customer's list
        customer.updateAccountBalance(-1 * price);
        customer.getVehicles().add(vehicle);
    }

    /**
     * Validates the customer's purchase, ensures sufficient funds, and processes
     * the transaction.
     * Displays the receipt and updates the account balance upon successful
     * checkout.
     * 
     * @param dealer       the DealershipManager handling the dealership state.
     * @param productsList the list of vehicles in the shopping cart.
     */
    public static void checkoutLogic(DealershipManager dealer, List<Vehicle> productsList) {
        Customer customer = dealer.getCurrentCustomer();
        double allVehiclesPrice = 0;
        double customerAccountBalance = customer.getAccountBalance();
    
        if (productsList == null || productsList.isEmpty() || customer == null) {
            PrettyUtils.printRed("Error: Vehicle list or Customer not found!");
            return;
        }
    
        for (Vehicle v : productsList) {
            // Possible coupon is generated
            Coupon coupon = dealer.getCouponManager().possibleCoupon();
            double vehiclePrice = v.calculateTotalPrice();
    
            if (coupon != null) {
                System.out.println(PrettyUtils.returnGreen("Lucky you, you got a coupon: " + coupon.getCode() + " !!"));
                System.out.println(PrettyUtils.returnYellow("Do you want to apply it? (Y/N)"));
                boolean applyingCoupon = Prompter.promptYesNo();
    
                if (applyingCoupon) {
                    try {
                        // Apply the coupon to the vehicle price
                        double discountedPrice = coupon.applyCoupon(vehiclePrice);
                        System.out.println(PrettyUtils.returnGreen("Coupon applied! Original Price: $" +
                                String.format("%.2f", vehiclePrice) + " -> Discounted Price: $" +
                                String.format("%.2f", discountedPrice)));
                        vehiclePrice = discountedPrice;
                    } catch (Exception e) {
                        PrettyUtils.printRed("Error applying coupon: " + e.getMessage());
                    }
                }
            }
    
            // Output vehicle info and accumulate price
            System.out.println(v);
            allVehiclesPrice += vehiclePrice;
        }
    
        // Output total price and account balance
        System.out.println(PrettyUtils.returnCyan("Total: $" + String.format("%.2f", allVehiclesPrice)));
        System.out.println(PrettyUtils.returnCyan("Your account balance: ") + 
            (customerAccountBalance > allVehiclesPrice
                ? PrettyUtils.returnGreen("$" + String.format("%.2f", customerAccountBalance))
                : PrettyUtils.returnRed("$" + String.format("%.2f", customerAccountBalance))));
    
        // Ask if they want to finalize the purchase
        System.out.println(PrettyUtils.returnYellow("Finalize your purchase? (Y/N)"));
        boolean confirmed = Prompter.promptYesNo();
        PrettyUtils.wipe();
    
        if (confirmed) {
            String receipt = PrettyUtils.returnYellow("Receipt:");
            for (Vehicle vehicle : productsList) {
                dealer.processCustomerVehicleTransaction(vehicle, customer, "sale");
                List<Transaction> transactions = dealer.getTransactionManager().getTransactions();
                System.out.println(PrettyUtils.returnCyan("Congrats") + " on your new " + 
                        PrettyUtils.returnCyan(vehicle.getMake() + " " + vehicle.getModel()) + "!");
                receipt += "\n" + transactions.get(transactions.size() - 1);
            }
    
            System.out.println("Updated Account Balance: $" + String.format("%.2f", customer.getAccountBalance()));
            dealer.getCurrentCart().emptyCart();
            viewReceipt(receipt);
        } else {
            PrettyUtils.printRed("Sale cancelled.");
        }
    }
    


    /**
     * Displays the receipt to the user if they confirm they want to view it.
     * 
     * @param receipt the receipt content to be displayed.
     */
    private static void viewReceipt(String receipt) {
        PrettyUtils.printYellow("Do you want to see the receipt?");
        boolean confirmed = Prompter.promptYesNo();
        PrettyUtils.wipe();
        if (confirmed) {
            System.out.println(receipt);
        }
    }

}
