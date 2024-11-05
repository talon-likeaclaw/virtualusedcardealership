/**
 * Manages all Transactions, has a List<Transaction>
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/4/2024
 */
package usedcardealership;

import java.util.List;

public class TransactionManager {
    private List<Transactions> transactionHistory;

    /**
     * Constructor, Initializes the field transactionHistory
     * 
     * @param transactionHistory a List<Transaction> 
     */
    public TransactionManager(List<Transactions> transactionHistory){
        throw new UnsupportedOperationException();
    }

    /**
     * Function sells Vehicle to a Customer, updates the transactionHistory
     * 
     * @param vehicle a Vehicle Object
     * @param customer a Customer object
     * @return Transaction
     */
    public Transaction sellVehicle(Vehicle vehicle, Customer customer){
        throw new UnsupportedOperationException();
    }

    /**
     * Function allows Customer to buy a Vehicle, updates the transactionHistory
     * 
     * @param vehicle a Vehicle Object
     * @param customer a Customer object
     * @return Transaction
     */
    public Transaction buyVehicle(Vehicle vehicle, Customer customer){
        throw new UnsupportedOperationException();
    }

    /**
     * Function searches transactions based on specified criteria (e.g., filter or sorting criteria 
     * 
     * @param criteria a IFilter sub-type object
     * @return List<Transaction>
     */
    public List<Transaction> searchTransaction(IFilter criteria){
        throw new UnsupportedOperationException();
    }

    /**
     * Function processes a transaction
     * 
     * @return void
     */

    //is it meant to be a private helper method?
    public void processTransaction(){
        throw new UnsupportedOperationException();
    }

    
}
