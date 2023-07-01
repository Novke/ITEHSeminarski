/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 10.4.28-MariaDB : Database - baza
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`baza` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `baza`;

/*Table structure for table `angazovanje` */

DROP TABLE IF EXISTS `angazovanje`;

CREATE TABLE `angazovanje` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  `profesorid` varchar(30) DEFAULT NULL,
  `predmetid` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `profesorid` (`profesorid`),
  KEY `predmetid` (`predmetid`),
  CONSTRAINT `angazovanje_ibfk_1` FOREIGN KEY (`profesorid`) REFERENCES `profesor` (`email`),
  CONSTRAINT `angazovanje_ibfk_2` FOREIGN KEY (`predmetid`) REFERENCES `predmet` (`sifra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `angazovanje` */

/*Table structure for table `predmet` */

DROP TABLE IF EXISTS `predmet`;

CREATE TABLE `predmet` (
  `sifra` int(10) NOT NULL,
  `naziv` varchar(50) DEFAULT NULL,
  `kod` varchar(10) DEFAULT NULL,
  `espb` int(2) DEFAULT NULL,
  PRIMARY KEY (`sifra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `predmet` */

/*Table structure for table `profesor` */

DROP TABLE IF EXISTS `profesor`;

CREATE TABLE `profesor` (
  `ime` varchar(30) DEFAULT NULL,
  `prezime` varchar(30) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `zvanje` varchar(30) DEFAULT NULL,
  `radnikEmail` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`email`),
  KEY `radnikEmail` (`radnikEmail`),
  CONSTRAINT `profesor_ibfk_1` FOREIGN KEY (`radnikEmail`) REFERENCES `radnik` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `profesor` */

/*Table structure for table `radnik` */

DROP TABLE IF EXISTS `radnik`;

CREATE TABLE `radnik` (
  `ime` varchar(30) DEFAULT NULL,
  `prezime` varchar(30) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `lozinka` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `radnik` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
