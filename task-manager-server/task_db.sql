CREATE DATABASE  IF NOT EXISTS `task` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `task`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: task
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `task_series_id` int DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `ref` varchar(100) NOT NULL,
  `lot` varchar(100) NOT NULL,
  `manufacturer` varchar(100) NOT NULL,
  `purchase_date` date NOT NULL,
  `notes` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `task_series_id` (`task_series_id`),
  CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`task_series_id`) REFERENCES `tasks_series` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES 
(1,NULL,'Drill Machine','Portable drill for wood','DR1234','LOT5678','Bosch','2023-11-15','Good condition',1),
(14,NULL,'Screwdriver Set','Set of 10 screwdrivers','SD1234','LOT7689','Stanley','2023-12-10','Includes all sizes',1),
(15,NULL,'Hammer','Steel hammer with rubber grip','HM2023','LOT8901','DeWalt','2023-10-05','Handle slightly worn',1),
(16,NULL,'Wrench','Adjustable spanner wrench','WR3456','LOT4567','Snap-on','2024-01-22','New in box',1),
(17,NULL,'Pliers','Needle nose pliers','PL9087','LOT1234','Klein Tools','2024-02-10','Rust-resistant coating',1),
(18,NULL,'Safety Goggles','Protective goggles for construction','SG2024','LOT9876','3M','2024-03-15','Clear lenses',1),
(19,NULL,'Measuring Tape','10-meter tape measure','MT6543','LOT3456','Stanley','2024-04-20','Durable case',1),
(20,NULL,'Utility Knife','Heavy-duty cutting knife','UK1123','LOT1122','Milwaukee','2024-05-25','Blade included',1),
(53,NULL,'Ladder','Aluminum step ladder, 6 feet','LD5000','LOT5689','Werner','2024-06-30','Lightweight',1),
(54,NULL,'Saw Blade','Circular saw blade, 7 inches','SB7007','LOT4356','Irwin','2024-07-05','Sharp teeth',1);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `tasks_series`
--

DROP TABLE IF EXISTS `tasks_series`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasks_series` (
  `id` int NOT NULL AUTO_INCREMENT,
  `task_series_qr_code` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks_series`
--

LOCK TABLES `tasks_series` WRITE;
/*!40000 ALTER TABLE `tasks_series` DISABLE KEYS */;
INSERT INTO `tasks_series` VALUES (99,'678'),(100,'699'),(101,'166'),(102,'78'),(104,'46'),(105,'888'),(106,'2222'),(107,'23'),(108,'34'),(120,'A011'),(121,'A999'),(122,'345A');
/*!40000 ALTER TABLE `tasks_series` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-15 18:33:53
