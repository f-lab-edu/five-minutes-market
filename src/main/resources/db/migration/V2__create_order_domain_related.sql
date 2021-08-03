CREATE TABLE `order` (
    `order_id` int NOT NULL AUTO_INCREMENT,
    `total_price` int NOT NULL,
    `address` varchar(100) NOT NULL,
    `payment` varchar(10) NOT NULL,
    `order_status` varchar(10)  NOT NULL,
    `message` varchar(50)  NOT NULL,
    `created_date` datetime  NOT NULL,
    `updated_date` datetime  NOT NULL,
    `user_id` int NOT NULL,
    PRIMARY KEY (`order_id`),
    KEY `user_id` (`user_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `order_product` (
    `order_product_id` int NOT NULL AUTO_INCREMENT,
    `product_name` varchar(40) NOT NULL,
    `amount` int NOT NULL,
    `price` int NOT NULL,
    `order_id` int NOT NULL,
    PRIMARY KEY (`order_product_id`),
    KEY `order_id` (`order_id`),
    FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;