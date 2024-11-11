package usedcardealership;

import usedcardealership.*;

public class UsedCarDealership {
    public static void main(String[] args) {
        // Vehicle Testing

        // MOTORCYCLE
        Motorcycle m = new Motorcycle(
                101, // id
                "Harley-Davidson", // make
                "Street 750", // model
                2020, // year
                7500.0, // price
                "Black", // color
                "Manual", // transmission
                "RWD", // driveType
                53, // horsepower
                233.0, // weight in lbs
                15000.0, // mileage in kilometers
                5.0, // damage percentage
                false, // isElectric
                749.0, // engineCC
                "Cruiser" // handlebarType
        );
        System.out.println(m);
        System.out.println();

        // RV
        RV myRV = new RV(
                202, // id
                "Winnebago", // make
                "Voyage", // model
                2022, // year
                75000.0, // price
                "White", // color
                "Automatic", // transmission
                "4WD", // driveType
                300, // horsepower
                12000.0, // weight in lbs
                20000.0, // mileage in kilometers
                10.0, // damage percentage
                false, // isElectric
                6, // numSeats
                2, // numDoors
                false, // hasSunRoof
                4, // sleepCapacity
                true // hasBathroom
        );
        System.out.println(myRV);
        System.out.println();

        // CAR
        Car mitsubishiLancer = new Car(
                505, // id
                "Mitsubishi", // make
                "Lancer", // model
                2018, // year
                18000.0, // price
                "White", // color
                "Manual", // transmission
                "FWD", // driveType
                168, // horsepower
                2900.0, // weight in lbs
                45000.0, // mileage in kilometers
                4.5, // damage percentage
                false, // isElectric
                5, // numSeats
                4, // numDoors
                false, // hasSunRoof
                false // isConvertible
        );
        mitsubishiLancer.addDamage(25.7); // Got into a little crash
        System.out.println(mitsubishiLancer);
        System.out.println();

        // SUV
        SUV hyundaiKona = new SUV(
                404, // id
                "Hyundai", // make
                "Kona", // model
                2022, // year
                21000.0, // price
                "White", // color
                "Automatic", // transmission
                "AWD", // driveType
                147, // horsepower
                3200.0, // weight in lbs
                10000.0, // mileage in kilometers
                2.5, // damage percentage
                false, // isElectric
                5, // numSeats
                4, // numDoors
                false, // hasSunRoof
                false // hasThirdRowSeating
        );
        hyundaiKona.addKilometerage(30000);
        System.out.println(hyundaiKona);
        System.out.println();

        // VAN
        Van fordTransit = new Van(
                606, // id
                "Ford", // make
                "Transit", // model
                2020, // year
                35000.0, // price
                "White", // color
                "Automatic", // transmission
                "RWD", // driveType
                275, // horsepower
                5000.0, // weight in lbs
                40000.0, // kilometerage in kilometers
                3.0, // damage percentage
                false, // isElectric
                2, // numSeats
                4, // numDoors
                false, // hasSunRoof
                487.3, // cargoCapacity in cubic feet
                true // hasSlidingDoors
        );
        System.out.println(fordTransit);
        System.out.println();

        // PICKUP TRUCK
        PickupTruck f150Lightning = new PickupTruck(
                707, // id
                "Ford", // make
                "F-150 Lightning", // model
                2022, // year
                45000.0, // price
                "Blue", // color
                "Automatic", // transmission
                "AWD", // driveType
                563, // horsepower
                6400.0, // weight in lbs
                5000.0, // kilometerage in kilometers
                2.0, // damage percentage
                true, // isElectric
                5, // numSeats
                4, // numDoors
                false, // hasSunRoof
                52.8, // cargoCapacity in cubic feet
                5.5, // bedLength in feet
                10000.0 // towingCapacity in lbs
        );
        System.out.println(f150Lightning);
    }
}
