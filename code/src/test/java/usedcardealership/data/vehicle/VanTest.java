/**
 * Test class for Van type
 * 
 * @author Talon Dunbar
 * @version 11/18/2024
 */

package usedcardealership.data.vehicle;

import org.junit.Test;

import usedcardealership.interaction.PrettyUtils;

import static org.junit.Assert.*;
import java.time.*;

public class VanTest {
    @Test
    public void testConstructor_initializesVan() {
        // Act
        Van test = new Van(
                "Van",
                51,
                "Honda",
                "Odyssey",
                2021,
                35000.00,
                "Silver",
                "Automatic",
                "FWD",
                280,
                4500.00,
                10000.00,
                0.0,
                false,
                8,
                4,
                true,
                14.0,
                true);

        // Assert
        assertEquals("Van", test.getType());
        assertEquals(51, test.getID());
        assertEquals("Honda", test.getMake());
        assertEquals("Odyssey", test.getModel());
        assertEquals(2021, test.getYear());
        assertEquals(35000.00, test.getPrice(), 0.001);
        assertEquals("Silver", test.getColor());
        assertEquals("Automatic", test.getTransmission());
        assertEquals("FWD", test.getDriveType());
        assertEquals(280, test.getHorsepower());
        assertEquals(4500.00, test.getWeight(), 0.001);
        assertEquals(10000.00, test.getKilometerage(), 0.001);
        assertEquals(0.0, test.getDamage(), 0.001);
        assertEquals(false, test.isElectric());
        assertEquals(8, test.getNumSeats());
        assertEquals(4, test.getNumDoors());
        assertEquals(true, test.hasSunRoof());
        assertEquals(14.0, test.getCargoCapacity(), 0.001);
        assertEquals(true, test.hasSlidingDoors());
    }

    @Test
    public void testCopyConstructor_copiesVan() {
        // Arrange
        Van original = new Van(
                "Van",
                51,
                "Honda",
                "Odyssey",
                2021,
                35000.00,
                "Silver",
                "Automatic",
                "FWD",
                280,
                4500.0,
                10000.0,
                0.0,
                false,
                8,
                4,
                true,
                14.0,
                true);

        // Act
        Van copy = new Van(original);

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
        assertEquals(original.hasSlidingDoors(), copy.hasSlidingDoors());
    }

    @Test
    public void testToString_outputsCorrectDetails() {
        // Arrange
        Van test = new Van(
                "Van",
                51,
                "Honda",
                "Odyssey",
                Year.now().getValue(),
                35000.00,
                "Silver",
                "Automatic",
                "FWD",
                280,
                4500.0,
                0.0,
                0.0,
                false,
                8,
                4,
                true,
                14.0,
                true);

        String expectedOutput = PrettyUtils.returnYellow("[51] ") + Year.now().getValue() + " Silver Honda Odyssey\n" +
                "Price: $35000.00 | Transmission: Automatic, FWD | Kilometrage: 0.00 km\n";

        // Act
        String actualOutput = test.toString();

        // Assert
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}
