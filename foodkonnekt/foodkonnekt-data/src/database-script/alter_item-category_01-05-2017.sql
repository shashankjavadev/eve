ALTER TABLE `item_category`
ADD COLUMN `sortOrder`  int(10) NULL DEFAULT 1 AFTER `id`;

