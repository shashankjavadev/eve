ALTER TABLE `modifiers`
ADD COLUMN `is_deleted`  int(10) NULL DEFAULT 0 AFTER `merchant_id`;