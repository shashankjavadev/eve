ALTER TABLE `merchant`
ADD COLUMN `allow_future_order`  int(1) NULL DEFAULT 0 AFTER `is_install`;