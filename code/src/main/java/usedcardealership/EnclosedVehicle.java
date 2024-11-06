/**
 * EnclosedVehicle type.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/6/2024
 */

package usedcardealership;

public abstract class EnclosedVehicle extends Vehicle {
  private int numSeats;
  private int numDoors;
  private boolean hasSunRoof;

  /**
   * EnclosedVehicle Constructor
   * Initializes the EnclosedVehicle fields
   * 
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
   * @param mileage      the number of kilometers the EnclosedVehicle has
   * @param damage       the damage of the EnclosedVehicle (00.00 - 100.00)
   * @param isElectric   if the EnclosedVehicle is electric of not
   * @param numSeats     the number of seats of the EnclosedVehicle
   * @param numDoor      the number of doors of the EnclosedVehicle
   * @param hasSunRoof   if the EnclosedVehicle has a sunroof or not
   */
  public EnclosedVehicle(int id, String make, String model, int year, double price, String color, String transmission,
      String driveType, int horsepower, double weight, double mileage, double damage, boolean isElectric, int numSeats,
      int numDoors, boolean hasSunRoof) {
    super(id, make, model, year, price, color, transmission, driveType, horsepower, weight, mileage, damage,
        isElectric);
    throw new UnsupportedOperationException("Not written yet");
  }

  public int getNumSeats() {
    throw new UnsupportedOperationException("Not written yet");
  }

  public int getNumDoors() {
    throw new UnsupportedOperationException("Not written yet");
  }

  public boolean getHasSunRoof() {
    throw new UnsupportedOperationException("Not written yet");
  }
}
