SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `java2` DEFAULT CHARACTER SET utf8;
USE `java2` ;

-- -----------------------------------------------------
-- Table `Java2ToDoApp`.`tasks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tasks` ;

CREATE TABLE IF NOT EXISTS `tasks` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `done` BOOLEAN NOT NULL DEFAULT 0,
  `due_date` DATE NOT NULL,
  `priority` ENUM('LOW','MEDIUM','HIGH') NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;


ALTER TABLE `tasks` ADD `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP();
ALTER TABLE `tasks` ADD `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP();
alter TABLE `tasks` add `user_id` BIGINT(20) NOT NULL;

ALTER TABLE `tasks` ADD FOREIGN KEY (`user_id`) REFERENCES `app_users`(`id`);
CREATE INDEX `idx_tasks_user_id` ON `tasks`(`user_id`);
-- -----------------------------------------------------
-- Table `Java2ToDoApp`.`app_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(32) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `full_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;
