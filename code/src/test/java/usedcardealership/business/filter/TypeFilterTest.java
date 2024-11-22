/**
 * Test class for Vehicle Type Filter
 * 
 * @author Talon Dunbar
 * @version 11/21/2024
 */

package usedcardealership.business.filter;

import usedcardealership.data.vehicle.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class TypeFilterTest {
    @Test
    public void testFilterMatchingType() {
        // Arrange
        VehicleTypeFilter filter = new VehicleTypeFilter("Car");
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
    public void testFilterNonMatchingType() {
        // Arrange
        VehicleTypeFilter filter = new VehicleTypeFilter("Van");
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
        VehicleTypeFilter filter = new VehicleTypeFilter("CAR");
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