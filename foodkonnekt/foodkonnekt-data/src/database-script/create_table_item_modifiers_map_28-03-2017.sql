CREATE TABLE `item_modifiers_map` (
`id`  int(10) NOT NULL ,
`modifiers_id`  int(10) NULL ,
`item_id`  int(10) NULL ,
`modifier_status`  int(10) NULL DEFAULT 1 ,
PRIMARY KEY (`id`)
);

ALTER TABLE `item_modifiers_map`
MODIFY COLUMN `id`  int(10) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `item_modifiers_map`
ADD COLUMN `modifier_group_id`  int(10) NULL AFTER `modifiers_id`;

