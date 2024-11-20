/**
 * Test class for PickupTruck type
 * 
 * @author Talon Dunbar
 * @version 11/18/2024
 */

package usedcardealership.data.vehicle;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.*;

public class PickupTruckTest {
    @Test
    public void testConstructor_initializesPickupTruck() {
        // Act
        PickupTruck test = new PickupTruck(
                "PickupTruck",
                41,
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

        // Assert
        assertEquals("PickupTruck", test.getType());
        assertEquals(41, test.getID());
        assertEquals("Ford", test.getMake());
        assertEquals("F-150", test.getModel());
        assertEquals(2021, test.getYear());
        assertEquals(45000.00, test.getPrice(), 0.001);
        assertEquals("Blue", test.getColor());
        assertEquals("Automatic", test.getTransmission());
        assertEquals("4WD", test.getDriveType());
        assertEquals(400, test.getHorsepower());
        assertEquals(6000.00, test.getWeight(), 0.001);
        assertEquals(15000.00, test.getKilometerage(), 0.001);
        assertEquals(0.05, test.getDamage(), 0.001);
        assertEquals(false, test.isElectric());
        assertEquals(5, test.getNumSeats());
        assertEquals(4, test.getNumDoors());
        assertEquals(true, test.hasSunRoof());
        assertEquals(3000.0, test.getCargoCapacity(), 0.001);
        assertEquals(5.5, test.getBedLength(), 0.001);
        assertEquals(1000.00, test.getTowingCapacity(), 0.001);
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
