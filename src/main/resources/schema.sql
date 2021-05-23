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

CREATE TABLE main_category
(
    main_category_id 		int AUTO_INCREMENT PRIMARY KEY,
    main_category_name      VARCHAR(50) NOT NULL
);

CREATE TABLE sub_category
(
    sub_category_id  		int AUTO_INCREMENT PRIMARY KEY,
    sub_category_name       VARCHAR(50) NOT NULL,
    main_category_id		BIGINT NOT NULL,
    FOREIGN KEY (main_category_id) REFERENCES main_category (main_category_id)
);

CREATE TABLE `option`
(
    option_id 		        int AUTO_INCREMENT PRIMARY KEY,
    is_optional		        boolean NOT NULL,
    product_id		        int	NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product (product_id)
);

CREATE TABLE option_item
(
    option_item_id 		    int AUTO_INCREMENT PRIMARY KEY,
    option_item_name		VARCHAR(50) NOT NULL,
    option_item_price		INT	NOT NULL,
    option_id			    int NOT NULL,
    FOREIGN KEY (option_id) REFERENCES `option` (option_id)
);
