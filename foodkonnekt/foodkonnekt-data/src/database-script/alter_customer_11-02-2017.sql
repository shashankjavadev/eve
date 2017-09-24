ALTER TABLE `customer`
ADD COLUMN `customerPosId`  varchar(50) NULL AFTER `customer_type`;

ALTER TABLE `customer`
ADD COLUMN `emailPosId`  varchar(50) NULL AFTER `email_id`,
ADD COLUMN `phonePosId`  varchar(50) NULL AFTER `phonenumber`;



