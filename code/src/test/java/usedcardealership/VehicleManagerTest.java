/**
 * Test class for VehicleManager
 * 
 * @author Talon Dunbar
 * @version 11/11/2024
 */

package usedcardealership;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;
import java.util.*;

public class VehicleManagerTest {

    @Test
    public void testConstructor_initializesInventoryAndDatabase() {
        // Arrange
        List<Vehicle> expectedInventory = new ArrayList<>();
        List<Vehicle> expectedDatabase = new ArrayList<>();

        // Populate the lists with sample vehicles
        expectedInventory
                .add(new Car(1, "Toyota", "Camry", 2021, 30000.0, "Blue", "Automatic", "FWD", 200, 1500.0, 10000.0,
                        5.0, false, 5, 4, false, false));
        expectedDatabase.add(new SUV(2, "Ford", "Explorer", 2019, 35000.0, "Black", "Automatic", "AWD", 250, 2000.0,
                15000.0, 10.0, false, 7, 4, true, false));

        // Act
        VehicleManager test = new VehicleManager(expectedInventory, expectedDatabase);

        // Assert
        assertEquals(expectedInventory, test.getInventory());
        assertEquals(expectedDatabase, test.getDatabase());
    }

    @Test
    public void testPrintVehicles_outputsCorrectDetails() {
        // Arrange
        List<Vehicle> vehicleList = new ArrayList<>();
        Car car = new Car(1, "Toyota", "Camry", 2021, 30000.0, "Blue", "Automatic",
                "FWD", 200, 1500.0, 10000.0, 5.0, false, 5, 4, false, false);
        vehicleList.add(car);

        VehicleManager test = new VehicleManager(vehicleList, new ArrayList<>());

        // Redirect System.out to capture output
        // Reference:
        // https://stackoverflow.com/questions/32241057/how-to-test-a-print-method-in-java-using-junit
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.printVehicles(vehicleList);

        // Act

        // Expected output
        String expectedOutput = "ID: 1\n" +
                "Make: Toyota\n" +
                "Model: Camry\n" +
                "Year: 2021\n" +
                "Price: $24550.0\n\n";

        // Assert
        assertEquals(expectedOutput, outContent.toString().toString());
        System.setOut(System.out);
    }
}
