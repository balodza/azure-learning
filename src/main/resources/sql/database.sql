CREATE TABLE Make
(
    id   INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL
);

CREATE TABLE Car
(
    id             INT PRIMARY KEY IDENTITY(1,1),
    model          NVARCHAR(100) NOT NULL,
    year           INT,
    version        NVARCHAR(255),
    engine_pistons INT,
    engine_volume  DOUBLE PRECISION,
    engine_power   INT,
    make_id        INT,
    CONSTRAINT FK_Car_Make FOREIGN KEY (make_id) REFERENCES Make (id)
);

INSERT INTO Make(name)
VALUES ('Audi');
INSERT INTO Make(name)
VALUES ('BMW');
INSERT INTO Make(name)
VALUES ('Mercedes');
INSERT INTO Make(name)
VALUES ('Honda');
INSERT INTO Make(name)
VALUES ('Toyota');
INSERT INTO Make(name)
VALUES ('Kia');
INSERT INTO Make(name)
VALUES ('Hyundai');

INSERT INTO Car(make_id, model, year, version, engine_pistons, engine_volume, engine_power)
VALUES (1, 'A7', 2014, '3.0 TFSI', 6, 3.0, 313);
INSERT INTO Car(make_id, model, year, version, engine_pistons, engine_volume, engine_power)
VALUES (1, 'A6', 2024, '2.0 TFSI', 4, 2.0, 249);
INSERT INTO Car(make_id, model, year, version, engine_pistons, engine_volume, engine_power)
VALUES (1, 'A4', 2022, '1.4 TFSI', 4, 1.4, 151);
INSERT INTO Car(make_id, model, year, version, engine_pistons, engine_volume, engine_power)
VALUES (1, 'Q5', 2023, '2.0 TFSI PHEV', 4, 2.0, 388);

INSERT INTO Car(make_id, model, year, version, engine_pistons, engine_volume, engine_power)
VALUES (2, '530', 2014, '3.0D', 6, 3.0, 258);
INSERT INTO Car(make_id, model, year, version, engine_pistons, engine_volume, engine_power)
VALUES (2, 'X5', 2022, '3.0D', 6, 3.0, 271);

INSERT INTO Car(make_id, model, year, version, engine_pistons, engine_volume, engine_power)
VALUES (3, 'GLE', 2018, '400E', 6, 3.0, 330);
