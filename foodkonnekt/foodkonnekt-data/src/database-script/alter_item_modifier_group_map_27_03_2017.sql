ALTER TABLE `item_modifiergroup_map`
ADD COLUMN `modifier_group_status`  int(10) NULL DEFAULT 1 AFTER `modifiers_limit`;

