CREATE TABLE `product` (
                           `product_id` int NOT NULL AUTO_INCREMENT COMMENT '상품 ID',
                           `main_category_id` int NOT NULL COMMENT '메인 카테고리 ID',
                           `sub_category_id` int NOT NULL COMMENT '서브 카테고리 ID',
                           `seller_id` varchar(50) NOT NULL COMMENT '판매자 ID',
                           `quantity` int NOT NULL DEFAULT '0' COMMENT '상품 수량',
                           `name` varchar(50) NOT NULL COMMENT '상품 이름',
                           `price` int NOT NULL DEFAULT '0' COMMENT '상품 가격',
                           `thumb` varchar(200) DEFAULT NULL COMMENT '썸네일 URL',
                           `detail` longtext COMMENT '상품 설명',
                           PRIMARY KEY (`product_id`),
                           KEY `main_category_index` (`main_category_id`),
                           KEY `sub_category_index` (`sub_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='상품 테이블';

CREATE TABLE `main_category` (
                            `main_category_id` int NOT NULL AUTO_INCREMENT,
                            `main_category_name` varchar(50) NOT NULL,
                            PRIMARY KEY (`main_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `sub_category` (
                            `sub_category_id` int NOT NULL AUTO_INCREMENT,
                            `sub_category_name` varchar(50) NOT NULL,
                            `main_category_id` int NOT NULL,
                            PRIMARY KEY (`sub_category_id`),
                            KEY `main_category_id` (`main_category_id`),
                            FOREIGN KEY (`main_category_id`) REFERENCES `main_category` (`main_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `product_option` (
                          `product_option_id` int NOT NULL AUTO_INCREMENT,
                          `optional` tinyint(1) NOT NULL,
                          `product_id` int NOT NULL,
                          PRIMARY KEY (`product_option_id`),
                          KEY `product_id` (`product_id`),
                          FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `product_option_item` (
                               `product_option_item_id` int NOT NULL AUTO_INCREMENT,
                               `product_option_item_name` varchar(50) NOT NULL,
                               `product_option_item_price` int NOT NULL,
                               `product_option_id` int NOT NULL,
                               PRIMARY KEY (`product_option_item_id`),
                               KEY `product_option_id` (`product_option_id`),
                               FOREIGN KEY (`product_option_id`) REFERENCES `product_option` (`product_option_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `user` (
                        `user_id` int NOT NULL AUTO_INCREMENT,
                        `user_name` varchar(50)  NOT NULL,
                        `email` varchar(50) NOT NULL,
                        `password` varchar(500) NOT NULL,
                        `address` varchar(100) NOT NULL,
                        `phone_number` varchar(11) NOT NULL,
                        `seller` tinyint NOT NULL,
                        `role_type_id` int NOT NULL,
                        `salt` varchar(20) NOT NULL,
                        PRIMARY KEY (`user_id`),
                        KEY `role_type_id` (`role_type_id`),
                        FOREIGN KEY (`role_type_id`) REFERENCES `role_type` (`role_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `role_type` (
                        `role_type_id` int NOT NULL AUTO_INCREMENT,
                        `role_type_name` varchar(20) NOT NULL,
                        PRIMARY KEY (`role_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `role_type` (`role_type_name`) VALUES ('ROLE_ADMIN');
INSERT INTO `role_type` (`role_type_name`) VALUES ('ROLE_USER');