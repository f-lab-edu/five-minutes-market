CREATE TABLE `payment_history` (
    `payment_history_id` int NOT NULL AUTO_INCREMENT,
    `tid` varchar(21) NOT NULL,
    `orders_id` int NOT NULL,
    `success` tinyint NOT NULL,
    `payment_log` varchar(1000)  NOT NULL,
    PRIMARY KEY (`payment_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;