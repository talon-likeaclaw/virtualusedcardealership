/**
 * Manages all Customers, has a List<Customer>
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/4/2024
 * 
 * @version 11/11/2024
 */

package usedcardealership.business.manager;

import java.util.*;
import usedcardealership.data.customer.*;

public class CustomerManager {

    private List<Customer> customerList;

    /**
     * Initializes the field customerList
     * 
     * @param customerList a List<Customer> with all customers 
     */
    public CustomerManager(List<Customer> customerList){
        if (customerList == null) {
            throw new IllegalArgumentException("Customer list cannot be null.");
        }
        this.customerList = customerList;
    }

    public List<Customer> getCustomers() {
        return this.customerList;
    }

    /**
     * Function updates the customerList List<Customer> to add a Customer object
     * 
     * @param customer a Customer object
     * @return void
     */
    public void addCustomer(Customer customer){
        validateCustomerNull(customer);
        if (!this.customerList.contains(customer)) {
            this.customerList.add(customer);
        }
    }

    /**
     * Function checks if the customerList List<Customer> contains a Customer object
     * 
     * @param customer a Customer object
     * @return boolean
     */
    public boolean searchCustomer(Customer customer){
        validateCustomerNull(customer);
        return this.customerList.contains(customer);
    }

    /**
     * Function updates a Cutomer's field
     * 
     * @param customer a Customer object
     * @return void
     */
    public void updateCustomer(Customer customer){
        validateCustomerNull(customer);
        for (int i = 0; i < this.customerList.size(); i++) {
            if (this.customerList.get(i).equals(customer)) {
                this.customerList.set(i, new Customer(customer));
                break;
            }
        }
    }

    /**
     * Validates that the customer parameter is not null
     * Throws exception if null
     * 
     * @param customer the customer to check
     */
    public void validateCustomerNull(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
    }
}