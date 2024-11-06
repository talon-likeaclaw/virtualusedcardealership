/**
 * PickupTruck type.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/6/2024
 */

package usedcardealership;

public class PickupTruck extends CargoCapacity {
  private double bedLength;
  private double towingCapacity;

  /**
   * PickupTruck Constructor
   * Initializes the PickupTruck fields
   * 
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
   * @param mileage        the number of kilometers the PickupTruck has
   * @param damage         the damage of the PickupTruck (00.00 - 100.00)
   * @param isElectric     if the PickupTruck is electric of not
   * @param numSeats       the number of seats of the PickupTruck
   * @param numDoor        the number of doors of the PickupTruck
   * @param hasSunRoof     if the PickupTruck has a sunroof or not
   * @param cargoCapacity  the PickupTruck's cargo capacity
   * @param bedLength      the length of the PickupTruck's flat bed
   * @param towingCapacity the weight the PickupTruck can tow
   */
  public PickupTruck(int id, String make, String model, int year, double price, String color, String transmission,
      String driveType, int horsepower, double weight, double mileage, double damage, boolean isElectric, int numSeats,
      int numDoors, boolean hasSunRoof, double cargoCapacity, double bedLength, double towingCapacity) {
    super(id, make, model, year, price, color, transmission, driveType, horsepower, weight, mileage, damage, isElectric,
        numSeats, numDoors, hasSunRoof, cargoCapacity);
    throw new UnsupportedOperationException("Not written yet");
  }

  @Override
  public String toString() {
    throw new UnsupportedOperationException("Not written yet");
  }

  public double getBedLength() {
    throw new UnsupportedOperationException("Not written yet");
  }

  public double getTowingCapacity() {
    throw new UnsupportedOperationException("Not written yet");
  }
}
