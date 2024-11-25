-- Drop tables to start fresh
DROP TABLE IF EXISTS vehicles CASCADE;
DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS customers_vehicles CASCADE;
DROP TABLE IF EXISTS transactions CASCADE;

-- CREATE database tables:
CREATE TABLE vehicles
(
    id                   INT PRIMARY KEY,
    type                 VARCHAR(50),
    make                 VARCHAR(50),
    model                VARCHAR(50),
    year                 INT,
    price                DECIMAL(10, 2),
    color                VARCHAR(30),
    transmission         VARCHAR(30),
    drive_type           VARCHAR(30),
    horsepower           INT,
    weight               DECIMAL(10, 2),
    kilometerage         DECIMAL(10, 2),
    damage               DECIMAL(10, 2),
    is_electric          BOOLEAN,
    engine_cc            DECIMAL(10, 2),
    handlebar_type       VARCHAR(50),
    num_doors            INT,
    num_seats            INT,
    has_sunroof          BOOLEAN,
    sleep_capacity       INT,
    has_bathroom         BOOLEAN,
    is_convertible       BOOLEAN,
    cargo_capacity       DECIMAL(10, 2),
    has_thirdrow_seating BOOLEAN,
    has_sliding_doors    BOOLEAN,
    bed_length           DECIMAL(10, 2),
    towing_capacity      DECIMAL(10, 2)
);

CREATE TABLE customers
(
    id              INT PRIMARY KEY,
    first_name      VARCHAR(50),
    last_name       VARCHAR(50),
    birthday        DATE,
    phone_number    VARCHAR(15),
    address         VARCHAR(100),
    account_balance DECIMAL(10, 2)
);

CREATE TABLE customers_vehicles
(
    customer_id INT REFERENCES customers (id) ON DELETE CASCADE,
    vehicle_id  INT REFERENCES vehicles (id) ON DELETE CASCADE,
    PRIMARY KEY (vehicle_id, customer_id)
);

CREATE TABLE transactions
(
    id          INT PRIMARY KEY,
    type        VARCHAR(50),
    date        DATE,
    price       DECIMAL(10, 2),
    tax         DECIMAL(10, 2),
    customer_id INT REFERENCES customers (id) ON DELETE CASCADE,
    vehicle_id  INT REFERENCES vehicles (id) ON DELETE CASCADE
);

-- INSERT data into table:
-- Dataset create by ChatGPT.
INSERT INTO vehicles (id, type, make, model, year, price, color, transmission, drive_type, horsepower, weight,
                      kilometerage, damage, is_electric, engine_cc, handlebar_type, num_doors, num_seats, has_sunroof,
                      sleep_capacity, has_bathroom, is_convertible, cargo_capacity, has_thirdrow_seating,
                      has_sliding_doors, bed_length, towing_capacity)
VALUES (1, 'Car', 'Toyota', 'Camry', 2020, 23500.00, 'White', 'Automatic', 'FWD', 203, 3300.00, 12345.67, 2.5, false,
        NULL, NULL, 4, 5, true, NULL, NULL, false, NULL, false, false, NULL, NULL),
       (2, 'SUV', 'Ford', 'Explorer', 2019, 32000.00, 'Black', 'Automatic', 'AWD', 290, 4200.00, 54321.21, 1.8, false,
        NULL, NULL, 5, 7, true, NULL, NULL, false, NULL, true, false, NULL, NULL),
       (3, 'PickupTruck', 'Chevrolet', 'Silverado', 2021, 45000.00, 'Blue', 'Manual', 'RWD', 355, 5000.00, 23154.88,
        3.2, false, NULL, NULL, 4, 5, false, NULL, NULL, false, 1500.00, false, false, 5.5, 12000.00),
       (4, 'Motorcycle', 'Yamaha', 'MT-07', 2018, 7500.00, 'Red', 'Manual', 'RWD', 75, 400.00, 1423.65, 0.5, true,
        689.00, 'Sport', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       (5, 'RV', 'Winnebago', 'Travato', 2022, 96000.00, 'Silver', 'Automatic', 'FWD', 230, 9100.00, 8765.23, 1.2,
        false, NULL, NULL, 3, 5, true, 6, true, false, NULL, false, false, NULL, NULL),
       (6, 'Van', 'Honda', 'Odyssey', 2017, 28000.00, 'Gray', 'Automatic', 'FWD', 280, 4500.00, 34567.89, 1.5, false,
        NULL, NULL, 5, 7, false, NULL, NULL, NULL, 1100.00, false, true, NULL, NULL),
       (7, 'Car', 'Honda', 'Civic', 2016, 20000.00, 'Blue', 'Automatic', 'FWD', 174, 2900.00, 67890.12, 3.1, false,
        NULL, NULL, 4, 5, true, NULL, NULL, false, NULL, false, false, NULL, NULL),
       (8, 'SUV', 'BMW', 'X5', 2018, 55000.00, 'White', 'Automatic', 'AWD', 355, 4800.00, 43210.33, 2.1, false, NULL,
        NULL, 5, 5, true, NULL, NULL, false, NULL, true, false, NULL, NULL),
       (9, 'PickupTruck', 'Ford', 'F-150', 2020, 47000.00, 'Black', 'Manual', 'AWD', 400, 5200.00, 98765.44, 1.7, false,
        NULL, NULL, 4, 5, true, NULL, NULL, false, 1600.00, false, false, 6.5, 15000.00),
       (10, 'Motorcycle', 'Honda', 'CB500X', 2019, 7800.00, 'Gray', 'Manual', 'RWD', 47, 390.00, 5432.76, 0.3, true,
        471.00, 'Adventure', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       (11, 'Car', 'Mazda', 'Mazda3', 2022, 25000.00, 'Red', 'Automatic', 'FWD', 186, 3200.00, 12345.67, 2.2, false,
        NULL, NULL, 4, 5, true, NULL, NULL, false, NULL, false, false, NULL, NULL),
       (12, 'SUV', 'Toyota', 'Highlander', 2017, 34500.00, 'Blue', 'Automatic', 'AWD', 295, 4500.00, 23456.78, 3.0,
        false, NULL, NULL, 5, 7, true, NULL, NULL, false, NULL, true, false, NULL, NULL),
       (13, 'PickupTruck', 'Ram', '1500', 2020, 40000.00, 'Black', 'Automatic', 'RWD', 395, 5200.00, 54321.00, 1.5,
        false, NULL, NULL, 4, 5, true, NULL, NULL, false, 1700.00, false, false, 6.5, 13000.00),
       (14, 'Car', 'Chevrolet', 'Malibu', 2021, 24000.00, 'Gray', 'Automatic', 'FWD', 160, 3100.00, 13500.50, 1.0,
        false, NULL, NULL, 4, 5, true, NULL, NULL, false, NULL, false, false, NULL, NULL),
       (15, 'SUV', 'Jeep', 'Grand Cherokee', 2020, 37000.00, 'Black', 'Automatic', 'AWD', 295, 4700.00, 22600.00, 2.3,
        false, NULL, NULL, 5, 5, true, NULL, NULL, false, NULL, true, false, NULL, NULL),
       (16, 'PickupTruck', 'Toyota', 'Tundra', 2022, 49000.00, 'Blue', 'Automatic', 'RWD', 389, 5800.00, 14500.34, 0.9,
        false, NULL, NULL, 4, 5, true, NULL, NULL, false, 1800.00, false, false, 6.8, 14000.00),
       (17, 'Motorcycle', 'Kawasaki', 'Ninja 400', 2021, 7000.00, 'Green', 'Manual', 'RWD', 49, 366.00, 2100.75, 0.2,
        true, 399.00, 'Sport', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       (18, 'RV', 'Coachmen', 'Freelander', 2019, 92000.00, 'Silver', 'Automatic', 'FWD', 250, 8800.00, 19200.22, 2.1,
        false, NULL, NULL, 3, 6, true, 4, true, false, NULL, false, false, NULL, NULL),
       (19, 'Van', 'Chrysler', 'Pacifica', 2018, 32000.00, 'Red', 'Automatic', 'FWD', 287, 4300.00, 28700.12, 1.5,
        false, NULL, NULL, 5, 7, false, NULL, NULL, NULL, 1200.00, false, true, NULL, NULL),
       (20, 'Car', 'Subaru', 'Impreza', 2022, 26000.00, 'White', 'Automatic', 'AWD', 182, 3200.00, 14800.77, 0.8, false,
        NULL, NULL, 4, 5, true, NULL, NULL, false, NULL, false, false, NULL, NULL),
       (21, 'SUV', 'Hyundai', 'Santa Fe', 2019, 34000.00, 'Blue', 'Automatic', 'AWD', 276, 4200.00, 13450.88, 1.3,
        false, NULL, NULL, 5, 5, true, NULL, NULL, false, NULL, true, false, NULL, NULL),
       (22, 'PickupTruck', 'Nissan', 'Frontier', 2021, 44000.00, 'Silver', 'Manual', 'RWD', 310, 5400.00, 15400.11, 1.7,
        false, NULL, NULL, 4, 5, true, NULL, NULL, false, 1550.00, false, false, 6.3, 13500.00),
       (23, 'Motorcycle', 'Ducati', 'Monster 821', 2020, 12000.00, 'Red', 'Manual', 'RWD', 109, 420.00, 5200.45, 0.3,
        true, 821.00, 'Cruiser', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       (24, 'Car', 'Ford', 'Fusion', 2019, 22000.00, 'Gray', 'Automatic', 'FWD', 175, 3300.00, 24500.67, 1.1, false,
        NULL, NULL, 4, 5, true, NULL, NULL, false, NULL, false, false, NULL, NULL),
       (25, 'SUV', 'Chevrolet', 'Traverse', 2020, 31000.00, 'White', 'Automatic', 'AWD', 305, 4600.00, 18500.23, 2.0,
        false, NULL, NULL, 5, 7, true, NULL, NULL, false, NULL, true, false, NULL, NULL),
       (26, 'PickupTruck', 'GMC', 'Sierra 1500', 2021, 48000.00, 'Blue', 'Automatic', 'RWD', 420, 5500.00, 23000.11,
        1.5, false, NULL, NULL, 4, 5, true, NULL, NULL, false, 1700.00, false, false, 6.6, 14500.00),
       (27, 'Motorcycle', 'Harley-Davidson', 'Street Glide', 2022, 22000.00, 'Black', 'Manual', 'RWD', 90, 390.00,
        3200.45, 0.5, true, 749.00, 'Touring', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       (28, 'RV', 'Thor', 'Four Winds', 2019, 85000.00, 'Silver', 'Automatic', 'FWD', 240, 8700.00, 19800.77, 1.9,
        false, NULL, NULL, 3, 6, true, 4, true, false, NULL, false, false, NULL, NULL),
       (29, 'Van', 'Ford', 'Econoline', 2017, 26000.00, 'Gray', 'Automatic', 'RWD', 265, 4100.00, 29000.50, 1.6, false,
        NULL, NULL, 4, 7, false, NULL, NULL, NULL, 1100.00, false, true, NULL, NULL),
       (30, 'Car', 'Nissan', 'Altima', 2021, 25000.00, 'Red', 'Automatic', 'FWD', 190, 3200.00, 12200.33, 0.8, false,
        NULL, NULL, 4, 5, true, NULL, NULL, false, NULL, false, false, NULL, NULL),
       (31, 'SUV', 'Toyota', 'RAV4', 2018, 30000.00, 'Blue', 'Automatic', 'AWD', 275, 4100.00, 20500.67, 1.2, false,
        NULL, NULL, 5, 5, true, NULL, NULL, false, NULL, true, false, NULL, NULL),
       (32, 'PickupTruck', 'Chevrolet', 'Colorado', 2020, 45000.00, 'Black', 'Manual', 'RWD', 355, 5300.00, 18700.44,
        1.3, false, NULL, NULL, 4, 5, true, NULL, NULL, false, 1500.00, false, false, 6.4, 12000.00),
       (33, 'Motorcycle', 'BMW', 'R 1250 GS', 2021, 19000.00, 'Gray', 'Manual', 'RWD', 134, 410.00, 4100.34, 0.4, true,
        1254.00, 'Adventure', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       (34, 'RV', 'Jayco', 'Redhawk', 2020, 93000.00, 'White', 'Automatic', 'FWD', 245, 9000.00, 20300.66, 2.0, false,
        NULL, NULL, 3, 6, true, 5, true, false, NULL, false, false, NULL, NULL),
       (35, 'Van', 'Mercedes-Benz', 'Sprinter', 2018, 45000.00, 'Silver', 'Automatic', 'AWD', 330, 5000.00, 30500.88,
        1.7, false, NULL, NULL, 5, 7, true, NULL, NULL, NULL, 1400.00, false, true, NULL, NULL),
       (36, 'Car', 'Hyundai', 'Elantra', 2019, 22000.00, 'Black', 'Automatic', 'FWD', 162, 3000.00, 15300.22, 1.0,
        false, NULL, NULL, 4, 5, true, NULL, NULL, false, NULL, false, false, NULL, NULL),
       (37, 'SUV', 'Honda', 'Pilot', 2021, 36000.00, 'Gray', 'Automatic', 'AWD', 280, 4300.00, 18200.55, 1.4, false,
        NULL, NULL, 5, 7, true, NULL, NULL, false, NULL, true, false, NULL, NULL),
       (38, 'PickupTruck', 'Toyota', 'Tacoma', 2019, 42000.00, 'Red', 'Automatic', 'RWD', 300, 5100.00, 23300.77, 1.6,
        false, NULL, NULL, 4, 5, true, NULL, NULL, false, 1400.00, false, false, 6.2, 13000.00),
       (39, 'Motorcycle', 'Suzuki', 'V-Strom 650', 2020, 8800.00, 'White', 'Manual', 'RWD', 65, 390.00, 2100.88, 0.3,
        true, 645.00, 'Touring', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       (40, 'RV', 'Forest River', 'Forester', 2018, 87000.00, 'Blue', 'Automatic', 'FWD', 250, 9200.00, 19800.33, 2.0,
        false, NULL, NULL, 3, 6, true, 4, true, false, NULL, false, false, NULL, NULL),
       (41, 'Car', 'Volkswagen', 'Jetta', 2022, 24000.00, 'White', 'Automatic', 'FWD', 170, 2900.00, 12000.34, 1.1,
        false, NULL, NULL, 4, 5, true, NULL, NULL, false, NULL, false, false, NULL, NULL),
       (42, 'SUV', 'Mazda', 'CX-5', 2020, 33000.00, 'Blue', 'Automatic', 'AWD', 250, 3900.00, 15000.88, 1.5, false,
        NULL, NULL, 5, 5, true, NULL, NULL, false, NULL, true, false, NULL, NULL),
       (43, 'PickupTruck', 'Ford', 'Maverick', 2021, 25000.00, 'Gray', 'Automatic', 'FWD', 200, 4500.00, 13500.67, 0.9,
        false, NULL, NULL, 4, 5, true, NULL, NULL, false, 1200.00, false, false, 5.5, 10000.00),
       (44, 'Motorcycle', 'Yamaha', 'YZF-R6', 2018, 12000.00, 'Blue', 'Manual', 'RWD', 117, 420.00, 3200.22, 0.3, true,
        599.00, 'Sport', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       (45, 'RV', 'Winnebago', 'Vista', 2019, 87000.00, 'White', 'Automatic', 'FWD', 240, 8800.00, 17000.55, 1.7, false,
        NULL, NULL, 3, 6, true, 4, true, false, NULL, false, false, NULL, NULL),
       (46, 'Van', 'Honda', 'Odyssey', 2021, 37000.00, 'Black', 'Automatic', 'FWD', 280, 4400.00, 15500.33, 1.6, false,
        NULL, NULL, 5, 7, true, NULL, NULL, NULL, 1300.00, false, true, NULL, NULL),
       (47, 'Car', 'Kia', 'Soul', 2020, 19000.00, 'Red', 'Automatic', 'FWD', 150, 2800.00, 11000.88, 1.0, false, NULL,
        NULL, 4, 5, true, NULL, NULL, false, NULL, false, false, NULL, NULL),
       (48, 'SUV', 'Subaru', 'Outback', 2018, 34000.00, 'Green', 'Automatic', 'AWD', 260, 3700.00, 18000.22, 1.3, false,
        NULL, NULL, 5, 5, true, NULL, NULL, false, NULL, true, false, NULL, NULL),
       (49, 'PickupTruck', 'Chevrolet', 'Silverado 1500', 2022, 45000.00, 'Blue', 'Automatic', 'RWD', 355, 5000.00,
        19000.99, 1.4, false, NULL, NULL, 4, 5, true, NULL, NULL, false, 1500.00, false, false, 6.5, 14000.00),
       (50, 'Motorcycle', 'Triumph', 'Bonneville T120', 2019, 13000.00, 'Silver', 'Manual', 'RWD', 100, 420.00, 3100.77,
        0.2, true, 1200.00, 'Cruiser', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO customers (id, first_name, last_name, birthday, phone_number, address, account_balance)
VALUES (1, 'John', 'Doe', '1985-02-15', '555-123-4567', '123 Elm St', 455000.75),
       (2, 'Jane', 'Smith', '1990-07-20', '555-987-6543', '456 Oak Ave', 65000.50),
       (3, 'Alice', 'Johnson', '1978-03-10', '555-234-5678', '789 Maple Blvd', 32000.00),
       (4, 'Bob', 'Brown', '1982-05-25', '555-345-6789', '321 Birch Dr', 37000.85),
       (5, 'Eve', 'Davis', '1995-09-30', '555-456-7890', '654 Pine Ln', 24000.20),
       (6, 'Chris', 'Wilson', '1980-11-05', '555-567-8901', '987 Spruce Way', 40000.65),
       (7, 'Grace', 'Miller', '1975-01-14', '555-678-9012', '741 Cedar Pl', 24000.40),
       (8, 'Frank', 'Taylor', '1992-08-18', '555-789-0123', '852 Walnut Ct', 27500.55),
       (9, 'Dana', 'Anderson', '1988-12-22', '555-890-1234', '963 Poplar Rd', 16000.30),
       (10, 'Hank', 'White', '1983-06-08', '555-901-2345', '159 Aspen Cir', 19500.00);

INSERT INTO customers_vehicles (customer_id, vehicle_id)
VALUES (1, 2),
       (2, 3),
       (3, 5),
       (3, 10),
       (4, 4),
       (5, 6),
       (5, 12),
       (6, 7),
       (7, 9),
       (7, 13),
       (8, 14),
       (9, 15),
       (9, 17),
       (10, 19);

INSERT INTO transactions (id, type, date, price, tax, customer_id, vehicle_id)
VALUES (1, 'Dealership Purchase', '2024-01-10', 30000.00, 4500.00, 1, 5),
       (2, 'Dealership Purchase', '2024-01-15', 45000.00, 6750.00, 2, 12),
       (3, 'Dealership Sale', '2024-01-20', 7000.00, 1050.00, 3, 8),
       (4, 'Dealership Sale', '2024-02-01', 25000.00, 3750.00, 4, 10),
       (5, 'Dealership Purchase', '2024-02-05', 60000.00, 9000.00, 5, 20);

-- Test queries
-- SELECT c.first_name, c.last_name, c.account_balance, v.make, v.model, v.price FROM vehicles v
--     JOIN customers_vehicles cv ON v.id = cv.vehicle_id
--     JOIN customers c ON c.id = cv.customer_id WHERE v.id IN (SELECT vehicle_id FROM customers_vehicles);