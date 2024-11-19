/**
 * Test class for Car type
 * 
 * @author Talon Dunbar
 * @version 11/18/2024
 */

package usedcardealership.data.vehicle;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.*;

public class CarTest {
    @Test
    public void testConstructor_initializesCar() {
        // Arrange
        String expectedType = "Car";
        int expectedId = 505;
        String expectedMake = "Mitsubishi";
        String expectedModel = "Lancer";
        int expectedYear = 2018;
        double expectedPrice = 18000.00;
        String expectedColor = "White";
        String expectedTransmission = "Manual";
        String expectedDriveType = "FWD";
        int expectedHorsepower = 168;
        double expectedWeight = 2900.00;
        double expectedKilometerage = 45000.00;
        double expectedDamage = 4.5;
        boolean expectedElectric = false;
        int expectedNumSeats = 5;
        int expectedNumDoors = 4;
        boolean expectedSunRoof = true;
        boolean expectedConvertible = false;

        // Act
        Car test = new Car(
                expectedType,
                expectedId,
                expectedMake,
                expectedModel,
                expectedYear,
                expectedPrice,
                expectedColor,
                expectedTransmission,
                expectedDriveType,
                expectedHorsepower,
                expectedWeight,
                expectedKilometerage,
                expectedDamage,
                expectedElectric,
                expectedNumSeats,
                expectedNumDoors,
                expectedSunRoof,
                expectedConvertible);

        // Assert
        assertEquals(expectedType, test.getType());
        assertEquals(expectedId, test.getID());
        assertEquals(expectedMake, test.getMake());
        assertEquals(expectedModel, test.getModel());
        assertEquals(expectedYear, test.getYear());
        assertEquals(expectedPrice, test.getPrice(), 0.001);
        assertEquals(expectedColor, test.getColor());
        assertEquals(expectedTransmission, test.getTransmission());
        assertEquals(expectedDriveType, test.getDriveType());
        assertEquals(expectedHorsepower, test.getHorsepower());
        assertEquals(expectedWeight, test.getWeight(), 0.001);
        assertEquals(expectedKilometerage, test.getKilometerage(), 0.001);
        assertEquals(expectedDamage, test.getDamage(), 0.001);
        assertEquals(expectedElectric, test.isElectric());
        assertEquals(expectedNumSeats, test.getNumSeats());
        assertEquals(expectedNumDoors, test.getNumDoors());
        assertEquals(expectedSunRoof, test.hasSunRoof());
        assertEquals(expectedConvertible, test.isConvertible());
    }

    @Test
    public void testCopyConstructor_copiesCar() {
        // Arrange
        Car original = new Car(
                "Car",
                505,
                "Mitsubishi",
                "Lancer",
                2018,
                18000.00,
                "White",
                "Manual",
                "FWD",
                168,
                2900.00,
                45000.00,
                4.5,
                false,
                5,
                4,
                true,
                false);

        // Act
        Car copy = new Car(original);

        // Assert
        assertEquals(original.getType(), copy.getType());
        assertEquals(original.getID(), copy.getID());
        assertEquals(original.getMake(), copy.getMake());
        assertEquals(original.getModel(), copy.getModel());
        assertEquals(original.getYear(), copy.getYear());
        assertEquals(original.getPrice(), copy.getPrice(), 0.001);
        assertEquals(original.getColor(), copy.getColor());
        assertEquals(original.getTransmission(), copy.getTransmission());
        assertEquals(original.getDriveType(), copy.getDriveType());
        assertEquals(original.getHorsepower(), copy.getHorsepower());
        assertEquals(original.getWeight(), copy.getWeight(), 0.001);
        assertEquals(original.getKilometerage(), copy.getKilometerage(), 0.001);
        assertEquals(original.getDamage(), copy.getDamage(), 0.001);
        assertEquals(original.isElectric(), copy.isElectric());
        assertEquals(original.getNumSeats(), copy.getNumSeats());
        assertEquals(original.getNumDoors(), copy.getNumDoors());
        assertEquals(original.hasSunRoof(), copy.hasSunRoof());
        assertEquals(original.isConvertible(), copy.isConvertible());
    }

    @Test
    public void testToString_outputsCorrectDetails() {
        // Arrange
        Car test = new Car(
                "Car",
                505,
                "Mitsubishi",
                "Lancer",
                Year.now().getValue(),
                18000.00,
                "White",
                "Manual",
                "FWD",
                168,
                2900.00,
                0.0,
                0.0,
                false,
                5,
                4,
                true,
                false);

        String expectedOutput = "Type: Car\n" +
                "ID: 505\n" +
                "Make: Mitsubishi\n" +
                "Model: Lancer\n" +
                "Year: " + Year.now().getValue() + "\n" +
                "Price: $18000.0\n" +
                "Color: White\n" +
                "Transmission: Manual\n" +
                "Drive Type: FWD\n" +
                "Horsepower: 168\n" +
                "Weight: 2900.0 lbs\n" +
                "Kilometerage: 0.0 km\n" +
                "Damage: 0.0%\n" +
                "Electric: False\n" +
                "Seats: 5\n" +
                "Doors: 4\n" +
                "Sunroof: Yes\n" +
                "Convertible: No";

        // Act
        String actualOutput = test.toString();

        // Assert
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}
