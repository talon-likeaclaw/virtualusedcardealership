/**
 * Manages all Transactions, has a List<Transaction>
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/4/2024
 */
package usedcardealership.business.manager;

import java.util.*;
import java.time.*;
import usedcardealership.business.filter.*;
import usedcardealership.data.customer.*;
import usedcardealership.data.transaction.*;
import usedcardealership.data.vehicle.*;

public class TransactionManager {
    private List<Transaction> transactionHistory;

    /**
     * Constructor, Initializes the field transactionHistory
     * 
     * @param transactionHistory a List<Transaction> 
     */
    public TransactionManager(List<Transaction> transactionHistory){
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
    public void addTransaction(Transaction transaction){
        this.transactionHistory.add(transaction);
    }

    /**
     * Function sells Vehicle to a Customer, updates the transactionHistory
     * 
     * @param vehicle a Vehicle Object
     * @param customer a Customer object
     * @return Transaction
     */
    /*id, date, price, tax, customer, vehicle */
    public Transaction sellVehicle(Vehicle vehicle, Customer customer){

        int newId = transactionHistory.size() + 1;
        LocalDate currentDate = LocalDate.now();
        double price = vehicle.calculateTotalPrice();

        Transaction sale = new Sale(newId, currentDate, price, customer, vehicle);
        addTransaction(sale);


        processTransaction(sale, customer);
        return sale;
    }

    /**
     * Calls the adequate function depending on the type of transaction
     * 
     * @return void
    */
    public void processTransaction(Transaction transaction, Customer customer) {
        if (transaction instanceof Sale) {
            processSale((Sale) transaction, customer);
        } else if (transaction instanceof Purchase) {
            processPurchase((Purchase) transaction, customer);
        }
    }


    /**
     * Processes the sale of a vehicle by updating the customer's account balance 
     * and removing the sold vehicle from their list of owned vehicles.
     * 
     * @param sale      the Transaction representing the sale
     * @param customer  the Customer who is selling the vehicle
     * @throws IllegalArgumentException if the customer does not own the vehicle being sold
    */
    private void processSale(Sale sale, Customer customer) {
        Vehicle vehicle = sale.getVehicle();
        if (!customer.getVehicles().contains(vehicle)) {
            throw new IllegalArgumentException("Customer does not own the vehicle being sold");
        }
        customer.updateAccountBalance(customer.getAccountBalance() + sale.getPrice());
        customer.getVehicles().remove(vehicle);
    }

    /**
     * Processes the purchase of a vehicle by adding the vehicle to the customer's list
     * of owned vehicles and deducting the purchase price from their account balance.
     * 
     * @param purchase   the Purchase transaction to process
     * @param customer   the Customer making the purchase
     * @throws IllegalArgumentException if the customer's balance is insufficient
    */
    private void processPurchase(Purchase purchase, Customer customer) {
        Vehicle vehicle = purchase.getVehicle();
        double price = purchase.calculateTotal();

        // Check if the customer has enough balance
        if (customer.getAccountBalance() < price) {
            throw new IllegalArgumentException("Insufficient balance for the purchase.");
        }

        // Deduct the price and add the vehicle to the customer's list
        customer.updateAccountBalance(customer.getAccountBalance() - price);
        customer.getVehicles().add(vehicle);
    }

    /**
     * Function searches transactions based on specified criteria (e.g., filter or sorting criteria )
     * 
     * @param criteria a IFilter sub-type object
     * @return List<Transaction>
     */

    //Do we keep this?
    public List<Transaction> searchTransaction(IFilter criteria){
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactionHistory) {
            if (criteria.filter(t)) {
            result.add(t);
            }
        }
        return result;
    }

    
}
