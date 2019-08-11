CREATE TABLE IF NOT EXISTS ADDRESS
(
    ID         INT         NOT NULL AUTO_INCREMENT,
    COUNTRY VARCHAR(50) NOT NULL,
    CITY VARCHAR(50) NOT NULL,
    STREET VARCHAR(50)  DEFAULT NULL,
    BUILDING_NUMBER VARCHAR(10)  DEFAULT NULL,
    LOCAL_NUMBER VARCHAR(10)  DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS PERSON
(
    ID         INT         NOT NULL,
    FIRST_NAME VARCHAR(50) NOT NULL,
    LAST_NAME  VARCHAR(20) NOT NULL,
    BIRTH_DATE DATE,
    ADDRESS_ID INT DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS PERSON2
(
    ID         INT         NOT NULL AUTO_INCREMENT,
    FIRST_NAME VARCHAR(50) NOT NULL,
    LAST_NAME  VARCHAR(20) NOT NULL,
    BIRTH_DATE DATE,
    ADDRESS_ID INT DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS PERSON3
(
    ID         INT         NOT NULL AUTO_INCREMENT,
    FIRST_NAME VARCHAR(50) NOT NULL,
    LAST_NAME  VARCHAR(20) NOT NULL,
    DOB DATE,
    ADDRESS_ID INT DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS CONFIGURATION (
    `KEY`  VARCHAR(20) NOT NULL,
    `VALUE`  VARCHAR(20) NOT NULL,
    PRIMARY KEY (`KEY`)
);


CREATE TABLE IF NOT EXISTS PRODUCT (
    `ID`  INT NOT NULL AUTO_INCREMENT,
    `NAME`  VARCHAR(20) NOT NULL,
    PRIMARY KEY (`ID`)
);

CREATE TABLE IF NOT EXISTS SALE (
    `PRODUCT_ID`  INT NOT NULL,
    `PERSON_ID`  INT NOT NULL,
    `SALE_DATE` DATETIME,
    PRIMARY KEY (`PRODUCT_ID`,`PERSON_ID`)
);