ALTER TABLE `merchant`
CHANGE COLUMN `unique_id` `merchant_uid`  varchar(1000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL AFTER `time_zone_id`;


ALTER TABLE `vendor`
CHANGE COLUMN `unique_id` `vendor_uid`  varchar(1000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL AFTER `companyId`;