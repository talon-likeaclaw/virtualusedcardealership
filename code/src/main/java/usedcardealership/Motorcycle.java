/**
 * Represents Motorcycle objects.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/6/2024
 */

package usedcardealership;

public class Motorcycle extends Vehicle {
    private double engineCC;
    private String handlebarType;

    /**
     * Motorcycle Constructor
     * Initializes the Motorcycle fields
     * 
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
     * @param mileage       the number of kilometers the Motorcycle has on the gauge
     * @param damage        the damage of the Motorcycle (00.00 - 100.00)
     * @param isElectric    if the Motorcycle is electric of not
     * @param engineCC      the Motorcycle engine's capcity
     * @param handlebarType the Motorcycle's handlebar type
     */
    public Motorcycle(int id, String make, String model, int year, double price, String color, String transmission,
            String driveType, int horsepower, double weight, double mileage, double damage, boolean isElectric,
            double engineCC, String handlebarType) {
        super(id, make, model, year, price, color, transmission, driveType, horsepower, weight, mileage, damage, isElectric);
        throw new UnsupportedOperationException("Not written yet");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not written yet");
    }

    public double getEngineCC() {
        throw new UnsupportedOperationException("Not written yet");
    }

    public String getHandleType() {
        throw new UnsupportedOperationException("Not written yet");
    }
}
