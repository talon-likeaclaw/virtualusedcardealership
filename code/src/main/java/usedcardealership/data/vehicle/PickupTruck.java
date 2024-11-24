/**
 * Represents Pickup Truck objects,
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/10/2024
 */

package usedcardealership.data.vehicle;

import usedcardealership.interaction.PrettyUtils;

public class PickupTruck extends CargoCapacity {
    private double bedLength;
    private double towingCapacity;

    /**
     * PickupTruck Constructor
     * Initializes the PickupTruck fields
     * 
     * @param type           the PickupTruck's type
     * @param id             the PickupTruck's unique identifier
     * @param make           the company that makes the PickupTruck
     * @param model          the name of the PickupTruck's model
     * @param year           the year the PickupTruck was released
     * @param price          the initial price of the PickupTruck
     * @param color          the color of the PickupTruck's paint
     * @param transmission   the type of transmission (Auto, Manual, CVT)
     * @param driveType      the drive type of the PickupTruck
     * @param horsepower     the PickupTruck's engine's horsepower
     * @param weight         the weight of the PickupTruck
     * @param kilometerage   the number of kilometers the PickupTruck has
     * @param damage         the damage of the PickupTruck (00.00 - 100.00)
     * @param isElectric     if the PickupTruck is electric of not
     * @param numSeats       the number of seats of the PickupTruck
     * @param numDoor        the number of doors of the PickupTruck
     * @param hasSunRoof     if the PickupTruck has a sunroof or not
     * @param cargoCapacity  the PickupTruck's cargo capacity
     * @param bedLength      the length of the PickupTruck's flat bed
     * @param towingCapacity the weight the PickupTruck can tow
     */
    public PickupTruck(
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
            double bedLength,
            double towingCapacity) {
        super(type, id, make, model, year, price, color, transmission, driveType, horsepower, weight,
                kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, cargoCapacity);
        this.bedLength = bedLength;
        this.towingCapacity = towingCapacity;
    }

    /**
     * Copy constructor for PickupTruck.
     * Creates a new PickupTruck instance with the same values as the input.
     * 
     * @param p the PickupTruck to copy
     */
    public PickupTruck(PickupTruck p) {
        super(p);
        this.bedLength = p.bedLength;
        this.towingCapacity = p.towingCapacity;
    }

@Override
public String getFullDetails() {
    return getCommonDetails() + "\n" +
            PrettyUtils.returnCyan("Bed Length: ") + String.format("%.1f", this.bedLength) + " feet\n" +
            PrettyUtils.returnCyan("Towing Capacity: ") + String.format("%.2f", this.towingCapacity) + " lbs";
}

    public double getBedLength() {
        return this.bedLength;
    }

    public double getTowingCapacity() {
        return this.towingCapacity;
    }

    /**
     * Returns an array of strings representing the fields of the PickupTruck
     * for CSV conversion
     *
     * @return String[] representing the fields of the PickupTruck
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
                String.valueOf(getBedLength()),
                String.valueOf(getTowingCapacity())
        };
    }
}
