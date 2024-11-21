/**
 * Test class for Drive Type Filter
 * 
 * @author Talon Dunbar
 * @version 11/21/2024
 */

package usedcardealership.business.filter;

import usedcardealership.data.vehicle.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class DriveTypeFilterTest {
    @Test
    public void testFilterMatchingDriveType() {
        // Arrange
        VehicleDriveFilter filter = new VehicleDriveFilter("FWD");
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
    public void testFilterNonMatchingDriveType() {
        // Arrange
        VehicleDriveFilter filter = new VehicleDriveFilter("AWD");
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
    public void testDriveFilterCaseInsensitiveMatching() {
        // Arrange
        VehicleDriveFilter filter = new VehicleDriveFilter("fwd");
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