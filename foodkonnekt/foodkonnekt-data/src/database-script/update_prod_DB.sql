ALTER TABLE `order_r`
ADD COLUMN `tipAmount`  double(10,0) NULL AFTER `orderAvgTime`;

ALTER TABLE `customer`
ADD COLUMN `customerPosId`  varchar(50) NULL AFTER `customer_type`;

ALTER TABLE `customer`
ADD COLUMN `emailPosId`  varchar(50) NULL AFTER `email_id`,
ADD COLUMN `phonePosId`  varchar(50) NULL AFTER `phonenumber`;

ALTER TABLE `location`
ADD COLUMN `addressPosId`  varchar(50) NULL AFTER `customer_id`;

ALTER TABLE `merchant`
ADD COLUMN `employeePosId`  varchar(50) NULL AFTER `allow_future_order`;

ALTER TABLE `order_r`
MODIFY COLUMN `orderDiscount`  double(10,0) NULL DEFAULT 0 AFTER `delivery_fee`,
ADD COLUMN `is_future_order`  int(3) NULL DEFAULT 0 AFTER `tipAmount`;

ALTER TABLE `merchant_subscription`
ADD COLUMN `billingStartDate`  datetime NULL AFTER `subscription_id`;

update merchant set is_install=2 where is_install=1