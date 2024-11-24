/**
 * EnclosedVehicle Abstract type.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/10/2024
 */

package usedcardealership.data.vehicle;

public abstract class EnclosedVehicle extends Vehicle {
    private int numSeats;
    private int numDoors;
    private boolean hasSunRoof;

    /**
     * EnclosedVehicle Constructor
     * Initializes the EnclosedVehicle fields
     * 
     * @param type         the EnclosedVehicle's type
     * @param id           the EnclosedVehicle's unique identifier
     * @param make         the company that makes the EnclosedVehicle
     * @param model        the name of the EnclosedVehicle's model
     * @param year         the year the EnclosedVehicle was released
     * @param price        the initial price of the EnclosedVehicle
     * @param color        the color of the EnclosedVehicle's paint
     * @param transmission the type of transmission (Auto, Manual, CVT)
     * @param driveType    the drive type of the EnclosedVehicle
     * @param horsepower   the EnclosedVehicle's engine's horsepower
     * @param weight       the weight of the EnclosedVehicle
     * @param kilometerage the number of kilometers the EnclosedVehicle has
     * @param damage       the damage of the EnclosedVehicle (00.00 - 100.00)
     * @param isElectric   if the EnclosedVehicle is electric of not
     * @param numSeats     the number of seats of the EnclosedVehicle
     * @param numDoor      the number of doors of the EnclosedVehicle
     * @param hasSunRoof   if the EnclosedVehicle has a sunroof or not
     */
    public EnclosedVehicle(
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
            boolean hasSunRoof) {
        super(type, id, make, model, year, price, color, transmission, driveType,
                horsepower, weight, kilometerage, damage, isElectric);
        if (numSeats < 1 || numSeats > 30) {
            throw new IllegalArgumentException("Number of seats must be between 1 and 30.");
        }
        if (numDoors < 1 || numDoors > 10) {
            throw new IllegalArgumentException("Number of doors must be between 1 and 10.");
        }
        this.numSeats = numSeats;
        this.numDoors = numDoors;
        this.hasSunRoof = hasSunRoof;
    }

    /**
     * Copy constructor for EnclosedVehicle.
     * Creates a new EnclosedVehicle instance with the same values as input.
     * 
     * @param e the EnclosedVehicle to copy
     */
    public EnclosedVehicle(EnclosedVehicle e) {
        super(e);
        if (e == null) {
            throw new IllegalArgumentException("Cannot copy from a null Enclosed Vehicle.");
        }
        this.numSeats = e.numSeats;
        this.numDoors = e.numDoors;
        this.hasSunRoof = e.hasSunRoof;
    }

    /**
     * Provides a formatted string containing
     * the common details of the EnclosedVehicle.
     * 
     * @return String - a formatted string with EnclosedVehicle's common details:
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
     */
    @Override
    public String getCommonDetails() {
        return super.getCommonDetails() + "\n" +
                "Seats: " + this.numSeats + "\n" +
                "Doors: " + this.numDoors + "\n" +
                "Sunroof: " + (this.hasSunRoof ? "Yes" : "No");
    }

    public abstract String getFullDetails();

    public int getNumSeats() {
        return this.numSeats;
    }

    public int getNumDoors() {
        return this.numDoors;
    }

    public boolean hasSunRoof() {
        return this.hasSunRoof;
    }
}
