CREATE TABLE cars(
    idCar INT PRIMARY KEY,
    brand TEXT NOT NULL,
    model TEXT NOT NULL,
    price MONEY NOT NULL
);

CREATE TABLE drivers(
    idDriver INT PRIMARY KEY,
    name TEXT NOT NULL,
    age INT,
    havingDriversLicense BOOLEAN,
    idCar INT REFERENCES cars (idCar)
);