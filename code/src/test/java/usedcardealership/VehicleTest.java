package usedcardealership;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import usedcardealership.data.vehicle.Car;
import usedcardealership.data.vehicle.Vehicle;

public class VehicleTest {
    @Test
    public void testConstructor_initializesVehicle() {
        // Act
        Vehicle test = new Car(
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
    }

    @Test
    public void testCopyConstructor_copiesVehicleFields() {
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
        Vehicle copy = new Vehicle(original) {
        };

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
    }

    @Test
    public void testAddKilometerage_addsPositiveKilometers() {
        // Arrange
        Vehicle test = new Car(
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
        double kilometersToAdd = 150.0;
        double expectedKilometerage = test.getKilometerage() + kilometersToAdd;

        // Act
        test.addKilometerage(kilometersToAdd);

        // Assert
        assertEquals(expectedKilometerage, test.getKilometerage(), 0.001);
    }

    @Test
    public void testAddKilometerage_throwsExceptionForNegativeKilometers() {
        // Arrange
        Vehicle test = new Car(
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
        try {
            test.addKilometerage(-50.0);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }
}
