ALTER TABLE `customer` DROP FOREIGN KEY `fk_vendor_id`;

ALTER TABLE `customer`
DROP COLUMN `vendor_id`,
MODIFY COLUMN `created_date`  varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL AFTER `phonenumber`;

ALTER TABLE `customer`
ADD COLUMN `merchant_id`  int(11) NULL AFTER `subscribe`;

ALTER TABLE `customer` ADD FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

