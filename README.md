# Virtual Used Car Dealership 🚗🏎️🏍️🚐🛻🚐🚙

A console-based Java application that manages a virtual used car dealership. Users can browse vehicles, filter inventory, sort inventory, select vehicles to add to their shopping cart, buy vehicles from their shopping cart, and sell vehicles to the dealership. This project uses object-oriented principles and implements polymorphism for file handling, database handling, data filtering, and data sorting.

---

## Contributors

- **Talon Dunbar**
- **Juan Badel**

---

## Overview

When you run the Java application (`UsedCarDealership.java`) you will initally be prompted for the initialization option of loading from CSV, or loading from database. If you have not set up the database on your local PostgreSQL server the program will opt for loading from CSV instead. On initialization, the program will load the necessary data from the resource of your choice and instantiate a `DealershipManager` object. You will be assigned a random customer in the list of customers every time you run the application. This makes the application a closed economy, where any vehicles that are sold or purchased will remain in the dealership or customer's inventory the next time your run the application. Any transactions that occur will also be saved in the `transactions.csv` or `transactions` table in the database. Transactions are processed per vehicle, a transaction cannot contain multiple vehicles.

### Database Set Up

In order to use the database for reading and storing the data, you must run the `usedcardealership.sql` file in your Database Management System of choice. You must also modify the `jdbcUrl`, `dbUser`, and `dbPassword` variables with your own PostgreSQL database URL, Username, and Password in the `initializeFromDb()` method in the `UsedCarDealership.java` class file.
