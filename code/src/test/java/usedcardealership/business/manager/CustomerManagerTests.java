package usedcardealership.business.manager;

import org.junit.Test;
import static org.junit.Assert.*;
import usedcardealership.data.customer.*;
import java.util.*;

public class CustomerManagerTests {

    @Test
    public void testAddAndSearchCustomer() {
        List<Customer> customerList = new ArrayList<>();
        CustomerManager customerManager = new CustomerManager(customerList);
        Customer customer3 = new Customer(3, "Jim", "Brown", "03/03/1980", "1122334455", "789 Pine Road", 2000.00, new ArrayList<>());
        customerManager.addCustomer(customer3);

        assertTrue(customerManager.searchCustomer(customer3));
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer1 = new Customer(1, "John", "John", "01/01/1990", "1111111111", "123 Street", 1000.00, new ArrayList<>());
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);

        CustomerManager customerManager = new CustomerManager(customerList);

        customer1.setPhoneNumber("2223334444");
        customer1.setAddress("999 Avenue");

        customerManager.updateCustomer(customer1);

        assertEquals("2223334444", customer1.getPhoneNumber());
        assertEquals("999 Avenue", customer1.getAddress());
    }
}
