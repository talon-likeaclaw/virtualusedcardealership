package usedcardealership.business.ComparatorTests;

import usedcardealership.data.vehicle.Car;
import usedcardealership.data.vehicle.Vehicle;
import usedcardealership.business.comparators.VehicleDamageCompare;
import static org.junit.Assert.*;
import org.junit.Test;

public class VehicleDamageCompareTests {

    @Test
    public void testCompareWhenVehicle1DamageIsGreater() {
        // Arrange
        VehicleDamageCompare comparator = new VehicleDamageCompare();
        Vehicle vehicle1 = new Car(
                "Car", 505, "Mitsubishi", "Lancer", 2018, 18000.00, "White", "Manual", "FWD",
                168, 2900.00, 45000.00, 4.5, false, 5, 4, true, false);
        vehicle1.addDamage(100); // Set damage for vehicle1
        Vehicle vehicle2 = new Car(
                "Car", 504, "Honda", "Civic", 2020, 22000.00, "Black", "Automatic", "FWD",
                160, 2800.00, 40000.00, 4.7, false, 5, 4, true, false);
        vehicle2.addDamage(50); // Set damage for vehicle2

        // Act & Assert
        assertTrue("Vehicle 1 damage should be greater than Vehicle 2 damage", comparator.compare(vehicle1, vehicle2) > 0);
    }

    @Test
    public void testCompareWhenVehicle1DamageIsSmaller() {
        // Arrange
        VehicleDamageCompare comparator = new VehicleDamageCompare();
        Vehicle vehicle1 = new Car(
                "Car", 503, "Toyota", "Corolla", 2017, 15000.00, "Red", "Manual", "FWD",
                170, 2800.00, 35000.00, 4.6, false, 5, 4, true, false);
        vehicle1.addDamage(30); // Set damage for vehicle1
        Vehicle vehicle2 = new Car(
                "Car", 504, "Honda", "Civic", 2020, 22000.00, "Black", "Automatic", "FWD",
                160, 2800.00, 40000.00, 4.7, false, 5, 4, true, false);
        vehicle2.addDamage(50); // Set damage for vehicle2

        // Act & Assert
        assertTrue("Vehicle 1 damage should be smaller than Vehicle 2 damage", comparator.compare(vehicle1, vehicle2) < 0);
    }

    @Test
    public void testCompareWhenDamagesAreEqual() {
        // Arrange
        VehicleDamageCompare comparator = new VehicleDamageCompare();
        Vehicle vehicle1 = new Car(
                "Car", 505, "Toyota", "Corolla", 2017, 15000.00, "Red", "Manual", "FWD",
                170, 2800.00, 35000.00, 0, false, 5, 4, true, false);
        vehicle1.addDamage(50); // Set damage for vehicle1
        Vehicle vehicle2 = new Car(
                "Car", 505, "Honda", "Civic", 2020, 22000.00, "Black", "Automatic", "FWD",
                160, 2800.00, 40000.00, 0, false, 5, 4, true, false);
        vehicle2.addDamage(50); // Set damage for vehicle2

        // Act & Assert
        assertEquals("Damages should be equal, so comparison should return 0", 0, comparator.compare(vehicle1, vehicle2));
    }

    @Test
    public void testCompareWhenBothVehiclesHaveNoDamage() {
        // Arrange
        VehicleDamageCompare comparator = new VehicleDamageCompare();
        Vehicle vehicle1 = new Car(
                "Car", 505, "Toyota", "Corolla", 2017, 15000.00, "Red", "Manual", "FWD",
                170, 2800.00, 35000.00, 4.6, false, 5, 4, true, false);
        vehicle1.addDamage(0); // Set no damage for vehicle1
        Vehicle vehicle2 = new Car(
                "Car", 505, "Honda", "Civic", 2020, 22000.00, "Black", "Automatic", "FWD",
                160, 2800.00, 40000.00, 4.7, false, 5, 4, true, false);
        vehicle2.addDamage(0); // Set no damage for vehicle2

        // Act & Assert
        assertEquals(-1, comparator.compare(vehicle1, vehicle2));
    }
}
