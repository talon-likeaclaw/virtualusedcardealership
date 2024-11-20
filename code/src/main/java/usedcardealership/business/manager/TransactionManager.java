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
     * Function sells Vehicle to a Customer, updates the transactionHistory
     * 
     * @param vehicle a Vehicle Object
     * @param customer a Customer object
     * @return Transaction
     */
    public Transaction sellVehicle(Vehicle vehicle, Customer customer){

        int newId = transactionHistory.size() + 1;

        Transaction sale = new Sale();
        processTransaction(sale);
        return sale;
    }

    /**
     * Function allows Customer to buy a Vehicle, updates the transactionHistory
     * 
     * @param vehicle a Vehicle Object
     * @param customer a Customer object
     * @return Transaction
     */
    public Transaction buyVehicle(Vehicle vehicle, Customer customer){

        Transaction purchase = new Purchase();
        processTransaction(purchase);
        return purchase;
    }

    /**
     * Function searches transactions based on specified criteria (e.g., filter or sorting criteria )
     * 
     * @param criteria a IFilter sub-type object
     * @return List<Transaction>
     */
    public List<Transaction> searchTransaction(IFilter criteria){
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactionHistory) {
            if (criteria.filter(t)) {
            result.add(t);
            }
        }
        return result;
    }

    /**
     * Function processes a transaction
     * 
     * @return void
     */
    //how exactly is Transaction processed?
    private void processTransaction(Transaction transaction){
        transactionHistory.add(transaction);
    }

    
}
