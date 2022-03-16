-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pizzeriadb
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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `idComment` int NOT NULL AUTO_INCREMENT,
  `idUser` varchar(200) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `idPizza` int NOT NULL,
  `date` datetime NOT NULL,
  `rating` int NOT NULL,
  `text` text CHARACTER SET utf8 COLLATE utf8_spanish_ci,
  PRIMARY KEY (`idComment`),
  KEY `FK_Comment_Pizza_idx` (`idPizza`),
  KEY `FK_Comment_User_idx` (`idUser`),
  CONSTRAINT `FK_Comment_Pizza` FOREIGN KEY (`idPizza`) REFERENCES `pizzas` (`idPizza`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (2,'1',1,'2022-03-16 00:00:00',5,'Hola caracola'),(3,'3',1,'2022-03-16 00:00:00',3,'Esto funciona o eso parece'),(4,'5',2,'2022-03-16 00:00:00',3,'La mejor pizza que he probado en mi vida');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredientpizzas`
--

DROP TABLE IF EXISTS `ingredientpizzas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredientpizzas` (
  `idPizza` int NOT NULL,
  `idIngredient` int NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`idPizza`,`idIngredient`),
  KEY `FK_ingredient_ingredientPizza` (`idIngredient`),
  CONSTRAINT `FK_Ingredient_IngredientPizza` FOREIGN KEY (`idIngredient`) REFERENCES `ingredients` (`id`),
  CONSTRAINT `FK_IngredientPizza_Pizza` FOREIGN KEY (`idPizza`) REFERENCES `pizzas` (`idPizza`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientpizzas`
--

LOCK TABLES `ingredientpizzas` WRITE;
/*!40000 ALTER TABLE `ingredientpizzas` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingredientpizzas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredients` (
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
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (1,'Clasica',3.50,'base'),(2,'Fina',3.50,'base'),(3,'Gruesa',3.50,'base'),(4,'Bordes Queso',4.50,'base'),(5,'Tomate',1.00,'salsa'),(6,'Tomate Albahaca',1.10,'salsa'),(7,'Barbacoa',1.20,'salsa'),(8,'Carbonara',1.20,'salsa'),(9,'Pesto Verde',1.50,'salsa'),(10,'Chocolate',2.00,'salsa'),(11,'Mozzarella',0.50,'otros'),(12,'Jamon Cocido',0.50,'otros'),(13,'Bacon',0.50,'otros'),(14,'Carne Picada',0.50,'otros'),(15,'Pollo',0.50,'otros'),(16,'Pepperoni',0.50,'otros'),(17,'Salchicha',0.50,'otros'),(18,'Jamon Serrano',0.50,'otros'),(19,'Salami',0.50,'otros'),(20,'Cebolla',0.15,'otros'),(21,'Pimiento Verde',0.15,'otros'),(22,'Pimiento Rojo',0.15,'otros'),(23,'Champiñon',0.15,'otros'),(24,'Seta',0.30,'otros'),(25,'Aceituna',0.20,'otros'),(26,'Alcachofa',0.20,'otros'),(27,'Maiz',0.15,'otros'),(28,'Piña',0.15,'otros'),(29,'Tomate Natural',0.15,'otros'),(30,'Jalapeño',0.20,'otros'),(31,'Rucula',0.30,'otros'),(32,'Atun',0.50,'otros'),(33,'Anchoa',0.50,'otros'),(34,'Gamba',0.50,'otros');
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `idOrder` int NOT NULL AUTO_INCREMENT,
  `idUser` varchar(120) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `orderDate` datetime NOT NULL,
  `deliveryDate` datetime DEFAULT NULL,
  `comment` text CHARACTER SET utf8 COLLATE utf8_spanish_ci,
  `idChef` varchar(120) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `idCourier` varchar(120) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `orderStatus` enum('solicitado','elaborandose','preparado','enviado','recibido','cancelado') CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `address` varchar(500) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idOrder`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizzaorders`
--

DROP TABLE IF EXISTS `pizzaorders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizzaorders` (
  `idOrder` int NOT NULL,
  `idPizza` int NOT NULL,
  `quantity` int NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idOrder`,`idPizza`),
  KEY `FK_PizzaOrder_Pizza_idx` (`idPizza`),
  KEY `FK_PizzaOrder_Order_idx` (`idOrder`),
  CONSTRAINT `FK_PizzaOrder_Order` FOREIGN KEY (`idOrder`) REFERENCES `orders` (`idOrder`) ON DELETE CASCADE,
  CONSTRAINT `FK_PizzaOrder_Pizza` FOREIGN KEY (`idPizza`) REFERENCES `pizzas` (`idPizza`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizzaorders`
--

LOCK TABLES `pizzaorders` WRITE;
/*!40000 ALTER TABLE `pizzaorders` DISABLE KEYS */;
/*!40000 ALTER TABLE `pizzaorders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizzas`
--

DROP TABLE IF EXISTS `pizzas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizzas` (
  `idPizza` int NOT NULL AUTO_INCREMENT,
  `idBase` int NOT NULL,
  `idSauce` int NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_spanish_ci,
  `netPrice` decimal(10,2) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `active` tinyint NOT NULL DEFAULT '1',
  `image` blob,
  PRIMARY KEY (`idPizza`),
  UNIQUE KEY `idPizza_UNIQUE` (`idPizza`),
  KEY `FK_Pizza_Ingredient_Base_idx` (`idBase`),
  KEY `FK_Pizza_Ingredient_Sauce_idx` (`idSauce`),
  CONSTRAINT `FK_Pizza_Ingredient_Base` FOREIGN KEY (`idBase`) REFERENCES `ingredients` (`id`),
  CONSTRAINT `FK_Pizza_Ingredient_Sauce` FOREIGN KEY (`idSauce`) REFERENCES `ingredients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizzas`
--

LOCK TABLES `pizzas` WRITE;
/*!40000 ALTER TABLE `pizzas` DISABLE KEYS */;
INSERT INTO `pizzas` VALUES (1,1,5,'Margarita Clasica',8.00,8.80,1,NULL),(2,2,5,'Margarita Fina',8.00,8.80,1,NULL),(3,3,5,'Margarita Gruesa',8.00,8.80,1,NULL),(4,4,5,'Margarita Queso',10.00,11.00,1,NULL);
/*!40000 ALTER TABLE `pizzas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-16 20:33:24
