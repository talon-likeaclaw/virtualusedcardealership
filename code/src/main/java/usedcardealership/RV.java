/**
 * RV type.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/6/2024
 */

package usedcardealership;

public class RV extends EnclosedVehicle {
  private int sleepCapacity;
  private boolean hasBathroom;

  /**
   * RV Constructor
   * Initializes the RV fields
   * 
   * @param id           the RV's unique identifier
   * @param make         the company that makes the RV
   * @param model        the name of the RV's model
   * @param year         the year the RV was released
   * @param price        the initial price of the RV
   * @param color        the color of the RV's paint
   * @param transmission the type of transmission (Auto, Manual, CVT)
   * @param driveType    the drive type of the RV
   * @param horsepower   the RV's engine's horsepower
   * @param weight       the weight of the RV
   * @param mileage      the number of kilometers the RV has
   * @param damage       the damage of the RV (00.00 - 100.00)
   * @param isElectric   if the RV is electric of not
   * @param numSeats     the number of seats of the RV
   * @param numDoor      the number of doors of the RV
   * @param hasSunRoof   if the RV has a sunroof or not
   */
  public RV(int id, String make, String model, int year, double price, String color, String transmission,
      String driveType, int horsepower, double weight, double mileage, double damage, boolean isElectric, int numSeats,
      int numDoors, boolean hasSunRoof, int getSleepCapacity, boolean hasBathroom) {
    super(id, make, model, year, price, color, transmission, driveType, horsepower, weight, mileage, damage, isElectric,
        numSeats, numDoors, hasSunRoof);
    throw new UnsupportedOperationException("Not written yet");
  }

  public int getSleepCapacity() {
    throw new UnsupportedOperationException("Not written yet");
  }

  public boolean getHasBathroom() {
    throw new UnsupportedOperationException("Not written yet");
  }
}
