/**
 * Test class for RV type
 * 
 * @author Talon Dunbar
 * @version 11/18/2024
 */

package usedcardealership.data.vehicle;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.*;

public class RVTest {
    @Test
    public void testConstructor_initializesRV() {
        // Act
        RV test = new RV(
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

        // Assert
        assertEquals("RV", test.getType());
        assertEquals(11, test.getID());
        assertEquals("Winnebago", test.getMake());
        assertEquals("Outlook", test.getModel());
        assertEquals(2021, test.getYear());
        assertEquals(60000.00, test.getPrice(), 0.001);
        assertEquals("White", test.getColor());
        assertEquals("Automatic", test.getTransmission());
        assertEquals("RWD", test.getDriveType());
        assertEquals(320, test.getHorsepower());
        assertEquals(9500.00, test.getWeight(), 0.001);
        assertEquals(5000.00, test.getKilometerage(), 0.001);
        assertEquals(0.05, test.getDamage(), 0.001);
        assertEquals(false, test.isElectric());
        assertEquals(6, test.getNumSeats());
        assertEquals(2, test.getNumDoors());
        assertEquals(true, test.hasSunRoof());
        assertEquals(4, test.getSleepCapacity());
        assertEquals(true, test.hasBathroom());
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

        String expectedOutput = "[11] " + Year.now().getValue() + " White Winnebago Outlook\n" +
                "Price: $60000.0 | Transmission: Automatic, RWD | Kilometrage: 0.0 km\n";

        // Act
        String actualOutput = test.toString();

        // Assert
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}
