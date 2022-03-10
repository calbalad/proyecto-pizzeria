-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: Pizzeria-DB
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
-- Table structure for table `Comment`
--

DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Comment` (
  `idComment` int NOT NULL AUTO_INCREMENT,
  `idUser` int NOT NULL,
  `idPizza` int NOT NULL,
  `date` datetime NOT NULL,
  `rating` int DEFAULT NULL,
  `text` text COLLATE utf8_spanish_ci,
  PRIMARY KEY (`idComment`),
  KEY `FK_Comment_Pizza_idx` (`idPizza`),
  KEY `FK_Comment_User_idx` (`idUser`),
  CONSTRAINT `FK_Comment_Pizza` FOREIGN KEY (`idPizza`) REFERENCES `Pizza` (`idPizza`),
  CONSTRAINT `FK_Comment_User` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comment`
--

LOCK TABLES `Comment` WRITE;
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ingredient`
--

DROP TABLE IF EXISTS `Ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ingredient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `type` enum('base','salsa','otros') CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ingredient`
--

LOCK TABLES `Ingredient` WRITE;
/*!40000 ALTER TABLE `Ingredient` DISABLE KEYS */;
INSERT INTO `Ingredient` VALUES (1,'Clasica',3.50,'base'),(2,'Fina',3.50,'base'),(3,'Gruesa',3.50,'base'),(4,'Bordes Queso',4.50,'base'),(5,'Tomate',1.00,'salsa'),(6,'Tomate Albahaca',1.10,'salsa'),(7,'Barbacoa',1.20,'salsa'),(8,'Carbonara',1.20,'salsa'),(9,'Pesto Verde',1.50,'salsa'),(10,'Chocolate',2.00,'salsa'),(11,'Mozzarella',0.50,'otros'),(12,'Jamon Cocido',0.50,'otros'),(13,'Bacon',0.50,'otros'),(14,'Carne Picada',0.50,'otros'),(15,'Pollo',0.50,'otros'),(16,'Pepperoni',0.50,'otros'),(17,'Salchicha',0.50,'otros'),(18,'Jamon Serrano',0.50,'otros'),(19,'Salami',0.50,'otros'),(20,'Cebolla',0.15,'otros'),(21,'Pimiento Verde',0.15,'otros'),(22,'Pimiento Rojo',0.15,'otros'),(23,'Champiñon',0.15,'otros'),(24,'Seta',0.30,'otros'),(25,'Aceituna',0.20,'otros'),(26,'Alcachofa',0.20,'otros'),(27,'Maiz',0.15,'otros'),(28,'Piña',0.15,'otros'),(29,'Tomate Natural',0.15,'otros'),(30,'Jalapeño',0.20,'otros'),(31,'Rucula',0.30,'otros'),(32,'Atun',0.50,'otros'),(33,'Anchoa',0.50,'otros'),(34,'Gamba',0.50,'otros');
/*!40000 ALTER TABLE `Ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IngredientPizza`
--

DROP TABLE IF EXISTS `IngredientPizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `IngredientPizza` (
  `idPizza` int NOT NULL,
  `idIngredient` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`idPizza`,`idIngredient`),
  KEY `FK_ingredient_ingredientPizza` (`idIngredient`),
  CONSTRAINT `FK_Ingredient_IngredientPizza` FOREIGN KEY (`idIngredient`) REFERENCES `Ingredient` (`id`),
  CONSTRAINT `FK_IngredientPizza_Pizza` FOREIGN KEY (`idPizza`) REFERENCES `Pizza` (`idPizza`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IngredientPizza`
--

LOCK TABLES `IngredientPizza` WRITE;
/*!40000 ALTER TABLE `IngredientPizza` DISABLE KEYS */;
/*!40000 ALTER TABLE `IngredientPizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Order`
--

DROP TABLE IF EXISTS `Order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Order` (
  `idOrder` int NOT NULL AUTO_INCREMENT,
  `idUser` varchar(120) NOT NULL,
  `orderDate` datetime NOT NULL,
  `deliveryDate` datetime DEFAULT NULL,
  `comment` text CHARACTER SET utf8 COLLATE utf8_spanish_ci,
  `idDeliveryAddress` int NOT NULL,
  `idChef` varchar(120) DEFAULT NULL,
  `idCourier` varchar(120) DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `orderStatus` enum('solicitado','elaborandose','preparado','enviado','recibido','cancelado') CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idOrder`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Order`
--

LOCK TABLES `Order` WRITE;
/*!40000 ALTER TABLE `Order` DISABLE KEYS */;
/*!40000 ALTER TABLE `Order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pizza`
--

DROP TABLE IF EXISTS `Pizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pizza` (
  `idPizza` int NOT NULL AUTO_INCREMENT,
  `idBase` int NOT NULL,
  `idSauce` int NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_spanish_ci,
  `netPrice` decimal(10,2) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `like` int DEFAULT NULL,
  `active` tinyint NOT NULL DEFAULT '1',
  `image` blob,
  PRIMARY KEY (`idPizza`),
  UNIQUE KEY `idPizza_UNIQUE` (`idPizza`),
  KEY `FK_Pizza_Ingredient_Base_idx` (`idBase`),
  KEY `FK_Pizza_Ingredient_Sauce_idx` (`idSauce`),
  CONSTRAINT `FK_Pizza_Ingredient_Base` FOREIGN KEY (`idBase`) REFERENCES `Ingredient` (`id`),
  CONSTRAINT `FK_Pizza_Ingredient_Sauce` FOREIGN KEY (`idSauce`) REFERENCES `Ingredient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pizza`
--

LOCK TABLES `Pizza` WRITE;
/*!40000 ALTER TABLE `Pizza` DISABLE KEYS */;
INSERT INTO `Pizza` VALUES (1,1,5,'Margarita Clasica',8.00,8.80,NULL,1,NULL),(2,2,5,'Margarita Fina',8.00,8.80,NULL,1,NULL),(3,3,5,'Margarita Gruesa',8.00,8.80,NULL,1,NULL),(4,4,5,'Margarita Queso',10.00,11.00,NULL,1,NULL);
/*!40000 ALTER TABLE `Pizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PizzaOrder`
--

DROP TABLE IF EXISTS `PizzaOrder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PizzaOrder` (
  `idOrder` int NOT NULL AUTO_INCREMENT,
  `idPizza` int NOT NULL,
  `quantity` int NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  KEY `FK_PizzaOrder_Pizza_idx` (`idPizza`),
  KEY `FK_PizzaOrder_Order_idx` (`idOrder`),
  CONSTRAINT `FK_PizzaOrder_Order` FOREIGN KEY (`idOrder`) REFERENCES `Order` (`idOrder`) ON DELETE CASCADE,
  CONSTRAINT `FK_PizzaOrder_Pizza` FOREIGN KEY (`idPizza`) REFERENCES `Pizza` (`idPizza`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PizzaOrder`
--

LOCK TABLES `PizzaOrder` WRITE;
/*!40000 ALTER TABLE `PizzaOrder` DISABLE KEYS */;
/*!40000 ALTER TABLE `PizzaOrder` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-10 20:34:38
