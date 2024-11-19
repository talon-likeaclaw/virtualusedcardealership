/**
 * Test class for PickupTruck type
 * 
 * @author Talon Dunbar
 * @version 11/18/2024
 */

package usedcardealership;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.*;
import usedcardealership.data.vehicle.*;

public class PickupTruckTest {
    @Test
    public void testConstructor_initializesPickupTruck() {
        // Arrange
        String expectedType = "PickupTruck";
        int expectedId = 41;
        String expectedMake = "Ford";
        String expectedModel = "F-150";
        int expectedYear = 2021;
        double expectedPrice = 45000.00;
        String expectedColor = "Blue";
        String expectedTransmission = "Automatic";
        String expectedDriveType = "4WD";
        int expectedHorsepower = 400;
        double expectedWeight = 6000.00;
        double expectedKilometerage = 15000.00;
        double expectedDamage = 0.05;
        boolean expectedElectric = false;
        int expectedNumSeats = 5;
        int expectedNumDoors = 4;
        boolean expectedSunRoof = true;
        double expectedCargoCapacity = 3000.0;
        double expectedBedLength = 5.5;
        double expectedTowingCapacity = 1000.00;

        // Act
        PickupTruck test = new PickupTruck(
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
                expectedCargoCapacity,
                expectedBedLength,
                expectedTowingCapacity);

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
        assertEquals(expectedCargoCapacity, test.getCargoCapacity(), 0.001);
        assertEquals(expectedBedLength, test.getBedLength(), 0.001);
        assertEquals(expectedTowingCapacity, test.getTowingCapacity(), 0.001);
    }

    @Test
    public void testCopyConstructor_copiesPickupTruck() {
        // Arrange
        PickupTruck original = new PickupTruck(
                "PickupTruck",
                1,
                "Ford",
                "F-150",
                2021,
                45000.00,
                "Blue",
                "Automatic",
                "4WD",
                400,
                6000.00,
                15000.00,
                0.05,
                false,
                5,
                4,
                true,
                3000.0,
                5.5,
                1000.00);

        // Act
        PickupTruck copy = new PickupTruck(original);

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
        assertEquals(original.getCargoCapacity(), copy.getCargoCapacity(), 0.001);
        assertEquals(original.getBedLength(), copy.getBedLength(), 0.001);
        assertEquals(original.getTowingCapacity(), copy.getTowingCapacity(), 0.001);
    }

    @Test
    public void testToString_outputsCorrectDetails() {
        // Arrange
        PickupTruck test = new PickupTruck(
                "PickupTruck",
                41,
                "Ford",
                "F-150",
                Year.now().getValue(),
                45000.00,
                "Blue",
                "Automatic",
                "4WD",
                400,
                6000.00,
                0.0,
                0.0,
                false,
                5,
                4,
                true,
                3000.0,
                5.5,
                1000.00);

        String expectedOutput = "Type: PickupTruck\n" +
                "ID: 41\n" +
                "Make: Ford\n" +
                "Model: F-150\n" +
                "Year: " + Year.now().getValue() + "\n" +
                "Price: $45000.0\n" +
                "Color: Blue\n" +
                "Transmission: Automatic\n" +
                "Drive Type: 4WD\n" +
                "Horsepower: 400\n" +
                "Weight: 6000.0 lbs\n" +
                "Kilometerage: 0.0 km\n" +
                "Damage: 0.0%\n" +
                "Electric: False\n" +
                "Seats: 5\n" +
                "Doors: 4\n" +
                "Sunroof: Yes\n" +
                "Cargo Capacity: 3000.0 cu ft\n" +
                "Bed Length: 5.5 feet\n" +
                "Towing Capacity: 1000.0 lbs";

        // Act
        String actualOutput = test.toString();

        // Assert
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}
