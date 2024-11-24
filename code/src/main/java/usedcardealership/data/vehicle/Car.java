/**
 * Represents Car objects.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/10/2024
 */

package usedcardealership.data.vehicle;

public class Car extends EnclosedVehicle {
    private boolean isConvertible;

    /**
     * Car Constructor
     * Initializes the Car fields
     * 
     * @param type          the Car's type
     * @param id            the Car's unique identifier
     * @param make          the company that makes the Car
     * @param model         the name of the Car's model
     * @param year          the year the Car was released
     * @param price         the initial price of the Car
     * @param color         the color of the Car's paint
     * @param transmission  the type of transmission (Auto, Manual, CVT)
     * @param driveType     the drive type of the Car
     * @param horsepower    the Car's engine's horsepower
     * @param weight        the weight of the Car
     * @param kilometerage  the number of kilometers the Car has
     * @param damage        the damage of the Car (00.00 - 100.00)
     * @param isElectric    if the Car is electric of not
     * @param numSeats      the number of seats of the Car
     * @param numDoor       the number of doors of the Car
     * @param hasSunRoof    if the Car has a sunroof or not
     * @param isConvertible if the Car is able to remove roof
     */
    public Car(
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
            boolean isConvertible) {
        super(type, id, make, model, year, price, color, transmission, driveType, horsepower,
                weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof);
        if (damage < 0 || damage > 100) {
            throw new IllegalArgumentException("Damage percentage must be between 0.00 and 100.00");
        }
        if (year < 1886) {
            throw new IllegalArgumentException("Year must be 1886 or later.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        if (kilometerage < 0) {
            throw new IllegalArgumentException("Kilometrage cannot be negative.");
        }
        this.isConvertible = isConvertible;
    }

    /**
     * Copy constructor for Car.
     * Creates a new Car instance with the same values as the given Car.
     * 
     * @param c the Car to copy
     */
    public Car(Car c) {
        super(c);
        if (c == null) {
            throw new IllegalArgumentException("Cannot copy from a null Car.");
        }
        this.isConvertible = c.isConvertible;
    }

    @Override
    public String getFullDetails() {
        return getCommonDetails() + "\n" +
                "Convertible: " + (this.isConvertible ? "Yes" : "No");
    }

    public boolean isConvertible() {
        return this.isConvertible;
    }

    /**
     * Returns an array of strings representing the fields of the Car
     * for CSV conversion
     *
     * @return String[] representing the fields of the Car
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
                String.valueOf(this.getNumSeats()),
                String.valueOf(this.getNumDoors()),
                String.valueOf(this.hasSunRoof()),
                String.valueOf(this.isConvertible)
        };
    }
}