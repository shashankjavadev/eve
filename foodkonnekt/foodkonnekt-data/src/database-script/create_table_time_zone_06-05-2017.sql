CREATE TABLE `time_zone` (
`id`  int(10) NOT NULL ,
`time_zone`  varchar(10) NULL ,
`hour_difference`  int(10) NULL DEFAULT 0 ,
PRIMARY KEY (`id`)
)
;

ALTER TABLE `time_zone`
CHANGE COLUMN `time_zone` `time_zone_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `id`,
ADD COLUMN `time_zone_code`  varchar(50) NULL AFTER `hour_difference`;

ALTER TABLE `time_zone`
MODIFY COLUMN `id`  int(10) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `time_zone`
ADD COLUMN `minut_difference`  int(10) NULL AFTER `hour_difference`;

ALTER TABLE `time_zone`
MODIFY COLUMN `minut_difference`  int(10) NULL DEFAULT 0 AFTER `hour_difference`;




