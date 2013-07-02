SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Currency`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Currency` (
  `id` VARCHAR(50) NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `spread` INT NOT NULL ,
  `pips` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Bar`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Bar` (
  `id` VARCHAR(50) NOT NULL ,
  `date` DATETIME NOT NULL ,
  `open` DOUBLE NULL ,
  `high` DOUBLE NULL ,
  `low` DOUBLE NULL ,
  `close` DOUBLE NULL ,
  `voume` INT NULL ,
  `currencyId` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Bar_Currency_idx` (`currencyId` ASC) ,
  CONSTRAINT `fk_Bar_Currency`
    FOREIGN KEY (`currencyId` )
    REFERENCES `mydb`.`Currency` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`StrategyType`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`StrategyType` (
  `id` VARCHAR(50) NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Strategy`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Strategy` (
  `id` VARCHAR(50) NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `strategyTypeId` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Strategy_StrategyType1_idx` (`strategyTypeId` ASC) ,
  CONSTRAINT `fk_Strategy_StrategyType1`
    FOREIGN KEY (`strategyTypeId` )
    REFERENCES `mydb`.`StrategyType` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`StrategyParameter`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`StrategyParameter` (
  `id` VARCHAR(50) NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `value` VARCHAR(45) NULL ,
  `strategyId` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_StrategyParameter_Strategy1_idx` (`strategyId` ASC) ,
  CONSTRAINT `fk_StrategyParameter_Strategy1`
    FOREIGN KEY (`strategyId` )
    REFERENCES `mydb`.`Strategy` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Order`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Order` (
  `id` VARCHAR(50) NOT NULL ,
  `operation` INT NULL ,
  `strategyId` VARCHAR(50) NOT NULL ,
  `barId` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Order_Strategy1_idx` (`strategyId` ASC) ,
  INDEX `fk_Order_Bar1_idx` (`barId` ASC) ,
  CONSTRAINT `fk_Order_Strategy1`
    FOREIGN KEY (`strategyId` )
    REFERENCES `mydb`.`Strategy` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_Bar1`
    FOREIGN KEY (`barId` )
    REFERENCES `mydb`.`Bar` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
