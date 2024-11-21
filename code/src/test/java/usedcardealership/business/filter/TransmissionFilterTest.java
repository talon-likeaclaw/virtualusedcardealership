/**
 * Test class for Transmission Filter
 * 
 * @author Talon Dunbar
 * @version 11/21/2024
 */

package usedcardealership.business.filter;

import usedcardealership.data.vehicle.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class TransmissionFilterTest {
    @Test
    public void testFilterMatchingTransmission() {
        // Arrange
        VehicleTransmissionFilter filter = new VehicleTransmissionFilter("Manual");
        Car test = new Car(
                "Car",
                505,
                "Mitsubishi",
                "Lancer",
                2018,
                18000.00,
                "Red",
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
        boolean result = filter.filter(test);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testFilterNonMatchingTransmission() {
        // Arrange
        VehicleTransmissionFilter filter = new VehicleTransmissionFilter("Automatic");
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

        // Act
        boolean result = filter.filter(test);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testFilterCaseInsensitiveMatching() {
        // Arrange
        VehicleTransmissionFilter filter = new VehicleTransmissionFilter("MANUAL");
        Car test = new Car(
                "Car",
                505,
                "Mitsubishi",
                "Lancer",
                2018,
                18000.00,
                "Red",
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
        boolean result = filter.filter(test);

        // Assert
        assertTrue(result);
    }
}