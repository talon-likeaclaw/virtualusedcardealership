/**
 * Represents Motorcycle objects.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/10/2024
 */

package usedcardealership.data.vehicle;

public class Motorcycle extends Vehicle {
    private double engineCC;
    private String handlebarType;

    /**
     * Motorcycle Constructor
     * Initializes the Motorcycle fields
     * 
     * @param type          the Motorcycle's type
     * @param id            the Motorcycle's unique identifier
     * @param make          the company that makes the Motorcycle
     * @param model         the name of the Motorcycle's model
     * @param year          the year the Motorcycle was released
     * @param price         the initial price of the Motorcycle
     * @param color         the color of the Motorcycle's paint
     * @param transmission  the type of transmission (Auto, Manual, CVT)
     * @param driveType     the drive type of the Motorcycle
     * @param horsepower    the Motorcycle's engine's horsepower
     * @param weight        the weight of the Motorcycle
     * @param kilometerage  the number of kilometers the Motorcycle has on the gauge
     * @param damage        the damage of the Motorcycle (00.00 - 100.00)
     * @param isElectric    if the Motorcycle is electric of not
     * @param engineCC      the Motorcycle engine's capcity
     * @param handlebarType the Motorcycle's handlebar type
     */
    public Motorcycle(
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
            double engineCC,
            String handlebarType) {
        super(type, id, make, model, year, price, color, transmission, driveType,
                horsepower, weight, kilometerage, damage, isElectric);
        this.engineCC = engineCC;
        this.handlebarType = handlebarType;
    }

    /**
     * Copy constructor for Motorcycle.
     * Creates a new Motorcycle instance with the same values as input.
     * 
     * @param m the Motorcycle to copy
     */
    public Motorcycle(Motorcycle m) {
        super(m);
        this.engineCC = m.engineCC;
        this.handlebarType = m.handlebarType;
    }

    @Override
    public String toString() {
        return this.getID() + " " + this.getMake() + " " + this.getModel() + " " + this.getYear() + " - " + this.getTransmission() + ", " + this.getDriveType();
    }

    public String getFullDetails() {
        return getCommonDetails() + "\n" +
        "Engine Capacity: " + this.engineCC + " cc\n" +
        "Handlebar Type: " + this.handlebarType;
    }

    public double getEngineCC() {
        return this.engineCC;
    }

    public String getHandleType() {
        return this.handlebarType;
    }
}
