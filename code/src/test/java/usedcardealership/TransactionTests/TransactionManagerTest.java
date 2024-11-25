package usedcardealership.TransactionTests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import usedcardealership.data.customer.Customer;
import usedcardealership.data.transaction.Purchase;
import usedcardealership.data.transaction.Transaction;
import usedcardealership.data.vehicle.SUV;
import usedcardealership.data.vehicle.Vehicle;
import usedcardealership.business.manager.TransactionManager;

public class TransactionManagerTest {

    @Test
    public void testAddTransaction() {
        List<Transaction> transactionHistory = new ArrayList<>();
        TransactionManager transactionManager = new TransactionManager(transactionHistory);
        Customer customer = new Customer(1, "John", "Doe", "1980-01-01", "123-456-7890", "123 Main St", 20000.0, new ArrayList<>());
        Vehicle vehicle = new SUV("SUV", 2, "Ford", "Explorer", 2019, 35000.0, "Black", "Automatic", "AWD",
                250, 2000.0, 15000.0, 10.0, false, 7, 4, true, false);
        Transaction transaction = new Purchase(1, LocalDate.now(), 15000.0, customer, vehicle);

        transactionManager.addTransaction(transaction);

        assertEquals(1, transactionManager.getTransactions().size());
        assertEquals(transaction, transactionManager.getTransactions().get(0));
    }

    @Test
    public void testHandlePurchaseTransaction() {
        List<Transaction> transactionHistory = new ArrayList<>();
        TransactionManager transactionManager = new TransactionManager(transactionHistory);
        Customer customer = new Customer(1, "John", "Doe", "1980-01-01", "123-456-7890", "123 Main St", 20000.0, new ArrayList<>());
        Vehicle vehicle = new SUV("SUV", 2, "Ford", "Explorer", 2019, 35000.0, "Black", "Automatic", "AWD",
        250, 2000.0, 15000.0, 10.0, false, 7, 4, true, false);
        customer.getVehicles().add(vehicle);
        customer.getAccountBalance();

        transactionManager.handleTransaction(vehicle, customer, "purchase");

        assertEquals(1, transactionManager.getTransactions().size());
        assertEquals(69450.0, customer.getAccountBalance(), 0.01);
    }

    @Test
    public void testHandleSaleTransaction() {
        List<Transaction> transactionHistory = new ArrayList<>();
        TransactionManager transactionManager = new TransactionManager(transactionHistory);
        Customer customer = new Customer(1, "John", "Doe", "1980-01-01", "123-456-7890", "123 Main St", 50000.0, new ArrayList<>());
        Vehicle vehicle = new SUV("SUV", 2, "Ford", "Explorer", 2019, 35000.0, "Black", "Automatic", "AWD",
        250, 2000.0, 15000.0, 10.0, false, 7, 4, true, false);
        customer.getAccountBalance();

        transactionManager.handleTransaction(vehicle, customer, "sale");

        assertEquals(1, transactionManager.getTransactions().size());
        assertEquals(66132.5, customer.getAccountBalance(), 0.01);
    }

    @Test
    public void testHandleTransactionInvalidType() {
        List<Transaction> transactionHistory = new ArrayList<>();
        TransactionManager transactionManager = new TransactionManager(transactionHistory);
        Customer customer = new Customer(1, "John", "Doe", "1980-01-01", "123-456-7890", "123 Main St", 20000.0, new ArrayList<>());
        Vehicle vehicle = new SUV("SUV", 2, "Ford", "Explorer", 2019, 35000.0, "Black", "Automatic", "AWD",
        250, 2000.0, 15000.0, 10.0, false, 7, 4, true, false);

        try {
            transactionManager.handleTransaction(vehicle, customer, "invalid_type");
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid transaction type: invalid_type", e.getMessage());
        }
    }

    @Test
    public void testProcessPurchaseWithNonOwnedVehicle() {
        List<Transaction> transactionHistory = new ArrayList<>();
        TransactionManager transactionManager = new TransactionManager(transactionHistory);
        Customer customer = new Customer(1, "John", "Doe", "1980-01-01", "123-456-7890", "123 Main St", 20000.0, new ArrayList<>());
        Vehicle vehicle = new SUV("SUV", 2, "Ford", "Explorer", 2019, 35000.0, "Black", "Automatic", "AWD",
                250, 2000.0, 15000.0, 10.0, false, 7, 4, true, false);

        double initialBalance = customer.getAccountBalance();

        try {
            transactionManager.handleTransaction(vehicle, customer, "purchase");
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Customer does not own the vehicle being sold", e.getMessage());
        }
        assertEquals(initialBalance, customer.getAccountBalance(), 0.01);
    }

    @Test
    public void testProcessSaleWithInsufficientBalance() {
        List<Transaction> transactionHistory = new ArrayList<>();
        TransactionManager transactionManager = new TransactionManager(transactionHistory);
        Customer customer = new Customer(1, "John", "Doe", "1980-01-01", "123-456-7890", "123 Main St", 0.0, new ArrayList<>());
        Vehicle vehicle = new SUV("SUV", 2, "Ford", "Explorer", 2019, 35000.0, "Black", "Automatic", "AWD",
                250, 2000.0, 15000.0, 10.0, false, 7, 4, true, false);

        double initialBalance = customer.getAccountBalance();

        try {
            transactionManager.handleTransaction(vehicle, customer, "sale");
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Insufficient balance for the sale.", e.getMessage());
        }
        assertEquals(initialBalance, customer.getAccountBalance(), 0.01);
    }

    @Test
    public void testTransactionHistoryConsistency() {
        List<Transaction> transactionHistory = new ArrayList<>();
        TransactionManager transactionManager = new TransactionManager(transactionHistory);
        Customer customer = new Customer(1, "John", "Doe", "1980-01-01", "123-456-7890", "123 Main St", 80000.0, new ArrayList<>());
        Vehicle vehicle1 = new SUV("SUV", 2, "Ford", "Explorer", 2019, 35000.0, "Black", "Automatic", "AWD",
                250, 2000.0, 15000.0, 10.0, false, 7, 4, true, false);
        Vehicle vehicle2 = new SUV("SUV", 2, "Ford", "Explorer", 2019, 35000.0, "Black", "Automatic", "AWD",
        250, 2000.0, 15000.0, 10.0, false, 7, 4, true, false);

        transactionManager.handleTransaction(vehicle1, customer, "sale");
        transactionManager.handleTransaction(vehicle2, customer, "sale");

        assertEquals(2, transactionManager.getTransactions().size());
    }
}
