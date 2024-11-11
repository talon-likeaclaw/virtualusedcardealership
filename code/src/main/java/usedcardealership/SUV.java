/**
 * Represents Sport Utility Vehicle objects.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/10/2024
 */

package usedcardealership;

public class SUV extends EnclosedVehicle {
    private boolean hasThirdRowSeating;

    /**
     * SUV Constructor
     * Initializes the SUV fields
     * 
     * @param id                 the SUV's unique identifier
     * @param make               the company that makes the SUV
     * @param model              the name of the SUV's model
     * @param year               the year the SUV was released
     * @param price              the initial price of the SUV
     * @param color              the color of the SUV's paint
     * @param transmission       the type of transmission (Auto, Manual, CVT)
     * @param driveType          the drive type of the SUV
     * @param horsepower         the SUV's engine's horsepower
     * @param weight             the weight of the SUV
     * @param mileage            the number of kilometers the SUV has
     * @param damage             the damage of the SUV (00.00 - 100.00)
     * @param isElectric         if the SUV is electric of not
     * @param numSeats           the number of seats of the SUV
     * @param numDoor            the number of doors of the SUV
     * @param hasSunRoof         if the SUV has a sunroof or not
     * @param hasThirdRowSeating if the SUV has a third row of seats
     */
    public SUV(
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
            double mileage,
            double damage,
            boolean isElectric,
            int numSeats,
            int numDoors,
            boolean hasSunRoof,
            boolean hasThirdRowSeating) {
        super(id, make, model, year, price, color, transmission, driveType, horsepower, weight, mileage, damage,
                isElectric, numSeats, numDoors, hasSunRoof);
        this.hasThirdRowSeating = hasThirdRowSeating;
    }

    @Override
    public String toString() {
        return "Type: SUV\n" +
                getCommonDetails() + "\n" +
                "Third Row Seating: " + (this.hasThirdRowSeating ? "Yes" : "No");
    }

    public boolean hasThirdRowSeating() {
        return this.hasThirdRowSeating;
    }
}