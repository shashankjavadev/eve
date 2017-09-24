DROP TABLE IF EXISTS `order_discount`;
CREATE TABLE `order_discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `coupon_code` varchar(60) DEFAULT NULL,
  `date` varchar(60) DEFAULT NULL,
  `invenotry_type` varchar(25) DEFAULT NULL,
  `discount` double(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_frk` (`order_id`),
  CONSTRAINT `order_frk` FOREIGN KEY (`order_id`) REFERENCES `order_r` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;


ALTER TABLE `order_discount`
ADD COLUMN `customer_id`  int(11) NULL AFTER `discount`;

ALTER TABLE `order_discount` ADD CONSTRAINT `cust_frk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

