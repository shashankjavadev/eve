ALTER TABLE `customer`
ADD COLUMN `vendor_id`  int(11) NULL AFTER `customerPosId`;

ALTER TABLE `customer` ADD CONSTRAINT `vendor_id_fk` FOREIGN KEY (`vendor_id`) REFERENCES `vendor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;