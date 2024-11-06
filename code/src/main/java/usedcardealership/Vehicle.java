/**
 * Vehicle abstract type.
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/6/2024
 */

package usedcardealership;


public abstract class Vehicle {
  private int id;
  private String make;
  private String model;
  private int year;
  private double price;
  private String color;
  private String transmission;
  private String driveType;
  private int horsepower;
  private double weight;
  private double mileage;
  private double damage;
  private boolean isElectric;

  /**
   * Vehicle Constructor
   * Initializes the Vehcile fields
   * 
   * @param id           the Vehicle's unique identifier
   * @param make         the company that makes the Vehicle
   * @param model        the name of the Vehicle's model
   * @param year         the year the Vehicle was released
   * @param price        the initial price of the Vehicle
   * @param color        the color of the Vehicle's paint
   * @param transmission the type of transmission (Auto, Manual, CVT)
   * @param driveType    the drive type of the Vehicle (front wheel, all wheel)
   * @param horsepower   the Vehicle's engine's horsepower
   * @param weight       the weight of the Vehicle
   * @param mileage      the number of kilometers the Vehicle has on the gauge
   * @param damage       the damage of the Vehcile (00.00 - 100.00)
   * @param isElectric   if the Vehicle is electric of not
   */
  public Vehicle(int id, String make, String model, int year, double price, String color, String transmission,
      String driveType, int horsepower, double weight, double mileage, double damage, boolean isElectric) {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Vehicle toString method override
   * 
   * @return String - formatted string of Vehicle's information
   */
  @Override
  public String toString() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * ID Getter
   * 
   * @return int - the vehicle's unique identifier
   */
  public int getID() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Make Getter
   * 
   * @return String - the Vehicle's make field
   */
  public String getMake() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Model Getter
   * 
   * @return String -  the Vehicle's model field
   */
  public String getModel() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Year Getter
   * 
   * @return int - the Vehicle's year field
   */
  public int getYear() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Price Getter
   * 
   * @return double - the Vehicle's price field
   */
  public double getPrice() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Color Getter
   * 
   * @return String - the Vehicle's color field
   */
  public String getColor() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Transmission Getter
   * 
   * @return String - the Vehicle's transmission field
   */
  public String getTransmission() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Drive Type Getter
   * 
   * @return String - the Vehicle's driveType field
   */
  public String getDriveType() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Horsepower Getter
   * 
   * @return int - the Vehcile's horsepower field
   */
  public int getHorsepower() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Weight Getter
   * 
   * @return double - the Vehicle's weight field
   */
  public double getWeight() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Mileage Getter
   * 
   * @return double -  the Vehicle's mileage field
   */
  public double getMileage() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * IsElectric Getter
   * 
   * @return boolean - the Vehcile's isElectric field
   */
  public boolean getIsElectric() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Sets the Vehicle's price field
   * 
   * @return void
   */
  public void setPrice() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Function to add kilometers to the Vehicle's mileage gauge
   * 
   * @param miles - the amount of milegae to add to the Vehcile's mileage field (must be positive)
   */
  public void addMileage(double miles) {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Function to add damage to the Vehcile's damage modifier
   * 
   * @param damage - the amount of damage (total cannot be over 100 or less than 0)
   */
  public void addDamage(double damage) {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Function for calculating the total price based on damage, year, milegae.
   * 
   * @return double - the price of the vehicle after depreciation
   */
  public double calculateTotalPrice() {
    throw new UnsupportedOperationException("Not written yet");
  }

  /**
   * Helper method for calculating depreciation based on damage, year, mileage
   * 
   * @return double - the amount of money to take off of the Vehicle's price
   */
  private double calculateDepreciation() {
    throw new UnsupportedOperationException("Not written yet");
  }
}
