CREATE TABLE `category_timing` (
`id`  int(11) NOT NULL ,
`day`  varchar(11) NULL ,
`start_time`  varchar(11) NULL ,
`end_time`  varchar(11) NULL ,
`category_id`  int(11) NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
;

ALTER TABLE `category_timing`
ADD COLUMN `isHoliday`  tinyint(1) NULL AFTER `end_time`;

ALTER TABLE `category_timing`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT FIRST ;

