CREATE DATABASE neo;

USE neo;

CREATE TABLE Continent (
    CONTINENT_NAME varchar(30),
    PRIMARY KEY (CONTINENT_NAME)
);

CREATE TABLE Country (
    COUNTRY_NAME varchar(30),
    CONTINENT_NAME varchar(30),
    PRIMARY KEY (COUNTRY_NAME),
    FOREIGN KEY (CONTINENT_NAME) REFERENCES Continent(CONTINENT_NAME)
);

CREATE TABLE City (
    CITY_NAME varchar(30),
    COUNTRY_NAME varchar(30),
    PRIMARY KEY (CITY_NAME, COUNTRY_NAME),
    FOREIGN KEY (COUNTRY_NAME) REFERENCES Country(COUNTRY_NAME)
);

INSERT INTO Continent values ('Europe'), ('Asia'), ('NA');
INSERT INTO Country values ('Poland', 'Europe'), ('Germany', 'Europe'), ('Russia', 'Asia'), ('Malaysia', 'Asia'), ('USA', 'NA');
INSERT INTO City values ('Wroclaw', 'Poland'), ('Gdansk', 'Poland'), ('Berlin', 'Germany'), ('Düsseldorf', 'Germany'), ('Moscow', 'Russia'), ('Perm', 'Russia'), ('Kuala Lumpur', 'Malaysia'), ('LA', 'USA');
