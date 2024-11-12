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
}
