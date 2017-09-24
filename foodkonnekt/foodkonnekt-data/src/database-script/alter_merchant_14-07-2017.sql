ALTER TABLE `merchant`
ADD COLUMN `future_days_ahead`  int(10) NULL DEFAULT 10 AFTER `employeePosId`;

ALTER TABLE `merchant`
MODIFY COLUMN `vendor_id`  int(20) NULL AFTER `accessToken`;



