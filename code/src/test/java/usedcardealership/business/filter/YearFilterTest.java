/**
 * Test class for Year Range Filter
 * 
 * @author Talon Dunbar
 * @version 11/21/2024
 */

package usedcardealership.business.filter;

import usedcardealership.data.vehicle.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class YearFilterTest {
    @Test
    public void testYearFilterWithinRange() {
        // Arrange
        VehicleYearRangeFilter filter = new VehicleYearRangeFilter(2017, 2019);
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
    public void testYearFilterBelowRange() {
        // Arrange
        VehicleYearRangeFilter filter = new VehicleYearRangeFilter(2020, 2022);
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
    public void testYearFilterAboveRange() {
        // Arrange
        VehicleYearRangeFilter filter = new VehicleYearRangeFilter(2016, 2017);
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
    public void testYearFilterLowerBound() {
        // Arrange
        VehicleYearRangeFilter filter = new VehicleYearRangeFilter(2018, 2020);
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
    public void testYearFilterUpperBound() {
        // Arrange
        VehicleYearRangeFilter filter = new VehicleYearRangeFilter(2016, 2018);
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
