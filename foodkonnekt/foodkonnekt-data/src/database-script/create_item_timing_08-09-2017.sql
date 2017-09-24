CREATE TABLE `item_timing` (
`id`  int(11) NOT NULL ,
`day`  varchar(11) NULL ,
`start_time`  varchar(11) NULL ,
`end_time`  varchar(11) NULL ,
`item_id`  int(11) NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
;

ALTER TABLE `item_timing`
ADD COLUMN `isHoliday`  tinyint(1) NULL AFTER `end_time`;

ALTER TABLE `item_timing`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT FIRST ;





