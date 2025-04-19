-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: event_m
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,'admin','admin123');
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrolled_events`
--

DROP TABLE IF EXISTS `enrolled_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrolled_events` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `event_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKriorm79dh1c6tbsk5vtkf2bdp` (`event_id`),
  KEY `FKsx1gw2ujb63nlrcnqxiksf7n` (`user_id`),
  CONSTRAINT `FKriorm79dh1c6tbsk5vtkf2bdp` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`),
  CONSTRAINT `FKsx1gw2ujb63nlrcnqxiksf7n` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrolled_events`
--

LOCK TABLES `enrolled_events` WRITE;
/*!40000 ALTER TABLE `enrolled_events` DISABLE KEYS */;
INSERT INTO `enrolled_events` VALUES (19,10,2),(21,10,3);
/*!40000 ALTER TABLE `enrolled_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `event_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `event_date` date NOT NULL,
  `location` varchar(255) NOT NULL,
  `reward` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `event_name` (`event_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (1,'Tree Plantation Drive','Join us in planting trees in the community park.','2025-04-22','Central Park',100),(2,'Beach Cleanup Day','Help clean up the shoreline and protect marine life.','2025-05-05','Sunset Beach',80),(3,'Recycling Workshop','Learn how to sort and recycle waste effectively.','2025-06-10','Community Center Hall',70),(4,'Bike-to-Work Challenge','Participate in a week-long sustainable transport event.','2025-07-01','Downtown Area',90),(5,'Green Talk','Attend a seminar on climate change and sustainability.','2025-08-15','Tech Auditorium',60),(10,'Spring Boot Workshop','Learn Spring Boot basics','2025-05-01','Room 101, Tech Building',100);
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_activities`
--

DROP TABLE IF EXISTS `personal_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal_activities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `impact` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_activities`
--

LOCK TABLES `personal_activities` WRITE;
/*!40000 ALTER TABLE `personal_activities` DISABLE KEYS */;
INSERT INTO `personal_activities` VALUES (1,'Recycle Regularly','Separate your waste into recyclables and non-recyclables.','Reduces landfill waste and conserves resources.'),(2,'Use Reusable Bags','Carry cloth or reusable bags when shopping.','Reduces plastic pollution.'),(3,'Bike or Walk to Work','Choose cycling or walking over driving short distances.','Reduces carbon emissions.'),(4,'Plant a Tree','Participate in local tree planting drives.','Improves air quality and biodiversity.'),(5,'Conserve Water','Fix leaks and turn off the tap when not in use.','Preserves a precious natural resource.');
/*!40000 ALTER TABLE `personal_activities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `total_reward_points` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'kmp','kmp@example.com','kmp','kmp','kmp',300),(2,'yash','yash@example.com','yash','yash','yash',200),(3,'pruthvi','pruthvi@example.com','pruthvi','pruthvi','pruthvi',600),(4,'rushit','rushit@gmail.com','rushit','rushit','rushit',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-16  9:45:14
