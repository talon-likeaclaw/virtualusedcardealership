/**
 * CargoCapacity Abstract type.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/10/2024
 */

package usedcardealership.data.vehicle;

public abstract class CargoCapacity extends EnclosedVehicle {
    private double cargoCapacity;

    /**
     * CargoCapacity Constructor
     * Initializes the CargoCapacity fields
     * 
     * @param type          the CargoCapacity's type
     * @param id            the CargoCapacity's unique identifier
     * @param make          the company that makes the CargoCapacity
     * @param model         the name of the CargoCapacity's model
     * @param year          the year the CargoCapacity was released
     * @param price         the initial price of the CargoCapacity
     * @param color         the color of the CargoCapacity's paint
     * @param transmission  the type of transmission (Auto, Manual, CVT)
     * @param driveType     the drive type of the CargoCapacity
     * @param horsepower    the CargoCapacity's engine's horsepower
     * @param weight        the weight of the CargoCapacity
     * @param kilometerage  the number of kilometers the CargoCapacity has
     * @param damage        the damage of the CargoCapacity (00.00 - 100.00)
     * @param isElectric    if the CargoCapacity is electric of not
     * @param numSeats      the number of seats of the CargoCapacity
     * @param numDoor       the number of doors of the CargoCapacity
     * @param hasSunRoof    if the CargoCapacity has a sunroof or not
     * @param cargoCapacity the CargoCapacity's cargo capacity
     */
    public CargoCapacity(
            String type,
            int id,
            String make,
            String model,
            int year,
            double price,
            String color,
            String transmission,
            String driveType,
            int horsepower,
            double weight,
            double kilometerage,
            double damage,
            boolean isElectric,
            int numSeats,
            int numDoors,
            boolean hasSunRoof,
            double cargoCapacity) {
        super(type, id, make, model, year, price, color, transmission, driveType, horsepower,
                weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof);
        this.cargoCapacity = cargoCapacity;
    }

    /**
     * Copy constructor for CargoCapacity.
     * Creates a new CargoCapacity instance with the same values as input.
     * 
     * @param c the CargoCapacity instance to copy
     */
    public CargoCapacity(CargoCapacity c) {
        super(c);
        if (c == null) {
            throw new IllegalArgumentException("Cannot copy from a null Cargo Capacity.");
        }
        this.cargoCapacity = c.cargoCapacity;
    }

    /**
     * Provides a formatted string containing the
     * common details of the CargoCapacity Vehicles
     * 
     * @return String - a formatted string with CargoCapacity details:
     * 
     *         ID: <vehicle id>
     *         Make: <vehicle make>
     *         Model: <vehicle model>
     *         Year: <vehicle year>
     *         Price: $<calculated total price>
     *         Color: <vehicle color>
     *         Transmission: <transmission type>
     *         Drive Type: <drive type>
     *         Horsepower: <horsepower>
     *         Weight: <weight> lbs
     *         Kilometerage: <kilometerage> km
     *         Damage: <damage>%
     *         Electric: <True/False>
     *         Seats: <number of seats>
     *         Doors: <number of doors>
     *         Sunroof: <Yes/No>
     *         Cargo Capacity: <cargo capacity> cu ft
     */
    @Override
    public String getCommonDetails() {
        return super.getCommonDetails() + "\n" +
                "Cargo Capacity: " + this.cargoCapacity + " cu ft";
    }

    public abstract String getFullDetails();

    public double getCargoCapacity() {
        return this.cargoCapacity;
    }
}