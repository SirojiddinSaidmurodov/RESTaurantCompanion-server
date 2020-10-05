drop database restaurant;
create database if not exists restaurant;
use restaurant;
CREATE TABLE IF NOT EXISTS `users` (
    id SERIAL PRIMARY KEY,
    `name` CHAR(40),
    `login` CHAR(40),
    `passwordHash` CHAR(64)
);
CREATE TABLE IF NOT EXISTS `cook` (
    id SERIAL,
    userID BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (userID) REFERENCES users (id) ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `waiter` (
    `id` SERIAL,
    `userID` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`userID`) REFERENCES `users` (`id`) ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `admin` (
    `id` SERIAL,
    `userID` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`userID`) REFERENCES `users` (`id`) ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `orders` (
    `id` SERIAL,
    `waiterID` BIGINT UNSIGNED NOT NULL,
    `tableID` INT,
    `orderStatus` TINYINT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`waiterID`) REFERENCES `waiter` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS dish (
    `id` SERIAL PRIMARY KEY,
    `dishName` BIGINT,
    `dishCost` INT,
    `dishAvailibility` TINYINT
);
CREATE TABLE IF NOT EXISTS `orderItems` (
    `id` SERIAL,
    `dishID` BIGINT UNSIGNED NOT NULL,
    `orderID` BIGINT UNSIGNED NOT NULL,
    `quantity` TINYINT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`dishID`) REFERENCES `dish` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`orderID`) REFERENCES `orders` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);
-- show tables from restaurant;