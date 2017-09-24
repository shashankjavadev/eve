

ALTER TABLE `merchant`
ADD COLUMN `allow_multiple_koupon`  int(1) NULL DEFAULT 0 AFTER `merchant_uid`;