-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.28 - MySQL Community Server - GPL
-- SO del servidor:              Linux
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para pizzeriadb
CREATE DATABASE IF NOT EXISTS `pizzeriadb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pizzeriadb`;

-- Volcando estructura para tabla pizzeriadb.comments
CREATE TABLE IF NOT EXISTS `comments` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla pizzeriadb.comments: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeriadb.ingredientpizzas
CREATE TABLE IF NOT EXISTS `ingredientpizzas` (
  `idPizza` int NOT NULL,
  `idIngredient` int NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`idPizza`,`idIngredient`),
  KEY `FK_ingredient_ingredientPizza` (`idIngredient`),
  CONSTRAINT `FK_Ingredient_IngredientPizza` FOREIGN KEY (`idIngredient`) REFERENCES `ingredients` (`id`),
  CONSTRAINT `FK_IngredientPizza_Pizza` FOREIGN KEY (`idPizza`) REFERENCES `pizzas` (`idPizza`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla pizzeriadb.ingredientpizzas: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ingredientpizzas` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingredientpizzas` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeriadb.ingredients
CREATE TABLE IF NOT EXISTS `ingredients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `type` enum('base','salsa','otros') CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla pizzeriadb.ingredients: ~34 rows (aproximadamente)
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
REPLACE INTO `ingredients` (`id`, `name`, `price`, `type`) VALUES
	(1, 'Clasica', 3.50, 'base'),
	(2, 'Fina', 3.50, 'base'),
	(3, 'Gruesa', 3.50, 'base'),
	(4, 'Bordes Queso', 4.50, 'base'),
	(5, 'Tomate', 1.00, 'salsa'),
	(6, 'Tomate Albahaca', 1.10, 'salsa'),
	(7, 'Barbacoa', 1.20, 'salsa'),
	(8, 'Carbonara', 1.20, 'salsa'),
	(9, 'Pesto Verde', 1.50, 'salsa'),
	(10, 'Chocolate', 2.00, 'salsa'),
	(11, 'Mozzarella', 0.50, 'otros'),
	(12, 'Jamon Cocido', 0.50, 'otros'),
	(13, 'Bacon', 0.50, 'otros'),
	(14, 'Carne Picada', 0.50, 'otros'),
	(15, 'Pollo', 0.50, 'otros'),
	(16, 'Pepperoni', 0.50, 'otros'),
	(17, 'Salchicha', 0.50, 'otros'),
	(18, 'Jamon Serrano', 0.50, 'otros'),
	(19, 'Salami', 0.50, 'otros'),
	(20, 'Cebolla', 0.15, 'otros'),
	(21, 'Pimiento Verde', 0.15, 'otros'),
	(22, 'Pimiento Rojo', 0.15, 'otros'),
	(23, 'Champiñon', 0.15, 'otros'),
	(24, 'Seta', 0.30, 'otros'),
	(25, 'Aceituna', 0.20, 'otros'),
	(26, 'Alcachofa', 0.20, 'otros'),
	(27, 'Maiz', 0.15, 'otros'),
	(28, 'Piña', 0.15, 'otros'),
	(29, 'Tomate Natural', 0.15, 'otros'),
	(30, 'Jalapeño', 0.20, 'otros'),
	(31, 'Rucula', 0.30, 'otros'),
	(32, 'Atun', 0.50, 'otros'),
	(33, 'Anchoa', 0.50, 'otros'),
	(34, 'Gamba', 0.50, 'otros');
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeriadb.orders
CREATE TABLE IF NOT EXISTS `orders` (
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

-- Volcando datos para la tabla pizzeriadb.orders: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeriadb.pizzaorders
CREATE TABLE IF NOT EXISTS `pizzaorders` (
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

-- Volcando datos para la tabla pizzeriadb.pizzaorders: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `pizzaorders` DISABLE KEYS */;
/*!40000 ALTER TABLE `pizzaorders` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeriadb.pizzas
CREATE TABLE IF NOT EXISTS `pizzas` (
  `idPizza` int NOT NULL AUTO_INCREMENT,
  `idBase` int NOT NULL,
  `idSauce` int NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_spanish_ci,
  `netPrice` decimal(10,2) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `like` int unsigned DEFAULT '0',
  `active` tinyint NOT NULL DEFAULT '1',
  `image` blob,
  PRIMARY KEY (`idPizza`),
  UNIQUE KEY `idPizza_UNIQUE` (`idPizza`),
  KEY `FK_Pizza_Ingredient_Base_idx` (`idBase`),
  KEY `FK_Pizza_Ingredient_Sauce_idx` (`idSauce`),
  CONSTRAINT `FK_Pizza_Ingredient_Base` FOREIGN KEY (`idBase`) REFERENCES `ingredients` (`id`),
  CONSTRAINT `FK_Pizza_Ingredient_Sauce` FOREIGN KEY (`idSauce`) REFERENCES `ingredients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla pizzeriadb.pizzas: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `pizzas` DISABLE KEYS */;
REPLACE INTO `pizzas` (`idPizza`, `idBase`, `idSauce`, `description`, `netPrice`, `amount`, `like`, `active`, `image`) VALUES
	(1, 1, 5, 'Margarita Clasica', 8.00, 8.80, NULL, 1, NULL),
	(2, 2, 5, 'Margarita Fina', 8.00, 8.80, NULL, 1, NULL),
	(3, 3, 5, 'Margarita Gruesa', 8.00, 8.80, NULL, 1, NULL),
	(4, 4, 5, 'Margarita Queso', 10.00, 11.00, NULL, 1, NULL);
/*!40000 ALTER TABLE `pizzas` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
