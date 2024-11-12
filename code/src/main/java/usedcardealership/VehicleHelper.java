/**
 * Class for static methods to help with vehicle loading/writing.
 * Reduces code duplication Vehicle and Customer loading.
 *
 * @author Talon Dunbar
 * @version 11/12/2024
 */

package usedcardealership;

public class VehicleHelper {
  public static Vehicle parseVehicle(String[] fields) {
    String type = fields[0];
    int id = Integer.parseInt(fields[1]);
    String make = fields[2];
    String model = fields[3];
    int year = Integer.parseInt(fields[4]);
    double price = Double.parseDouble(fields[5]);
    String color = fields[6];
    String transmission = fields[7];
    String driveType = fields[8];
    int horsepower = Integer.parseInt(fields[9]);
    double weight = Double.parseDouble(fields[10]);
    double kilometerage = Double.parseDouble(fields[11]);
    double damage = Double.parseDouble(fields[12]);
    boolean isElectric = Boolean.parseBoolean(fields[13]);

    if (type.equals("Motorcycle")) {
      double engineCC = Double.parseDouble(fields[14]);
      String handlebarType = fields[15];
      return new Motorcycle(id, make, model, year, price, color, transmission, driveType, horsepower,
          weight, kilometerage, damage, isElectric, engineCC, handlebarType);
    } else if (type.equals("RV")) {
      int numSeats = Integer.parseInt(fields[14]);
      int numDoors = Integer.parseInt(fields[15]);
      boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
      int sleepCapacity = Integer.parseInt(fields[17]);
      boolean hasBathroom = Boolean.parseBoolean(fields[18]);
      return new RV(id, make, model, year, price, color, transmission, driveType, horsepower,
          weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, sleepCapacity, hasBathroom);
    } else if (type.equals("Car")) {
      int numSeats = Integer.parseInt(fields[14]);
      int numDoors = Integer.parseInt(fields[15]);
      boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
      boolean isConvertible = Boolean.parseBoolean(fields[17]);
      return new Car(id, make, model, year, price, color, transmission, driveType, horsepower,
          weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, isConvertible);
    } else if (type.equals("SUV")) {
      int numSeats = Integer.parseInt(fields[14]);
      int numDoors = Integer.parseInt(fields[15]);
      boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
      boolean hasThirdRowSeating = Boolean.parseBoolean(fields[17]);
      return new SUV(id, make, model, year, price, color, transmission, driveType, horsepower,
          weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, hasThirdRowSeating);
    } else if (type.equals("PickupTruck")) {
      int numSeats = Integer.parseInt(fields[14]);
      int numDoors = Integer.parseInt(fields[15]);
      boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
      double cargoCapacity = Double.parseDouble(fields[17]);
      double bedLength = Double.parseDouble(fields[18]);
      double towingCapacity = Double.parseDouble(fields[19]);
      return new PickupTruck(id, make, model, year, price, color, transmission, driveType, horsepower,
          weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof,
          cargoCapacity, bedLength, towingCapacity);
    } else if (type.equals("Van")) {
      int numSeats = Integer.parseInt(fields[14]);
      int numDoors = Integer.parseInt(fields[15]);
      boolean hasSunRoof = Boolean.parseBoolean(fields[16]);
      double cargoCapacity = Double.parseDouble(fields[17]);
      boolean hasSlidingDoors = Boolean.parseBoolean(fields[18]);
      return new Van(id, make, model, year, price, color, transmission, driveType, horsepower,
          weight, kilometerage, damage, isElectric, numSeats, numDoors, hasSunRoof, cargoCapacity, hasSlidingDoors);
    } else {
      return null;
    }
  }

  /**
   * Converts a Vehicle object to a CSV string based on its type and getters.
   * 
   * @param Vehicle vehicle - The Vehicle to convert.
   * @return String - A CSV string representation of the vehicle.
   */
  public static String convertVehicleToCSV(Vehicle vehicle) {
    if (vehicle instanceof Motorcycle) {
      Motorcycle m = (Motorcycle) vehicle;
      return String.join(",",
          "Motorcycle",
          String.valueOf(m.getID()),
          m.getMake(),
          m.getModel(),
          String.valueOf(m.getYear()),
          String.valueOf(m.getPrice()),
          m.getColor(),
          m.getTransmission(),
          m.getDriveType(),
          String.valueOf(m.getHorsepower()),
          String.valueOf(m.getWeight()),
          String.valueOf(m.getKilometerage()),
          String.valueOf(m.getDamage()),
          String.valueOf(m.isElectric()),
          String.valueOf(m.getEngineCC()),
          m.getHandleType());
    } else if (vehicle instanceof RV) {
      RV rv = (RV) vehicle;
      return String.join(",",
          "RV",
          String.valueOf(rv.getID()),
          rv.getMake(),
          rv.getModel(),
          String.valueOf(rv.getYear()),
          String.valueOf(rv.getPrice()),
          rv.getColor(),
          rv.getTransmission(),
          rv.getDriveType(),
          String.valueOf(rv.getHorsepower()),
          String.valueOf(rv.getWeight()),
          String.valueOf(rv.getKilometerage()),
          String.valueOf(rv.getDamage()),
          String.valueOf(rv.isElectric()),
          String.valueOf(rv.getNumSeats()),
          String.valueOf(rv.getNumDoors()),
          String.valueOf(rv.hasSunRoof()),
          String.valueOf(rv.getSleepCapacity()),
          String.valueOf(rv.hasBathroom()));
    } else if (vehicle instanceof Car) {
      Car car = (Car) vehicle;
      return String.join(",",
          "Car",
          String.valueOf(car.getID()),
          car.getMake(),
          car.getModel(),
          String.valueOf(car.getYear()),
          String.valueOf(car.getPrice()),
          car.getColor(),
          car.getTransmission(),
          car.getDriveType(),
          String.valueOf(car.getHorsepower()),
          String.valueOf(car.getWeight()),
          String.valueOf(car.getKilometerage()),
          String.valueOf(car.getDamage()),
          String.valueOf(car.isElectric()),
          String.valueOf(car.getNumSeats()),
          String.valueOf(car.getNumDoors()),
          String.valueOf(car.hasSunRoof()),
          String.valueOf(car.isConvertible()));
    } else if (vehicle instanceof SUV) {
      SUV suv = (SUV) vehicle;
      return String.join(",",
          "SUV",
          String.valueOf(suv.getID()),
          suv.getMake(),
          suv.getModel(),
          String.valueOf(suv.getYear()),
          String.valueOf(suv.getPrice()),
          suv.getColor(),
          suv.getTransmission(),
          suv.getDriveType(),
          String.valueOf(suv.getHorsepower()),
          String.valueOf(suv.getWeight()),
          String.valueOf(suv.getKilometerage()),
          String.valueOf(suv.getDamage()),
          String.valueOf(suv.isElectric()),
          String.valueOf(suv.getNumSeats()),
          String.valueOf(suv.getNumDoors()),
          String.valueOf(suv.hasSunRoof()),
          String.valueOf(suv.hasThirdRowSeating()));
    } else if (vehicle instanceof PickupTruck) {
      PickupTruck truck = (PickupTruck) vehicle;
      return String.join(",",
          "PickupTruck",
          String.valueOf(truck.getID()),
          truck.getMake(),
          truck.getModel(),
          String.valueOf(truck.getYear()),
          String.valueOf(truck.getPrice()),
          truck.getColor(),
          truck.getTransmission(),
          truck.getDriveType(),
          String.valueOf(truck.getHorsepower()),
          String.valueOf(truck.getWeight()),
          String.valueOf(truck.getKilometerage()),
          String.valueOf(truck.getDamage()),
          String.valueOf(truck.isElectric()),
          String.valueOf(truck.getNumSeats()),
          String.valueOf(truck.getNumDoors()),
          String.valueOf(truck.hasSunRoof()),
          String.valueOf(truck.getCargoCapacity()),
          String.valueOf(truck.getBedLength()),
          String.valueOf(truck.getTowingCapacity()));
    } else if (vehicle instanceof Van) {
      Van van = (Van) vehicle;
      return String.join(",",
          "Van",
          String.valueOf(van.getID()),
          van.getMake(),
          van.getModel(),
          String.valueOf(van.getYear()),
          String.valueOf(van.getPrice()),
          van.getColor(),
          van.getTransmission(),
          van.getDriveType(),
          String.valueOf(van.getHorsepower()),
          String.valueOf(van.getWeight()),
          String.valueOf(van.getKilometerage()),
          String.valueOf(van.getDamage()),
          String.valueOf(van.isElectric()),
          String.valueOf(van.getNumSeats()),
          String.valueOf(van.getNumDoors()),
          String.valueOf(van.hasSunRoof()),
          String.valueOf(van.getCargoCapacity()),
          String.valueOf(van.hasSlidingDoors()));
    } else {
      return "";
    }
  }
}
