/**
 * Test class for SUV type
 * 
 * @author Talon Dunbar
 * @version 11/18/2024
 */

package usedcardealership.data.vehicle;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.*;

public class SUVTest {
    @Test
    public void testConstructor_initializesSUV() {
        // Arrange
        String expectedType = "SUV";
        int expectedId = 404;
        String expectedMake = "Hyundai";
        String expectedModel = "Kona";
        int expectedYear = 2022;
        double expectedPrice = 21000.00;
        String expectedColor = "White";
        String expectedTransmission = "Automatic";
        String expectedDriveType = "AWD";
        int expectedHorsepower = 147;
        double expectedWeight = 3200.00;
        double expectedKilometerage = 10000.00;
        double expectedDamage = 2.5;
        boolean expectedElectric = false;
        int expectedNumSeats = 5;
        int expectedNumDoors = 4;
        boolean expectedSunRoof = true;
        boolean expectedThirdRow = false;

        // Act
        SUV test = new SUV(
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
                expectedThirdRow);

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
        assertEquals(expectedThirdRow, test.hasThirdRowSeating());
    }

    @Test
    public void testCopyConstructor_copiesSUV() {
        // Arrange
        SUV original = new SUV(
                "SUV",
                404,
                "Hyundai",
                "Kona",
                2022,
                21000.00,
                "White",
                "Automatic",
                "AWD",
                147,
                3200.00,
                10000.00,
                2.5,
                false,
                5,
                4,
                true,
                false);

        // Act
        SUV copy = new SUV(original);

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
        assertEquals(original.hasThirdRowSeating(), copy.hasThirdRowSeating());
    }

    @Test
    public void testToString_outputsCorrectDetails() {
        // Arrange
        SUV test = new SUV(
                "SUV",
                404,
                "Hyundai",
                "Kona",
                Year.now().getValue(),
                21000.00,
                "White",
                "Automatic",
                "AWD",
                147,
                3200.00,
                0.0,
                0.0,
                false,
                5,
                4,
                true,
                false);

        String expectedOutput = "Type: SUV\n" +
                "ID: 404\n" +
                "Make: Hyundai\n" +
                "Model: Kona\n" +
                "Year: " + Year.now().getValue() + "\n" +
                "Price: $21000.0\n" +
                "Color: White\n" +
                "Transmission: Automatic\n" +
                "Drive Type: AWD\n" +
                "Horsepower: 147\n" +
                "Weight: 3200.0 lbs\n" +
                "Kilometerage: 0.0 km\n" +
                "Damage: 0.0%\n" +
                "Electric: False\n" +
                "Seats: 5\n" +
                "Doors: 4\n" +
                "Sunroof: Yes\n" +
                "Third Row Seating: No";

        // Act
        String actualOutput = test.toString();

        // Assert
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}
