ALTER TABLE `customer`
MODIFY COLUMN `password`  varchar(1000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL AFTER `email_id`;

