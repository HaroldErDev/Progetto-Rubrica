CREATE SCHEMA IF NOT EXISTS `rubrica`;

CREATE TABLE `rubrica`.`persona` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(45) NOT NULL,
	`cognome` VARCHAR(45) NOT NULL,
	`indirizzo` VARCHAR(45) NOT NULL,
	`telefono` VARCHAR(45) NOT NULL,
	`eta` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `rubrica`.`utente` (
	`idUtente` INT NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(45) NOT NULL,
	`password` VARCHAR(45) NOT NULL,
	PRIMARY KEY (`idutente`),
	UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE
);