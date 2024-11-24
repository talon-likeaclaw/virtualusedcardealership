package usedcardealership.business.ComparatorTests;

import usedcardealership.data.vehicle.Car;
import usedcardealership.data.vehicle.PickupTruck;
import usedcardealership.data.vehicle.Vehicle;
import usedcardealership.business.comparators.VehicleTypeCompare;
import static org.junit.Assert.*;
import org.junit.Test;

public class VehicleTypeCompareTests {

    @Test
    public void testCompareWhenVehicle1TypeIsLexicographicallyGreater() {
        // Arrange
        VehicleTypeCompare comparator = new VehicleTypeCompare();
        Vehicle truck = new PickupTruck("PickupTruck", 2, "Ford", "F-150", 2020, 45000.0, "Red", "Automatic",
                "AWD",
                400, 2500.0,
                20000.0, 15.0, false, 5, 4, false, 60.0, 6.5, 10000.0);
        Vehicle car = new Car(
                "Car", 504, "Honda", "Civic", 2020, 22000.00, "Black", "Automatic", "FWD",
                160, 2800.00, 40000.00, 4.7, false, 5, 4, true, false);

        // Act & Assert
        assertTrue(comparator.compare(truck, car) > 0);
    }

    @Test
    public void testCompareWhenVehicle1TypeIsLexicographicallySmaller() {
        // Arrange
        VehicleTypeCompare comparator = new VehicleTypeCompare();
        Vehicle truck = new PickupTruck("PickupTruck", 2, "Ford", "F-150", 2020, 45000.0, "Red", "Automatic",
                "AWD",
                400, 2500.0,
                20000.0, 15.0, false, 5, 4, false, 60.0, 6.5, 10000.0);
        Vehicle car = new Car(
                "Car", 504, "Honda", "Civic", 2020, 22000.00, "Black", "Automatic", "FWD",
                160, 2800.00, 40000.00, 4.7, false, 5, 4, true, false);

        // Act & Assert
        assertTrue(comparator.compare(car, truck) < 0);
    }

    @Test
    public void testCompareWhenTypesAreEqual() {
        // Arrange
        VehicleTypeCompare comparator = new VehicleTypeCompare();
        Vehicle vehicle1 = new Car(
                "Car", 505, "Toyota", "Corolla", 2017, 15000.00, "Red", "Manual", "FWD",
                170, 2800.00, 35000.00, 4.6, false, 5, 4, true, false);
        Vehicle vehicle2 = new Car(
                "Car", 505, "Honda", "Civic", 2020, 22000.00, "Black", "Automatic", "FWD",
                160, 2800.00, 40000.00, 4.7, false, 5, 4, true, false);

        // Act & Assert
        assertEquals(0, comparator.compare(vehicle1, vehicle2));
    }
}
