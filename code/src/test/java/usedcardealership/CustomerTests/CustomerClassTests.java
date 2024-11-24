package usedcardealership.CustomerTests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import usedcardealership.data.customer.Customer;

public class CustomerClassTests {
    @Test
    public void testToString() {
        Customer customer = new Customer(1, "Alice", "Smith", "1985-07-15", "987654321",
                "456 Oak St", 1500.0, new ArrayList<>());

        String expected = "Customer ID: 1\n" +
                "Name: Alice Smith\n" +
                "Birthday: 1985-07-15\n" +
                "Phone: 987654321\n" +
                "Address: 456 Oak St\n" +
                "Account Balance: $1500.0";

        assertEquals(expected, customer.toString());
    }

    @Test
    public void testUpdateAccountBalance() {
        Customer customer = new Customer(1, "Bob", "Jones", "1992-05-20", "555123456",
                "789 Pine Rd", 2000.0, new ArrayList<>());

        customer.updateAccountBalance(500.0);
        assertEquals(2500.0, customer.getAccountBalance(), 0.0);
    }

    @Test
    public void testSetPhoneNumber() {
        Customer customer = new Customer(2, "Chris", "Chris", "1990-03-10", "111222333",
                "987 Ln", 3000.0, new ArrayList<>());

        customer.setPhoneNumber("444555666");
        assertEquals("444555666", customer.getPhoneNumber());
    }

    @Test
    public void testSetAddress() {
        Customer customer = new Customer(3, "Diana", "Prince", "1988-12-25", "999888777",
                "321 Blvd", 4000.0, new ArrayList<>());

        // Update address
        customer.setAddress("654 Spruce Ave");
        assertEquals("654 Spruce Ave", customer.getAddress());
    }

    @Test
    public void testEquals() {
        Customer customer1 = new Customer(4, "Evan", "Peters", "1995-11-15", "123321123",
                "159 Willow St", 1200.0, new ArrayList<>());
        Customer customer2 = new Customer(4, "Evan", "Peters", "1995-11-15", "123321123",
                "159 Willow St", 1200.0, new ArrayList<>());

        Customer customer3 = new Customer(5, "NotEvan", "NotPeters", "1995-11-15", "123321123",
                "159 Willow St", 1200.0, new ArrayList<>());

        assertTrue(customer1.equals(customer2));
        assertFalse(customer1.equals(customer3));
    }
}
