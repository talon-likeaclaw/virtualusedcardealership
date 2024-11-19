/**
 * Test class for RV type
 * 
 * @author Talon Dunbar
 * @version 11/18/2024
 */

package usedcardealership;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.*;
import usedcardealership.data.vehicle.*;

public class RVTest {
    @Test
    public void testConstructor_initializesRV() {
        // Arrange
        String expectedType = "RV";
        int expectedId = 11;
        String expectedMake = "Winnebago";
        String expectedModel = "Outlook";
        int expectedYear = 2021;
        double expectedPrice = 60000.00;
        String expectedColor = "White";
        String expectedTransmission = "Automatic";
        String expectedDriveType = "RWD";
        int expectedHorsepower = 320;
        double expectedWeight = 9500.00;
        double expectedKilometerage = 5000.00;
        double expectedDamage = 0.05;
        boolean expectedElectric = false;
        int expectedNumSeats = 6;
        int expectedNumDoors = 2;
        boolean expectedSunRoof = true;
        int expectedSleepCapacity = 4;
        boolean expectedBathroom = true;

        // Act
        RV test = new RV(
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
                expectedSleepCapacity,
                expectedBathroom);

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
        assertEquals(expectedSleepCapacity, test.getSleepCapacity());
        assertEquals(expectedBathroom, test.hasBathroom());
    }

    @Test
    public void testCopyConstructor_copiesRV() {
        // Arrange
        RV original = new RV(
                "RV",
                11,
                "Winnebago",
                "Outlook",
                2021,
                60000.00,
                "White",
                "Automatic",
                "RWD",
                320,
                9500.00,
                5000.00,
                0.05,
                false,
                6,
                2,
                true,
                4,
                true);

        // Act
        RV copy = new RV(original);

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
        assertEquals(original.getSleepCapacity(), copy.getSleepCapacity());
        assertEquals(original.hasBathroom(), copy.hasBathroom());
    }

    @Test
    public void testToString_outputsCorrectDetails() {
        // Arrange
        RV test = new RV(
                "RV",
                11,
                "Winnebago",
                "Outlook",
                Year.now().getValue(),
                60000.00,
                "White",
                "Automatic",
                "RWD",
                320,
                9500.00,
                0.0,
                0.0,
                false,
                6,
                2,
                true,
                4,
                true);

        String expectedOutput = "Type: RV\n" +
                "ID: 11\n" +
                "Make: Winnebago\n" +
                "Model: Outlook\n" +
                "Year: " + Year.now().getValue() + "\n" +
                "Price: $60000.0\n" +
                "Color: White\n" +
                "Transmission: Automatic\n" +
                "Drive Type: RWD\n" +
                "Horsepower: 320\n" +
                "Weight: 9500.0 lbs\n" +
                "Kilometerage: 0.0 km\n" +
                "Damage: 0.0%\n" +
                "Electric: False\n" +
                "Seats: 6\n" +
                "Doors: 2\n" +
                "Sunroof: Yes\n" +
                "Sleep Capacity: 4\n" +
                "Bathroom: Yes";

        // Act
        String actualOutput = test.toString();

        // Assert
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}
