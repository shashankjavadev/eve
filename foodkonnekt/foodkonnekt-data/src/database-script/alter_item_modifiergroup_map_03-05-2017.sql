ALTER TABLE `item_modifiergroup_map`
ADD COLUMN `sortOrder`  int(10) NULL DEFAULT 1 AFTER `modifier_group_status`;