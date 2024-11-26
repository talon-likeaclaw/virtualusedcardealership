# Virtual Used Car Dealership 🚗 🏍️ 🚐 🛻 🚐 🚙 🏎️

A console-based Java application that manages a virtual used car dealership. Users can browse vehicles, filter inventory, sort inventory, select vehicles to add to their shopping cart, buy vehicles from their shopping cart, and sell vehicles to the dealership. This project uses object-oriented principles and implements polymorphism for file handling, database handling, data filtering, and data sorting.

---

## Contributors

- **Talon Dunbar**
- **Juan Badel**

---

## Overview

When you run the Java application (`UsedCarDealership.java`) you will initally be prompted for the initialization option of loading from CSV files or a PostgreSQL database.

If the database is not configured, the program will opt for loading from CSV files for initialization instead.

### Features

- **Data Loading Options**: Initialize the dealership data from either a PostgreSQL database or CSV files
- **Random Customer Assignment**: Each session assigns you a random customer to interact with the dealership.
- **Closed Economy**: Vehicles sold by customers remain in the dealership's inventory, and vehicles purchased by customers remain in their account for future sessions.
- **Single-Vehicle Transactions**: Each trasnaction involves only one vehicles. If a customer purchases multiple vehicles the program will create a new transaction for each vehicle.

---

## Database Set Up

To use the PostgreSQL database for reading and storing data:

1. Run the `usedcardealership.sql` file in your Database Management System (DBMS) of choice.
2. Update the database connection settings in the `initializeFromDb()` method in the `UsedCarDealership.java` file:
   - `jdbcUrl`: Your PostgreSQL database URL.
   - `dbUser`: Your PostgreSQL username.
   - `dbPassword`: Your PostgreSQL password.
