/**
 * Test class for Kilometrage Range Filter
 * 
 * @author Talon Dunbar
 * @version 11/21/2024
 */

package usedcardealership.business.filter;

import usedcardealership.data.vehicle.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class KilometrageFilterTest {
    @Test
    public void testKilometrageFilterWithinRange() {
        // Arrange
        VehicleKilometerageRangeFilter filter = new VehicleKilometerageRangeFilter(5000, 20000);
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
                10000.00,
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
    public void testKilometrageFilterBelowRange() {
        // Arrange
        VehicleKilometerageRangeFilter filter = new VehicleKilometerageRangeFilter(5000, 20000);
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
                3000.00,
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
    public void testKilometrageFilterAboveRange() {
        // Arrange
        VehicleKilometerageRangeFilter filter = new VehicleKilometerageRangeFilter(5000, 20000);
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
                30000.00,
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
    public void testKilometrageFilterLowerBound() {
        // Arrange
        VehicleKilometerageRangeFilter filter = new VehicleKilometerageRangeFilter(5000, 20000);
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
                5000.00,
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
    public void testKilometrageFilterUpperBound() {
        // Arrange
        VehicleKilometerageRangeFilter filter = new VehicleKilometerageRangeFilter(5000, 20000);
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
                20000.00,
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
