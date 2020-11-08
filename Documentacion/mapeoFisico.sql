-- MySQL Script generated by MySQL Workbench
-- sáb 07 nov 2020 07:55:39
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(250) NOT NULL,
  `identificacion` VARCHAR(15) NOT NULL,
  `sexo` VARCHAR(10) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`turno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`turno` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `horaEntrada` TIME NOT NULL,
  `horaSalida` TIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`empleado` (
  `codigoUsuario` INT NOT NULL,
  `idTurno` INT NOT NULL,
  `tipoEmpleado` INT NOT NULL,
  PRIMARY KEY (`codigoUsuario`),
  INDEX `FK_EMPLEADO_TO_TURNO_idx` (`idTurno` ASC) VISIBLE,
  CONSTRAINT `FK_EMPLEADO_TO_USUARIO`
    FOREIGN KEY (`codigoUsuario`)
    REFERENCES `mydb`.`usuario` (`codigo`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMPLEADO_TO_TURNO`
    FOREIGN KEY (`idTurno`)
    REFERENCES `mydb`.`turno` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cliente` (
  `codigoUsuario` INT NOT NULL,
  `birth` DATE NOT NULL,
  `pdfDPI` MEDIUMBLOB NOT NULL,
  PRIMARY KEY (`codigoUsuario`),
  CONSTRAINT `FK_CLIENTE_TO_USUARIO`
    FOREIGN KEY (`codigoUsuario`)
    REFERENCES `mydb`.`usuario` (`codigo`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cuenta` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoCliente` INT NOT NULL,
  `fechaCreacion` DATE NOT NULL,
  `saldo` FLOAT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `FK_CUENTA_TO_CLIENTE_idx` (`codigoCliente` ASC) VISIBLE,
  CONSTRAINT `FK_CUENTA_TO_CLIENTE`
    FOREIGN KEY (`codigoCliente`)
    REFERENCES `mydb`.`cliente` (`codigoUsuario`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`valoresGlobales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`valoresGlobales` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(60) NOT NULL,
  `valor` FLOAT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`transaccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`transaccion` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codCuenta` INT NOT NULL,
  `tipo` VARCHAR(8) NOT NULL,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `monto` FLOAT NOT NULL,
  `codCajero` INT NOT NULL,
  `saldoCuenta` FLOAT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `FK_TRANSACCION_TO_CUENTA_idx` (`codCuenta` ASC) VISIBLE,
  INDEX `FK_TRANSACCION_TO_EMPLEADO_idx` (`codCajero` ASC) VISIBLE,
  CONSTRAINT `FK_TRANSACCION_TO_CUENTA`
    FOREIGN KEY (`codCuenta`)
    REFERENCES `mydb`.`cuenta` (`codigo`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_TRANSACCION_TO_EMPLEADO`
    FOREIGN KEY (`codCajero`)
    REFERENCES `mydb`.`empleado` (`codigoUsuario`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cambioRealizado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cambioRealizado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codGerente` INT NOT NULL,
  `codUsuarioModificado` INT NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_CAMBIO_TO_EMPLEADO_idx` (`codGerente` ASC) VISIBLE,
  INDEX `FK_CAMBIO_TO_USUARIO_idx` (`codUsuarioModificado` ASC) VISIBLE,
  CONSTRAINT `FK_CAMBIO_TO_EMPLEADO`
    FOREIGN KEY (`codGerente`)
    REFERENCES `mydb`.`empleado` (`codigoUsuario`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CAMBIO_TO_USUARIO`
    FOREIGN KEY (`codUsuarioModificado`)
    REFERENCES `mydb`.`usuario` (`codigo`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cuentaAsociada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cuentaAsociada` (
  `codCliente` INT NOT NULL,
  `codCuenta` INT NOT NULL,
  PRIMARY KEY (`codCliente`, `codCuenta`),
  INDEX `FK_CUENTAASO_TO_CUENTA_idx` (`codCuenta` ASC) VISIBLE,
  CONSTRAINT `FK_CUENTAASO_TO_CLIENTE`
    FOREIGN KEY (`codCliente`)
    REFERENCES `mydb`.`cliente` (`codigoUsuario`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CUENTAASO_TO_CUENTA`
    FOREIGN KEY (`codCuenta`)
    REFERENCES `mydb`.`cuenta` (`codigo`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`solicitudAsociacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`solicitudAsociacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codCliente` INT NOT NULL,
  `codCuenta` INT NOT NULL,
  `estado` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `FK_SOLICITUD_TO_CLIENTE_idx` (`codCliente` ASC) VISIBLE,
  INDEX `FK_SOLICITUD_TO_CUENTA_idx` (`codCuenta` ASC) VISIBLE,
  CONSTRAINT `FK_SOLICITUD_TO_CLIENTE`
    FOREIGN KEY (`codCliente`)
    REFERENCES `mydb`.`cliente` (`codigoUsuario`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_SOLICITUD_TO_CUENTA`
    FOREIGN KEY (`codCuenta`)
    REFERENCES `mydb`.`cuenta` (`codigo`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
