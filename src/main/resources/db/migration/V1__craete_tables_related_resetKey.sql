DROP TABLE IF EXISTS `reset_key_box`;
DROP TABLE IF EXISTS `shedlock`;

CREATE TABLE `reset_pw_key` (
     `reset_pw_key_id` int NOT NULL AUTO_INCREMENT,
     `reset_key` varchar(40) NOT NULL,
     `expire_date` datetime NOT NULL,
     `email` varchar(50)  NOT NULL,
     PRIMARY KEY (`reset_pw_key_id`),
     INDEX `idx_reset_key` (`reset_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `reset_key_box` (
     `reset_key_box_id` int NOT NULL AUTO_INCREMENT,
     `reset_key` varchar(40) NOT NULL,
     `email` varchar(50) NOT NULL,
     `user_name` varchar(50)  NOT NULL,
     `occurred_on` datetime NOT NULL,
     PRIMARY KEY (`reset_key_box_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `shedlock` (
    `name` VARCHAR(64) NOT NULL,
    `lock_until` TIMESTAMP(3) NOT NULL,
    `locked_at` TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    `locked_by` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`name`)
);