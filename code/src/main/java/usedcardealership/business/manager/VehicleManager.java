/**
 * Manages all Vehicles, inventory and database.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/10/2024
 */

package usedcardealership.business.manager;

import java.util.*;

import usedcardealership.business.comparators.*;
import usedcardealership.business.filter.*;
import usedcardealership.data.filehandling.VehicleFileHandler;
import usedcardealership.data.vehicle.*;
import usedcardealership.interaction.PrettyUtils;
import usedcardealership.interaction.Prompter;

public class VehicleManager {
    private List<Vehicle> inventory;
    private List<Vehicle> database;

    /**
     * VehicleManager Constructor
     * Initializes the dealership's inventory and database of vehicles.
     * 
     * @param inventory list of vehicles dealership currently has in inventory.
     * @param database  list of vehicles dealership has seen before.
     */
    public VehicleManager(List<Vehicle> inventory, List<Vehicle> database) {
        if (inventory == null) {
            throw new IllegalArgumentException("Inventory list cannot be null.");
        }
        if (database == null) {
            throw new IllegalArgumentException("Database list cannot be null.");
        }
        this.inventory = inventory;
        this.database = database;
    }

    public List<Vehicle> getInventory() {
        return this.inventory;
    }

    public List<Vehicle> getDatabase() {
        return this.database;
    }

    /**
     * Gets a vehicle based on id input
     * 
     * @param vehicleId the id of the vehicle to check for
     * @return the vehicle with the specified id
     */
    public Vehicle getVehicleById(int vehicleId) {
        if (vehicleId <= 0) {
            throw new IllegalArgumentException("vehicleId must be positive integer.");
        } else {
            for (Vehicle v : this.inventory) {
                if (v.getID() == vehicleId) {
                    return v;
                }
            }
            return null;
        }
    }

    public List<Vehicle> sortVehiclesById(List<Vehicle> vehicles) {
        validateListVehiclesNull(vehicles);
        Collections.sort(vehicles, new VehicleIdCompare());
        return vehicles;
    }

    /**
     * Method for adding a vehicle to the dealership's inventory or database.
     * Only adds if it doesn't already exitst.
     * 
     * @param v the vehicle that is being added.
     */
    public void addVehicle(Vehicle v) {
        validateVehicleNull(v);
        if (!inventory.contains(v)) {
            inventory.add(v);
        }
        if (!database.contains(v)) {
            database.add(v);
        }
    }

    /**
     * Method that removes a vehicle from the dealership's inventory.
     * 
     * @param v the vehicle that is being removed.
     */
    public void removeVehicle(Vehicle v) {
        validateVehicleNull(v);
        inventory.remove(v);
    }

    /**
     * Method that searches for vehicles in the inventory based on criteria.
     * 
     * @param criteria a IFilter sub-type object to determine filtering criteria.
     * @return List<Vehicle> list of filtered vehicles based on criteria input.
     */
    public List<Vehicle> searchInventory(IFilter<Vehicle> criteria) {
        return searchList(criteria, this.inventory);
    }

    /**
     * Method that searches for a vehicles in the database based on criteria.
     * 
     * @param criteria a IFilter sub-type object
     * @return List<Vehicle> list of filtered vehicles based on criteria input.
     */
    public List<Vehicle> searchDatabase(IFilter<Vehicle> criteria) {
        return searchList(criteria, this.database);
    }

    /**
     * Helper method for searching within a list for specific criteria
     * 
     * @param v
     */
    public List<Vehicle> searchList(IFilter<Vehicle> criteria, List<Vehicle> list) {
        if (criteria == null) {
            throw new IllegalArgumentException("Criteria cannot be null.");
        }
        validateListVehiclesNull(list);
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : list) {
            if (criteria.filter(v)) {
                result.add(v);
            }
        }
        return result;
    }

    /**
     * Method that allows us to update a vehicles mutable fields in Lists
     * 
     * @param v vehicle with updated data.
     */
    public void updateVehicle(Vehicle v) {
        validateVehicleNull(v);
        updateVehicleInList(inventory, v);
        updateVehicleInList(database, v);
    }

    /**
     * Helper method that updates a vehicle's information within a given list.
     * 
     * @param updatedVehicle the vehicle with updated data.
     */
    private void updateVehicleInList(List<Vehicle> vehicleList, Vehicle updatedVehicle) {
        validateListVehiclesNull(vehicleList);
        validateVehicleNull(updatedVehicle);
        for (int i = 0; i < vehicleList.size(); i++) {
            if (vehicleList.get(i).equals(updatedVehicle)) {
                // Check the type of updatedVehicle and call correct copy constructor
                if (updatedVehicle instanceof Car) {
                    vehicleList.set(i, new Car((Car) updatedVehicle));
                } else if (updatedVehicle instanceof SUV) {
                    vehicleList.set(i, new SUV((SUV) updatedVehicle));
                } else if (updatedVehicle instanceof Van) {
                    vehicleList.set(i, new Van((Van) updatedVehicle));
                } else if (updatedVehicle instanceof PickupTruck) {
                    vehicleList.set(i, new PickupTruck((PickupTruck) updatedVehicle));
                } else if (updatedVehicle instanceof Motorcycle) {
                    vehicleList.set(i, new Motorcycle((Motorcycle) updatedVehicle));
                } else if (updatedVehicle instanceof RV) {
                    vehicleList.set(i, new RV((RV) updatedVehicle));
                } else {
                    throw new IllegalArgumentException("Unsupported vehicle type.");
                }
                break;
            }
        }
    }

    /**
     * Sorts the list of vehicles based on the given comparator and order
     *
     * @param vehicles   the list of vehicles to sort
     * @param comparator the comparator to use for sorting
     * @param ascending  true for ascending order, false for descending
     */
    public void sortVehicles(List<Vehicle> vehicles, Comparator<Vehicle> comparator, boolean ascending) {
        validateListVehiclesNull(vehicles);
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }
        if (!ascending) {
            comparator = comparator.reversed(); // found in javadocs for comparator
        }
        Collections.sort(vehicles, comparator);
    }

    /**
     * Validates that vehicle is not null
     * 
     * @param v the vehicle to check
     * @throws IllegalArgumentException if null
     */
    public void validateVehicleNull(Vehicle v) {
        if (v == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
    }

    /**
     * Validate that vehicle list is not null
     * 
     * @param vehicles the list of vehicles to check
     * @throws IllegalArgumentException if list is null
     */
    public void validateListVehiclesNull(List<Vehicle> vehicles) {
        if (vehicles == null) {
            throw new IllegalArgumentException("Vehicle list cannot be null.");
        }
    }

    /**
     * Initilizes a VehicleFileLoader and loads from file path
     * 
     * @param filePath the file path to load from
     * @return a list of vehicles loaded from file
     */
    public static List<Vehicle> initializeListVehicle(String filePath) {
        try {
            VehicleFileHandler vehicleLoader = new VehicleFileHandler(filePath);
            return vehicleLoader.load();
        } catch (Exception e) {
            PrettyUtils.printRed("Error loading vehicles from file: " + filePath);
            PrettyUtils.printRed(e.getMessage());
            Prompter.promptEnter();
            return new ArrayList<>();
        }
    }

    /**
     * Displays the all of the unique available criteria to choose from
     * 
     * @param dealership the DealershipManager object
     * @param filterType the method we are filtering by
     */
    public static void displayAvailableCriteria(DealershipManager dealership, String filterType) {
        // Create String hash set for unique values only
        HashSet<String> criteriaSet = new HashSet<>();
        // Depending on filterType, print unique values to choose from
        switch (filterType) {
            case "type":
                for (Vehicle v : dealership.getInventory()) {
                    criteriaSet.add(v.getType());
                }
                break;
            case "make":
                for (Vehicle v : dealership.getInventory()) {
                    criteriaSet.add(v.getMake());
                }
                break;
            case "color":
                for (Vehicle v : dealership.getInventory()) {
                    criteriaSet.add(v.getColor());
                }
                break;
            case "drive":
                for (Vehicle v : dealership.getInventory()) {
                    criteriaSet.add(v.getDriveType());
                }
                break;
            case "trans":
                for (Vehicle v : dealership.getInventory()) {
                    criteriaSet.add(v.getTransmission());
                }
                break;
            default:
                PrettyUtils.printRed("No available criteria to display for this filter.");
                Prompter.promptEnter();
                return;
        }
        // If there are no options to choose from print warning
        if (criteriaSet.size() == 0) {
            PrettyUtils.printRed("No options available.");
            Prompter.promptEnter();
        } else {
            // Convert HashSet to List for sorting
            List<String> sortedCriteria = new ArrayList<>(criteriaSet);
            // Sort alphabetically
            Collections.sort(sortedCriteria);
            PrettyUtils.printYellow("Available options:");
            for (String criteria : sortedCriteria) {
                System.out.println("- " + criteria);
            }
        }
    }

    /**
     * Allows customer to select vehicle from list by ID
     * 
     * @param dealership the DealershipManager object
     * @param vehicles   the list of vehicles to select from
     */
    public static void selectVehiclesFromList(DealershipManager dealership, List<Vehicle> vehicles) {
        boolean inPage = true;
        while (inPage) {
            PrettyUtils.wipe();
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
            // Print out prompt and get user input for sorting
            System.out.println(Prompter.getPrompt("id-sort"));
            String input = Prompter.promptString();
            // If null go back else trim and set to lowercase
            if (input == null) {
                inPage = false;
                break;
            } else {
                input = input.trim().toLowerCase();
            }
            try {
                // Check if input is numeric (assumes it's a vehicle ID)
                int vehicleID = Integer.parseInt(input);
                Vehicle selectedVehicle = dealership.getVehicleManager().getVehicleById(vehicleID);
                if (selectedVehicle != null) {
                    vehicleDetailsMenu(dealership, vehicleID, vehicles);
                } else {
                    PrettyUtils.printRed("\nInvalid Vehicle ID!");
                    Prompter.promptEnter();
                }
            } catch (NumberFormatException e) {
                // Get sorting type and order
                String[] sortingInfo = input.split(" ");
                String sortType = sortingInfo[0];
                // Default to ascending if no "desc"
                boolean ascending = sortingInfo.length < 2 || !sortingInfo[1].equals("desc");
                switch (sortType) {
                    case "id":
                        dealership.getVehicleManager().sortVehicles(vehicles, new VehicleIdCompare(), ascending);
                        System.out.println("\nSorting by ID " + (ascending ? "ascending." : "descending."));
                        break;
                    case "price":
                        dealership.getVehicleManager().sortVehicles(vehicles, new VehiclePriceCompare(), ascending);
                        System.out.println("\nSorting by Price " + (ascending ? "ascending." : "descending."));
                        break;
                    case "year":
                        dealership.getVehicleManager().sortVehicles(vehicles, new VehicleYearCompare(), ascending);
                        System.out.println("\nSorting by Year " + (ascending ? "ascending." : "descending."));
                        break;
                    case "kilometrage":
                        dealership.getVehicleManager().sortVehicles(vehicles, new VehicleKilometerageCompare(),
                                ascending);
                        System.out.println("\nSorting by Kilometrage " + (ascending ? "ascending." : "descending."));
                        break;
                    case "damage":
                        dealership.getVehicleManager().sortVehicles(vehicles, new VehicleDamageCompare(), ascending);
                        System.out.println("\nSorting by Damage " + (ascending ? "ascending." : "descending."));
                        break;
                    default:
                        PrettyUtils.printRed("\nInvalid option. Please enter a valid vehicle ID or sorting type.");
                }
                Prompter.promptEnter();
            }
        }
    }

    /**
     * Menu that asks user if they want to purchase vehicle or go back
     * 
     */
    // TODO: Add a straight to checkoout option
    private static void vehicleDetailsMenu(DealershipManager dealership, int vehicleId, List<Vehicle> vehicles) {
        boolean inPage = true;
        int testDriveCount = 0;
        Vehicle vehicle = dealership.getVehicleManager().getVehicleById(vehicleId);
        while (inPage) {
            PrettyUtils.wipe();
            System.out.println(vehicle.getFullDetails());
            PrettyUtils.printYellow("\nWould you like to:");
            String menu = PrettyUtils.returnYellow("1:") + " Test Drive Vehicle\n" +
                    PrettyUtils.returnYellow("2:") + " Add Vehicle to Cart\n" +
                    PrettyUtils.returnYellow("0:") + " Return to Vehicle List";
            int choice = Prompter.promptOption(menu, 2);
            if (choice == -1) {
                continue;
            }
            switch (choice) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    if (testDriveCount < 1) {
                        try {
                            dealership.getVehicleManager().getVehicleById(vehicleId).testDrive();
                            Prompter.promptEnter();
                            testDriveCount++;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        PrettyUtils.printRed("\nYou just test drove this vehicle!");
                        Prompter.promptEnter();
                        PrettyUtils.wipe();
                    }
                    break;
                case 2:
                    CustomerManager.addVehicleToCart(dealership, vehicle, vehicles);
                    break;
            }
        }
    }
}
