ALTER TABLE `order_r`
MODIFY COLUMN `orderDiscount`  double(10,0) NULL DEFAULT 0 AFTER `delivery_fee`,
ADD COLUMN `is_future_order`  int(3) NULL DEFAULT 0 AFTER `tipAmount`;

