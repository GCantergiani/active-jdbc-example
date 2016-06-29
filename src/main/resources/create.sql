SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `jawira` ;
CREATE SCHEMA IF NOT EXISTS `jawira` ;
USE `jawira` ;

-- -----------------------------------------------------
-- Table `jawira`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jawira`.`users` ;

CREATE TABLE IF NOT EXISTS `jawira`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL UNIQUE,
  `password` VARCHAR(100) NOT NULL,
  `type` ENUM('admin','user') NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `enable` TINYINT NOT NULL DEFAULT 1,
  `user_created` DATETIME NOT NULL,
  `last_access` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jawira`.`Topology`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jawira`.`topologies` ;

CREATE TABLE IF NOT EXISTS `jawira`.`topologies` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `IdStormTopology` VARCHAR(45) NOT NULL,
  `contentRaw` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Topology_User_idx` (`user_id` ASC),
  CONSTRAINT `fk_Topology_User`
    FOREIGN KEY (`user_id`)
    REFERENCES `jawira`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jawira`.`Config`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jawira`.`configs` ;

CREATE TABLE IF NOT EXISTS `jawira`.`configs` (
  `key` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`key`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO users (username,password,type,email,user_created)
VALUES ("admin","d033e22ae348aeb5660fc2140aec35850c4da997","admin","admin@jawira.com",NOW());
