ALTER TABLE `orders`
    CHANGE COLUMN `order_status` `order_status` VARCHAR(30) NOT NULL ;

UPDATE `orders` SET `order_status` = 'PAYMENT_WAITING' WHERE (`order_id` = '1');
UPDATE `orders` SET `order_status` = 'PAYMENT_WAITING' WHERE (`order_id` = '4');