ALTER TABLE `modifiergroup`
MODIFY COLUMN `name`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `id`;

ALTER TABLE `modifiers`
MODIFY COLUMN `name`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `id`;

ALTER TABLE `category`
MODIFY COLUMN `name`  varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL AFTER `id`;



