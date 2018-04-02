CREATE DATABASE  IF NOT EXISTS `ttenistourney` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ttenistourney`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ttenistourney
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `match`
--

DROP TABLE IF EXISTS `match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match` (
  `id` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `player1` varchar(45) NOT NULL,
  `player2` varchar(45) NOT NULL,
  `tournament_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `person_mail1_idx` (`player1`),
  KEY `person_mail2_idx` (`player2`),
  KEY `tournament_name_idx` (`tournament_name`),
  CONSTRAINT `tournament_name` FOREIGN KEY (`tournament_name`) REFERENCES `tournament` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user1_mail` FOREIGN KEY (`player1`) REFERENCES `user` (`mail`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user2_mail` FOREIGN KEY (`player2`) REFERENCES `user` (`mail`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match`
--

LOCK TABLES `match` WRITE;
/*!40000 ALTER TABLE `match` DISABLE KEYS */;
INSERT INTO `match` VALUES (0,1,'dummyuser@mail.com','dummyuser2@mail.com','Dummy tourney 1'),(1,1,'dummyuser@mail.com','dummyadmin@mail.com','Dummy tourney 1');
/*!40000 ALTER TABLE `match` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-01 19:22:11
