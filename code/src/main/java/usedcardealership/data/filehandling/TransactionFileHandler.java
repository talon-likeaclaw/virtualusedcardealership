/**
 * Class for handling reading and writing Transactions from/to files
 *
 * @param <Transaction> the input Transaction list
 * @author Talon Dunbar
 * @version 11/19/2024
 */

package usedcardealership.data.filehandling;

import java.nio.file.*;
import java.time.LocalDate;
import java.io.*;
import java.util.*;

import usedcardealership.data.IDataHandler;
import usedcardealership.data.customer.*;
import usedcardealership.data.transaction.*;
import usedcardealership.data.vehicle.*;

public class TransactionFileHandler implements IDataHandler<Transaction> {
  private final Path filePath;

  /**
   * Constructs a TransactionFileHandler with the specified file path.
   * 
   * @param String filePath - The path to the file containing Transaction data.
   */
  public TransactionFileHandler(String filePath)  {
    this.filePath = Paths.get(filePath);
  }

  /**
   * Loads Transaction from the file and returns them as a list.
   * 
   * @return List<Transaction> - A list of Transactions loaded from file.
   */
  @Override
  public List<Transaction> load() {
    List<Transaction> transactions = new ArrayList<>();
    try {
      List<String> allLines = Files.readAllLines(this.filePath);

      for (String line : allLines) {
        // Split transaction, customer, and vehicle sections using # delimiter
        String[] sections = line.split("#");

        // Transaction section
        String[] transactionFields = sections[0].split(",");
        int id = Integer.parseInt(transactionFields[0]);
        String type = transactionFields[1];
        String dateString = transactionFields[2];
        LocalDate date = LocalDate.parse(dateString);

        double price = Double.parseDouble(transactionFields[3]);
        // Customer section
        String[] customerFields = sections[1].split(",");
        Customer customer = parseCustomer(customerFields);

        // Vehicle section
        String[] vehicleFields = sections[2].split(",");
        Vehicle vehicle = VehicleHelper.parseVehicle(vehicleFields);

        Transaction transaction = new Transaction(id, type, date, price, customer, vehicle);
        transactions.add(transaction);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return transactions;
  }

  /**
   * Converts a List of Transactions into CSV and saves to file
   * 
   * @param transactions - List of transactions to write to file.
   */
  public void save(List<Transaction> transactions) {
    List<String> lines = new ArrayList<>();

    for (Transaction transaction : transactions) {
      // Convert Transaction info.
      String transactionLine = String.join(",",
          String.valueOf(transaction.getID()),
          transaction.getType(),
          String.valueOf(transaction.getDate()),
          String.valueOf(transaction.getPrice()),
          String.valueOf(transaction.getTax()));

      // Convert Customer info.
      Customer customer = transaction.getCustomer();
      String customerLine = String.join(",",
          String.valueOf(customer.getID()),
          customer.getFirstName(),
          customer.getLastName(),
          customer.getBirthday(),
          customer.getPhoneNumber(),
          customer.getAddress(),
          String.valueOf(customer.getAccountBalance()));

      //Convert Vehicle info.
      Vehicle vehicle = transaction.getVehicle();
      String vehicleLine = VehicleHelper.convertVehicleToCSV(vehicle);

      // Combine into one line.
      String fullLine = String.join("#", transactionLine, customerLine, vehicleLine);
      lines.add(fullLine);
    }
    try {
      Files.write(this.filePath, lines);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Helper method for converting a String[] of fields into Customer object.
   * 
   * @param customerFields - the String[] of Customer fields.
   * @return Customer - the parsed Customer object.
   */
  private Customer parseCustomer(String[] customerFields) {
    int id = Integer.parseInt(customerFields[0]);
    String firstName = customerFields[1];
    String lastName = customerFields[2];
    String birthday = customerFields[3];
    String phoneNumber = customerFields[4];
    String address = customerFields[5];
    double accountBalance = Double.parseDouble(customerFields[6]);

    // Not worth including vehicle list for Customers in Transaction CSV
    return new Customer(id, firstName, lastName, birthday, phoneNumber,
        address, accountBalance, new ArrayList<>());
  }
}
