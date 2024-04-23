CREATE SCHEMA IF NOT EXISTS client_list;

USE client_list;

CREATE TABLE `client_list`.`client` (
  `id` VARCHAR(60) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(35) NOT NULL,
  `phone_number` INT NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `date_born` DATE NOT NULL,
  `photo` VARCHAR(120) NULL,
  PRIMARY KEY (`id`));
  
INSERT INTO client(id, name, last_name, email, phone_number, country, date_born)
VALUES ('42b40eef-045c-4f6b-b', 'Jonh', 'Richars', 'john.richars@maiil.com', 55014523, 'United States' , '1987-10-21');


CREATE TABLE `client_list`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);
  
  CREATE TABLE `client_list`.`authorities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `authority` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_id_authority` (`user_id` ASC, `authority` ASC) VISIBLE,
  CONSTRAINT `fk_authorities_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `client_list`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
-- Both passwords are = 12345
INSERT INTO users(username,password,enabled)
VALUES ('admin','$2a$10$WDEiq/zOeblqRvm6vlwFMuQkitak2MhuHECoheR53eBjxz4zhRf66',1);
INSERT INTO users(username,password,enabled)
VALUES ('user1','$2a$10$Ase6qKhVDdMMCbvw5AVrD.kdMD1n2/dvGF4IvFc10T3gPnT6QxGKO',1);

INSERT INTO authorities(user_id,authority)
VALUE(1,'ROLE_ADMIN');
INSERT INTO authorities(user_id,authority)
VALUE(1,'ROLE_USER');
INSERT INTO authorities(user_id,authority)
VALUE(2,'ROLE_USER');

 
SELECT * FROM client;
SELECT * FROM users;   
SELECT * FROM authorities;