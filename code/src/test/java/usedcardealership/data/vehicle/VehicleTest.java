/**
 * Test class for Vehicle abstract type
 * 
 * @author Talon Dunbar
 * @version 11/19/2024
 */

package usedcardealership.data.vehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.time.*;
import org.junit.Test;

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
        Vehicle copy = new Car(original);

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
    public void testEqualsOverride_returnsTrue() {
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

        boolean expectedResult = true;

        // Act
        Boolean actualResult = test.equals(test);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testEqualsOverride_returnsFalse() {
        // Arrange
        Vehicle testOne = new Car(
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

        Vehicle testTwo = new Car(
                "Car",
                24,
                "Chevrolet",
                "Malibu",
                2018,
                23000.00,
                "Gray",
                "Automatic",
                "FWD",
                160,
                3100.00,
                25000.00,
                0.1,
                false,
                5,
                4,
                false,
                false);

        boolean expectedResult = false;

        // Act
        Boolean actualResult = testOne.equals(testTwo);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testTestDrive_addsKilometerDamageRange() {
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

        final double MAX_DAMAGE = 1.0;
        final double MAX_KILOMETER = 50.0;
        final double MAX_CRASH_DAMAGE = 90.0;
        double initialDamage = test.getDamage();
        double initialKilometerage = test.getKilometerage();

        // Act
        test.testDrive();
        double resultingDamage = test.getDamage();
        double resultingKilometerage = test.getKilometerage();

        // Assert
        // Validate that kilometerage is within the range
        double maxPossibleKilometerage = initialKilometerage + MAX_KILOMETER;
        assertTrue("Kilometerage should increase within the expected range",
                resultingKilometerage >= initialKilometerage &&
                        resultingKilometerage <= maxPossibleKilometerage);

        // Validate that damage is within the range (with crash)
        double maxPossibleDamage = initialDamage + MAX_DAMAGE * MAX_CRASH_DAMAGE;
        assertTrue("Damage should increase within the expected range",
                resultingDamage >= initialDamage && resultingDamage <= maxPossibleDamage);
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

    @Test
    public void testAddDamage_addsPositiveDamage() {
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
        double damageToAdd = 3.0;
        double expectedDamage = test.getDamage() + damageToAdd;

        // Act
        test.addDamage(damageToAdd);

        // Assert
        assertEquals(expectedDamage, test.getDamage(), 0.001);
    }

    @Test
    public void testAddDamage_throwsExceptionForNegativeResultingDamage() {
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
            test.addDamage(-10.0);
            fail("IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testCalculateTotalPrice_returnsCorrectValue() {
        // Arrange
        Vehicle test = new Car(
                "Car",
                505,
                "Mitsubishi",
                "Lancer",
                Year.now().getValue() - 5,
                18000.00,
                "White",
                "Manual",
                "FWD",
                168,
                2900.00,
                50000.00,
                10.0,
                false,
                5,
                4,
                true,
                false);

        // Depreciation calculations:
        double ageDepreciation = 18000.00 * 0.05 * 5; // 5% per year
        double kilometerageDepreciation = 50000.00 * 0.02; // $0.02 per km
        double damageDepreciation = 18000.00 * (10.0 / 100) * 0.50; // 50% damage rate
        double totalDepreciation = ageDepreciation + kilometerageDepreciation + damageDepreciation;

        if (totalDepreciation > 18000.00) {
            totalDepreciation = 18000.00;
        }

        double expectedTotalPrice = 18000.00 - totalDepreciation;

        // Act
        double actualTotalPrice = test.calculateTotalPrice();

        // Assert
        assertEquals(expectedTotalPrice, actualTotalPrice, 0.001);
    }
}
