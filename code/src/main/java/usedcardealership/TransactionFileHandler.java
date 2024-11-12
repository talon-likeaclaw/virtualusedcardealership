/**
 * Class for handling reading and writing Transactions from/to files
 *
 * @param <Transaction> the input Transaction list
 * @author Talon Dunbar
 * @version 11/12/2024
 */

package usedcardealership;

import java.nio.file.*;
import java.io.*;
import java.util.*;

public class TransactionFileHandler implements IDataHandler<Transaction> {
  private final Path filePath;

  /**
   * Constructs a TransactionFileHandler with the specified file path.
   * 
   * @param String filePath - The path to the file containing Transaction data.
   * @throws IOException
   */
  public TransactionFileHandler(String filePath) {
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
        String[] transactionFields = line.split(",");

        int id = Integer.parseInt(transactionFields[0]);
        String type = transactionFields[1];
        String date = transactionFields[2];
        double price = Double.parseDouble(transactionFields[3]);
        double tax = Double.parseDouble(transactionFields[4]);
        String[] customerFields = transactionFields[5].split(",");
        Customer customer = parseCustomer(customerFields); // TODO: parseCustomer()
        String[] vehicleFields = transactionFields[6].split(",");
        Vehicle vehicle = VehicleHelper.parseVehicle(vehicleFields);

        Transaction transaction = new Transaction(id, type, date, price, tax, customer, vehicle);
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
          transaction.getDate(),
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
      String fullLine = String.join(",", transactionLine, customerLine, vehicleLine);
      lines.add(fullLine);
    }
    try {
      Files.write(this.filePath, lines);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
