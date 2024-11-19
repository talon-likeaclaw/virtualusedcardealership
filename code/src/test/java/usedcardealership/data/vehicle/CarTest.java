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
        // Act
        Car test = new Car(
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

        // Assert
        assertEquals("Car", test.getType());
        assertEquals(505, test.getID());
        assertEquals("Mitsubishi", test.getMake());
        assertEquals("Lancer", test.getModel());
        assertEquals(2018, test.getYear());
        assertEquals(18000.00, test.getPrice(), 0.001);
        assertEquals("White", test.getColor());
        assertEquals("Manual", test.getTransmission());
        assertEquals("FWD", test.getDriveType());
        assertEquals(168, test.getHorsepower());
        assertEquals(2900.00, test.getWeight(), 0.001);
        assertEquals(45000.00, test.getKilometerage(), 0.001);
        assertEquals(4.5, test.getDamage(), 0.001);
        assertEquals(false, test.isElectric());
        assertEquals(5, test.getNumSeats());
        assertEquals(4, test.getNumDoors());
        assertEquals(true, test.hasSunRoof());
        assertEquals(false, test.isConvertible());
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
