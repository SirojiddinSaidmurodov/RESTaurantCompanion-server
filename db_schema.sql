drop database if exists restaurant;
create database restaurant;
use restaurant;
CREATE TABLE IF NOT EXISTS `user` (
    id SERIAL PRIMARY KEY,
    `name` CHAR(40),
    `login` CHAR(40),
    `passwordHash` CHAR(64)
) comment = "Basic user entity";
CREATE TABLE IF NOT EXISTS `waiter` (
    `id` SERIAL,
    `userID` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`userID`) REFERENCES `user` (`id`) ON UPDATE CASCADE
)comment = "Users that can add orders";
CREATE TABLE IF NOT EXISTS `orders` (
    `id` SERIAL,
    `waiterID` BIGINT UNSIGNED NOT NULL,
    `tableID` INT,
    `orderStatus` TINYINT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`waiterID`) REFERENCES `waiter` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS meal (
    `id` SERIAL PRIMARY KEY,
    `mealName` CHAR(50),
    `mealCost` INT,
    `mealAvailibility` TINYINT
);
CREATE TABLE IF NOT EXISTS `orderItems` (
    `id` SERIAL,
    `mealID` BIGINT UNSIGNED NOT NULL,
    `orderID` BIGINT UNSIGNED NOT NULL,
    `quantity` TINYINT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`mealID`) REFERENCES `meal` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`orderID`) REFERENCES `orders` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);
-- show tables from restaurant;