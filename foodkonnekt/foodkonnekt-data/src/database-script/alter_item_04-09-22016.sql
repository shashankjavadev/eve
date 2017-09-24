ALTER TABLE `item`
ADD COLUMN `allow_modifier_limit`  int(3) ZEROFILL NULL DEFAULT 0 AFTER `modifiers_limit`;

