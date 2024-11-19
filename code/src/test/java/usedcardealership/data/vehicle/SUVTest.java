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
        // Act
        SUV test = new SUV(
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

        // Assert
        assertEquals("SUV", test.getType());
        assertEquals(404, test.getID());
        assertEquals("Hyundai", test.getMake());
        assertEquals("Kona", test.getModel());
        assertEquals(2022, test.getYear());
        assertEquals(21000.00, test.getPrice(), 0.001);
        assertEquals("White", test.getColor());
        assertEquals("Automatic", test.getTransmission());
        assertEquals("AWD", test.getDriveType());
        assertEquals(147, test.getHorsepower());
        assertEquals(3200.00, test.getWeight(), 0.001);
        assertEquals(10000.00, test.getKilometerage(), 0.001);
        assertEquals(2.5, test.getDamage(), 0.001);
        assertEquals(false, test.isElectric());
        assertEquals(5, test.getNumSeats());
        assertEquals(4, test.getNumDoors());
        assertEquals(true, test.hasSunRoof());
        assertEquals(false, test.hasThirdRowSeating());
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
