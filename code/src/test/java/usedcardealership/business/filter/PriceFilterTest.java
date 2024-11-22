/**
 * Test class for Price Range Filter
 * 
 * @author Talon Dunbar
 * @version 11/21/2024
 */

package usedcardealership.business.filter;

import usedcardealership.data.vehicle.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.time.*;

public class PriceFilterTest {
    @Test
    public void testPriceFilterWithinRange() {
        // Arrange
        VehiclePriceRangeFilter filter = new VehiclePriceRangeFilter(5000, 20000);
        Car test = new Car(
                "Car",
                505,
                "Mitsubishi",
                "Lancer",
                Year.now().getValue(),
                18000.00,
                "Red",
                "Manual",
                "FWD",
                168,
                2900.00,
                0.0,
                0.0,
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
    public void testPriceFilterBelowRange() {
        // Arrange
        VehiclePriceRangeFilter filter = new VehiclePriceRangeFilter(5000, 20000);
        Car test = new Car(
                "Car",
                505,
                "Mitsubishi",
                "Lancer",
                Year.now().getValue(),
                2000.00,
                "Red",
                "Manual",
                "FWD",
                168,
                2900.00,
                0.0,
                0.0,
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
    public void testPriceFilterAboveRange() {
        // Arrange
        VehiclePriceRangeFilter filter = new VehiclePriceRangeFilter(5000, 20000);
        Car test = new Car(
                "Car",
                505,
                "Mitsubishi",
                "Lancer",
                Year.now().getValue(),
                21000.00,
                "Red",
                "Manual",
                "FWD",
                168,
                2900.00,
                0.0,
                0.0,
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
    public void testPriceFilterLowerBound() {
        // Arrange
        VehiclePriceRangeFilter filter = new VehiclePriceRangeFilter(5000, 20000);
        Car test = new Car(
                "Car",
                505,
                "Mitsubishi",
                "Lancer",
                Year.now().getValue(),
                5000.00,
                "Red",
                "Manual",
                "FWD",
                168,
                2900.00,
                0.0,
                0.0,
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
    public void testPriceFilterUpperBound() {
        // Arrange
        VehiclePriceRangeFilter filter = new VehiclePriceRangeFilter(5000, 20000);
        Car test = new Car(
                "Car",
                505,
                "Mitsubishi",
                "Lancer",
                Year.now().getValue(),
                20000.00,
                "Red",
                "Manual",
                "FWD",
                168,
                2900.00,
                0.0,
                0.0,
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
