-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pizzeria-db
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
-- Table structure for table `Comentarios`
--

DROP TABLE IF EXISTS `Comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Comentarios` (
  `IdComentarios` int NOT NULL AUTO_INCREMENT,
  `IdUsuario` int NOT NULL,
  `IdPizza` int NOT NULL,
  `Fecha` datetime NOT NULL,
  `Puntuacion` int DEFAULT NULL,
  `Texto` text COLLATE utf8_spanish_ci,
  PRIMARY KEY (`IdComentarios`),
  KEY `FK_Comentarios_Pizzas_idx` (`IdPizza`),
  KEY `FK_Comentarios_Usuarios_idx` (`IdUsuario`),
  CONSTRAINT `FK_Comentarios_Pizzas` FOREIGN KEY (`IdPizza`) REFERENCES `Pizzas` (`IdPizza`),
  CONSTRAINT `FK_Comentarios_Usuarios` FOREIGN KEY (`IdUsuario`) REFERENCES `Usuarios` (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comentarios`
--

LOCK TABLES `Comentarios` WRITE;
/*!40000 ALTER TABLE `Comentarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Direcciones`
--

DROP TABLE IF EXISTS `Direcciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Direcciones` (
  `IdDirecciones` int NOT NULL AUTO_INCREMENT,
  `IdUsuario` int NOT NULL,
  `Calle` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `Numero` int NOT NULL,
  `Ciudad` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `Poblacion` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `CodigoPostal` int NOT NULL,
  `Nombre` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`IdDirecciones`),
  KEY `FK_Direcciones_Usuarios_idx` (`IdUsuario`),
  CONSTRAINT `FK_Direcciones_Usuarios` FOREIGN KEY (`IdUsuario`) REFERENCES `Usuarios` (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Direcciones`
--

LOCK TABLES `Direcciones` WRITE;
/*!40000 ALTER TABLE `Direcciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `Direcciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ingredientes`
--

DROP TABLE IF EXISTS `Ingredientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ingredientes` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `Importe` decimal(10,2) NOT NULL,
  `Tipo` enum('base','salsa','otros') COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`),
  UNIQUE KEY `Nombre_UNIQUE` (`Nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ingredientes`
--

LOCK TABLES `Ingredientes` WRITE;
/*!40000 ALTER TABLE `Ingredientes` DISABLE KEYS */;
INSERT INTO `Ingredientes` VALUES (1,'Clasica',3.50,'base'),(2,'Fina',3.50,'base'),(3,'Gruesa',3.50,'base'),(4,'Bordes Queso',4.50,'base'),(5,'Tomate',1.00,'salsa'),(6,'Tomate Albahaca',1.10,'salsa'),(7,'Barbacoa',1.20,'salsa'),(8,'Carbonara',1.20,'salsa'),(9,'Pesto Verde',1.50,'salsa'),(10,'Chocolate',2.00,'salsa'),(11,'Mozzarella',0.50,'otros'),(12,'Jamon Cocido',0.50,'otros'),(13,'Bacon',0.50,'otros'),(14,'Carne Picada',0.50,'otros'),(15,'Pollo',0.50,'otros'),(16,'Pepperoni',0.50,'otros'),(17,'Salchicha',0.50,'otros'),(18,'Jamon Serrano',0.50,'otros'),(19,'Salami',0.50,'otros'),(20,'Cebolla',0.15,'otros'),(21,'Pimiento Verde',0.15,'otros'),(22,'Pimiento Rojo',0.15,'otros'),(23,'Champiñon',0.15,'otros'),(24,'Seta',0.30,'otros'),(25,'Aceituna',0.20,'otros'),(26,'Alcachofa',0.20,'otros'),(27,'Maiz',0.15,'otros'),(28,'Piña',0.15,'otros'),(29,'Tomate Natural',0.15,'otros'),(30,'Jalapeño',0.20,'otros'),(31,'Rucula',0.30,'otros'),(32,'Atun',0.50,'otros'),(33,'Anchoa',0.50,'otros'),(34,'Gamba',0.50,'otros');
/*!40000 ALTER TABLE `Ingredientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IngredientesPizzas`
--

DROP TABLE IF EXISTS `IngredientesPizzas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `IngredientesPizzas` (
  `IdPizza` int NOT NULL,
  `IdIngrediente` int NOT NULL,
  `Cantidad` int NOT NULL,
  PRIMARY KEY (`IdPizza`,`IdIngrediente`),
  KEY `FK_Ingredientes_IngredientesPizza` (`IdIngrediente`),
  CONSTRAINT `FK_Ingredientes_IngredientesPizza` FOREIGN KEY (`IdIngrediente`) REFERENCES `Ingredientes` (`Id`),
  CONSTRAINT `FK_IngredientesPizzas_Pizzas` FOREIGN KEY (`IdPizza`) REFERENCES `Pizzas` (`IdPizza`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IngredientesPizzas`
--

LOCK TABLES `IngredientesPizzas` WRITE;
/*!40000 ALTER TABLE `IngredientesPizzas` DISABLE KEYS */;
/*!40000 ALTER TABLE `IngredientesPizzas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pedidos`
--

DROP TABLE IF EXISTS `Pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pedidos` (
  `IdPedido` int NOT NULL AUTO_INCREMENT,
  `IdUsuario` int NOT NULL,
  `FechaPedido` datetime NOT NULL,
  `FechaEntrega` datetime NOT NULL,
  `Comentario` text COLLATE utf8_spanish_ci,
  `IdDireccionReparto` int NOT NULL,
  `IdCocinero` int NOT NULL,
  `IdRepartidor` int NOT NULL,
  `Importe` decimal(10,2) NOT NULL,
  `EstadoPedido` enum('solicitado','elaborandose','preparado','enviado','recibido','cancelado') COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`IdPedido`),
  KEY `FK_Pedidos_Usuarios_idx` (`IdUsuario`),
  KEY `FK_Pedidos_Direcciones_idx` (`IdDireccionReparto`,`IdUsuario`),
  CONSTRAINT `FK_Pedidos_Direcciones` FOREIGN KEY (`IdDireccionReparto`) REFERENCES `Direcciones` (`IdDirecciones`),
  CONSTRAINT `FK_Pedidos_Usuarios` FOREIGN KEY (`IdUsuario`) REFERENCES `Usuarios` (`IdUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pedidos`
--

LOCK TABLES `Pedidos` WRITE;
/*!40000 ALTER TABLE `Pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PizzaPedido`
--

DROP TABLE IF EXISTS `PizzaPedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PizzaPedido` (
  `IdPedido` int NOT NULL AUTO_INCREMENT,
  `IdPizza` int NOT NULL,
  `Cantidad` int NOT NULL,
  `Importe` decimal(10,2) NOT NULL,
  KEY `FK_PizzasPedido_Pizzas_idx` (`IdPizza`),
  KEY `FK_PizzaPedido_Pedido_idx` (`IdPedido`),
  CONSTRAINT `FK_PizzaPedido_Pedidos` FOREIGN KEY (`IdPedido`) REFERENCES `Pedidos` (`IdPedido`) ON DELETE CASCADE,
  CONSTRAINT `FK_PizzaPedido_Pizzas` FOREIGN KEY (`IdPizza`) REFERENCES `Pizzas` (`IdPizza`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PizzaPedido`
--

LOCK TABLES `PizzaPedido` WRITE;
/*!40000 ALTER TABLE `PizzaPedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `PizzaPedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pizzas`
--

DROP TABLE IF EXISTS `Pizzas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pizzas` (
  `IdPizza` int NOT NULL AUTO_INCREMENT,
  `IdBase` int NOT NULL,
  `IdSalsa` int NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci,
  `ImporteNeto` decimal(10,2) NOT NULL,
  `Importe` decimal(10,2) NOT NULL,
  `MeGusta` int DEFAULT NULL,
  `Activa` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`IdPizza`),
  UNIQUE KEY `idPizzas_UNIQUE` (`IdPizza`),
  KEY `FK_Pizzas_Ingredientes_base_idx` (`IdBase`),
  KEY `FK_Pizzas_Ingedientes_salsa_idx` (`IdSalsa`),
  CONSTRAINT `FK_Pizzas_Ingedientes_salsa` FOREIGN KEY (`IdSalsa`) REFERENCES `Ingredientes` (`Id`),
  CONSTRAINT `FK_Pizzas_Ingredientes_base` FOREIGN KEY (`IdBase`) REFERENCES `Ingredientes` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pizzas`
--

LOCK TABLES `Pizzas` WRITE;
/*!40000 ALTER TABLE `Pizzas` DISABLE KEYS */;
INSERT INTO `Pizzas` VALUES (1,1,5,'Margarita Clasica',8.00,8.80,NULL,1),(2,2,5,'Margarita Fina',8.00,8.80,NULL,1),(3,3,5,'Margarita Gruesa',8.00,8.80,NULL,1),(4,4,5,'Margarita Queso',10.00,11.00,NULL,1);
/*!40000 ALTER TABLE `Pizzas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuarios`
--

DROP TABLE IF EXISTS `Usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Usuarios` (
  `IdUsuario` int NOT NULL AUTO_INCREMENT,
  `Email` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `Rol` enum('cliente','gerente','cocinero','repartidor') COLLATE utf8_spanish_ci NOT NULL,
  `Nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `Apellido` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `Contraseña` varchar(8000) COLLATE utf8_spanish_ci NOT NULL,
  `FechaNacimiento` date DEFAULT NULL,
  `Activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`IdUsuario`),
  UNIQUE KEY `email_UNIQUE` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuarios`
--

LOCK TABLES `Usuarios` WRITE;
/*!40000 ALTER TABLE `Usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `Usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-06 17:47:57
