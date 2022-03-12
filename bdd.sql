CREATE DATABASE  IF NOT EXISTS `listaClientes` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `listaClientes`;


DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(35) DEFAULT NULL,
  `apellido` varchar(35) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;



LOCK TABLES `cliente` WRITE;


INSERT INTO `cliente` VALUES 
	(1,'Manuel','Ortiz','ortiz@mail.com'),
	(2,'Alejandra','Gomez','gomez@mail'),
	(3,'Lucas','Hernandez','hernandez@mail.com'),
	(4,'Juan','Martinez','martinez@mail.com');


UNLOCK TABLES;



