/**
 * Represents Van objects.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/10/2024
 */

package usedcardealership.data.vehicle;

import usedcardealership.interaction.PrettyUtils;

public class Van extends CargoCapacity {
    private boolean hasSlidingDoors;

    /**
     * Van Constructor
     * Initializes the Van fields
     * 
     * @param type            the Van's type
     * @param id              the Van's unique identifier
     * @param make            the company that makes the Van
     * @param model           the name of the Van's model
     * @param year            the year the Van was released
     * @param price           the initial price of the Van
     * @param color           the color of the Van's paint
     * @param transmission    the type of transmission (Auto, Manual, CVT)
     * @param driveType       the drive type of the Van
     * @param horsepower      the Van's engine's horsepower
     * @param weight          the weight of the Van
     * @param kilometerage    the number of kilometers the Van has
     * @param damage          the damage of the Van (00.00 - 100.00)
     * @param isElectric      if the Van is electric of not
     * @param numSeats        the number of seats of the Van
     * @param numDoor         the number of doors of the Van
     * @param hasSunRoof      if the Van has a sunroof or not
     * @param cargoCapacity   the Van's cargo capacity
     * @param hasSlidingDoors if the Van has sliding doors or not
     */
    public Van(
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
            double cargoCapacity,
            boolean hasSlidingDoors) {
        super(type, id, make, model, year, price, color, transmission, driveType, horsepower, weight,
                kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, cargoCapacity);
        this.hasSlidingDoors = hasSlidingDoors;
    }

    /**
     * Copy constructor for Van.
     * Creates a new Van instance with the same values as the given Van.
     * 
     * @param v the Van to copy
     */
    public Van(Van v) {
        super(v);
        this.hasSlidingDoors = v.hasSlidingDoors;
    }

    @Override
    public String getFullDetails() {
        return getCommonDetails() + "\n" +
                PrettyUtils.returnCyan("Sliding Doors: ") +
                (this.hasSlidingDoors ? PrettyUtils.returnGreen("Yes") : PrettyUtils.returnRed("No"));
    }

    public boolean hasSlidingDoors() {
        return this.hasSlidingDoors;
    }

    /**
     * Returns an array of strings representing the fields of the Van
     * for CSV conversion
     *
     * @return String[] representing the fields of the Van
     */
    @Override
    public String[] toCSVFields() {
        return new String[] {
                getType(),
                String.valueOf(getID()),
                getMake(),
                getModel(),
                String.valueOf(getYear()),
                String.valueOf(getPrice()),
                getColor(),
                getTransmission(),
                getDriveType(),
                String.valueOf(getHorsepower()),
                String.valueOf(getWeight()),
                String.valueOf(getKilometerage()),
                String.valueOf(getDamage()),
                String.valueOf(isElectric()),
                String.valueOf(getNumSeats()),
                String.valueOf(getNumDoors()),
                String.valueOf(hasSunRoof()),
                String.valueOf(getCargoCapacity()),
                String.valueOf(hasSlidingDoors())
        };
    }
}
