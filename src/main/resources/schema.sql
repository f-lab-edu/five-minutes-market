CREATE TABLE `product` (
                           `product_id` int NOT NULL AUTO_INCREMENT COMMENT '상품 ID',
                           `main_category_id` int NOT NULL COMMENT '메인 카테고리 ID',
                           `sub_category_id` int NOT NULL COMMENT '서브 카테고리 ID',
                           `name` varchar(50) NOT NULL COMMENT '상품 이름',
                           `price` int NOT NULL DEFAULT '0' COMMENT '상품 가격',
                           `thumb` varchar(200) DEFAULT NULL COMMENT '썸네일 URL',
                           `detail` longtext COMMENT '상품 설명',
                           PRIMARY KEY (`product_id`),
                           KEY `main_category_index` (`main_category_id`),
                           KEY `sub_category_index` (`sub_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='상품 테이블'