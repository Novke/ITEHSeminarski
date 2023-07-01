/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.24-MariaDB : Database - januar2023
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`januar2023` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `januar2023`;

/*Table structure for table `angazovanje` */

DROP TABLE IF EXISTS `angazovanje`;

CREATE TABLE `angazovanje` (
  `AngazovanjeID` bigint(10) NOT NULL AUTO_INCREMENT,
  `DatumAngazovanja` date DEFAULT NULL,
  `EmailKorisnika` varchar(50) DEFAULT NULL,
  `ProfesorID` bigint(10) DEFAULT NULL,
  `PredmetID` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`AngazovanjeID`),
  KEY `fk_profesorID` (`ProfesorID`),
  KEY `fk_predmetID` (`PredmetID`),
  CONSTRAINT `fk_predmetID` FOREIGN KEY (`PredmetID`) REFERENCES `predmet` (`PredmetID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_profesorID` FOREIGN KEY (`ProfesorID`) REFERENCES `profesor` (`ProfesorID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

/*Data for the table `angazovanje` */

insert  into `angazovanje`(`AngazovanjeID`,`DatumAngazovanja`,`EmailKorisnika`,`ProfesorID`,`PredmetID`) values 
(1,'2022-10-11','ari@gmail.com',1,4),
(2,'2022-10-10','ari@gmail.com',2,4),
(3,'2022-10-10','ari@gmail.com',3,5),
(5,'2012-05-11','ari@gmail.com',1,5),
(7,'2022-10-10','ari@gmail.com',2,6),
(8,'2022-10-10','ari@gmail.com',1,6);

/*Table structure for table `predmet` */

DROP TABLE IF EXISTS `predmet`;

CREATE TABLE `predmet` (
  `PredmetID` bigint(10) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(50) DEFAULT NULL,
  `Kod` varchar(10) DEFAULT NULL,
  `ESPB` int(7) DEFAULT NULL,
  PRIMARY KEY (`PredmetID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

/*Data for the table `predmet` */

insert  into `predmet`(`PredmetID`,`Naziv`,`Kod`,`ESPB`) values 
(4,'Predmet 1','ABC1234567',6),
(5,'Predmet 2','CBA1234567',4),
(6,'Predmet 3','ASD1234567',6);

/*Table structure for table `profesor` */

DROP TABLE IF EXISTS `profesor`;

CREATE TABLE `profesor` (
  `ProfesorID` bigint(10) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(30) DEFAULT NULL,
  `Prezime` varchar(30) DEFAULT NULL,
  `Zvanje` varchar(30) DEFAULT NULL,
  `EmailKorisnika` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ProfesorID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `profesor` */

insert  into `profesor`(`ProfesorID`,`Ime`,`Prezime`,`Zvanje`,`EmailKorisnika`) values 
(1,'Petar','Petrovic','Docent','ari@gmail.com'),
(2,'Mira','Ivkovic','Docent','ari@gmail.com'),
(3,'Isidor','Brankovic','Vanredni profesor','ari@gmail.com'),
(4,'Jovan','Petrovic','Docent','ari@gmail.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
