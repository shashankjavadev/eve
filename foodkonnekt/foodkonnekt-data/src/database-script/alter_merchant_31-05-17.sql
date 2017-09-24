ALTER TABLE `merchant`
ADD COLUMN `unique_id`  varchar(1000) NULL AFTER `time_zone_id`;

ALTER TABLE `vendor`
ADD COLUMN `unique_id`  varchar(1000) NULL AFTER `companyId`;