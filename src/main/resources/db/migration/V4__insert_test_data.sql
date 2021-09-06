-- user data
-- password: good123!!
INSERT INTO `user` (user_id, user_name, email, password, address, phone_number, seller, role_type_id, salt)
VALUES (1, 'test user1', 'test1@test.com', '496ba86a6fb68334bcc922ed4a31c094a28ac94303c3e977aa43002c9fe99afe', 'test address1', '01023456789', 0, 2, '76d8e6696f8467df');
-- password: test123!!
INSERT INTO `user` (user_id, user_name, email, password, address, phone_number, seller, role_type_id, salt)
VALUES (2, 'test seller1', 'seller2@test.com', '67c017fe365dda6ede64c51fed9b18386d4578d378a33ee891685ded7210004e', 'test address2', '01033337777', 1, 2, '48157d186d5d30db');

-- order data
INSERT INTO `order` (order_id, total_price, address, order_status, message, created_date, updated_date, user_id)
VALUES (1, 10000000, 'test address1', 'PAYMENT_WAITING', '배송 잘부탁드리겠습니다!', NOW(), NOW(), 1);
INSERT INTO `order` (order_id, total_price, address, order_status, message, created_date, updated_date, user_id)
VALUES (2, 27000, 'test address1', 'DELIVERY', '배송 잘부탁드리겠습니다!', NOW(), NOW(), 1);
INSERT INTO `order` (order_id, total_price, address, order_status, message, created_date, updated_date, user_id)
VALUES (3, 40000, 'test address2', 'CANCELED', 'test message delivery', NOW(), NOW(), 2);
INSERT INTO `order` (order_id, total_price, address, order_status, message, created_date, updated_date, user_id)
VALUES (4, 90000, 'test address2', 'PAYMENT_WAITING', 'test message', NOW(), NOW(), 2);

-- order_product
INSERT INTO `order_product` (product_name, amount, price, order_id)
VALUES ('참외', 10, 3000, 1);
INSERT INTO `order_product` (product_name, amount, price, order_id)
VALUES ('연필', 5, 1600, 1);
INSERT INTO `order_product` (product_name, amount, price, order_id)
VALUES ('노트', 5, 4000, 2);
INSERT INTO `order_product` (product_name, amount, price, order_id)
VALUES ('신발', 1, 7000, 2);
INSERT INTO `order_product` (product_name, amount, price, order_id)
VALUES ('수박', 2, 20000, 3);
INSERT INTO `order_product` (product_name, amount, price, order_id)
VALUES ('셔츠', 2, 15000, 4);
INSERT INTO `order_product` (product_name, amount, price, order_id)
VALUES ('그릇', 3, 20000, 4);