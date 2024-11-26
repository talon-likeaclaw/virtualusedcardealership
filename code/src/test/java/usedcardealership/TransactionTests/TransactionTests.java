package usedcardealership.TransactionTests;

import org.junit.Test;
import static org.junit.Assert.*;

import usedcardealership.data.customer.Customer;
import usedcardealership.data.transaction.Transaction;
import usedcardealership.data.vehicle.SUV;
import usedcardealership.data.vehicle.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;

public class TransactionTests{
    @Test
    public void testTransactionConstructorAndGetters() {
        Customer customer = new Customer(1, "John", "Doe", "2014-01-01", "514-999-9999", "362 Wallaby Way", 5000.0, new ArrayList<>());
        Vehicle vehicle = new SUV("SUV", 2, "Ford", "Explorer", 2019, 35000.0, "Black", "Automatic", "AWD",
                250, 2000.0, 15000.0, 10.0, false, 7, 4, true, false);
        LocalDate date = LocalDate.of(2024, 11, 6);

        Transaction transaction = new Transaction(1, "purchase", date, 20000.0, customer, vehicle);

        assertEquals(1, transaction.getID());
        assertEquals("purchase", transaction.getType());
        assertEquals(date, transaction.getDate());
        assertEquals(20000.0, transaction.getPrice(), 0.01);
        assertEquals(1.15, transaction.getTax(), 0.01);
        assertEquals(customer, transaction.getCustomer());
        assertEquals(vehicle, transaction.getVehicle());
    }

    @Test
    public void testSetDate() {
        Transaction transaction = new Transaction();
        LocalDate date = LocalDate.of(2024, 11, 6);

        transaction.setDate(date);

        assertEquals(date, transaction.getDate());
    }

    @Test
    public void testSetPrice() {
        Transaction transaction = new Transaction();

        transaction.setPrice(25000.0);

        assertEquals(25000.0, transaction.getPrice(), 0.01);
    }

}
