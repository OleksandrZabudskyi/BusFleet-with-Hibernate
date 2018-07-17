
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema BusFleet
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BusFleet` DEFAULT CHARACTER SET utf8 ;
USE `BusFleet` ;

-- -----------------------------------------------------
-- Table `BusFleet`.`bus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BusFleet`.`bus` (
  `busId` INT NOT NULL AUTO_INCREMENT,
  `busModel` VARCHAR(45) NOT NULL,
  `licensePlate` VARCHAR(45) NOT NULL,
  `manufactureYear` INT NOT NULL,
  `parkingSpot` VARCHAR(45) NOT NULL,
  `used` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`busId`),
  UNIQUE INDEX `licensePlate_UNIQUE` (`licensePlate` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `BusFleet`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BusFleet`.`user` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(512) NOT NULL,
  `phoneNumber` VARCHAR(45) NOT NULL,
  `role` ENUM('ADMIN', 'DRIVER') NOT NULL,
  `drivingLicenceNumber` VARCHAR(45) NULL,
  `drivingExperience` INT NULL,
  `assigned` TINYINT(1) NULL DEFAULT 0,
  `registered` TINYINT(1) NULL DEFAULT 0,
  `passportNumber` VARCHAR(45) NULL,
  `passportRegistration` VARCHAR(128) NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `phoneNumber_UNIQUE` (`phoneNumber` ASC),
  UNIQUE INDEX `drivingLicenceNumber_UNIQUE` (`drivingLicenceNumber` ASC),
  UNIQUE INDEX `passportNumber_UNIQUE` (`passportNumber` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `BusFleet`.`route`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BusFleet`.`route` (
  `routeId` INT NOT NULL AUTO_INCREMENT,
  `routeName` VARCHAR(45) NOT NULL,
  `destinationFrom` VARCHAR(128) NOT NULL,
  `destinationTo` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`routeId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `BusFleet`.`trip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BusFleet`.`trip` (
  `tripId` INT NOT NULL,
  `tripNumber` VARCHAR(45) NOT NULL,
  `tripStartTime` TIME NOT NULL,
  `tripEndTime` TIME NOT NULL,
  `routeId` INT NOT NULL,
  `busId` INT NULL,
  `driverId` INT NULL,
  `confirmation` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`tripId`),
  INDEX `fk_designation_busId_idx` (`busId` ASC),
  INDEX `fk_designation_roteId_idx` (`routeId` ASC),
  INDEX `fk_designation_adminId_idx` (`driverId` ASC),
  CONSTRAINT `fk_trip_busId`
    FOREIGN KEY (`busId`)
    REFERENCES `BusFleet`.`bus` (`busId`)
    ON DELETE SET NULL
    ON UPDATE SET NULL,
  CONSTRAINT `fk_trip_roteId`
    FOREIGN KEY (`routeId`)
    REFERENCES `BusFleet`.`route` (`routeId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_trip_driverId`
    FOREIGN KEY (`driverId`)
    REFERENCES `BusFleet`.`user` (`userId`)
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `BusFleet`.`bus_has_driver`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BusFleet`.`bus_has_driver` (
  `bus_busId` INT NOT NULL,
  `user_userId` INT NOT NULL,
  PRIMARY KEY (`bus_busId`, `user_userId`),
  INDEX `fk_bus_has_user_user1_idx` (`user_userId` ASC),
  INDEX `fk_bus_has_user_bus1_idx` (`bus_busId` ASC),
  CONSTRAINT `fk_bus_has_user_bus1`
    FOREIGN KEY (`bus_busId`)
    REFERENCES `BusFleet`.`bus` (`busId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bus_has_user_user1`
    FOREIGN KEY (`user_userId`)
    REFERENCES `BusFleet`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `BusFleet`.`bus`
-- -----------------------------------------------------
START TRANSACTION;
USE `BusFleet`;
INSERT INTO `BusFleet`.`bus` (`busId`, `busModel`, `licensePlate`, `manufactureYear`, `parkingSpot`, `used`) VALUES (1, 'Electron A1801', 'AA0074AO', 2016, 'A1', FALSE);
INSERT INTO `BusFleet`.`bus` (`busId`, `busModel`, `licensePlate`, `manufactureYear`, `parkingSpot`, `used`) VALUES (2, 'Tulpan A084', 'AA1492AO', 2015, 'A2', FALSE);
INSERT INTO `BusFleet`.`bus` (`busId`, `busModel`, `licensePlate`, `manufactureYear`, `parkingSpot`, `used`) VALUES (3, 'Ruta 41', 'AA1567AO', 2014, 'B1', FALSE);
INSERT INTO `BusFleet`.`bus` (`busId`, `busModel`, `licensePlate`, `manufactureYear`, `parkingSpot`, `used`) VALUES (4, 'Ruta 41', 'AA1568AO', 2014, 'B2', FALSE);
INSERT INTO `BusFleet`.`bus` (`busId`, `busModel`, `licensePlate`, `manufactureYear`, `parkingSpot`, `used`) VALUES (5, 'Electron A1801', 'AA1569AB', 2017, 'A3', FALSE);
INSERT INTO `BusFleet`.`bus` (`busId`, `busModel`, `licensePlate`, `manufactureYear`, `parkingSpot`, `used`) VALUES (6, 'Tulpan A084', 'AA1578BB', 2016, 'B3', FALSE);

COMMIT;


-- -----------------------------------------------------
-- Data for table `BusFleet`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `BusFleet`;
INSERT INTO `BusFleet`.`user` (`userId`, `firstName`, `lastName`, `email`, `password`, `phoneNumber`, `role`, `drivingLicenceNumber`, `drivingExperience`, `assigned`, `registered`, `passportNumber`, `passportRegistration`) VALUES (1, 'Viktor', 'Bondar', 'bondar@gmail.com', '5bWD77+9EBLvv70E77+9N++/ve+/vS3Mszo=,-1551076064', '+38(096)111-11-11', 'DRIVER', 'KHF001678', 15, FALSE, TRUE, NULL, NULL);
INSERT INTO `BusFleet`.`user` (`userId`, `firstName`, `lastName`, `email`, `password`, `phoneNumber`, `role`, `drivingLicenceNumber`, `drivingExperience`, `assigned`, `registered`, `passportNumber`, `passportRegistration`) VALUES (2, 'Mihail', 'Ivanov', 'ivan@gmail.com', '77+9Ae+/vX/vv73vv707I+aSkjDvv71zCu+/vQ==,-69665180', '+38(096)111-11-12', 'DRIVER', 'KBH001799', 20, FALSE, TRUE, NULL, NULL);
INSERT INTO `BusFleet`.`user` (`userId`, `firstName`, `lastName`, `email`, `password`, `phoneNumber`, `role`, `drivingLicenceNumber`, `drivingExperience`, `assigned`, `registered`, `passportNumber`, `passportRegistration`) VALUES (3, 'Artur', 'Aliev', 'aliev@gmail.com', '77+977+9SO+/vciT77+977+9N2Z/fnPvv70SQw==,-1758309945', '+38(096)111-11-13', 'ADMIN', NULL, NULL, NULL, TRUE, 'CM034567', 'Kiev region city Boyarka Str. Bilogorodska 51 app 172');
INSERT INTO `BusFleet`.`user` (`userId`, `firstName`, `lastName`, `email`, `password`, `phoneNumber`, `role`, `drivingLicenceNumber`, `drivingExperience`, `assigned`, `registered`, `passportNumber`, `passportRegistration`) VALUES (4, 'Alex', 'Gronov', 'gronov@gmail.com', 'Ee+/ve+/ve+/vRQz77+9xYrvv73vv71TbmYk,-412925094', '+38(099)111-11-15', 'ADMIN', 'NULL', NULL, NULL, TRUE, 'CK045678', 'KIev Popudrenka 120 appartment 120');
INSERT INTO `BusFleet`.`user` (`userId`, `firstName`, `lastName`, `email`, `password`, `phoneNumber`, `role`, `drivingLicenceNumber`, `drivingExperience`, `assigned`, `registered`, `passportNumber`, `passportRegistration`) VALUES (5, 'Viktor', 'Novak', 'novak@gmail.com', '77+9yYLvv70ceu+/ve+/ve+/vTR2Eu+/ve+/ve+/ve+/vQ==,1128203477', '+38(063)456-24-56', 'DRIVER', 'KBH001920', 20, FALSE, TRUE, NULL, NULL);
INSERT INTO `BusFleet`.`user` (`userId`, `firstName`, `lastName`, `email`, `password`, `phoneNumber`, `role`, `drivingLicenceNumber`, `drivingExperience`, `assigned`, `registered`, `passportNumber`, `passportRegistration`) VALUES (6, 'Vlad', 'Zaev', 'zaev@gmail.com', '77+977+9a++/ve+/vRw/77+9VO+/ve+/ve+/ve+/ve+/ve+/vWg=,2032224966', '+38(044)456-24-57', 'DRIVER', 'DFG56789', 20, FALSE, TRUE, NULL, NULL);
INSERT INTO `BusFleet`.`user` (`userId`, `firstName`, `lastName`, `email`, `password`, `phoneNumber`, `role`, `drivingLicenceNumber`, `drivingExperience`, `assigned`, `registered`, `passportNumber`, `passportRegistration`) VALUES (7, 'Nikolay', 'Fedin', 'fedin@gmail.com', 'QEYfZzgc77+9EEvvv73vv71m77+9Gu+/vXo=,-553349130', '+38(099)111-77-15', 'ADMIN', NULL, NULL, NULL, TRUE, 'CM045689', 'KIev Lepse 16 appartment 56');

COMMIT;


-- -----------------------------------------------------
-- Data for table `BusFleet`.`route`
-- -----------------------------------------------------
START TRANSACTION;
USE `BusFleet`;
INSERT INTO `BusFleet`.`route` (`routeId`, `routeName`, `destinationFrom`, `destinationTo`) VALUES (1, '423', 'Kiev Pass', 'Borispil');
INSERT INTO `BusFleet`.`route` (`routeId`, `routeName`, `destinationFrom`, `destinationTo`) VALUES (2, '450', 'Demiivska', 'Zhitomir');
INSERT INTO `BusFleet`.`route` (`routeId`, `routeName`, `destinationFrom`, `destinationTo`) VALUES (3, '470', 'Demiivska', 'Uman');

COMMIT;


-- -----------------------------------------------------
-- Data for table `BusFleet`.`trip`
-- -----------------------------------------------------
START TRANSACTION;
USE `BusFleet`;
INSERT INTO `BusFleet`.`trip` (`tripId`, `tripNumber`, `tripStartTime`, `tripEndTime`, `routeId`, `busId`, `driverId`, `confirmation`) VALUES (1, '423_1', '10:00:00', '11:30:00', 1, NULL, NULL, DEFAULT);
INSERT INTO `BusFleet`.`trip` (`tripId`, `tripNumber`, `tripStartTime`, `tripEndTime`, `routeId`, `busId`, `driverId`, `confirmation`) VALUES (2, '423_2', '13:00:00', '14:30:00', 1, NULL, NULL, DEFAULT);
INSERT INTO `BusFleet`.`trip` (`tripId`, `tripNumber`, `tripStartTime`, `tripEndTime`, `routeId`, `busId`, `driverId`, `confirmation`) VALUES (3, '423_3', '16:00:00', '17:30:00', 1, NULL, NULL, DEFAULT);
INSERT INTO `BusFleet`.`trip` (`tripId`, `tripNumber`, `tripStartTime`, `tripEndTime`, `routeId`, `busId`, `driverId`, `confirmation`) VALUES (4, '423_4', '19:00:00', '20:30:00', 1, NULL, NULL, DEFAULT);
INSERT INTO `BusFleet`.`trip` (`tripId`, `tripNumber`, `tripStartTime`, `tripEndTime`, `routeId`, `busId`, `driverId`, `confirmation`) VALUES (5, '423_5', '22:00:00', '23:30:00', 1, NULL, NULL, DEFAULT);
INSERT INTO `BusFleet`.`trip` (`tripId`, `tripNumber`, `tripStartTime`, `tripEndTime`, `routeId`, `busId`, `driverId`, `confirmation`) VALUES (6, '470_1', '10:00:00', '14:00:00', 3, NULL, NULL, DEFAULT);
INSERT INTO `BusFleet`.`trip` (`tripId`, `tripNumber`, `tripStartTime`, `tripEndTime`, `routeId`, `busId`, `driverId`, `confirmation`) VALUES (7, '470_2', '15:00:00', '19:00:00', 3, NULL, NULL, DEFAULT);
INSERT INTO `BusFleet`.`trip` (`tripId`, `tripNumber`, `tripStartTime`, `tripEndTime`, `routeId`, `busId`, `driverId`, `confirmation`) VALUES (8, '470_3', '17:00:00', '21:00:00', 3, NULL, NULL, DEFAULT);
INSERT INTO `BusFleet`.`trip` (`tripId`, `tripNumber`, `tripStartTime`, `tripEndTime`, `routeId`, `busId`, `driverId`, `confirmation`) VALUES (9, '450_1', '09:00:00', '12:00:00', 2, NULL, NULL, DEFAULT);
INSERT INTO `BusFleet`.`trip` (`tripId`, `tripNumber`, `tripStartTime`, `tripEndTime`, `routeId`, `busId`, `driverId`, `confirmation`) VALUES (10, '450_2', '18:00:00', '22:00:00', 2, NULL, NULL, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `BusFleet`.`bus_has_driver`
-- -----------------------------------------------------
START TRANSACTION;
USE `BusFleet`;
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (1, 1);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (1, 2);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (1, 5);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (1, 6);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (2, 1);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (2, 2);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (2, 5);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (2, 6);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (3, 1);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (3, 2);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (3, 5);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (3, 6);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (4, 1);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (4, 2);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (4, 5);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (4, 6);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (5, 1);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (5, 2);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (5, 5);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (5, 6);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (6, 1);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (6, 2);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (6, 5);
INSERT INTO `BusFleet`.`bus_has_driver` (`bus_busId`, `user_userId`) VALUES (6, 6);

COMMIT;


