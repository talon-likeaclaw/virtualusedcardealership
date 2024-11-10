/**
 * Vehicle Abstract type.
 * 
 * @version 11/10/2024
 */

package usedcardealership;

import java.time.*;

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
    private double kilometerage;
    private double damage;
    private boolean isElectric;

    /**
     * Vehicle Constructor
     * Initializes the Vehicle fields
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
     * @param kilometerage the number of kilometers the Vehicle has on the gauge
     * @param damage       the damage of the Vehicle (0.00 - 100.00)
     * @param isElectric   if the Vehicle is electric or not
     */
    public Vehicle(
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
            boolean isElectric) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.color = color;
        this.transmission = transmission;
        this.driveType = driveType;
        this.horsepower = horsepower;
        this.weight = weight;
        this.kilometerage = kilometerage;
        this.damage = damage;
        this.isElectric = isElectric;
    }

    @Override
    public String toString() {
        return "Vehicle ID: " + this.id + ", " + this.make + " " +
                this.model + " (" + this.year + "), $" + calculateTotalPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vehicle) {
            Vehicle v = (Vehicle) o;
            return v.id == this.id && v.kilometerage == this.kilometerage;
        }
        return false;
    }

    public int getID() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getDriveType() {
        return driveType;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public double getWeight() {
        return weight;
    }

    public double getKilometerage() {
        return kilometerage;
    }

    public double getDamage() {
        return damage;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Adds kilometers to the Vehicle's kilometerage gauge.
     * 
     * @param kilometers - the amount of kilometers to add to the Vehicle's
     *                   kilometerage (must be positive)
     */
    public void addKilometerage(double kilometers) {
        if (kilometers > 0) {
            this.kilometerage += kilometers;
        } else {
            throw new IllegalArgumentException("Kilometers must be greater than zero.");
        }
    }

    /**
     * Changes amount of damage to the Vehicle's damage modifier field.
     * 
     * @param damage - the amount of damage to add (postive or negative)
     */
    public void addDamage(double damage) {
        double newDamage = this.damage + damage;
        if (newDamage >= 0 && newDamage <= 100) {
            this.damage = newDamage;
        } else if (newDamage < 0) {
            throw new IllegalArgumentException("Vehicle damage may not be below zero.");
        } else {
            throw new IllegalArgumentException("Vehicle damage may not exceed one hundred.");
        }
    }

    /**
     * Calculates the total price based on depreciation from age, mileage, and
     * damage.
     * 
     * @return the price of the vehicle after depreciation.
     */
    public double calculateTotalPrice() {
        return this.price - calculateDepreciation();
    }

    /**
     * Calculates depreciation based on damage, year, and mileage.
     * 
     * @return the amount of money to deduct from the Vehicle's price.
     *         If depreciation exceeds the Vehicle's price, returns the full price.
     */
    private double calculateDepreciation() {
        double totalDepreciation = 0.0;
        totalDepreciation += calculateAgeDepreciation();
        totalDepreciation += calculateKilometerageDepreciation();
        totalDepreciation += calculateDamageDepreciation();
        // Cap depreciation at the vehicle's full price
        return totalDepreciation >= this.price ? this.price : totalDepreciation;
    }

    /**
     * Calculates the depreciation based on the age of the vehicle.
     * 
     * @return the amount to deduct from the vehicle's price due to age.
     */
    private double calculateAgeDepreciation() {
        int currentYear = Year.now().getValue();
        final double AGE_DEPRECIATION_RATE = 0.05;
        return (currentYear - this.year) * AGE_DEPRECIATION_RATE * this.price; // 5% per year
    }

    /**
     * Calculates the depreciation based on the vehicle's kilometerage .
     * 
     * @return the amount to deduct from the vehicle's price due to kilometerage.
     */
    private double calculateKilometerageDepreciation() {
        final double KILOMETER_DEPRECIATION_RATE = 0.02;
        return this.kilometerage * KILOMETER_DEPRECIATION_RATE; // $0.02 per kilometer
    }

    /**
     * Calculates the depreciation based on the vehicle's damage percentage.
     * 
     * @return the amount to deduct from the vehicle's price due to damage.
     */
    private double calculateDamageDepreciation() {
        final double DAMAGE_DEPRECIATION_RATE = 0.50;
        return (this.damage / 100) * this.price * DAMAGE_DEPRECIATION_RATE; // 50% impact of damage on price
    }
}