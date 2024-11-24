package usedcardealership.business.ComparatorTests;

import usedcardealership.data.vehicle.Car;
import usedcardealership.data.vehicle.Vehicle;
import usedcardealership.business.comparators.VehicleKilometerageCompare;
import static org.junit.Assert.*;
import org.junit.Test;

public class VehicleKilometerageCompareTests {

    @Test
    public void testCompareWhenVehicle1KilometerageIsGreater() {
        // Arrange
        VehicleKilometerageCompare comparator = new VehicleKilometerageCompare();
        Vehicle vehicle1 = new Car(
                "Car", 505, "Mitsubishi", "Lancer", 2018, 18000.00, "White", "Manual", "FWD",
                168, 2900.00, 45000.00, 4.5, false, 5, 4, true, false);
        Vehicle vehicle2 = new Car(
                "Car", 504, "Honda", "Civic", 2020, 22000.00, "Black", "Automatic", "FWD",
                160, 2800.00, 40000.00, 4.7, false, 5, 4, true, false);

        // Act & Assert
        assertTrue(comparator.compare(vehicle1, vehicle2) > 0);
    }

    @Test
    public void testCompareWhenVehicle1KilometerageIsSmaller() {
        // Arrange
        VehicleKilometerageCompare comparator = new VehicleKilometerageCompare();
        Vehicle vehicle1 = new Car(
                "Car", 503, "Toyota", "Corolla", 2017, 15000.00, "Red", "Manual", "FWD",
                170, 2800.00, 35000.00, 4.6, false, 5, 4, true, false);
        Vehicle vehicle2 = new Car(
                "Car", 504, "Honda", "Civic", 2020, 22000.00, "Black", "Automatic", "FWD",
                160, 2800.00, 40000.00, 4.7, false, 5, 4, true, false);

        // Act & Assert
        assertTrue(comparator.compare(vehicle1, vehicle2) < 0);
    }

    @Test
    public void testCompareWhenKilometeragesAreEqual() {
        // Arrange
        VehicleKilometerageCompare comparator = new VehicleKilometerageCompare();
        Vehicle vehicle1 = new Car(
                "Car", 505, "Toyota", "Corolla", 2017, 15000.00, "Red", "Manual", "FWD",
                170, 2800.00, 35000.00, 4.6, false, 5, 4, true, false);
        Vehicle vehicle2 = new Car(
                "Car", 506, "Honda", "Civic", 2020, 22000.00, "Black", "Automatic", "FWD",
                160, 2800.00, 35000.00, 4.7, false, 5, 4, true, false);

        // Act & Assert
        assertEquals(0, comparator.compare(vehicle1, vehicle2));
    }
}
