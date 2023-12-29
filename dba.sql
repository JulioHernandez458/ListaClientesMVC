CREATE SCHEMA IF NOT EXISTS client_list;

USE client_list;

CREATE TABLE `client_list`.`client` (
  `id` VARCHAR(60) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(35) NOT NULL,
  `create_at` DATE NOT NULL,
  `photo` VARCHAR(120) NULL,
  PRIMARY KEY (`id`));
  
INSERT INTO client(id, name, last_name, email, create_at)
VALUES ('1D25FE', 'Jonh', 'Richars', 'john.richars@maiil.com', '1987-10-21');

select * from client;
