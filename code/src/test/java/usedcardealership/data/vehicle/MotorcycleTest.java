/**
 * Test class for Motorcycle type
 * 
 * @author Talon Dunbar
 * @version 11/18/2024
 */

package usedcardealership.data.vehicle;

import org.junit.Test;

import usedcardealership.interaction.PrettyUtils;

import static org.junit.Assert.*;
import java.time.*;

public class MotorcycleTest {
    @Test
    public void testConstructor_initializesMotorcycle() {
        // Act
        Motorcycle test = new Motorcycle(
                "Motorcycle",
                1,
                "Honda",
                "CBR600RR",
                2020,
                12000.00,
                "Red",
                "Manual",
                "Chain",
                120,
                200.00,
                5000.00,
                0.1,
                false,
                599.00,
                "Sport");

        // Assert
        assertEquals("Motorcycle", test.getType());
        assertEquals(1, test.getID());
        assertEquals("Honda", test.getMake());
        assertEquals("CBR600RR", test.getModel());
        assertEquals(2020, test.getYear());
        assertEquals(12000.00, test.getPrice(), 0.001);
        assertEquals("Red", test.getColor());
        assertEquals("Manual", test.getTransmission());
        assertEquals("Chain", test.getDriveType());
        assertEquals(120, test.getHorsepower());
        assertEquals(200.00, test.getWeight(), 0.001);
        assertEquals(5000.00, test.getKilometerage(), 0.001);
        assertEquals(0.1, test.getDamage(), 0.001);
        assertEquals(false, test.isElectric());
        assertEquals(599.00, test.getEngineCC(), 0.001);
        assertEquals("Sport", test.getHandleType());
    }

    @Test
    public void testCopyConstructor_copiesMotorcycle() {
        // Arrange
        Motorcycle original = new Motorcycle(
                "Motorcycle",
                1,
                "Honda",
                "CBR600RR",
                2020,
                12000.00,
                "Red",
                "Manual",
                "Chain",
                120,
                200.00,
                5000.00,
                0.1,
                false,
                599.00,
                "Sport");

        // Act
        Motorcycle copy = new Motorcycle(original);

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
        assertEquals(original.getEngineCC(), copy.getEngineCC(), 0.001);
        assertEquals(original.getHandleType(), copy.getHandleType());
    }

    @Test
    public void testToString_outputsCorrectDetails() {
        // Arrange
        Motorcycle test = new Motorcycle(
                "Motorcycle",
                1,
                "Honda",
                "CBR600RR",
                Year.now().getValue(),
                12000.00,
                "Red",
                "Manual",
                "Chain",
                120,
                200.00,
                0.0,
                0.0,
                false,
                599.00,
                "Sport");

        String expectedOutput = PrettyUtils.returnYellow("[1] ") + Year.now().getValue() + " Red Honda CBR600RR\n" +
                "Price: $12000.00 | Transmission: Manual, Chain | Kilometrage: 0.00 km\n";
        // Act
        String actualOutput = test.toString();

        // Assert
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}
