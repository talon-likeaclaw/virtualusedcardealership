package usedcardealership;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.*;
import usedcardealership.data.vehicle.*;

public class VanTest {
    @Test
    public void testConstructor_initializesPickupTruck() {
        // Arrange
        String expectedType = "Van";
        int expectedId = 51;
        String expectedMake = "Honda";
        String expectedModel = "Odyssey";
        int expectedYear = 2021;
        double expectedPrice = 35000.00;
        String expectedColor = "Silver";
        String expectedTransmission = "Automatic";
        String expectedDriveType = "FWD";
        int expectedHorsepower = 280;
        double expectedWeight = 4500;
        double expectedKilometerage = 10000.00;
        double expectedDamage = 0.0;
        boolean expectedElectric = false;
        int expectedNumSeats = 8;
        int expectedNumDoors = 4;
        boolean expectedSunRoof = true;
        double expectedCargoCapacity = 14.0;
        boolean expectedSlidingDoors = true;

        // Act
        Van test = new Van(
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
                expectedSlidingDoors);

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
        assertEquals(expectedSlidingDoors, test.hasSlidingDoors());
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

        String expectedOutput = "Type: Van\n" +
                "ID: 51\n" +
                "Make: Honda\n" +
                "Model: Odyssey\n" +
                "Year: " + Year.now().getValue() + "\n" +
                "Price: $35000.0\n" +
                "Color: Silver\n" +
                "Transmission: Automatic\n" +
                "Drive Type: FWD\n" +
                "Horsepower: 280\n" +
                "Weight: 4500.0 lbs\n" +
                "Kilometerage: 0.0 km\n" +
                "Damage: 0.0%\n" +
                "Electric: False\n" +
                "Seats: 8\n" +
                "Doors: 4\n" +
                "Sunroof: Yes\n" +
                "Cargo Capacity: 14.0 cu ft\n" +
                "Sliding Doors: Yes";

        // Act
        String actualOutput = test.toString();

        // Assert
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}
