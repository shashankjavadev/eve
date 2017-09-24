ALTER TABLE `merchant`
ADD COLUMN `active_customer_feedback`  int(2) NULL DEFAULT 0 AFTER `future_days_ahead`;

ALTER TABLE `merchant`
ADD COLUMN `time_zone_id`  int(10) NULL AFTER `active_customer_feedback`;

ALTER TABLE `merchant` ADD CONSTRAINT `fk_time_zone_id` FOREIGN KEY (`time_zone_id`) REFERENCES `time_zone` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

