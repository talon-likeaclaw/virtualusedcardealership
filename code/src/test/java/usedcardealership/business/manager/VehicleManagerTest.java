/**
 * Test class for VehicleManager
 * 
 * @author Talon Dunbar
 * @version 11/11/2024
 */

package usedcardealership.business.manager;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import usedcardealership.data.vehicle.*;

public class VehicleManagerTest {

    @Test
    public void testConstructor_initializesInventoryAndDatabase() {
        // Arrange
        List<Vehicle> expectedInventory = new ArrayList<>();
        List<Vehicle> expectedDatabase = new ArrayList<>();

        // Populate the lists with sample vehicles
        expectedInventory.add(new Car("Car", 1, "Toyota", "Camry", 2021, 30000.0, "Blue", "Automatic", "FWD",
                200, 1500.0, 10000.0, 5.0, false, 5, 4, false, false));
        expectedDatabase.add(new SUV("SUV", 2, "Ford", "Explorer", 2019, 35000.0, "Black", "Automatic", "AWD",
                250, 2000.0, 15000.0, 10.0, false, 7, 4, true, false));

        // Act
        VehicleManager test = new VehicleManager(expectedInventory, expectedDatabase);

        // Assert
        assertEquals(expectedInventory, test.getInventory());
        assertEquals(expectedDatabase, test.getDatabase());
    }

    @Test
    public void testAddVehicle_addsNewVehicleToInventoryAndDatabase() {
        // Arrange
        List<Vehicle> inventory = new ArrayList<>();
        List<Vehicle> database = new ArrayList<>();
        VehicleManager test = new VehicleManager(inventory, database);

        Vehicle car = new Car("Car", 1, "Toyota", "Camry", 2021, 30000.0, "Blue", "Automatic",
                "FWD", 200, 1500.0, 10000.0, 5.0, false, 5, 4, false, false);

        // Act
        test.addVehicle(car);

        // Assert
        assertEquals(1, inventory.size());
        assertEquals(1, database.size());
        assertEquals(car, inventory.get(0));
        assertEquals(car, database.get(0));
    }

    @Test
    public void testAddVehicle_doesNotAddDuplicateVehicles() {
        // Arrange
        List<Vehicle> inventory = new ArrayList<>();
        List<Vehicle> database = new ArrayList<>();
        VehicleManager test = new VehicleManager(inventory, database);

        Vehicle car = new Car("Car", 1, "Toyota", "Camry", 2021, 30000.0, "Blue", "Automatic",
                "FWD", 200, 1500.0, 10000.0, 5.0, false, 5, 4, false, false);

        // Act
        test.addVehicle(car);
        test.addVehicle(car);

        // Assert
        assertEquals(1, inventory.size());
        assertEquals(1, database.size());
        assertEquals(car, inventory.get(0));
        assertEquals(car, database.get(0));
    }

    @Test
    public void testRemoveVehicle_removesExistingVehicle() {
        // Arrange
        List<Vehicle> inventory = new ArrayList<>();
        List<Vehicle> database = new ArrayList<>();
        Vehicle car = new Car("Car", 1, "Toyota", "Camry", 2021, 30000.0, "Blue", "Automatic", "FWD",
                200, 1500.0, 10000.0, 5.0, false, 5, 4, false, false);
        inventory.add(car);
        VehicleManager test = new VehicleManager(inventory, database);

        // Act
        test.removeVehicle(car);

        // Assert
        assertEquals(0, inventory.size());
        assertEquals(true, !inventory.contains(car));
    }

    @Test
    public void testRemoveVehicle_doesNothingForNonExistingVehicle() {
        // Arrange
        List<Vehicle> inventory = new ArrayList<>();
        List<Vehicle> database = new ArrayList<>();
        Vehicle car = new Car("Car", 1, "Toyota", "Camry", 2021, 30000.0, "Blue", "Automatic", "FWD", 200,
                1500.0,
                10000.0,
                5.0, false, 5, 4, false, false);
        Vehicle truck = new PickupTruck("PickupTruck", 2, "Ford", "F-150", 2020, 45000.0, "Red", "Automatic",
                "AWD",
                400, 2500.0,
                20000.0, 15.0, false, 5, 4, false, 60.0, 6.5, 10000.0);
        inventory.add(truck);
        VehicleManager test = new VehicleManager(inventory, database);

        // Act
        test.removeVehicle(car);

        // Assert
        assertEquals(1, inventory.size());
        assertEquals(true, inventory.contains(truck));
    }

    @Test
    public void testUpdateVehicle_updatesVehicleInBothLists() {
        // Arrange
        List<Vehicle> inventory = new ArrayList<>();
        List<Vehicle> database = new ArrayList<>();
        Vehicle car = new Car("Car", 1, "Toyota", "Camry", 2021, 30000.0, "Blue", "Automatic", "FWD", 200,
                1500.0,
                10000.0,
                5.0, false, 5, 4, false, false);
        inventory.add(car);
        database.add(car);
        VehicleManager test = new VehicleManager(inventory, database);

        Vehicle updatedCar = new Car("Car", 1, "Toyota", "Camry", 2021, 30000.0, "Red", "Automatic", "FWD", 200,
                1500.0,
                12000.0, 5.0, false, 5, 4, false, false);

        // Act
        test.updateVehicle(updatedCar);

        // Assert
        assertEquals(updatedCar, inventory.get(0));
        assertEquals(updatedCar, database.get(0));
    }
}
