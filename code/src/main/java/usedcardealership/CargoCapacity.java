/**
 * CargoCapacity Abstract type.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/6/2024
 */

package usedcardealership;

public abstract class CargoCapacity extends EnclosedVehicle {
    private double cargoCapacity;

    /**
     * CargoCapacity Constructor
     * Initializes the CargoCapacity fields
     * 
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
        super(id, make, model, year, price, color, transmission, driveType, horsepower,
                weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof);
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public String getCommonDetails() {
        return super.getCommonDetails() + "\n" +
                "Cargo Capacity: " + this.cargoCapacity + " cubic feet";
    }

    public double getCargoCapacity() {
        return this.cargoCapacity;
    }
}