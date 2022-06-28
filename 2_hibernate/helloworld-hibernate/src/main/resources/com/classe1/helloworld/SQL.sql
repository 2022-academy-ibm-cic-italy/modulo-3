CREATE DATABASE `hello-world`

USE `hello-world`

CREATE TABLE `messages` (
                            `MESSAGE_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
                            `MESSAGE_TEXT` VARCHAR(255) NULL DEFAULT NULL,
                            PRIMARY KEY (`MESSAGE_ID`)
)


#drop database `hello-world`