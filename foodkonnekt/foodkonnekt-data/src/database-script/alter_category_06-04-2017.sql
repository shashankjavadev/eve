ALTER TABLE `category`
MODIFY COLUMN `sortOrder`  int(45) NULL DEFAULT 0 AFTER `name`;