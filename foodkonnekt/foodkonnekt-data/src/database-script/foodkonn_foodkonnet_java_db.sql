/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : foodkonn_foodkonnet_java_db

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2016-07-23 13:03:39
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `sortOrder` varchar(45) DEFAULT NULL,
  `pos_category_id` varchar(20) DEFAULT NULL,
  `desc` varchar(45) DEFAULT NULL,
  `merchant_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_merchant_1` (`merchant_id`),
  CONSTRAINT `fk_merchant_1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=711 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO category VALUES ('690', '16oz Fountain Drink', '11', 'MZH33JGD8NKC4', null, '16');
INSERT INTO category VALUES ('691', 'Tortas', '9', 'ZPP41AJJEHT40', null, '16');
INSERT INTO category VALUES ('692', 'Breakfast Items', '7', '5GTR7FM6W4ASE', null, '16');
INSERT INTO category VALUES ('693', 'Combo Items', '6', 'R6MCJXPFTAMHP', null, '16');
INSERT INTO category VALUES ('694', 'Salad Items', '5', '1HNKJGHBPAN2P', null, '16');
INSERT INTO category VALUES ('695', 'Desserts', '3', '6DD7R5T4REYWT', null, '16');
INSERT INTO category VALUES ('696', 'Drinks', '2', 'M56T69XBG0VMG', null, '16');
INSERT INTO category VALUES ('697', 'Sides', '1', '28ZJR1244CVB0', null, '16');
INSERT INTO category VALUES ('698', 'Chips', '0', 'G022X3AC4XDEE', null, '16');
INSERT INTO category VALUES ('699', '16oz Fountain Drink', '11', 'E04VZTFDX7SDY', null, '17');
INSERT INTO category VALUES ('700', 'Catering ', '10', 'P22731EEVQ3QA', null, '17');
INSERT INTO category VALUES ('701', 'Tortas', '9', '9TKD2AG0MC1TY', null, '17');
INSERT INTO category VALUES ('702', 'Kids Meal', '8', '33G28QJSMBEC8', null, '17');
INSERT INTO category VALUES ('703', 'Breakfast Items', '7', '8TYWH8QAB0YHW', null, '17');
INSERT INTO category VALUES ('704', 'Combo Items', '6', '06R7K6KW9K7M0', null, '17');
INSERT INTO category VALUES ('705', 'Salad Items', '5', 'FBTX62HVJMMXC', null, '17');
INSERT INTO category VALUES ('706', 'Signature Tacos and Other Items', '4', '8X1YZPCTYJT2W', null, '17');
INSERT INTO category VALUES ('707', 'Desserts', '3', 'K2EBG3H1WFQZA', null, '17');
INSERT INTO category VALUES ('708', 'Drinks', '2', 'KYQW8AWE5ZXR4', null, '17');
INSERT INTO category VALUES ('709', 'Sides', '1', 'XFET1RWBGTRWM', null, '17');
INSERT INTO category VALUES ('710', 'Chips', '0', 'JQJ0HJ51S8MK6', null, '17');

-- ----------------------------
-- Table structure for `category_item_map`
-- ----------------------------
DROP TABLE IF EXISTS `category_item_map`;
CREATE TABLE `category_item_map` (
  `category_id` int(20) DEFAULT NULL,
  `item_id` int(20) DEFAULT NULL,
  KEY `fk_item_id` (`item_id`),
  CONSTRAINT `category_item_map_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of category_item_map
-- ----------------------------

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email_id` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phonenumber` varchar(50) DEFAULT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `created_date` varchar(50) DEFAULT NULL,
  `updated_date` varchar(50) DEFAULT NULL,
  `image` longtext,
  `birth_date` varchar(50) DEFAULT NULL,
  `anniversary_date` varchar(50) DEFAULT NULL,
  `subscribe` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vendor_id` (`vendor_id`),
  CONSTRAINT `fk_vendor_id` FOREIGN KEY (`vendor_id`) REFERENCES `vendor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO customer VALUES ('1', 'praveen', 'Raghuvanshi', 'praveen.raghuvanshii@gmail.com', '12345678', '1234567890', '4', null, null, 'data:image/png;base64,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', 'Mar,14', 'Apr,12', 'true');
INSERT INTO customer VALUES ('2', 'Vicky', null, 'vikas@gmail.com', '12345678', '1234567890', '4', null, null, null, null, null, null);
INSERT INTO customer VALUES ('3', 'rakesh', null, 'rakesh@gmail.com', '12345678', '1234567890', '4', null, null, null, null, null, null);
INSERT INTO customer VALUES ('4', 'dddddd', null, 'ddd@gmail.com', '12345678', '1234567890', '4', null, null, null, null, null, null);
INSERT INTO customer VALUES ('5', 'sumit', null, 'sumit@gmail.com', '12345678', '1234567890', '5', null, null, null, null, null, null);
INSERT INTO customer VALUES ('6', 'aaaa', null, 'aaaa@gmail.com', '12345678', '1234567890', '5', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isHidden` varchar(10) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `priceType` varchar(45) DEFAULT NULL,
  `isRevenue` varchar(10) DEFAULT NULL,
  `modifiedTime` date DEFAULT NULL,
  `pos_item_id` varchar(30) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `isDefaultTaxRates` tinyint(1) DEFAULT NULL,
  `unitName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ifpkoub1n371ibos4cdrdo2re` (`merchant_id`),
  CONSTRAINT `FK_ifpkoub1n371ibos4cdrdo2re` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4400 DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 10240 kB; (`locaiton_id`) REFER `foodkonnekt/st';

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO item VALUES ('4208', '0', 'Torta', '6.99', 'FIXED', '1', '2015-12-04', 'YK00HG176KQTA', '16', '1', null);
INSERT INTO item VALUES ('4209', '0', 'Breakfast Tacos', '1.99', 'FIXED', '1', '2015-12-04', 'BBZMKAVEQ12S4', '16', '1', null);
INSERT INTO item VALUES ('4210', '0', 'Breakfast Burritos', '3.99', 'FIXED', '1', '2015-12-04', 'ZG5KVG7QZ63T4', '16', '1', null);
INSERT INTO item VALUES ('4211', '0', 'Big D', '5.69', 'FIXED', '1', '2016-06-20', 'G0XHDSHXNXPY8', '16', '1', null);
INSERT INTO item VALUES ('4212', '0', 'Chilaquilles', '7.69', 'FIXED', '1', '2015-12-04', 'SD0NPDHY1SKYT', '16', '1', null);
INSERT INTO item VALUES ('4213', '0', 'Huevos Rancheros', '7.69', 'FIXED', '1', '2015-12-04', 'XK464M0AEVCME', '16', '1', null);
INSERT INTO item VALUES ('4214', '0', 'Migas', '7.69', 'FIXED', '1', '2015-12-04', 'C3GRRFFF9AR6G', '16', '1', null);
INSERT INTO item VALUES ('4215', '0', 'Bacon & Egg Platter', '7.69', 'FIXED', '1', '2015-12-04', '3B2KMYTKWM8KP', '16', '1', null);
INSERT INTO item VALUES ('4216', '0', 'Chorizo & Egg Platter', '7.69', 'FIXED', '1', '2015-12-04', 'JAHEPENN2N5T0', '16', '1', null);
INSERT INTO item VALUES ('4217', '0', 'Ham & Egg Platter', '7.69', 'FIXED', '1', '2015-12-04', 'WXWSKCS46RCAG', '16', '1', null);
INSERT INTO item VALUES ('4218', '0', 'Potato & Egg Platter', '7.69', 'FIXED', '1', '2015-12-04', 'M72AR11XXEEMM', '16', '1', null);
INSERT INTO item VALUES ('4219', '0', 'Sausage & Egg Platter', '7.69', 'FIXED', '1', '2015-12-04', 'YB8HDJZZRF8CM', '16', '1', null);
INSERT INTO item VALUES ('4220', '0', 'Steak & Egg Platter', '7.69', 'FIXED', '1', '2015-12-04', 'ERYP9BR78PQFP', '16', '1', null);
INSERT INTO item VALUES ('4221', '0', 'El Monje', '1.99', 'FIXED', '1', '2015-12-04', 'DP0YPYVX4BF06', '16', '1', null);
INSERT INTO item VALUES ('4222', '0', 'B-fats Sanwich', '5.99', 'FIXED', '1', '2015-12-04', 'Z2BCGKWJG1ZP2', '16', '1', null);
INSERT INTO item VALUES ('4223', '0', 'Combo #1 Mexican Street Taco', '7.99', 'FIXED', '1', '2015-12-04', 'BJ7SCXMWKWC0J', '16', '1', null);
INSERT INTO item VALUES ('4224', '0', 'Combo #2 Signature Taco', '7.99', 'FIXED', '1', '2016-06-26', 'ZACYC5221H6YC', '16', '1', null);
INSERT INTO item VALUES ('4225', '0', 'Combo #3 Crispy Taco', '7.59', 'FIXED', '1', '2015-12-04', 'WPDJJY5PZZMWT', '16', '1', null);
INSERT INTO item VALUES ('4226', '0', 'Combo #4 Quesadilla Platter', '7.99', 'FIXED', '1', '2015-12-04', 'MBJB2JQVPS6NE', '16', '1', null);
INSERT INTO item VALUES ('4227', '0', 'Combo #5 Burrito Platter', '7.99', 'FIXED', '1', '2015-12-04', 'DA55PMQ7VBGQJ', '16', '1', null);
INSERT INTO item VALUES ('4228', '0', 'Combo #6 Cubano Combo', '7.99', 'FIXED', '1', '2015-12-04', 'CKK4K0GZRWKRT', '16', '1', null);
INSERT INTO item VALUES ('4229', '0', 'Combo #7 Fish Platter', '8.99', 'FIXED', '1', '2015-12-04', '5Z63X4FHVCBKA', '16', '1', null);
INSERT INTO item VALUES ('4230', '0', '#8Enchilada Platter', '8.99', 'FIXED', '1', '2016-06-21', 'V2DGMGJ4AHAGT', '16', '1', null);
INSERT INTO item VALUES ('4231', '0', 'Kids Meal', '3.49', 'FIXED', '1', '2015-12-04', '5JS10FKQR00HC', '16', '1', null);
INSERT INTO item VALUES ('4232', '0', 'Steak Salad', '6.99', 'FIXED', '1', '2015-12-04', 'VQXJ7V2WZ8QM0', '16', '1', null);
INSERT INTO item VALUES ('4233', '0', 'Chicken Salad', '6.99', 'FIXED', '1', '2015-12-04', '1MXBWHHQ3E8BJ', '16', '1', null);
INSERT INTO item VALUES ('4234', '0', 'Chicken Chipotle Salad', '6.99', 'FIXED', '1', '2015-12-04', 'BPS7N7ZNSP8HE', '16', '1', null);
INSERT INTO item VALUES ('4235', '0', 'Taco Salad', '6.99', 'FIXED', '1', '2015-12-04', 'RK2XF7D20VFDR', '16', '1', null);
INSERT INTO item VALUES ('4236', '0', 'Churros', '1.99', 'FIXED', '1', '2015-12-04', '4J866NPKYAHHT', '16', '1', null);
INSERT INTO item VALUES ('4237', '0', 'Tres Leches Cake', '3.59', 'FIXED', '1', '2015-12-04', 'ZR9VRKBAP23M4', '16', '1', null);
INSERT INTO item VALUES ('4238', '0', 'Churros  1/2 Docena', '9.99', 'FIXED', '1', '2015-12-04', '2WBJHJNGV0JRC', '16', '1', null);
INSERT INTO item VALUES ('4239', '0', 'Churros 12', '19.99', 'FIXED', '1', '2015-12-04', '7R1S5VRPYVY8M', '16', '1', null);
INSERT INTO item VALUES ('4240', '0', '32oz Fountain Drink', '1.99', 'FIXED', '1', '2015-12-04', 'RNJTCTYWEEF3M', '16', '1', null);
INSERT INTO item VALUES ('4241', '0', 'Mexican Coke', '2.49', 'FIXED', '1', '2015-12-04', '0HTK6JAW09TCR', '16', '1', null);
INSERT INTO item VALUES ('4242', '0', 'Mexican Flavored Water', '2.49', 'FIXED', '1', '2015-12-04', '9MC7K971DEM9W', '16', '1', null);
INSERT INTO item VALUES ('4243', '0', 'Coffee', '1.99', 'FIXED', '1', '2015-12-04', 'M1JFS8886C1H8', '16', '1', null);
INSERT INTO item VALUES ('4244', '0', 'Bottled Water', '2', 'FIXED', '1', '2015-12-04', '6BPVDKNW131QW', '16', '1', null);
INSERT INTO item VALUES ('4245', '0', 'Monster', '2', 'FIXED', '1', '2015-12-04', 'F7Z4P8KKHSAMP', '16', '1', null);
INSERT INTO item VALUES ('4246', '0', 'Powerade', '2', 'FIXED', '1', '2015-12-04', 'QCAK42Q394AFC', '16', '1', null);
INSERT INTO item VALUES ('4247', '0', 'Orange Juice', '1.99', 'FIXED', '1', '2015-12-04', 'ZYD4EQCVQ36MY', '16', '1', null);
INSERT INTO item VALUES ('4248', '0', 'Tropical Blend Juice', '1.99', 'FIXED', '1', '2015-12-04', 'F9NVZ4WBEX05A', '16', '1', null);
INSERT INTO item VALUES ('4249', '0', 'Mexican Fanta', '2.49', 'FIXED', '1', '2015-12-04', '211HGHYT532AW', '16', '1', null);
INSERT INTO item VALUES ('4250', '0', 'Side of Chips', '1.49', 'FIXED', '1', '2015-12-04', 'JJ6G7NMD0KC3P', '16', '1', null);
INSERT INTO item VALUES ('4251', '0', 'Rice', '1.49', 'FIXED', '1', '2015-12-04', 'KHJCSC2FKEMYJ', '16', '1', null);
INSERT INTO item VALUES ('4252', '0', 'Beans', '1.49', 'FIXED', '1', '2015-12-04', '7EM5H8YDNF1GC', '16', '1', null);
INSERT INTO item VALUES ('4253', '0', 'Guacamole', '1.99', 'FIXED', '1', '2015-12-04', 'JG7ZK4PZ09D72', '16', '1', null);
INSERT INTO item VALUES ('4254', '0', 'Queso', '2.09', 'FIXED', '1', '2015-12-04', 'B68ZTAQ02FE1C', '16', '1', null);
INSERT INTO item VALUES ('4255', '0', 'Potatoes', '1.49', 'FIXED', '1', '2015-12-04', 'D6FSMD42GNQQ8', '16', '1', null);
INSERT INTO item VALUES ('4256', '0', 'Sour Cream', '0.69', 'FIXED', '1', '2015-12-04', 'QXFBMEE5HSHYC', '16', '1', null);
INSERT INTO item VALUES ('4257', '0', 'Side Of Salsa', '1', 'FIXED', '1', '2015-12-04', 'KQY1QV14NQ1QG', '16', '1', null);
INSERT INTO item VALUES ('4258', '0', 'Sliced Avocado', '1', 'FIXED', '1', '2015-12-04', 'K9JAJD2FV3R1A', '16', '1', null);
INSERT INTO item VALUES ('4259', '0', 'Charro Beans', '2.99', 'FIXED', '1', '2015-12-04', 'M3G0R99EC87VR', '16', '1', null);
INSERT INTO item VALUES ('4260', '0', 'Menudo', '9.99', 'FIXED', '1', '2015-12-04', '5D3CD4SQQJ9B4', '16', '1', null);
INSERT INTO item VALUES ('4261', '0', 'Atole', '1.99', 'FIXED', '1', '2015-12-04', 'MQ0790THP7TJC', '16', '1', null);
INSERT INTO item VALUES ('4262', '0', 'Chips & Salsa', '2.59', 'FIXED', '1', '2015-12-04', 'Y8FY3E8QYC88T', '16', '1', null);
INSERT INTO item VALUES ('4263', '0', 'Chips & Queso', '3.59', 'FIXED', '1', '2015-12-04', 'AD1RMHNXMJHE4', '16', '1', null);
INSERT INTO item VALUES ('4264', '0', 'Chips & Guacamole', '3.59', 'FIXED', '1', '2015-12-04', 'S5D83TVYFFEZG', '16', '1', null);
INSERT INTO item VALUES ('4265', '0', 'Trio', '4.99', 'FIXED', '1', '2015-12-04', '40XD7TJR73GF2', '16', '1', null);
INSERT INTO item VALUES ('4266', '0', 'testP', '0.25', 'FIXED', '1', '2016-06-19', '11228F3XWR92C', '16', '1', null);
INSERT INTO item VALUES ('4267', '1', 'Convenience Fee', '0', 'FIXED', '1', '2016-01-05', 'TRQE2WVJT5A7Y', '16', '1', '');
INSERT INTO item VALUES ('4268', '1', 'Delivery Fee', '1', 'FIXED', '1', '2016-01-05', 'WNQTYPS5RACXE', '16', '1', '');
INSERT INTO item VALUES ('4269', '1', 'Convenience Fee', '3', 'FIXED', '1', '2016-01-05', 'V4T5PKYRV5TW6', '16', '1', '');
INSERT INTO item VALUES ('4270', '1', 'Delivery Fee', '2', 'FIXED', '1', '2016-01-05', 'C3ADM0XN6Y2DR', '16', '1', '');
INSERT INTO item VALUES ('4271', '1', 'Delivery Fee', '1', 'FIXED', '1', '2016-01-05', '1QFTHC31ZMWF4', '16', '1', '');
INSERT INTO item VALUES ('4272', '1', 'Convenience Fee', '2.1', 'FIXED', '1', '2015-12-04', 'V1027M8RJ0KXC', '16', '0', '');
INSERT INTO item VALUES ('4273', '1', 'Delivery Fee', '1', 'FIXED', '1', '2015-12-04', 'REAN4BNCN1B10', '16', '0', '');
INSERT INTO item VALUES ('4274', '0', 'Tamal', '1.99', 'FIXED', '1', '2015-12-04', 'GCZ57NBS3HEJC', '16', '1', null);
INSERT INTO item VALUES ('4275', '0', 'Crispy Taco', '1.5', 'FIXED', '1', '2015-12-04', 'HMPNFNYCWQEJR', '16', '1', null);
INSERT INTO item VALUES ('4276', '0', '6pack Signature Tacos', '15.99', 'FIXED', '1', '2015-12-04', '8VHG5NA07SZC8', '16', '1', null);
INSERT INTO item VALUES ('4278', '0', '6 Pack Street Tacos', '11', 'FIXED', '1', '2015-12-04', 'N5FCHS9DZF77J', '16', '1', null);
INSERT INTO item VALUES ('4279', '0', '10 Pack Street Tacos', '18', 'FIXED', '1', '2015-12-04', 'YRJD77CARTF50', '16', '1', null);
INSERT INTO item VALUES ('4280', '0', 'Gallon of Tea', '4.95', 'FIXED', '1', '2015-12-04', 'Y5P271NKB2654', '16', '1', null);
INSERT INTO item VALUES ('4281', '0', 'Large Rice', '5', 'FIXED', '1', '2015-12-04', 'A5YTDDCP9337P', '16', '1', null);
INSERT INTO item VALUES ('4282', '0', 'Large Beans', '5', 'FIXED', '1', '2015-12-04', 'R6GVBJE1NRSGY', '16', '1', null);
INSERT INTO item VALUES ('4283', '0', 'Large Guacamole', '12', 'FIXED', '1', '2015-12-04', '31PB210T34QD6', '16', '1', null);
INSERT INTO item VALUES ('4284', '0', 'Large Salsa', '5', 'FIXED', '1', '2015-12-04', 'MN111KE7W8VPP', '16', '1', null);
INSERT INTO item VALUES ('4285', '0', 'Large Queso', '10', 'FIXED', '1', '2015-12-04', 'QJFPQ4EC9YGD0', '16', '1', null);
INSERT INTO item VALUES ('4286', '0', 'Tostada', '2.29', 'FIXED', '1', '2015-12-04', '1SAAJTVM27KVW', '16', '1', null);
INSERT INTO item VALUES ('4287', '0', 'Tortilla Soup', '2.99', 'FIXED', '1', '2015-12-04', 'NHVFTJCF1E78G', '16', '1', null);
INSERT INTO item VALUES ('4288', '0', 'Quesadilla', '6.99', 'FIXED', '1', '2015-12-04', 'BMG91XV0SH0FE', '16', '1', null);
INSERT INTO item VALUES ('4289', '0', 'Nachos', '5.99', 'FIXED', '1', '2015-12-04', 'GMSD0ECSE1AS2', '16', '1', null);
INSERT INTO item VALUES ('4290', '0', 'Grande Burrito', '5.99', 'FIXED', '1', '2015-12-04', 'BKS8KHAY5P83P', '16', '1', null);
INSERT INTO item VALUES ('4291', '0', 'Pescador', '2.99', 'FIXED', '1', '2015-12-04', '6SYW06EVQTQDP', '16', '1', null);
INSERT INTO item VALUES ('4292', '0', 'Marinero', '2.99', 'FIXED', '1', '2015-12-04', 'J3MXMMQZZPF8T', '16', '1', null);
INSERT INTO item VALUES ('4293', '0', 'Capitan', '2.99', 'FIXED', '1', '2015-12-04', 'FPVH66A4CFXP6', '16', '1', null);
INSERT INTO item VALUES ('4294', '0', 'El Verde', '2.99', 'FIXED', '1', '2015-12-04', 'WAEAH988HMZGE', '16', '1', null);
INSERT INTO item VALUES ('4295', '0', 'Monterrey', '2.99', 'FIXED', '1', '2015-12-04', 'FM8C4NCAXN2VC', '16', '1', null);
INSERT INTO item VALUES ('4296', '0', 'Chicken Fajita', '2.99', 'FIXED', '1', '2015-12-04', '3QSY4D5KAWJJY', '16', '1', null);
INSERT INTO item VALUES ('4297', '0', 'Steak Fajita', '2.99', 'FIXED', '1', '2015-12-04', 'N5JSF8YRBDXB0', '16', '1', null);
INSERT INTO item VALUES ('4298', '0', 'Chicken Chipotle', '2.99', 'FIXED', '1', '2015-12-04', 'E899R2X3YGA6P', '16', '1', null);
INSERT INTO item VALUES ('4299', '0', 'Presidente', '2.99', 'FIXED', '1', '2015-12-04', 'KN5JY00JV3WK4', '16', '1', null);
INSERT INTO item VALUES ('4300', '0', 'Street Tacos', '1.99', 'FIXED', '1', '2015-12-04', '55B84VW87D6RP', '16', '1', null);
INSERT INTO item VALUES ('4301', '0', 'Large Queso', '10', 'FIXED', '1', '2015-12-08', 'J8X5A5RJAJ58M', '17', '1', null);
INSERT INTO item VALUES ('4302', '0', 'Large Salsa', '5', 'FIXED', '1', '2015-12-08', 'W7FRE14HV9Z7G', '17', '1', null);
INSERT INTO item VALUES ('4303', '0', 'Large Guacamole', '12', 'FIXED', '1', '2015-12-08', 'EP5THRZDXVRXC', '17', '1', null);
INSERT INTO item VALUES ('4304', '0', 'Large Beans', '5', 'FIXED', '1', '2015-12-08', 'GBXZER3CG96RC', '17', '1', null);
INSERT INTO item VALUES ('4305', '0', 'Large Rice', '5', 'FIXED', '1', '2015-12-08', '9XDHY6M4TQDZP', '17', '1', null);
INSERT INTO item VALUES ('4306', '0', 'Side of Chips', '1.49', 'FIXED', '1', '2015-12-08', '35WG4ES46MR2M', '17', '1', null);
INSERT INTO item VALUES ('4307', '0', 'Gallon of Tea', '4.95', 'FIXED', '1', '2015-12-08', 'QPDPP48CRW2GA', '17', '1', null);
INSERT INTO item VALUES ('4308', '0', 'Torta', '6.99', 'FIXED', '1', '2015-12-08', 'QV6T5EG0YVFR6', '17', '1', null);
INSERT INTO item VALUES ('4309', '0', 'Kids Meal', '3.49', 'FIXED', '1', '2015-12-08', 'ZED8BKPRTE2DW', '17', '1', null);
INSERT INTO item VALUES ('4310', '0', 'Breakfast Tacos', '1.99', 'FIXED', '1', '2015-12-08', 'BBDQB5HJ3B1ER', '17', '1', null);
INSERT INTO item VALUES ('4311', '0', 'Breakfast Burritos', '3.99', 'FIXED', '1', '2015-12-08', '9PEQHK06AMAZY', '17', '1', null);
INSERT INTO item VALUES ('4312', '0', 'Big D', '5.69', 'FIXED', '1', '2015-12-08', 'VXM0BQB9M009G', '17', '1', null);
INSERT INTO item VALUES ('4313', '0', 'Chilaquilles', '7.69', 'FIXED', '1', '2015-12-08', 'SN2HRH82YCSHJ', '17', '1', null);
INSERT INTO item VALUES ('4314', '0', 'Huevos Rancheros', '7.69', 'FIXED', '1', '2015-12-08', 'A93J1T0467BDJ', '17', '1', null);
INSERT INTO item VALUES ('4315', '0', 'Migas', '7.69', 'FIXED', '1', '2015-12-08', 'M3P7WJ6YBYSVT', '17', '1', null);
INSERT INTO item VALUES ('4316', '0', 'Bacon & Egg Platter', '7.69', 'FIXED', '1', '2015-12-08', 'J89S0AX2R0AFP', '17', '1', null);
INSERT INTO item VALUES ('4317', '0', 'Chorizo & Egg Platter', '7.69', 'FIXED', '1', '2015-12-08', '9WJVAQZ7DYFZY', '17', '1', null);
INSERT INTO item VALUES ('4318', '0', 'Ham & Egg Platter', '7.69', 'FIXED', '1', '2015-12-08', '7JH2D03BYAC84', '17', '1', null);
INSERT INTO item VALUES ('4319', '0', 'Potato & Egg Platter', '7.69', 'FIXED', '1', '2015-12-08', '25WVDPJAGAVPT', '17', '1', null);
INSERT INTO item VALUES ('4320', '0', 'Sausage & Egg Platter', '7.69', 'FIXED', '1', '2015-12-08', 'GAH4PM08CPDSP', '17', '1', null);
INSERT INTO item VALUES ('4321', '0', 'Steak & Egg Platter', '7.69', 'FIXED', '1', '2015-12-08', 'D1M52KX9DETJT', '17', '1', null);
INSERT INTO item VALUES ('4322', '0', 'El Monje', '1.99', 'FIXED', '1', '2015-12-08', '6FZ26ZB1WSDYR', '17', '1', null);
INSERT INTO item VALUES ('4323', '0', 'B-fats Sanwich', '5.99', 'FIXED', '1', '2015-12-08', 'DHCCS14CBCYPP', '17', '1', null);
INSERT INTO item VALUES ('4324', '0', 'Combo #1 Mexican Street Taco', '7.99', 'FIXED', '1', '2015-12-08', '78W2K5R2150ZP', '17', '1', null);
INSERT INTO item VALUES ('4325', '0', 'Combo #2 Signature Taco', '7.99', 'FIXED', '1', '2015-12-08', 'B4GFZQYV5RGKW', '17', '1', null);
INSERT INTO item VALUES ('4326', '0', 'Combo #3 Crispy Taco', '7.59', 'FIXED', '1', '2015-12-08', 'V47GH4KKX5N4A', '17', '1', null);
INSERT INTO item VALUES ('4327', '0', 'Combo #4 Quesadilla Platter', '7.99', 'FIXED', '1', '2015-12-08', 'Z1D9PNCBK6ATG', '17', '1', null);
INSERT INTO item VALUES ('4328', '0', 'Combo #5 Burrito Platter', '7.99', 'FIXED', '1', '2015-12-08', 'Y5VFAH3124XR8', '17', '1', null);
INSERT INTO item VALUES ('4329', '0', 'Combo #6 Cubano Combo', '7.99', 'FIXED', '1', '2015-12-08', 'FB879D0NC01YG', '17', '1', null);
INSERT INTO item VALUES ('4330', '0', 'Combo #7 Fish Platter', '8.99', 'FIXED', '1', '2015-12-08', 'KGCQPZVHQZZMW', '17', '1', null);
INSERT INTO item VALUES ('4331', '0', '#8Enchilada Platter', '8.99', 'FIXED', '1', '2016-05-22', 'PPFGQ8K52VD0R', '17', '0', null);
INSERT INTO item VALUES ('4332', '0', '25 Tacos', '45', 'FIXED', '1', '2015-12-08', '1FGTKVBY8VDKA', '17', '1', null);
INSERT INTO item VALUES ('4333', '0', 'Steak Salad', '6.99', 'FIXED', '1', '2015-12-08', 'Q8EVQVJH74ERA', '17', '1', null);
INSERT INTO item VALUES ('4334', '0', 'Chicken Salad', '6.99', 'FIXED', '1', '2015-12-08', 'P40PTHGJY3ATW', '17', '1', null);
INSERT INTO item VALUES ('4335', '0', 'Chicken Chipotle Salad', '6.99', 'FIXED', '1', '2015-12-08', '6066WTVFZ35VR', '17', '1', null);
INSERT INTO item VALUES ('4336', '0', 'Taco Salad', '6.99', 'FIXED', '1', '2015-12-08', '0ZCKP97P7PHEM', '17', '1', null);
INSERT INTO item VALUES ('4337', '0', 'Street Tacos', '1.99', 'FIXED', '1', '2015-12-08', '1EFKNKBJTFMEE', '17', '1', null);
INSERT INTO item VALUES ('4338', '0', 'Presidente', '2.99', 'FIXED', '1', '2015-12-08', 'W5X0XPV1EB26A', '17', '1', null);
INSERT INTO item VALUES ('4339', '0', 'Chicken Chipotle', '2.99', 'FIXED', '1', '2015-12-08', 'CRPA54T5X3FHG', '17', '1', null);
INSERT INTO item VALUES ('4340', '0', 'Steak Fajita', '2.99', 'FIXED', '1', '2015-12-08', '19BAF6AA73JPG', '17', '1', null);
INSERT INTO item VALUES ('4341', '0', 'Chicken Fajita', '2.99', 'FIXED', '1', '2015-12-08', 'RP35Z7MD6Y8A2', '17', '1', null);
INSERT INTO item VALUES ('4342', '0', 'Monterrey', '2.99', 'FIXED', '1', '2015-12-08', 'Z92VSFTV6H36J', '17', '1', null);
INSERT INTO item VALUES ('4343', '0', 'El Verde', '2.99', 'FIXED', '1', '2015-12-08', 'SGA8EYNKHP482', '17', '1', null);
INSERT INTO item VALUES ('4344', '0', 'Capitan', '2.99', 'FIXED', '1', '2015-12-08', 'CJS8JPFFRYP34', '17', '1', null);
INSERT INTO item VALUES ('4345', '0', 'Marinero', '2.99', 'FIXED', '1', '2015-12-08', '2WGGZE8DMM7Y2', '17', '1', null);
INSERT INTO item VALUES ('4346', '0', 'Pescador', '2.99', 'FIXED', '1', '2015-12-08', 'VZVNT0N0QPS44', '17', '1', null);
INSERT INTO item VALUES ('4347', '0', 'Grande Burrito', '5.99', 'FIXED', '1', '2015-12-08', '71GM72V4R1FB2', '17', '1', null);
INSERT INTO item VALUES ('4348', '0', 'Nachos', '5.99', 'FIXED', '1', '2015-12-08', '0J7CQZPDKY05Y', '17', '1', null);
INSERT INTO item VALUES ('4349', '0', 'Quesadilla', '6.99', 'FIXED', '1', '2015-12-08', 'N1PV9ET8M20YW', '17', '1', null);
INSERT INTO item VALUES ('4350', '0', 'Tortilla Soup', '2.99', 'FIXED', '1', '2015-12-08', 'BFGYKR9MATA3P', '17', '1', null);
INSERT INTO item VALUES ('4351', '0', '10 Pack Street Tacos', '18', 'FIXED', '1', '2015-12-08', '2JAK0YV90436T', '17', '1', null);
INSERT INTO item VALUES ('4352', '0', '6 Pack Street Tacos', '11', 'FIXED', '1', '2015-12-08', '6ZSDYQ6JM5ZVA', '17', '1', null);
INSERT INTO item VALUES ('4353', '0', '10 Pack Signature Tacos', '25.99', 'FIXED', '1', '2016-06-10', 'GZS8Y85GQESKP', '17', '1', null);
INSERT INTO item VALUES ('4354', '0', '6pack Signature Tacos', '15.99', 'FIXED', '1', '2015-12-08', 'RX572D062V0Q2', '17', '1', null);
INSERT INTO item VALUES ('4355', '0', 'Crispy Taco', '1.5', 'FIXED', '1', '2015-12-08', 'EAT4G9CVXPJBA', '17', '1', null);
INSERT INTO item VALUES ('4356', '0', 'Churros', '1.99', 'FIXED', '1', '2015-12-08', 'QPPTM1EQDKNKC', '17', '1', null);
INSERT INTO item VALUES ('4357', '0', 'Tres Leches Cake', '3.59', 'FIXED', '1', '2015-12-08', '3QG26KYHFZ6Y4', '17', '1', null);
INSERT INTO item VALUES ('4358', '0', 'Churros  1/2 Docena', '9.99', 'FIXED', '1', '2015-12-08', 'XFR4966SPMFQG', '17', '1', null);
INSERT INTO item VALUES ('4359', '0', 'Churros 12', '19.99', 'FIXED', '1', '2015-12-08', 'ZJBK63YQB2H88', '17', '1', null);
INSERT INTO item VALUES ('4360', '0', '32oz Fountain Drink', '1.99', 'FIXED', '1', '2015-12-08', 'TRVK3ZNKQ0ZN4', '17', '1', null);
INSERT INTO item VALUES ('4361', '0', 'Mexican Coke', '2.49', 'FIXED', '1', '2015-12-08', 'X38KBXYWNM2YR', '17', '1', null);
INSERT INTO item VALUES ('4362', '0', 'Mexican Flavored Water', '2.49', 'FIXED', '1', '2015-12-08', '3AY3BSQS72H8Y', '17', '1', null);
INSERT INTO item VALUES ('4363', '0', 'Jarritos', '1.99', 'FIXED', '1', '2015-12-08', 'PVVVDB43DC42E', '17', '1', null);
INSERT INTO item VALUES ('4364', '0', 'Coffee', '1.99', 'FIXED', '1', '2015-12-08', 'NSG4MRQDDN9XW', '17', '1', null);
INSERT INTO item VALUES ('4365', '0', 'Bottled Water', '2', 'FIXED', '1', '2015-12-08', 'V7PY74J5GXDRA', '17', '1', null);
INSERT INTO item VALUES ('4366', '0', 'Monster', '2', 'FIXED', '1', '2015-12-08', 'C9ZSP2CBTT3JR', '17', '1', null);
INSERT INTO item VALUES ('4367', '0', 'Powerade', '2', 'FIXED', '1', '2015-12-08', 'P1RB3YKHTFM7J', '17', '1', null);
INSERT INTO item VALUES ('4368', '0', 'Orange Juice', '1.99', 'FIXED', '1', '2015-12-08', 'JFP708JBSXQ7E', '17', '1', null);
INSERT INTO item VALUES ('4369', '0', 'Apple Juice', '1.99', 'FIXED', '1', '2015-12-08', 'MK72D6BYAC5EE', '17', '1', null);
INSERT INTO item VALUES ('4370', '0', 'Tropical Blend Juice', '1.99', 'FIXED', '1', '2015-12-08', '1KZS6BCAYM6G0', '17', '1', null);
INSERT INTO item VALUES ('4371', '0', 'Mexican Fanta', '2.49', 'FIXED', '1', '2015-12-08', 'EV9JVWZRK40NE', '17', '1', null);
INSERT INTO item VALUES ('4372', '0', 'Rice', '1.49', 'FIXED', '1', '2015-12-08', '27CC476VHWG5J', '17', '1', null);
INSERT INTO item VALUES ('4373', '0', 'Beans', '1.49', 'FIXED', '1', '2015-12-08', 'HM0QNHAQRH9Y4', '17', '1', null);
INSERT INTO item VALUES ('4374', '0', 'Guacamole', '1.99', 'FIXED', '1', '2015-12-08', 'KXD06G2KC8E4C', '17', '1', null);
INSERT INTO item VALUES ('4375', '0', 'Queso', '2.09', 'FIXED', '1', '2015-12-08', 'QXMXMH7CKMGTE', '17', '1', null);
INSERT INTO item VALUES ('4376', '0', 'Potatoes', '1.49', 'FIXED', '1', '2015-12-08', '0XAPAZ71RBXS6', '17', '1', null);
INSERT INTO item VALUES ('4377', '0', 'Sour Cream', '0.69', 'FIXED', '1', '2015-12-08', '1HV3JHKPMHT3T', '17', '1', null);
INSERT INTO item VALUES ('4378', '0', 'Side Of Salsa', '1', 'FIXED', '1', '2015-12-08', 'K88G8FNYVHG8T', '17', '1', null);
INSERT INTO item VALUES ('4379', '0', 'Sliced Avocado', '1', 'FIXED', '1', '2015-12-08', 'AG4DNQV84VFWC', '17', '1', null);
INSERT INTO item VALUES ('4380', '0', 'Charro Beans', '2.99', 'FIXED', '1', '2015-12-08', '4R5J6D0E1G0EY', '17', '1', null);
INSERT INTO item VALUES ('4381', '0', 'Menudo', '9.99', 'FIXED', '1', '2015-12-08', '8R8GSSQ788MPW', '17', '1', null);
INSERT INTO item VALUES ('4382', '0', '12 Tamales', '22', 'FIXED', '1', '2015-12-08', 'BV866XXWHXA4P', '17', '1', null);
INSERT INTO item VALUES ('4383', '0', '6 Tamales', '11', 'FIXED', '1', '2015-12-08', 'BRZ94QZAC1ZB2', '17', '1', null);
INSERT INTO item VALUES ('4384', '0', 'Atole', '1.99', 'FIXED', '1', '2015-12-08', 'M5SNE3AS1SAJA', '17', '1', null);
INSERT INTO item VALUES ('4385', '0', 'Chips & Salsa', '2.59', 'FIXED', '1', '2015-12-08', 'YGV7Q8EH4HG5Y', '17', '1', null);
INSERT INTO item VALUES ('4386', '0', 'Chips & Queso', '3.59', 'FIXED', '1', '2015-12-08', 'G8HK5SNZFJZKW', '17', '1', null);
INSERT INTO item VALUES ('4387', '0', 'Chips & Guacamole', '3.59', 'FIXED', '1', '2015-12-08', 'CYTPGABEBN8H2', '17', '1', null);
INSERT INTO item VALUES ('4388', '0', 'Trio', '4.99', 'FIXED', '1', '2015-12-08', 'K201WDT78RME4', '17', '1', null);
INSERT INTO item VALUES ('4389', '0', 'Test Item', '5', 'FIXED', '1', '2016-06-16', 'Z27HQ561T1GQE', '17', '1', null);
INSERT INTO item VALUES ('4390', '1', 'Online Order Test Item', '0.01', 'FIXED', '1', '2016-05-31', 'WPE6YCWQ7N94T', '17', '0', null);
INSERT INTO item VALUES ('4391', '1', 'Delivery Fee', '1', 'FIXED', '1', '2016-01-23', 'BRZAWYENXBH6W', '17', '1', '');
INSERT INTO item VALUES ('4392', '1', 'Delivery Fee', '0', 'FIXED', '1', '2016-01-23', '5CY2XV8NK909M', '17', '0', '');
INSERT INTO item VALUES ('4393', '1', 'Delivery Fee', '2', 'FIXED', '1', '2016-01-07', 'Q09VNJ5MH0TH8', '17', '0', '');
INSERT INTO item VALUES ('4394', '1', 'Delivery Fee', '1', 'FIXED', '1', '2016-01-07', '2HZ820XXSDFCT', '17', '1', '');
INSERT INTO item VALUES ('4395', '1', 'Convenience Fee', '2.1', 'FIXED', '1', '2015-12-08', 'MMARB2HAVYSM0', '17', '0', '');
INSERT INTO item VALUES ('4396', '1', 'Delivery Fee', '3', 'FIXED', '1', '2015-12-08', 'X7HCV5XMA882T', '17', '0', '');
INSERT INTO item VALUES ('4397', '0', 'Tamal', '1.99', 'FIXED', '1', '2015-12-08', 'EWG6HFJ9QP61T', '17', '1', null);
INSERT INTO item VALUES ('4398', '0', '16oz Fountain Drink', '1', 'FIXED', '1', '2015-12-08', 'HT5Z2GYCP6NBA', '17', '1', null);
INSERT INTO item VALUES ('4399', '0', 'Tostada', '2.29', 'FIXED', '1', '2015-12-08', '1E1BZWF845MB0', '17', '1', null);

-- ----------------------------
-- Table structure for `item_category`
-- ----------------------------
DROP TABLE IF EXISTS `item_category`;
CREATE TABLE `item_category` (
  `category_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FK_gpetmnw6hl68ks81y0lwofwl3` (`item_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `item_category_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `item_category_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item_category
-- ----------------------------
INSERT INTO item_category VALUES ('691', '4208', '66');
INSERT INTO item_category VALUES ('692', '4211', '67');
INSERT INTO item_category VALUES ('692', '4212', '68');
INSERT INTO item_category VALUES ('692', '4216', '69');
INSERT INTO item_category VALUES ('692', '4217', '70');
INSERT INTO item_category VALUES ('692', '4221', '71');
INSERT INTO item_category VALUES ('692', '4222', '72');
INSERT INTO item_category VALUES ('692', '4218', '73');
INSERT INTO item_category VALUES ('692', '4210', '74');
INSERT INTO item_category VALUES ('692', '4209', '75');
INSERT INTO item_category VALUES ('692', '4214', '76');
INSERT INTO item_category VALUES ('692', '4219', '77');
INSERT INTO item_category VALUES ('692', '4215', '78');
INSERT INTO item_category VALUES ('692', '4213', '79');
INSERT INTO item_category VALUES ('692', '4220', '80');
INSERT INTO item_category VALUES ('693', '4224', '81');
INSERT INTO item_category VALUES ('693', '4225', '82');
INSERT INTO item_category VALUES ('693', '4227', '83');
INSERT INTO item_category VALUES ('693', '4228', '84');
INSERT INTO item_category VALUES ('693', '4230', '85');
INSERT INTO item_category VALUES ('693', '4226', '86');
INSERT INTO item_category VALUES ('693', '4229', '87');
INSERT INTO item_category VALUES ('693', '4223', '88');
INSERT INTO item_category VALUES ('693', '4231', '89');
INSERT INTO item_category VALUES ('694', '4232', '90');
INSERT INTO item_category VALUES ('694', '4233', '91');
INSERT INTO item_category VALUES ('694', '4234', '92');
INSERT INTO item_category VALUES ('694', '4235', '93');
INSERT INTO item_category VALUES ('695', '4236', '94');
INSERT INTO item_category VALUES ('695', '4238', '95');
INSERT INTO item_category VALUES ('695', '4239', '96');
INSERT INTO item_category VALUES ('695', '4237', '97');
INSERT INTO item_category VALUES ('696', '4242', '98');
INSERT INTO item_category VALUES ('696', '4245', '99');
INSERT INTO item_category VALUES ('696', '4248', '100');
INSERT INTO item_category VALUES ('696', '4240', '101');
INSERT INTO item_category VALUES ('696', '4243', '102');
INSERT INTO item_category VALUES ('696', '4241', '103');
INSERT INTO item_category VALUES ('696', '4247', '104');
INSERT INTO item_category VALUES ('696', '4249', '105');
INSERT INTO item_category VALUES ('696', '4246', '106');
INSERT INTO item_category VALUES ('696', '4244', '107');
INSERT INTO item_category VALUES ('697', '4261', '108');
INSERT INTO item_category VALUES ('697', '4250', '109');
INSERT INTO item_category VALUES ('697', '4254', '110');
INSERT INTO item_category VALUES ('697', '4257', '111');
INSERT INTO item_category VALUES ('697', '4255', '112');
INSERT INTO item_category VALUES ('697', '4253', '113');
INSERT INTO item_category VALUES ('697', '4258', '114');
INSERT INTO item_category VALUES ('697', '4251', '115');
INSERT INTO item_category VALUES ('697', '4260', '116');
INSERT INTO item_category VALUES ('697', '4252', '117');
INSERT INTO item_category VALUES ('697', '4256', '118');
INSERT INTO item_category VALUES ('697', '4259', '119');
INSERT INTO item_category VALUES ('698', '4265', '120');
INSERT INTO item_category VALUES ('698', '4263', '121');
INSERT INTO item_category VALUES ('698', '4264', '122');
INSERT INTO item_category VALUES ('698', '4262', '123');
INSERT INTO item_category VALUES ('700', '4303', '124');
INSERT INTO item_category VALUES ('700', '4305', '125');
INSERT INTO item_category VALUES ('700', '4307', '126');
INSERT INTO item_category VALUES ('700', '4306', '127');
INSERT INTO item_category VALUES ('700', '4302', '128');
INSERT INTO item_category VALUES ('700', '4301', '129');
INSERT INTO item_category VALUES ('700', '4304', '130');
INSERT INTO item_category VALUES ('701', '4308', '131');
INSERT INTO item_category VALUES ('702', '4309', '132');
INSERT INTO item_category VALUES ('703', '4317', '133');
INSERT INTO item_category VALUES ('703', '4321', '134');
INSERT INTO item_category VALUES ('703', '4313', '135');
INSERT INTO item_category VALUES ('703', '4319', '136');
INSERT INTO item_category VALUES ('703', '4322', '137');
INSERT INTO item_category VALUES ('703', '4311', '138');
INSERT INTO item_category VALUES ('703', '4320', '139');
INSERT INTO item_category VALUES ('703', '4312', '140');
INSERT INTO item_category VALUES ('703', '4314', '141');
INSERT INTO item_category VALUES ('703', '4323', '142');
INSERT INTO item_category VALUES ('703', '4315', '143');
INSERT INTO item_category VALUES ('703', '4310', '144');
INSERT INTO item_category VALUES ('703', '4318', '145');
INSERT INTO item_category VALUES ('703', '4316', '146');
INSERT INTO item_category VALUES ('704', '4324', '147');
INSERT INTO item_category VALUES ('704', '4328', '148');
INSERT INTO item_category VALUES ('704', '4329', '149');
INSERT INTO item_category VALUES ('704', '4330', '150');
INSERT INTO item_category VALUES ('704', '4332', '151');
INSERT INTO item_category VALUES ('704', '4327', '152');
INSERT INTO item_category VALUES ('704', '4325', '153');
INSERT INTO item_category VALUES ('704', '4326', '154');
INSERT INTO item_category VALUES ('704', '4309', '155');
INSERT INTO item_category VALUES ('704', '4331', '156');
INSERT INTO item_category VALUES ('705', '4333', '157');
INSERT INTO item_category VALUES ('705', '4336', '158');
INSERT INTO item_category VALUES ('705', '4335', '159');
INSERT INTO item_category VALUES ('705', '4334', '160');
INSERT INTO item_category VALUES ('706', '4337', '161');
INSERT INTO item_category VALUES ('706', '4349', '162');
INSERT INTO item_category VALUES ('706', '4341', '163');
INSERT INTO item_category VALUES ('706', '4339', '164');
INSERT INTO item_category VALUES ('706', '4347', '165');
INSERT INTO item_category VALUES ('706', '4344', '166');
INSERT INTO item_category VALUES ('706', '4340', '167');
INSERT INTO item_category VALUES ('706', '4350', '168');
INSERT INTO item_category VALUES ('706', '4348', '169');
INSERT INTO item_category VALUES ('706', '4353', '170');
INSERT INTO item_category VALUES ('706', '4354', '171');
INSERT INTO item_category VALUES ('706', '4342', '172');
INSERT INTO item_category VALUES ('706', '4343', '173');
INSERT INTO item_category VALUES ('706', '4355', '174');
INSERT INTO item_category VALUES ('706', '4345', '175');
INSERT INTO item_category VALUES ('706', '4352', '176');
INSERT INTO item_category VALUES ('706', '4351', '177');
INSERT INTO item_category VALUES ('706', '4338', '178');
INSERT INTO item_category VALUES ('706', '4346', '179');
INSERT INTO item_category VALUES ('707', '4357', '180');
INSERT INTO item_category VALUES ('707', '4358', '181');
INSERT INTO item_category VALUES ('707', '4359', '182');
INSERT INTO item_category VALUES ('707', '4356', '183');
INSERT INTO item_category VALUES ('708', '4370', '184');
INSERT INTO item_category VALUES ('708', '4365', '185');
INSERT INTO item_category VALUES ('708', '4369', '186');
INSERT INTO item_category VALUES ('708', '4364', '187');
INSERT INTO item_category VALUES ('708', '4360', '188');
INSERT INTO item_category VALUES ('708', '4362', '189');
INSERT INTO item_category VALUES ('708', '4363', '190');
INSERT INTO item_category VALUES ('708', '4361', '191');
INSERT INTO item_category VALUES ('708', '4368', '192');
INSERT INTO item_category VALUES ('708', '4366', '193');
INSERT INTO item_category VALUES ('708', '4371', '194');
INSERT INTO item_category VALUES ('708', '4367', '195');
INSERT INTO item_category VALUES ('709', '4381', '196');
INSERT INTO item_category VALUES ('709', '4383', '197');
INSERT INTO item_category VALUES ('709', '4306', '198');
INSERT INTO item_category VALUES ('709', '4380', '199');
INSERT INTO item_category VALUES ('709', '4382', '200');
INSERT INTO item_category VALUES ('709', '4376', '201');
INSERT INTO item_category VALUES ('709', '4373', '202');
INSERT INTO item_category VALUES ('709', '4379', '203');
INSERT INTO item_category VALUES ('709', '4372', '204');
INSERT INTO item_category VALUES ('709', '4378', '205');
INSERT INTO item_category VALUES ('709', '4384', '206');
INSERT INTO item_category VALUES ('709', '4374', '207');
INSERT INTO item_category VALUES ('709', '4375', '208');
INSERT INTO item_category VALUES ('709', '4377', '209');
INSERT INTO item_category VALUES ('710', '4385', '210');
INSERT INTO item_category VALUES ('710', '4387', '211');
INSERT INTO item_category VALUES ('710', '4388', '212');
INSERT INTO item_category VALUES ('710', '4386', '213');

-- ----------------------------
-- Table structure for `item_modifiergroup_map`
-- ----------------------------
DROP TABLE IF EXISTS `item_modifiergroup_map`;
CREATE TABLE `item_modifiergroup_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `modifier_group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `modifier_group_id` (`modifier_group_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `item_modifiergroup_map_ibfk_2` FOREIGN KEY (`modifier_group_id`) REFERENCES `modifiergroup` (`id`),
  CONSTRAINT `item_modifiergroup_map_ibfk_3` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=491 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of item_modifiergroup_map
-- ----------------------------
INSERT INTO item_modifiergroup_map VALUES ('412', '4230', '3325');
INSERT INTO item_modifiergroup_map VALUES ('413', '4300', '3328');
INSERT INTO item_modifiergroup_map VALUES ('414', '4214', '3329');
INSERT INTO item_modifiergroup_map VALUES ('415', '4289', '3330');
INSERT INTO item_modifiergroup_map VALUES ('416', '4291', '3331');
INSERT INTO item_modifiergroup_map VALUES ('417', '4295', '3332');
INSERT INTO item_modifiergroup_map VALUES ('419', '4225', '3334');
INSERT INTO item_modifiergroup_map VALUES ('420', '4300', '3335');
INSERT INTO item_modifiergroup_map VALUES ('421', '4231', '3336');
INSERT INTO item_modifiergroup_map VALUES ('422', '4223', '3337');
INSERT INTO item_modifiergroup_map VALUES ('423', '4233', '3338');
INSERT INTO item_modifiergroup_map VALUES ('424', '4292', '3339');
INSERT INTO item_modifiergroup_map VALUES ('425', '4223', '3340');
INSERT INTO item_modifiergroup_map VALUES ('426', '4296', '3341');
INSERT INTO item_modifiergroup_map VALUES ('427', '4229', '3342');
INSERT INTO item_modifiergroup_map VALUES ('428', '4234', '3343');
INSERT INTO item_modifiergroup_map VALUES ('429', '4227', '3344');
INSERT INTO item_modifiergroup_map VALUES ('430', '4300', '3345');
INSERT INTO item_modifiergroup_map VALUES ('431', '4300', '3346');
INSERT INTO item_modifiergroup_map VALUES ('432', '4224', '3348');
INSERT INTO item_modifiergroup_map VALUES ('433', '4226', '3349');
INSERT INTO item_modifiergroup_map VALUES ('434', '4209', '3350');
INSERT INTO item_modifiergroup_map VALUES ('435', '4223', '3351');
INSERT INTO item_modifiergroup_map VALUES ('437', '4228', '3353');
INSERT INTO item_modifiergroup_map VALUES ('438', '4209', '3354');
INSERT INTO item_modifiergroup_map VALUES ('439', '4299', '3355');
INSERT INTO item_modifiergroup_map VALUES ('440', '4265', '3356');
INSERT INTO item_modifiergroup_map VALUES ('441', '4208', '3357');
INSERT INTO item_modifiergroup_map VALUES ('442', '4288', '3358');
INSERT INTO item_modifiergroup_map VALUES ('443', '4290', '3359');
INSERT INTO item_modifiergroup_map VALUES ('444', '4230', '3360');
INSERT INTO item_modifiergroup_map VALUES ('445', '4293', '3361');
INSERT INTO item_modifiergroup_map VALUES ('446', '4232', '3362');
INSERT INTO item_modifiergroup_map VALUES ('447', '4210', '3363');
INSERT INTO item_modifiergroup_map VALUES ('448', '4350', '3364');
INSERT INTO item_modifiergroup_map VALUES ('449', '4334', '3365');
INSERT INTO item_modifiergroup_map VALUES ('450', '4337', '3366');
INSERT INTO item_modifiergroup_map VALUES ('451', '4325', '3367');
INSERT INTO item_modifiergroup_map VALUES ('452', '4324', '3368');
INSERT INTO item_modifiergroup_map VALUES ('453', '4349', '3369');
INSERT INTO item_modifiergroup_map VALUES ('454', '4330', '3371');
INSERT INTO item_modifiergroup_map VALUES ('455', '4388', '3372');
INSERT INTO item_modifiergroup_map VALUES ('456', '4331', '3373');
INSERT INTO item_modifiergroup_map VALUES ('457', '4327', '3374');
INSERT INTO item_modifiergroup_map VALUES ('458', '4328', '3375');
INSERT INTO item_modifiergroup_map VALUES ('459', '4345', '3376');
INSERT INTO item_modifiergroup_map VALUES ('460', '4337', '3377');
INSERT INTO item_modifiergroup_map VALUES ('461', '4310', '3378');
INSERT INTO item_modifiergroup_map VALUES ('462', '4309', '3379');
INSERT INTO item_modifiergroup_map VALUES ('463', '4329', '3380');
INSERT INTO item_modifiergroup_map VALUES ('464', '4331', '3381');
INSERT INTO item_modifiergroup_map VALUES ('465', '4348', '3382');
INSERT INTO item_modifiergroup_map VALUES ('466', '4310', '3383');
INSERT INTO item_modifiergroup_map VALUES ('467', '4308', '3384');
INSERT INTO item_modifiergroup_map VALUES ('468', '4346', '3385');
INSERT INTO item_modifiergroup_map VALUES ('469', '4315', '3386');
INSERT INTO item_modifiergroup_map VALUES ('470', '4324', '3387');
INSERT INTO item_modifiergroup_map VALUES ('471', '4326', '3388');
INSERT INTO item_modifiergroup_map VALUES ('472', '4342', '3389');
INSERT INTO item_modifiergroup_map VALUES ('473', '4347', '3390');
INSERT INTO item_modifiergroup_map VALUES ('474', '4340', '3392');
INSERT INTO item_modifiergroup_map VALUES ('475', '4344', '3395');
INSERT INTO item_modifiergroup_map VALUES ('476', '4335', '3396');
INSERT INTO item_modifiergroup_map VALUES ('477', '4338', '3397');
INSERT INTO item_modifiergroup_map VALUES ('478', '4353', '3398');
INSERT INTO item_modifiergroup_map VALUES ('479', '4337', '3399');
INSERT INTO item_modifiergroup_map VALUES ('480', '4324', '3400');
INSERT INTO item_modifiergroup_map VALUES ('481', '4343', '3401');
INSERT INTO item_modifiergroup_map VALUES ('482', '4312', '3402');
INSERT INTO item_modifiergroup_map VALUES ('483', '4325', '3403');
INSERT INTO item_modifiergroup_map VALUES ('484', '4337', '3404');
INSERT INTO item_modifiergroup_map VALUES ('485', '4311', '3405');
INSERT INTO item_modifiergroup_map VALUES ('486', '4333', '3406');
INSERT INTO item_modifiergroup_map VALUES ('487', '4341', '3407');
INSERT INTO item_modifiergroup_map VALUES ('488', '4224', '3335');
INSERT INTO item_modifiergroup_map VALUES ('489', '4224', '3345');
INSERT INTO item_modifiergroup_map VALUES ('490', '4224', '3346');

-- ----------------------------
-- Table structure for `item_taxes`
-- ----------------------------
DROP TABLE IF EXISTS `item_taxes`;
CREATE TABLE `item_taxes` (
  `tax_rate_id` int(10) DEFAULT NULL,
  `item_id` int(10) DEFAULT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_tax_rate_id` (`tax_rate_id`),
  KEY `fk_tax_item_id` (`item_id`),
  CONSTRAINT `fk_tax_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `fk_tax_rate_id` FOREIGN KEY (`tax_rate_id`) REFERENCES `taxrate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=668 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item_taxes
-- ----------------------------
INSERT INTO item_taxes VALUES ('38', '4266', '481');
INSERT INTO item_taxes VALUES ('38', '4267', '482');
INSERT INTO item_taxes VALUES ('38', '4268', '483');
INSERT INTO item_taxes VALUES ('38', '4269', '484');
INSERT INTO item_taxes VALUES ('38', '4270', '485');
INSERT INTO item_taxes VALUES ('38', '4271', '486');
INSERT INTO item_taxes VALUES ('38', '4261', '487');
INSERT INTO item_taxes VALUES ('38', '4274', '488');
INSERT INTO item_taxes VALUES ('38', '4260', '489');
INSERT INTO item_taxes VALUES ('38', '4239', '490');
INSERT INTO item_taxes VALUES ('38', '4238', '491');
INSERT INTO item_taxes VALUES ('38', '4249', '492');
INSERT INTO item_taxes VALUES ('38', '4248', '493');
INSERT INTO item_taxes VALUES ('38', '4247', '494');
INSERT INTO item_taxes VALUES ('38', '4230', '495');
INSERT INTO item_taxes VALUES ('38', '4235', '496');
INSERT INTO item_taxes VALUES ('38', '4246', '497');
INSERT INTO item_taxes VALUES ('38', '4245', '498');
INSERT INTO item_taxes VALUES ('38', '4244', '499');
INSERT INTO item_taxes VALUES ('38', '4243', '500');
INSERT INTO item_taxes VALUES ('38', '4259', '501');
INSERT INTO item_taxes VALUES ('38', '4275', '502');
INSERT INTO item_taxes VALUES ('38', '4276', '503');
INSERT INTO item_taxes VALUES ('38', '4278', '505');
INSERT INTO item_taxes VALUES ('38', '4222', '506');
INSERT INTO item_taxes VALUES ('38', '4279', '507');
INSERT INTO item_taxes VALUES ('38', '4258', '508');
INSERT INTO item_taxes VALUES ('38', '4280', '509');
INSERT INTO item_taxes VALUES ('38', '4250', '510');
INSERT INTO item_taxes VALUES ('38', '4281', '511');
INSERT INTO item_taxes VALUES ('38', '4282', '512');
INSERT INTO item_taxes VALUES ('38', '4283', '513');
INSERT INTO item_taxes VALUES ('38', '4284', '514');
INSERT INTO item_taxes VALUES ('38', '4285', '515');
INSERT INTO item_taxes VALUES ('38', '4257', '516');
INSERT INTO item_taxes VALUES ('38', '4208', '517');
INSERT INTO item_taxes VALUES ('38', '4221', '518');
INSERT INTO item_taxes VALUES ('38', '4218', '519');
INSERT INTO item_taxes VALUES ('38', '4216', '520');
INSERT INTO item_taxes VALUES ('38', '4217', '521');
INSERT INTO item_taxes VALUES ('38', '4219', '522');
INSERT INTO item_taxes VALUES ('38', '4215', '523');
INSERT INTO item_taxes VALUES ('38', '4220', '524');
INSERT INTO item_taxes VALUES ('38', '4213', '525');
INSERT INTO item_taxes VALUES ('38', '4212', '526');
INSERT INTO item_taxes VALUES ('38', '4214', '527');
INSERT INTO item_taxes VALUES ('38', '4286', '528');
INSERT INTO item_taxes VALUES ('38', '4264', '529');
INSERT INTO item_taxes VALUES ('38', '4263', '530');
INSERT INTO item_taxes VALUES ('38', '4262', '531');
INSERT INTO item_taxes VALUES ('38', '4256', '532');
INSERT INTO item_taxes VALUES ('38', '4255', '533');
INSERT INTO item_taxes VALUES ('38', '4254', '534');
INSERT INTO item_taxes VALUES ('38', '4253', '535');
INSERT INTO item_taxes VALUES ('38', '4252', '536');
INSERT INTO item_taxes VALUES ('38', '4251', '537');
INSERT INTO item_taxes VALUES ('38', '4242', '538');
INSERT INTO item_taxes VALUES ('38', '4241', '539');
INSERT INTO item_taxes VALUES ('38', '4240', '540');
INSERT INTO item_taxes VALUES ('38', '4237', '541');
INSERT INTO item_taxes VALUES ('38', '4236', '542');
INSERT INTO item_taxes VALUES ('38', '4231', '543');
INSERT INTO item_taxes VALUES ('38', '4210', '544');
INSERT INTO item_taxes VALUES ('38', '4209', '545');
INSERT INTO item_taxes VALUES ('38', '4229', '546');
INSERT INTO item_taxes VALUES ('38', '4228', '547');
INSERT INTO item_taxes VALUES ('38', '4227', '548');
INSERT INTO item_taxes VALUES ('38', '4226', '549');
INSERT INTO item_taxes VALUES ('38', '4225', '550');
INSERT INTO item_taxes VALUES ('38', '4224', '551');
INSERT INTO item_taxes VALUES ('38', '4223', '552');
INSERT INTO item_taxes VALUES ('38', '4234', '553');
INSERT INTO item_taxes VALUES ('38', '4233', '554');
INSERT INTO item_taxes VALUES ('38', '4232', '555');
INSERT INTO item_taxes VALUES ('38', '4265', '556');
INSERT INTO item_taxes VALUES ('38', '4211', '557');
INSERT INTO item_taxes VALUES ('38', '4287', '558');
INSERT INTO item_taxes VALUES ('38', '4288', '559');
INSERT INTO item_taxes VALUES ('38', '4289', '560');
INSERT INTO item_taxes VALUES ('38', '4290', '561');
INSERT INTO item_taxes VALUES ('38', '4291', '562');
INSERT INTO item_taxes VALUES ('38', '4292', '563');
INSERT INTO item_taxes VALUES ('38', '4293', '564');
INSERT INTO item_taxes VALUES ('38', '4294', '565');
INSERT INTO item_taxes VALUES ('38', '4295', '566');
INSERT INTO item_taxes VALUES ('38', '4296', '567');
INSERT INTO item_taxes VALUES ('38', '4297', '568');
INSERT INTO item_taxes VALUES ('38', '4298', '569');
INSERT INTO item_taxes VALUES ('38', '4299', '570');
INSERT INTO item_taxes VALUES ('38', '4300', '571');
INSERT INTO item_taxes VALUES ('42', '4389', '572');
INSERT INTO item_taxes VALUES ('42', '4391', '573');
INSERT INTO item_taxes VALUES ('42', '4394', '574');
INSERT INTO item_taxes VALUES ('42', '4384', '575');
INSERT INTO item_taxes VALUES ('42', '4382', '576');
INSERT INTO item_taxes VALUES ('42', '4383', '577');
INSERT INTO item_taxes VALUES ('42', '4397', '578');
INSERT INTO item_taxes VALUES ('42', '4381', '579');
INSERT INTO item_taxes VALUES ('42', '4398', '580');
INSERT INTO item_taxes VALUES ('42', '4332', '581');
INSERT INTO item_taxes VALUES ('42', '4359', '582');
INSERT INTO item_taxes VALUES ('42', '4358', '583');
INSERT INTO item_taxes VALUES ('42', '4371', '584');
INSERT INTO item_taxes VALUES ('42', '4370', '585');
INSERT INTO item_taxes VALUES ('42', '4369', '586');
INSERT INTO item_taxes VALUES ('42', '4368', '587');
INSERT INTO item_taxes VALUES ('43', '4331', '588');
INSERT INTO item_taxes VALUES ('42', '4331', '589');
INSERT INTO item_taxes VALUES ('41', '4331', '590');
INSERT INTO item_taxes VALUES ('42', '4336', '591');
INSERT INTO item_taxes VALUES ('42', '4367', '592');
INSERT INTO item_taxes VALUES ('42', '4366', '593');
INSERT INTO item_taxes VALUES ('42', '4365', '594');
INSERT INTO item_taxes VALUES ('42', '4364', '595');
INSERT INTO item_taxes VALUES ('42', '4380', '596');
INSERT INTO item_taxes VALUES ('42', '4355', '597');
INSERT INTO item_taxes VALUES ('42', '4354', '598');
INSERT INTO item_taxes VALUES ('42', '4353', '599');
INSERT INTO item_taxes VALUES ('42', '4352', '600');
INSERT INTO item_taxes VALUES ('42', '4323', '601');
INSERT INTO item_taxes VALUES ('42', '4351', '602');
INSERT INTO item_taxes VALUES ('42', '4379', '603');
INSERT INTO item_taxes VALUES ('42', '4307', '604');
INSERT INTO item_taxes VALUES ('42', '4306', '605');
INSERT INTO item_taxes VALUES ('42', '4305', '606');
INSERT INTO item_taxes VALUES ('42', '4304', '607');
INSERT INTO item_taxes VALUES ('42', '4303', '608');
INSERT INTO item_taxes VALUES ('42', '4302', '609');
INSERT INTO item_taxes VALUES ('42', '4301', '610');
INSERT INTO item_taxes VALUES ('42', '4378', '611');
INSERT INTO item_taxes VALUES ('42', '4308', '612');
INSERT INTO item_taxes VALUES ('42', '4322', '613');
INSERT INTO item_taxes VALUES ('42', '4319', '614');
INSERT INTO item_taxes VALUES ('42', '4317', '615');
INSERT INTO item_taxes VALUES ('42', '4318', '616');
INSERT INTO item_taxes VALUES ('42', '4320', '617');
INSERT INTO item_taxes VALUES ('42', '4316', '618');
INSERT INTO item_taxes VALUES ('42', '4321', '619');
INSERT INTO item_taxes VALUES ('42', '4314', '620');
INSERT INTO item_taxes VALUES ('42', '4313', '621');
INSERT INTO item_taxes VALUES ('42', '4315', '622');
INSERT INTO item_taxes VALUES ('42', '4399', '623');
INSERT INTO item_taxes VALUES ('42', '4387', '624');
INSERT INTO item_taxes VALUES ('42', '4386', '625');
INSERT INTO item_taxes VALUES ('42', '4385', '626');
INSERT INTO item_taxes VALUES ('42', '4377', '627');
INSERT INTO item_taxes VALUES ('42', '4376', '628');
INSERT INTO item_taxes VALUES ('42', '4375', '629');
INSERT INTO item_taxes VALUES ('42', '4374', '630');
INSERT INTO item_taxes VALUES ('42', '4373', '631');
INSERT INTO item_taxes VALUES ('42', '4372', '632');
INSERT INTO item_taxes VALUES ('42', '4363', '633');
INSERT INTO item_taxes VALUES ('42', '4362', '634');
INSERT INTO item_taxes VALUES ('42', '4361', '635');
INSERT INTO item_taxes VALUES ('42', '4360', '636');
INSERT INTO item_taxes VALUES ('42', '4357', '637');
INSERT INTO item_taxes VALUES ('42', '4356', '638');
INSERT INTO item_taxes VALUES ('42', '4309', '639');
INSERT INTO item_taxes VALUES ('42', '4311', '640');
INSERT INTO item_taxes VALUES ('42', '4310', '641');
INSERT INTO item_taxes VALUES ('42', '4330', '642');
INSERT INTO item_taxes VALUES ('42', '4329', '643');
INSERT INTO item_taxes VALUES ('42', '4328', '644');
INSERT INTO item_taxes VALUES ('42', '4327', '645');
INSERT INTO item_taxes VALUES ('42', '4326', '646');
INSERT INTO item_taxes VALUES ('42', '4325', '647');
INSERT INTO item_taxes VALUES ('42', '4324', '648');
INSERT INTO item_taxes VALUES ('42', '4335', '649');
INSERT INTO item_taxes VALUES ('42', '4334', '650');
INSERT INTO item_taxes VALUES ('42', '4333', '651');
INSERT INTO item_taxes VALUES ('42', '4388', '652');
INSERT INTO item_taxes VALUES ('42', '4312', '653');
INSERT INTO item_taxes VALUES ('42', '4350', '654');
INSERT INTO item_taxes VALUES ('42', '4349', '655');
INSERT INTO item_taxes VALUES ('42', '4348', '656');
INSERT INTO item_taxes VALUES ('42', '4347', '657');
INSERT INTO item_taxes VALUES ('42', '4346', '658');
INSERT INTO item_taxes VALUES ('42', '4345', '659');
INSERT INTO item_taxes VALUES ('42', '4344', '660');
INSERT INTO item_taxes VALUES ('42', '4343', '661');
INSERT INTO item_taxes VALUES ('42', '4342', '662');
INSERT INTO item_taxes VALUES ('42', '4341', '663');
INSERT INTO item_taxes VALUES ('42', '4340', '664');
INSERT INTO item_taxes VALUES ('42', '4339', '665');
INSERT INTO item_taxes VALUES ('42', '4338', '666');
INSERT INTO item_taxes VALUES ('42', '4337', '667');

-- ----------------------------
-- Table structure for `location`
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address1` varchar(256) DEFAULT NULL,
  `address2` varchar(256) DEFAULT NULL,
  `address3` varchar(256) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `zip` int(11) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_address_1_idx` (`customer_id`),
  KEY `fk_merchant_id` (`merchant_id`),
  CONSTRAINT `fk_merchant_id` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `location_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of location
-- ----------------------------
INSERT INTO location VALUES ('18', '600 marathon-test', 'drive', 'tts', 'campbell', '95008', 'california', 'US', '16', null);
INSERT INTO location VALUES ('19', '600 marathon drive', '', '', 'campbell', '95008', 'california', 'US', '17', null);

-- ----------------------------
-- Table structure for `merchant`
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `pos_merchant_id` varchar(45) DEFAULT NULL,
  `accessToken` varchar(4000) DEFAULT NULL,
  `vendor_id` int(20) NOT NULL,
  `merchant_logo` longtext,
  `storeId` varchar(2000) DEFAULT NULL,
  `subscription_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ild2oak9iqy274425koik48w2` (`vendor_id`),
  KEY `fk_subscription_id` (`subscription_id`),
  CONSTRAINT `FK_ild2oak9iqy274425koik48w2` FOREIGN KEY (`vendor_id`) REFERENCES `vendor` (`id`),
  CONSTRAINT `fk_subscription_id` FOREIGN KEY (`subscription_id`) REFERENCES `subscriptions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 10240 kB; (`location_ID`) REFER `foodkonnekt/st';

-- ----------------------------
-- Records of merchant
-- ----------------------------
INSERT INTO merchant VALUES ('16', '1348397589465784637', '12 04', '17AEJMS46D3ZM', '75e3cfe6-b6bf-da27-2a3f-2fab1647b805', '4', 'iVBORw0KGgoAAAANSUhEUgAAANYAAAA-CAYAAABX9OXBAAAACXBIWXMAAAsTAAALEwEAmpwYAAAKT2lDQ1BQaG90b3Nob3AgSUNDIHByb2ZpbGUAAHjanVNnVFPpFj333vRCS4iAlEtvUhUIIFJCi4AUkSYqIQkQSoghodkVUcERRUUEG8igiAOOjoCMFVEsDIoK2AfkIaKOg6OIisr74Xuja9a89-bN_rXXPues852zzwfACAyWSDNRNYAMqUIeEeCDx8TG4eQuQIEKJHAAEAizZCFz_SMBAPh-PDwrIsAHvgABeNMLCADATZvAMByH_w_qQplcAYCEAcB0kThLCIAUAEB6jkKmAEBGAYCdmCZTAKAEAGDLY2LjAFAtAGAnf-bTAICd-Jl7AQBblCEVAaCRACATZYhEAGg7AKzPVopFAFgwABRmS8Q5ANgtADBJV2ZIALC3AMDOEAuyAAgMADBRiIUpAAR7AGDIIyN4AISZABRG8lc88SuuEOcqAAB4mbI8uSQ5RYFbCC1xB1dXLh4ozkkXKxQ2YQJhmkAuwnmZGTKBNA_g88wAAKCRFRHgg_P9eM4Ors7ONo62Dl8t6r8G_yJiYuP-5c-rcEAAAOF0ftH-LC-zGoA7BoBt_qIl7gRoXgugdfeLZrIPQLUAoOnaV_Nw-H48PEWhkLnZ2eXk5NhKxEJbYcpXff5nwl_AV_1s-X48_Pf14L7iJIEyXYFHBPjgwsz0TKUcz5IJhGLc5o9H_LcL__wd0yLESWK5WCoU41EScY5EmozzMqUiiUKSKcUl0v9k4t8s-wM-3zUAsGo-AXuRLahdYwP2SycQWHTA4vcAAPK7b8HUKAgDgGiD4c93_-8__UegJQCAZkmScQAAXkQkLlTKsz_HCAAARKCBKrBBG_TBGCzABhzBBdzBC_xgNoRCJMTCQhBCCmSAHHJgKayCQiiGzbAdKmAv1EAdNMBRaIaTcA4uwlW4Dj1wD_phCJ7BKLyBCQRByAgTYSHaiAFiilgjjggXmYX4IcFIBBKLJCDJiBRRIkuRNUgxUopUIFVIHfI9cgI5h1xGupE7yAAygvyGvEcxlIGyUT3UDLVDuag3GoRGogvQZHQxmo8WoJvQcrQaPYw2oefQq2gP2o8-Q8cwwOgYBzPEbDAuxsNCsTgsCZNjy7EirAyrxhqwVqwDu4n1Y8-xdwQSgUXACTYEd0IgYR5BSFhMWE7YSKggHCQ0EdoJNwkDhFHCJyKTqEu0JroR-cQYYjIxh1hILCPWEo8TLxB7iEPENyQSiUMyJ7mQAkmxpFTSEtJG0m5SI-ksqZs0SBojk8naZGuyBzmULCAryIXkneTD5DPkG-Qh8lsKnWJAcaT4U-IoUspqShnlEOU05QZlmDJBVaOaUt2ooVQRNY9aQq2htlKvUYeoEzR1mjnNgxZJS6WtopXTGmgXaPdpr-h0uhHdlR5Ol9BX0svpR-iX6AP0dwwNhhWDx4hnKBmbGAcYZxl3GK-YTKYZ04sZx1QwNzHrmOeZD5lvVVgqtip8FZHKCpVKlSaVGyovVKmqpqreqgtV81XLVI-pXlN9rkZVM1PjqQnUlqtVqp1Q61MbU2epO6iHqmeob1Q_pH5Z_YkGWcNMw09DpFGgsV_jvMYgC2MZs3gsIWsNq4Z1gTXEJrHN2Xx2KruY_R27iz2qqaE5QzNKM1ezUvOUZj8H45hx-Jx0TgnnKKeX836K3hTvKeIpG6Y0TLkxZVxrqpaXllirSKtRq0frvTau7aedpr1Fu1n7gQ5Bx0onXCdHZ4_OBZ3nU9lT3acKpxZNPTr1ri6qa6UbobtEd79up-6Ynr5egJ5Mb6feeb3n-hx9L_1U_W36p_VHDFgGswwkBtsMzhg8xTVxbzwdL8fb8VFDXcNAQ6VhlWGX4YSRudE8o9VGjUYPjGnGXOMk423GbcajJgYmISZLTepN7ppSTbmmKaY7TDtMx83MzaLN1pk1mz0x1zLnm-eb15vft2BaeFostqi2uGVJsuRaplnutrxuhVo5WaVYVVpds0atna0l1rutu6cRp7lOk06rntZnw7Dxtsm2qbcZsOXYBtuutm22fWFnYhdnt8Wuw-6TvZN9un2N_T0HDYfZDqsdWh1-c7RyFDpWOt6azpzuP33F9JbpL2dYzxDP2DPjthPLKcRpnVOb00dnF2e5c4PziIuJS4LLLpc-Lpsbxt3IveRKdPVxXeF60vWdm7Obwu2o26_uNu5p7ofcn8w0nymeWTNz0MPIQ-BR5dE_C5-VMGvfrH5PQ0-BZ7XnIy9jL5FXrdewt6V3qvdh7xc-9j5yn-M-4zw33jLeWV_MN8C3yLfLT8Nvnl-F30N_I_9k_3r_0QCngCUBZwOJgUGBWwL7-Hp8Ib-OPzrbZfay2e1BjKC5QRVBj4KtguXBrSFoyOyQrSH355jOkc5pDoVQfujW0Adh5mGLw34MJ4WHhVeGP45wiFga0TGXNXfR3ENz30T6RJZE3ptnMU85ry1KNSo-qi5qPNo3ujS6P8YuZlnM1VidWElsSxw5LiquNm5svt_87fOH4p3iC-N7F5gvyF1weaHOwvSFpxapLhIsOpZATIhOOJTwQRAqqBaMJfITdyWOCnnCHcJnIi_RNtGI2ENcKh5O8kgqTXqS7JG8NXkkxTOlLOW5hCepkLxMDUzdmzqeFpp2IG0yPTq9MYOSkZBxQqohTZO2Z-pn5mZ2y6xlhbL-xW6Lty8elQfJa7OQrAVZLQq2QqboVFoo1yoHsmdlV2a_zYnKOZarnivN7cyzytuQN5zvn__tEsIS4ZK2pYZLVy0dWOa9rGo5sjxxedsK4xUFK4ZWBqw8uIq2Km3VT6vtV5eufr0mek1rgV7ByoLBtQFr6wtVCuWFfevc1-1dT1gvWd-1YfqGnRs-FYmKrhTbF5cVf9go3HjlG4dvyr-Z3JS0qavEuWTPZtJm6ebeLZ5bDpaql-aXDm4N2dq0Dd9WtO319kXbL5fNKNu7g7ZDuaO_PLi8ZafJzs07P1SkVPRU-lQ27tLdtWHX-G7R7ht7vPY07NXbW7z3_T7JvttVAVVN1WbVZftJ-7P3P66Jqun4lvttXa1ObXHtxwPSA_0HIw6217nU1R3SPVRSj9Yr60cOxx--_p3vdy0NNg1VjZzG4iNwRHnk6fcJ3_ceDTradox7rOEH0x92HWcdL2pCmvKaRptTmvtbYlu6T8w-0dbq3nr8R9sfD5w0PFl5SvNUyWna6YLTk2fyz4ydlZ19fi753GDborZ752PO32oPb--6EHTh0kX_i-c7vDvOXPK4dPKy2-UTV7hXmq86X23qdOo8_pPTT8e7nLuarrlca7nuer21e2b36RueN87d9L158Rb_1tWeOT3dvfN6b_fF9_XfFt1-cif9zsu72Xcn7q28T7xf9EDtQdlD3YfVP1v-3Njv3H9qwHeg89HcR_cGhYPP_pH1jw9DBY-Zj8uGDYbrnjg-OTniP3L96fynQ89kzyaeF_6i_suuFxYvfvjV69fO0ZjRoZfyl5O_bXyl_erA6xmv28bCxh6-yXgzMV70VvvtwXfcdx3vo98PT-R8IH8o_2j5sfVT0Kf7kxmTk_8EA5jz_GMzLdsAAEp7aVRYdFhNTDpjb20uYWRvYmUueG1wAAAAAAA8P3hwYWNrZXQgYmVnaW49Iu-7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI_Pgo8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjYtYzAxNCA3OS4xNTY3OTcsIDIwMTQvMDgvMjAtMDk6NTM6MDIgICAgICAgICI-CiAgIDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI-CiAgICAgIDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiCiAgICAgICAgICAgIHhtbG5zOnhtcD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLyIKICAgICAgICAgICAgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iCiAgICAgICAgICAgIHhtbG5zOnN0RXZ0PSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VFdmVudCMiCiAgICAgICAgICAgIHhtbG5zOnBob3Rvc2hvcD0iaHR0cDovL25zLmFkb2JlLmNvbS9waG90b3Nob3AvMS4wLyIKICAgICAgICAgICAgeG1sbnM6ZGM9Imh0dHA6Ly9wdXJsLm9yZy9kYy9lbGVtZW50cy8xLjEvIgogICAgICAgICAgICB4bWxuczp0aWZmPSJodHRwOi8vbnMuYWRvYmUuY29tL3RpZmYvMS4wLyIKICAgICAgICAgICAgeG1sbnM6ZXhpZj0iaHR0cDovL25zLmFkb2JlLmNvbS9leGlmLzEuMC8iPgogICAgICAgICA8eG1wOkNyZWF0b3JUb29sPkFkb2JlIFBob3Rvc2hvcCBDQyAyMDE0IChXaW5kb3dzKTwveG1wOkNyZWF0b3JUb29sPgogICAgICAgICA8eG1wOkNyZWF0ZURhdGU-MjAxNS0wNi0xN1QxNDoxNDoxMCswNTowMDwveG1wOkNyZWF0ZURhdGU-CiAgICAgICAgIDx4bXA6TWV0YWRhdGFEYXRlPjIwMTUtMDYtMTdUMTQ6MTQ6MTArMDU6MDA8L3htcDpNZXRhZGF0YURhdGU-CiAgICAgICAgIDx4bXA6TW9kaWZ5RGF0ZT4yMDE1LTA2LTE3VDE0OjE0OjEwKzA1OjAwPC94bXA6TW9kaWZ5RGF0ZT4KICAgICAgICAgPHhtcE1NOkluc3RhbmNlSUQ-eG1wLmlpZDpkYmE0MTNkNy1kMmRlLTIzNGItOTUwNC0zNmFiMmE0ZDc1OWQ8L3htcE1NOkluc3RhbmNlSUQ-CiAgICAgICAgIDx4bXBNTTpEb2N1bWVudElEPmFkb2JlOmRvY2lkOnBob3Rvc2hvcDozMzZhYWY3NC0xNGQxLTExZTUtYThmYi1lNzkxNzRmZjdjMzQ8L3htcE1NOkRvY3VtZW50SUQ-CiAgICAgICAgIDx4bXBNTTpPcmlnaW5hbERvY3VtZW50SUQ-eG1wLmRpZDo2ZWMwZTNmYS03MmUxLTI0NGItOTk4Zi1kYzNlNDMzNzNlN2I8L3htcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD4KICAgICAgICAgPHhtcE1NOkhpc3Rvcnk-CiAgICAgICAgICAgIDxyZGY6U2VxPgogICAgICAgICAgICAgICA8cmRmOmxpIHJkZjpwYXJzZVR5cGU9IlJlc291cmNlIj4KICAgICAgICAgICAgICAgICAgPHN0RXZ0OmFjdGlvbj5jcmVhdGVkPC9zdEV2dDphY3Rpb24-CiAgICAgICAgICAgICAgICAgIDxzdEV2dDppbnN0YW5jZUlEPnhtcC5paWQ6NmVjMGUzZmEtNzJlMS0yNDRiLTk5OGYtZGMzZTQzMzczZTdiPC9zdEV2dDppbnN0YW5jZUlEPgogICAgICAgICAgICAgICAgICA8c3RFdnQ6d2hlbj4yMDE1LTA2LTE3VDE0OjE0OjEwKzA1OjAwPC9zdEV2dDp3aGVuPgogICAgICAgICAgICAgICAgICA8c3RFdnQ6c29mdHdhcmVBZ2VudD5BZG9iZSBQaG90b3Nob3AgQ0MgMjAxNCAoV2luZG93cyk8L3N0RXZ0OnNvZnR3YXJlQWdlbnQ-CiAgICAgICAgICAgICAgIDwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpIHJkZjpwYXJzZVR5cGU9IlJlc291cmNlIj4KICAgICAgICAgICAgICAgICAgPHN0RXZ0OmFjdGlvbj5zYXZlZDwvc3RFdnQ6YWN0aW9uPgogICAgICAgICAgICAgICAgICA8c3RFdnQ6aW5zdGFuY2VJRD54bXAuaWlkOmRiYTQxM2Q3LWQyZGUtMjM0Yi05NTA0LTM2YWIyYTRkNzU5ZDwvc3RFdnQ6aW5zdGFuY2VJRD4KICAgICAgICAgICAgICAgICAgPHN0RXZ0OndoZW4-MjAxNS0wNi0xN1QxNDoxNDoxMCswNTowMDwvc3RFdnQ6d2hlbj4KICAgICAgICAgICAgICAgICAgPHN0RXZ0OnNvZnR3YXJlQWdlbnQ-QWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpPC9zdEV2dDpzb2Z0d2FyZUFnZW50PgogICAgICAgICAgICAgICAgICA8c3RFdnQ6Y2hhbmdlZD4vPC9zdEV2dDpjaGFuZ2VkPgogICAgICAgICAgICAgICA8L3JkZjpsaT4KICAgICAgICAgICAgPC9yZGY6U2VxPgogICAgICAgICA8L3htcE1NOkhpc3Rvcnk-CiAgICAgICAgIDxwaG90b3Nob3A6RG9jdW1lbnRBbmNlc3RvcnM-CiAgICAgICAgICAgIDxyZGY6QmFnPgogICAgICAgICAgICAgICA8cmRmOmxpPjAxMEQ1QkQ5NkRGMDU1NTMyNkU4RDc4NjhBNDg5OTMwPC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-MDJFMEY4RjRCMEM4QkE2RUQyMTBEREZFNEE2OTgzNDQ8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT4wQjU1MkNGRTk3MDNEOTEzQTlBN0E3OUNGMzdCNUIxQjwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPjBGODk1MDZFOEM1MTA1NEQzNEE2NzJDQjFBMDdFRTE3PC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-MTE3RDU0REJDMDgxM0JERjE3QTE2OTAwREQ3MEM0RjQ8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT4xMjczNkNGMDg4QUZGNDE1NkVBODdCRUQ5QjMyQjY1QzwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPjJBNEE0RkRDNjk1NzI1QUEwRjVCQzMxMUYzMjZERDYzPC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-MzMxMTkwMEVBMTg1MjBCMjc2Q0I5OEYzMzg1RDU4NkI8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT4zNUYxMjcwMDU0QUM4QjhFNjZENEMxMzVFQTI1RkVEMjwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPjM3OUYxNEQ3RDRBRUVDOUZCOTYyODBEQjdDNUJEMDU5PC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-M0U3QjY2NDVDREJBNUU5NTUxMDlDOUZDMjRFQjQ2Mjk8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT4zRUEzOTgxRTIyMjA1NTU3RkZGRTRCRDVEODI2OTY1RDwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPjNFQzhDNDA0NUNGMzAxODA2NThDRjNBMUEzRjlFMjc4PC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-NENFNUMyRTRDMTA5QUMxMjg4QzA1NTdBRDMyRkJDMjM8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT40RDUxM0E3REY1NjMzOEZERjI2MkVGOEM5MzlFRDgyMjwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPjUwMjA0QjdCMThGRjU5QzA0NDg5MTE3N0Y5NjUyQUVCPC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-NTlDMjkyNERDREM5RjVCNjRGOENGNDBFMkJGQUQyODE8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT41QTk0NTAwNjBEQzY4QkE2OEE5Mzc5RDA4NzYyNEJENjwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPjVEMDg4RjBCRUI2MDNDMEYwRUY0MTE1Q0FBQTQ5N0NGPC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-NjJCMzk3N0MxQTI0MzdDOEVGQUVDMjBDQ0REQkFERDU8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT42MkRCNEQ0RkEwMDVFRDVBRjUxRDgwRUE2MDlCQjJBNzwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPjY2ODAwMTFGNjM5OTY3QjIyNEM3MDQ2OEJGM0RFMUI1PC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-NzM4MkM3M0FBMUYwREE5RkMxM0NFRkY2OENGQjY0NzI8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT43NDJDRTMzRUI3NjA2RjlDN0I5MjVGMkU3QzE0ODMzMTwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPjc1RjlFMEFCQzNBQjkxOEIwMzVDNzY2QTE0RDdFMURDPC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-ODBGODM4RTZCRjU4OEUwRTI5QkQ2MzRFMUNBNEY5Nzg8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT44QjlEMzIxREU5RDM5MjE5MTBBNDc0QjUzQUU1RjYwRTwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPjhENkNBNzVCRTUxNDA2RTM4NjA1NTM5MTc3Qjk0QjA3PC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-OTBEMzU1QURFMkEzMjRDRDU4N0I1RjMzNzBFMEQyNTM8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT5BNjdGRkEzQzg4NEU1NTM0NERDQTY4NDJCNzJCRDIxMjwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPkE3OEFCNjc1QTY2NjM4QjlBMURGNUIxQ0ExNjM0ODVDPC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-QThGMjE5RDNGQjJGMkM2NTAyRTQxMjYyODdBQ0E0NjQ8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT5BOUQ1OEU4MzRDQUYwNjQ2QUFGNDYxMUQ5MzQyNjkyODwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPkFGNTdBNzQ0QTFCMDM1MkNFRTk2OTFBQTA1RkE3QTdBPC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-QjNDN0NEQTBCQUQ0QjA5QzEzMjY4RkUzMDFENTNFMjY8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT5COTM0RTYwMUY1QjYzNUI3MzQ5ODA2NzFCMDFERDQ0QTwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPkJEMTEwMzg1QTg4ODE4QTRDRTJEMTQ3ODk1OTVCMDFEPC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-QkQ0NDJCMTlENTZEQjRCMUMxNUQ5NTI0ODNERDBFQzU8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT5DODg5QTlERDg1OEQ2N0ZFN0RBMUI2RDAxOEIzNTEyMTwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPkNCNDcwNEIwOUQ0NTVCNzIyNDY0QzkwOEM4RTkzMDA1PC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-Q0VFN0QwRjhGQzQ0ODNGNzdERjQ1QzEwNzFFOUM4QTM8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT5EQjlBRjAzMTA2NEUwNkZBNzZDMjQ2OUQ0NTg4OENEMjwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPkVFRTRBODc3MzE2Mzg5MkVDQjc4NTBCNDRDNEJDMzMxPC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-RjlDMjZCNzlBNzkxRkNBNUY5OUY5RTg5RTQ3QjA1NDI8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT5hZG9iZTpkb2NpZDpwaG90b3Nob3A6OTI1MmMxOGQtNmQyMi0xMWRlLWE0MDUtYzY4NmMyNGI4YzgxPC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-eG1wLmRpZDowNDlBOUVBQzRERjZFMTExQTg1RDkyQkY4OURDREZGNzwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPnhtcC5kaWQ6MEFFOEY3RTVDOENFRTMxMUI4QzZEOEFFNkY0NDczNkI8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT54bXAuZGlkOjEyMzgzMEU4OURGNUUxMTFCRkRDQTIxMzEyMTIyMkVCPC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-eG1wLmRpZDoxRkY4MTYzNjBBRDcxMUU0OUYxNEVCRDZBREE0MzZGNzwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPnhtcC5kaWQ6NDQ2MzdENzIzOTIwNjgxMUFBODNBRDJFNEY5NjQwNjc8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT54bXAuZGlkOjRBNjc4REM1MURDOUUzMTFBMTRDQTE3REJFMThENjhEPC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-eG1wLmRpZDo1RDYzMUY0RTYxQzlFMTExQjBFNURGNzQ1MUVBQUY1MDwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPnhtcC5kaWQ6NUY1RjZENkY4NzUyMTFFMTg0QjA4NDhGMzQ4Nzg2NkU8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT54bXAuZGlkOjc5QzYzQ0FFMDBDODExRTM5REYyQUVFNEY4OEE1RkUyPC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-eG1wLmRpZDo4QjlCOUQxRDNDMDBFNDExQjFDQURDMzEyMDBFQkZDQTwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPnhtcC5kaWQ6OUY0OTdBOTg5MzY2RTQxMThFMzdFN0VGQUZBOEJDQzI8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT54bXAuZGlkOkFCMzJEODhDQUE1REU0MTE4ODgyOTM4NDQ3NzJBN0I5PC9yZGY6bGk-CiAgICAgICAgICAgICAgIDxyZGY6bGk-eG1wLmRpZDpBQzc3RDg4OUJDQ0VFMzExQjhDNkQ4QUU2RjQ0NzM2QjwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpPnhtcC5kaWQ6QjlEQ0Y5MTVEODQyRTMxMTg2RDlCN0I0QkE3MUE1QUM8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT54bXAuZGlkOkYyNUVENDE0QTE2OEU0MTFCNUFBRDU1RkM2RUFBQkQzPC9yZGY6bGk-CiAgICAgICAgICAgIDwvcmRmOkJhZz4KICAgICAgICAgPC9waG90b3Nob3A6RG9jdW1lbnRBbmNlc3RvcnM-CiAgICAgICAgIDxwaG90b3Nob3A6Q29sb3JNb2RlPjM8L3Bob3Rvc2hvcDpDb2xvck1vZGU-CiAgICAgICAgIDxwaG90b3Nob3A6SUNDUHJvZmlsZT5zUkdCIElFQzYxOTY2LTIuMTwvcGhvdG9zaG9wOklDQ1Byb2ZpbGU-CiAgICAgICAgIDxkYzpmb3JtYXQ-aW1hZ2UvcG5nPC9kYzpmb3JtYXQ-CiAgICAgICAgIDx0aWZmOk9yaWVudGF0aW9uPjE8L3RpZmY6T3JpZW50YXRpb24-CiAgICAgICAgIDx0aWZmOlhSZXNvbHV0aW9uPjcyMDAwMC8xMDAwMDwvdGlmZjpYUmVzb2x1dGlvbj4KICAgICAgICAgPHRpZmY6WVJlc29sdXRpb24-NzIwMDAwLzEwMDAwPC90aWZmOllSZXNvbHV0aW9uPgogICAgICAgICA8dGlmZjpSZXNvbHV0aW9uVW5pdD4yPC90aWZmOlJlc29sdXRpb25Vbml0PgogICAgICAgICA8ZXhpZjpDb2xvclNwYWNlPjE8L2V4aWY6Q29sb3JTcGFjZT4KICAgICAgICAgPGV4aWY6UGl4ZWxYRGltZW5zaW9uPjIxNDwvZXhpZjpQaXhlbFhEaW1lbnNpb24-CiAgICAgICAgIDxleGlmOlBpeGVsWURpbWVuc2lvbj42MjwvZXhpZjpQaXhlbFlEaW1lbnNpb24-CiAgICAgIDwvcmRmOkRlc2NyaXB0aW9uPgogICA8L3JkZjpSREY-CjwveDp4bXBtZXRhPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgIAo8P3hwYWNrZXQgZW5kPSJ3Ij8-j8x0VwAAACBjSFJNAAB6JQAAgIMAAPn_AACA6QAAdTAAAOpgAAA6mAAAF2-SX8VGAAATg0lEQVR42uydeZhV5X3HP-fcbWaYhX0VEUEYcSGABjQSKEE0GKumqTzNY2ITY_VJ2iRtUqvGamiMRmtj2rRWTWK1sVaN2qBocFcExAUdIoLsm8OwDcPsdz2nf7y_A-e-c-6Gc2fulfN7nvvMnHPPOfddft_f_p7XuOH66ykmdVlh5g78gEuHLSeWqqLEyQRuBp4DGoFBQBdgATOB8cAdgC3XTwV-DFQBPwWWl3wHDYtQoIuN7RO4t2khlWYcn3qfgsX-gYiRZF3nSUwdsI0TKw6QtAOlDqy_AXYDSWAc0AykgGnAWQIsBGirXfcuAC4GlpZq50Jmkn2xQTx76HxiVoiIkfQRUERGKi5yDYuWRDWP7Z-DaVjlMCYtQFSAlQDi8rdbtJcNRICnPe59RrRcSZIBNHScTEP7yeyIjiiX-fCB5UU2EDYTtCSreaXlM5iGhVG-45WSvxcAwzNcc1VJmiZGis5UBavaTqU6ECXka6vyBpYjKU3D5umDs9gTG0rILNtJjQEh4Mos13xVrikZChgWnakIDzbNJ2qFfU31aQGWApdN2EzyUstUDMMSuJUVGWIKVgHzslw3FRhcSs0OmjG2dI9hc_cYwkbK5_pPE7CU5EyxtXs0W7rGEDISpTwmcfGx4pqfFQeGAgNz3D-2ZAIWRoJNneN48uDnqArEjoQzffoUAcvEptuKsLqtnhQGRmlOcwIYA4wETgEmABNREcIkMCSPZ4wuDVClaE7U8uDeBSStQKmO96eSgn39g5VmjDXtE5ldt46xkQMk7GCpjcl-4G5UZLBC--5OoC6PZ4RLQmoaFqvb64laISr8fNWnV2MdQbOR4sWW6ZhGCrt0fa2KLAGMMphYi-ZEDWvaJ_oRwOMFWAHDYnv3SDZ1jSVslJUktYHDeVx3uL8bGjLjrGqdwuFkDQE_Cnh8AMvEpsuK8Fb7ZJKUle0fAA7kcd3u_vWtkhyID-at9noqzZjP5ccLsACqzBjvtU-kKT6EcCBKxEykfUrUfKkEWoHNWa5pA5r6V60avNF6Ot1WyA9YHC_BC7dNFTJS_P7AuVww6F2SHK0htGyTumAX4yobiacqSskPq5SgxmPATRmueQlo78sARZoQMmOsODSDVw9PpSbQ5XP48QYsJ4ixJzaY-5oWpkEnZZsMDHZy2oAdXDh4DbWhDmKpSClo8QpUpfvDWYB1DxRXTQQMi6CRwLKDNCdrePHQtLTv9sSG-Cbg8QwshxEqPQIYMSvEG62nczBRx9yBa5lc1YhlmxS50tBC5aoyUYv83SjgukL7fgXwajEbGDFjHE7WsiM6jp3REaxuqydmhXpYAiEj5RuBxzOwspk4NYFudkRHcO-eL7Fw8DvMGfRHIkaimLkvS8Dx-Qzfv-z6_zvAbFTiGGA7cJk8o9dJlYQleLFlBg3tE9gVGya-aMozR-WD6jgNXhRiLg4IRHn58FR-tnMRW7tHEwl0YxaPdf5FAhA67QaWuY47gG2u4-XAweKBKs7S5pksbf4s-xIDqQ10EzaSfnDCB9YnY6yQkaLbCvPwvnn8du8CtVCvOH7EIWAWqgLDrckuQq3JOtoscK_aDBSn70r_LG0-h5dapjHAjBE2kj6cfGD1rvaKWSHeaZvEQ_vm8177KZhGUVhsA6p8yaEmYH1_9DkciLG1ezTLDk33l9H7Plaxfa8o27pHsic2hElVe6gwY6R6f8l_UhNAAY4udCw62UDYSNKaqOaFlukMMGO-2edrrOIzXcRMELeDPNC0gC4rUoyynX5NnoWMFG2pKn7d9EV2Rkf4ZUk-sPrQTDKSbO0exZauMQTNRCkX9BaM6YCZYEPnieyKDvNNQB9Yfa-5KgMxnjhwHhs7Tiy3gt4sAiPOuo4JLGme5S9O9IHVXx2w6bbCrG6vJ2mbZe-HmIYNhs2bkvj1_SofWP1GlWachvaTaYwNI1Tm73QIGUme3P95NnSO9U1AH1j9T0EzxQst0zFKe_Fkjsmw2BsfxNqO8YT9xYk-sEqBAobFzugI1neOI1Kmkj5kJnij9TTarcpi5ed88oFVuK8VtcK83VZPvAxfnBI2k-yJDWVN2ym-CegDq_R8rbWdytcKlpmvZdkGyw-fQdIO-gELH1ilRTaq7On5lhmY5QYsDLZFR_lvqfWBVaq-Vopd0eGs7xxPuMxMKv9tSscPsEJAtXzC5dEhm5gV4q32yUStsG9W-WS4Pv0OrBGojdRWAluBLai9oG4rBy1Xacb5oGMczYk6v8bu-KaLgCeAx-VvRV_9sFd1-3zgeQ8AjUC9t_wWirRKtjd9rZCRKuZiSJ_Kg04FvpyD3_tEY9UDL3qc98W-T-VI3R4yt881lkH64j5Q73j4EfCx-FmD6MM1ST75VK7kBlYN8DnX8XbgEqDTHyaffDp2U7Ca9A3THvJB5ZNPGSkAnJkPsPRw-kZ_7HzyKSPdAvxdPsCq0r7zX6Xqk0_e9HXgH4G9-QBLB5JfCeqTTz3pS-ImgdqT2pOCqK0_q4DPat9NA_6IeltRrt0zDGA4cBpqe9FBqD2iNgHrSH9HX6FUDUxBpQJGiQDYAXwgf48lSmmgtkI9AzhZ2tss5u9aaXtva2wDtYVqEJW-qERFW7tM7GyJ7BpUPsbd_-3S_5159r9W_GcbtRVsE0dDz3XAJNS-ySnUi0k3k3tjh6EyN4gv7t7eaJg8cwzqxaY7UIUGhY5pBLVN7SThLxtolHnaTu9HqIdIn2xU1VGnppVmAc-4jqcAJ0k7DJeyOhgE7ic9iebQT-SzU8CSaTfuM4CfAQvwTsDFgT-gNhFYV2AnrwP-isybaa8H_kGen-8gT0JthZqpvR2oqpNJvTxpPwIWu6yEVcACEwvTsHhi_2wOJOrcVfnDpf9XZen_OuB66X-2XOO3gdvl_90ynybwY-BaAZ6bDqPeCHxnBsvFBH6NihoDLEG9XnukjO1lHj77PuB7qCqIXPmkCuBqGbMRGa75CPgh8By9k5-ahnq9uOMSRYGzXMCaAryp3bNIPjr9tSnIzEaVWaIiN4lWW0jmrHZYJuADcfjyeQHgPJFy15F9h_opIkGeF62Tj228MUd7q4UJv9GLoLpGhJQDqgbgfKDTMODJA7NZ1XaqG1QLBAA_yNH_04GlwLM5-u9mvKj0v0HGt9bj-oHS3qVZ-MPWxuxymbNFeNeVjgAeBW4le93eWGAN8G9ZQIVo8KWo3V0-aUXFKRqoAM4DPpT_x4slky8FgyL1h0knxmsSZquYcbaHxPpPkSq6dnoFtStHLTDHZS4gEnI88M0sEvYrwO88zq8S7RkCpspgOPQF4H2RMAcNIGEH9J1JrnDZxn1JlwP3uo63AnOBrkigm-Ut03ij9XT3XlaLhAF1WiFgCwEztLm6UJjxLNQrsnVyj_UE4CmX1H9CTPZqAfSlrmvPB74r2ksndyn-n8gcALwuGvRjMV0vESZ16Eb5foXHM4cJ4Adr4_W4tLVSfufPXd9fK3z3_WPUXGOAdzVQzZXxdLTnrwRYARFmQZdm3-jhXjUGRd0aMiluVXcd8IjHIALc4AGqG4WBWqWDhkzW14Ffuq67Uhpzu0cnP-MBqiUiud3-RAQ4V9rnSLVxYhacF7VD8Vm1GxkWOkzKNhE7-Lf9AKr5qE3qHNqPSsK3RsyE2s60bTJVR99BP9MDVI-Judvo6n-FMOvjLo023uk_2bciMl1a9CHN77lXGPdFTRjeQ8_yIK9nzgde08zyXwjT_7NmFi_UgGBKX92guklAHXWdux_4J1RV0HA5910Zi5UFzs9g4Xm31r5MhIM7qLdQhFM18LZLqD8oONEpZcogJMV51-3spMckTRR1jqYxbhdNZcmAWahdO_5dnD734Nzm4cOENSYEuEP8v63SDls-UdGMp5L-TvWzgWsMQCvB_UU_gGqmxqBtwDnAvoCRYl98IL9p-iJ744MdEzACPKk9YzHwVREq7v53y7MnirZx_-ZVOdplCfPc7xFMsFE7Ut6tmXljczzzoEjylz183STwc41Z59MzvXOuaD6H7hY-iXq0cZ0IkA7X-bsobGOKammTu2_fBH7v8Xtx6UeHFmtolWP9Y7nD7SEPleYV2fqhh__wSo5OvAV8TTung_PzGtieEy2YzSlvEXPFPcC3hYzEgPUdJzo7xo8ELu5jUNWL5Eaz2beBWoz5dttkmmKDiZhxRwBcKGaJQ0-JZM7W_2bpvw7Gyiz3NJG-HZEX6VpzZB7zuz4HmH-n8VaN7vC7_u8Cbs5h2m0G_kOL2I3KIzLraPxnRRi4LbT_ynG_SXqKKpRLhedLlRpANok6zIeeEj_IoYs0h_sa7fq_Jb-q-j0SxTsiiRJ2aOb4yn3UBLqwbHMGfbOGzGGCE4TRKjRp_AGoVwc0x-tY2TqFAYFu94RfofmqP8iz_7vEXHMHCWZkuT6ex3js9bAmcmnBXLQ5i-CukDFy6FnRymaWj6FpQXL0G9EmAfEr3ZsL3i4ar9eoUIYbpanw_yH_RLKlSZgqVA7JQf6ZGmC3FNCuNBMybCTnvtcxkUPJGkzDOr2PtFQXKk-1ymWzd4mZfMR3NQ2Llw9PS9vMXBh3tuv4bQlU5Ev_qx3PyjHnRp5CIl_gmHnOfyaq0yKAcyTS-4KYpl6fZR5gyDbXURHkd4lQd-hO8eV6dUlJoWFKPaT7foH3r89gYoQ102AFha0B269xxYSQkXS8rNo-AtZYMXfGaqZqj-hXY2yIfiqEytvhitYVkvw8ID7xQFdbSo2ygblK04rDXVHGQmhKlu_aUGVI3_Ywe3t9vWGhwCpUkuW6P5OUSBVxEotFu8T5fcfF2GOA_xbz-YjTOyjYwc7YMCJGr-2QUu5LpVPCS6ZLAC8t8BkVwBtZvq8VnzUmboZDyyTotas_gdXh4aQ_W8D9J3k4347t647-TBdw5MswA7Xj3SnbdJi2q4-YYwAq93cOKmnuhI0Xibn8l4CVtIIsGv4a3XvDbIuOdF4lnRCJ6txzYoH9r9PGYG-ZAatT5snJef4BlWLoTaoQrf73qGjqxS7tuBpVebGvv3ysRk1LfZn8Q5yGSHS3T7LN5VCv05zQkQW068J0NWouHx5upcKMY2Ns6GNTp1Eccbeg-JrY8qaFQVUgyqzajzCxHfAnSM8hnoeqxcuXFmrHb5cZsNokCOXQn1KcPZ2Doh0vR6UG3LGDlWSvcinIMjKPQbI8qUW75uV570zSQ8Nvkr7LvJ7AvTnP59agym-O4MqyjZUjQi0qlG0b7_QDo2zUghGgonw3AUbMijC9ZjOjI83ErJBjUj-u-Rw3FqCtbtWsinfLDFgJ0lM2p4h5ViiT52uBRQW8Da5zE6QNVZ8g5nDMwLJRSTs3PU16eVEmx36Jdk6PxCwlvRznWlSVRjYKiw_jzl_cFTaShzd0jaVVRQU_xrt8ptj0rocDvhj4DkDKDnLJ0DepCXSRVPsnP6UJmu-jErm5zJtHSa9WuBuVuCw3ukc7foj86j-RwM_9qOqhfMmJ2Lqjr9OEnyMZ7rFIj4KfmUlrHUt-Zy3pYfMK8SkuomfCLIDKsjdwtPwEVB7hLe3abnpWCj-IytF4RfYmoMKxl7rOHQJ-ql5_lsRUVquNKnnpD3rFo0-_BP4iaZuMr9jL1aOXUWnGidvBDnpWTTyFKv-p9pDOE4FXNTO4EVWtUo60jvQlGZNQqYtpGfg0BExGrazYD3zrGIJph1CpicOuc19ApW-8tF9S88NmeYC5Fqg_lqpgW8yaSS7TrkI0ziYZnO0SqLiQnrmFBtFEXo75SxKxcZfU3IJabrAEVRhZjSp9Od-j03PEXtfpfVR94-39wDBOPd99rnOPAO1RK7z0hMgBrhq9jAeaLqAzVfFMyEjq7bxVtNczwHvyrPnSV928mUf5vqfEFr740GWB1Euf3xchtV0Y9wxxQ8Z9wmAc4tudLb_hCLBLRINe6wHW5128Z4o_-wKq7Gy4zMv_HWu5fUyiKvdp5tokAV0megZV-5YtUvevAo7faFG_K7OYhvuE2bKt97pTJuWGfmCaX0kwZrF7LAyYHbPCK8ZG9vOtUct4oGmB3ZmquCNgWJ2oZRMODUUtY8m0lGW39H8T5U0togFe09yLafLJRpvoWd2RL21BVWK85zp3tZjU12lK4FF6JqYX6PjI9jKZAXmA6xuopcq5EsXrUaX-l9EzZO8luR4QNb8kj2jSYgF0rkWUlgQE5ubR3oc1x3aUhy1taL7d0Bx9-ommiUHlXebErTAnVOzjK8NWELXCtqHMxdPIncpoE41-ag5QDdT83VzJM13gejn0w7X_cz2zKk_tskdAtDgPXkGiexeLZXTAwyzL1_V5n_QiYFB1sT_XIpSNwJ-RveJor3HD9dc7B5WiYp1E3Ra81_ZkCiJMFPVcL6ZhCtgg0b-POLal7gYqyXq2SLI6V-dWir_n6ainbJPvnbCEEeEWJzigt7cetYRjktjrcTFDXkdV0w9BlVw5y9nXapLLEPDXyP8HOZo-yEQB1FqygDzLlGc3mIZtxy2TR_bPY2PXCU5-y0SVSTm2fLXMzx7xP9bmGagYicqN2TIP63L4I2Fpp9PGjzxM7AkcrRZpyUNb1Ml4OfzVQO5yuIGonOa5wl9VYu1sl-BQAypnl6mgYLi4JJbM0Zo8_DCnX849EblPXzYzVPyx6aKEEqiFnq8DH_7_AA8zyi6_vjr6AAAAAElFTkSuQmCC', null, '3');
INSERT INTO merchant VALUES ('17', '857 645 8871', '12 07', '9GK2J085R8A3A', '2b5d6fe3-eec3-0f35-2e6d-4525d9fecb76', '5', null, null, '2');

-- ----------------------------
-- Table structure for `merchant_orders`
-- ----------------------------
DROP TABLE IF EXISTS `merchant_orders`;
CREATE TABLE `merchant_orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_count` int(11) DEFAULT NULL,
  `merchant_subscription` int(11) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `merchant_subscription` (`merchant_subscription`),
  CONSTRAINT `merchant_orders_ibfk_1` FOREIGN KEY (`merchant_subscription`) REFERENCES `merchant_subscription` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of merchant_orders
-- ----------------------------
INSERT INTO merchant_orders VALUES ('3', '2', '3', '2016-07-24', '2016-06-24');
INSERT INTO merchant_orders VALUES ('4', '1', '4', '2016-07-24', '2016-06-24');

-- ----------------------------
-- Table structure for `merchant_subscription`
-- ----------------------------
DROP TABLE IF EXISTS `merchant_subscription`;
CREATE TABLE `merchant_subscription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_id` int(11) DEFAULT NULL,
  `subscription_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `merchant_id` (`merchant_id`),
  KEY `subscription_id` (`subscription_id`),
  CONSTRAINT `merchant_subscription_ibfk_1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`),
  CONSTRAINT `merchant_subscription_ibfk_2` FOREIGN KEY (`subscription_id`) REFERENCES `subscriptions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of merchant_subscription
-- ----------------------------
INSERT INTO merchant_subscription VALUES ('3', '16', '3');
INSERT INTO merchant_subscription VALUES ('4', '17', '2');

-- ----------------------------
-- Table structure for `modifiergroup`
-- ----------------------------
DROP TABLE IF EXISTS `modifiergroup`;
CREATE TABLE `modifiergroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `showByDefault` tinyint(1) DEFAULT NULL,
  `pos_modifiergroup_id` varchar(20) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `merchant_id` (`merchant_id`),
  CONSTRAINT `modifiergroup_ibfk_1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3408 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of modifiergroup
-- ----------------------------
INSERT INTO modifiergroup VALUES ('3325', 'Extras', '0', 'JPX02J6D59K1M', '16');
INSERT INTO modifiergroup VALUES ('3326', 'Combo #1 toppings', '1', 'PHN4W4H4MMZ5T', '16');
INSERT INTO modifiergroup VALUES ('3327', 'Steak Fajita', '1', 'MK73QWDA8K9Y6', '16');
INSERT INTO modifiergroup VALUES ('3328', 'Street Tacos toppings', '1', 'FGVJ13CM1N7V4', '16');
INSERT INTO modifiergroup VALUES ('3329', 'Migas Adds', '1', 'C65S61C7DCP7P', '16');
INSERT INTO modifiergroup VALUES ('3330', 'Nachos toppings', '1', '0833QCZ1JP2ZT', '16');
INSERT INTO modifiergroup VALUES ('3331', 'Pescador toppings', '1', 'WGVNQJXBBV7KE', '16');
INSERT INTO modifiergroup VALUES ('3332', 'Monterrey toppings', '1', 'J08MY7697S49P', '16');
INSERT INTO modifiergroup VALUES ('3333', 'Signature Taco 1', '1', 'CBXJRB5H540ME', '16');
INSERT INTO modifiergroup VALUES ('3334', 'Combo #3 toppings', '1', 'WCCZSD8DWARK2', '16');
INSERT INTO modifiergroup VALUES ('3335', 'No', '1', 'JJZK4G075T9KP', '16');
INSERT INTO modifiergroup VALUES ('3336', 'Kids Meal toppings', '1', '03RMQAADQ5VM4', '16');
INSERT INTO modifiergroup VALUES ('3337', 'Taco 2', '1', '1535CH35FEEVR', '16');
INSERT INTO modifiergroup VALUES ('3338', 'Chicken Salad toppings', '1', '2XVCJVQXHQ1XP', '16');
INSERT INTO modifiergroup VALUES ('3339', 'Marinero toppings', '1', 'RK7JYG8K8V0F0', '16');
INSERT INTO modifiergroup VALUES ('3340', 'Taco 1', '1', 'KFM26Z1P6RJK0', '16');
INSERT INTO modifiergroup VALUES ('3341', 'Chicken Fajita toppings', '1', 'M508MJ64J57D4', '16');
INSERT INTO modifiergroup VALUES ('3342', 'Combo #7 toppings', '1', 'BBANP266VFX52', '16');
INSERT INTO modifiergroup VALUES ('3343', 'Chicken Chipotle Salad toppings', '1', 'KPVS5MA3TX22T', '16');
INSERT INTO modifiergroup VALUES ('3344', 'Combo #5 toppings', '1', '06ME1HX5CXGB0', '16');
INSERT INTO modifiergroup VALUES ('3345', 'Salsa Choice', '1', '5JV578P6GEB4T', '16');
INSERT INTO modifiergroup VALUES ('3346', 'Tortillas', '1', 'V36F9FART380W', '16');
INSERT INTO modifiergroup VALUES ('3347', 'Combo #2 toppings', '1', '1DCPYJPK2PR1A', '16');
INSERT INTO modifiergroup VALUES ('3348', 'Signature Taco 2', '1', '86Q0RJ6K89V2A', '16');
INSERT INTO modifiergroup VALUES ('3349', 'Combo #4 toppings', '1', 'XQ05MVNJ9BDN2', '16');
INSERT INTO modifiergroup VALUES ('3350', 'Breakfast Tacos toppings', '1', 'YJGYR2583NXQ0', '16');
INSERT INTO modifiergroup VALUES ('3351', 'Taco 3', '1', '4R23XWDZ9827P', '16');
INSERT INTO modifiergroup VALUES ('3352', 'Signature Taco Toppings', '1', 'RXKS1AS025JTP', '16');
INSERT INTO modifiergroup VALUES ('3353', 'Combo #6 toppings', '1', 'M1XKMVGQ2C8R2', '16');
INSERT INTO modifiergroup VALUES ('3354', 'Extra', '1', 'Q01Z8R7HN3S72', '16');
INSERT INTO modifiergroup VALUES ('3355', 'Presidente toppings', '1', '7ZHTCJF0YN2QG', '16');
INSERT INTO modifiergroup VALUES ('3356', 'Trio toppings', '1', '78B3C0BYVC3VW', '16');
INSERT INTO modifiergroup VALUES ('3357', 'Torta Toppings', '1', 'TRHDV792HVHM4', '16');
INSERT INTO modifiergroup VALUES ('3358', 'Quesadilla toppings', '1', 'R6NRTBPAMT7SW', '16');
INSERT INTO modifiergroup VALUES ('3359', 'Grande Burrito toppings', '1', '3V0Q7XT83WFHP', '16');
INSERT INTO modifiergroup VALUES ('3360', 'Red Salsa', '1', 'MEQAPNM0PVWDG', '16');
INSERT INTO modifiergroup VALUES ('3361', 'Capitan toppings', '1', 'G7ZTBFKJDKVYE', '16');
INSERT INTO modifiergroup VALUES ('3362', 'Steak Salad toppings', '1', 'A74DM69D84T9W', '16');
INSERT INTO modifiergroup VALUES ('3363', 'Breakfast Burritos toppings', '1', 'C5WF7D9EPT3FT', '16');
INSERT INTO modifiergroup VALUES ('3364', 'Tortilla Soup toppings', '1', 'XYD40BMAARQPE', '17');
INSERT INTO modifiergroup VALUES ('3365', 'Chicken Salad toppings', '1', 'TDQNHSXYQR8QW', '17');
INSERT INTO modifiergroup VALUES ('3366', 'Tortillas', '1', '4X0TEAFAP3NMJ', '17');
INSERT INTO modifiergroup VALUES ('3367', 'Signature Taco 2', '1', 'HNTPRBAVK20KM', '17');
INSERT INTO modifiergroup VALUES ('3368', 'Taco 2', '1', 'APPHQ87HFHD0R', '17');
INSERT INTO modifiergroup VALUES ('3369', 'Quesadilla toppings', '1', 'KH8VVC4SEW7T4', '17');
INSERT INTO modifiergroup VALUES ('3370', 'Breakfast Platter toppings', '1', 'PXPPT87DPDZHR', '17');
INSERT INTO modifiergroup VALUES ('3371', 'Combo #7 toppings', '1', 'Q63QF7SA61ZS4', '17');
INSERT INTO modifiergroup VALUES ('3372', 'Trio toppings', '1', 'KK8PGFJKQM5R2', '17');
INSERT INTO modifiergroup VALUES ('3373', 'Red Salsa', '1', '8RM0D8RW7J0QJ', '17');
INSERT INTO modifiergroup VALUES ('3374', 'Combo #4 toppings', '1', '1NTRMY3YA54YE', '17');
INSERT INTO modifiergroup VALUES ('3375', 'Combo #5 toppings', '1', 'B3K1DG1Z7A9VA', '17');
INSERT INTO modifiergroup VALUES ('3376', 'Marinero toppings', '1', '7P8X9QC1VVJZC', '17');
INSERT INTO modifiergroup VALUES ('3377', 'Salsa Choice', '1', 'V141Q5B0X189T', '17');
INSERT INTO modifiergroup VALUES ('3378', 'Breakfast Tacos toppings', '1', 'E88D81M44VEN4', '17');
INSERT INTO modifiergroup VALUES ('3379', 'Kids Meal toppings', '1', 'WF5VQCJA54GE8', '17');
INSERT INTO modifiergroup VALUES ('3380', 'Combo #6 toppings', '1', 'MP46BV2YV7RYE', '17');
INSERT INTO modifiergroup VALUES ('3381', 'Green Salsa', '1', '7K323MCH364C4', '17');
INSERT INTO modifiergroup VALUES ('3382', 'Nachos toppings', '1', '4ZEY2QCVD0JTR', '17');
INSERT INTO modifiergroup VALUES ('3383', 'Extra', '1', 'HDZK1GMH437QT', '17');
INSERT INTO modifiergroup VALUES ('3384', 'Torta Toppings', '1', 'JEV7AR6DTCFAR', '17');
INSERT INTO modifiergroup VALUES ('3385', 'Pescador toppings', '1', 'JHQ29FZKJK0GT', '17');
INSERT INTO modifiergroup VALUES ('3386', 'Migas Adds', '1', 'PCBVQRZ8867R8', '17');
INSERT INTO modifiergroup VALUES ('3387', 'Taco 3', '1', 'YC937M89MGP0C', '17');
INSERT INTO modifiergroup VALUES ('3388', 'Combo #3 toppings', '1', '0MKKXYPHH6H8W', '17');
INSERT INTO modifiergroup VALUES ('3389', 'Monterrey toppings', '1', 'B4TSPHVQSY76G', '17');
INSERT INTO modifiergroup VALUES ('3390', 'Grande Burrito toppings', '1', '35Z81MFD74PM8', '17');
INSERT INTO modifiergroup VALUES ('3391', 'Steak Fajita', '1', 'VA530EYMBKCD6', '17');
INSERT INTO modifiergroup VALUES ('3392', 'Steak Fajita toppings', '1', 'C5GGMT67WPXX0', '17');
INSERT INTO modifiergroup VALUES ('3393', 'Combo #2 toppings', '1', 'XBET7HC6FS2KT', '17');
INSERT INTO modifiergroup VALUES ('3394', 'Combo #1 toppings', '1', '4CS30VCP5AF4J', '17');
INSERT INTO modifiergroup VALUES ('3395', 'Capitan toppings', '1', '6AHK2088N4JMC', '17');
INSERT INTO modifiergroup VALUES ('3396', 'Chicken Chipotle Salad toppings', '1', 'ZHSK13FQJ9W8P', '17');
INSERT INTO modifiergroup VALUES ('3397', 'Presidente toppings', '1', 'F0H7NSZ0W1Q38', '17');
INSERT INTO modifiergroup VALUES ('3398', 'Signature Taco Toppings', '1', '63X1C28RHZQSC', '17');
INSERT INTO modifiergroup VALUES ('3399', 'Street Tacos toppings', '1', 'TRVQ6ACN83YYE', '17');
INSERT INTO modifiergroup VALUES ('3400', 'Taco 1', '1', 'A7X4T3J658D9W', '17');
INSERT INTO modifiergroup VALUES ('3401', 'El Verde toppings', '1', '8GYACFHPWQWQW', '17');
INSERT INTO modifiergroup VALUES ('3402', 'Big D toppings', '1', 'T6N6GNJ9W5NSR', '17');
INSERT INTO modifiergroup VALUES ('3403', 'Signature Taco 1', '1', 'BDYZSQXJAP7FT', '17');
INSERT INTO modifiergroup VALUES ('3404', 'No', '1', 'MGWP7QTZWRJG2', '17');
INSERT INTO modifiergroup VALUES ('3405', 'Breakfast Burritos toppings', '1', 'WXBN0YQ3SVH9W', '17');
INSERT INTO modifiergroup VALUES ('3406', 'Steak Salad toppings', '1', 'Q8ATJWYKTKN70', '17');
INSERT INTO modifiergroup VALUES ('3407', 'Chicken Fajita toppings', '1', 'PVHT3XQGZW2AA', '17');

-- ----------------------------
-- Table structure for `modifiergroup_modifiers`
-- ----------------------------
DROP TABLE IF EXISTS `modifiergroup_modifiers`;
CREATE TABLE `modifiergroup_modifiers` (
  `modifiergroup_id` int(10) DEFAULT NULL,
  `modifiers_id` int(10) DEFAULT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `map_modifier_group_fk` (`modifiergroup_id`),
  KEY `map_modifier_fk` (`modifiers_id`),
  CONSTRAINT `map_modifier_fk` FOREIGN KEY (`modifiers_id`) REFERENCES `modifiers` (`id`),
  CONSTRAINT `map_modifier_group_fk` FOREIGN KEY (`modifiergroup_id`) REFERENCES `modifiergroup` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2147 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of modifiergroup_modifiers
-- ----------------------------
INSERT INTO modifiergroup_modifiers VALUES ('3325', '6626', '1591');
INSERT INTO modifiergroup_modifiers VALUES ('3326', '6627', '1592');
INSERT INTO modifiergroup_modifiers VALUES ('3326', '6628', '1593');
INSERT INTO modifiergroup_modifiers VALUES ('3326', '6629', '1594');
INSERT INTO modifiergroup_modifiers VALUES ('3326', '6630', '1595');
INSERT INTO modifiergroup_modifiers VALUES ('3326', '6631', '1596');
INSERT INTO modifiergroup_modifiers VALUES ('3326', '6632', '1597');
INSERT INTO modifiergroup_modifiers VALUES ('3326', '6633', '1598');
INSERT INTO modifiergroup_modifiers VALUES ('3326', '6634', '1599');
INSERT INTO modifiergroup_modifiers VALUES ('3326', '6635', '1600');
INSERT INTO modifiergroup_modifiers VALUES ('3328', '6636', '1601');
INSERT INTO modifiergroup_modifiers VALUES ('3328', '6637', '1602');
INSERT INTO modifiergroup_modifiers VALUES ('3328', '6638', '1603');
INSERT INTO modifiergroup_modifiers VALUES ('3328', '6639', '1604');
INSERT INTO modifiergroup_modifiers VALUES ('3328', '6640', '1605');
INSERT INTO modifiergroup_modifiers VALUES ('3328', '6641', '1606');
INSERT INTO modifiergroup_modifiers VALUES ('3328', '6642', '1607');
INSERT INTO modifiergroup_modifiers VALUES ('3328', '6643', '1608');
INSERT INTO modifiergroup_modifiers VALUES ('3328', '6644', '1609');
INSERT INTO modifiergroup_modifiers VALUES ('3328', '6645', '1610');
INSERT INTO modifiergroup_modifiers VALUES ('3328', '6646', '1611');
INSERT INTO modifiergroup_modifiers VALUES ('3329', '6647', '1612');
INSERT INTO modifiergroup_modifiers VALUES ('3329', '6648', '1613');
INSERT INTO modifiergroup_modifiers VALUES ('3329', '6649', '1614');
INSERT INTO modifiergroup_modifiers VALUES ('3329', '6650', '1615');
INSERT INTO modifiergroup_modifiers VALUES ('3329', '6651', '1616');
INSERT INTO modifiergroup_modifiers VALUES ('3329', '6652', '1617');
INSERT INTO modifiergroup_modifiers VALUES ('3330', '6653', '1618');
INSERT INTO modifiergroup_modifiers VALUES ('3330', '6654', '1619');
INSERT INTO modifiergroup_modifiers VALUES ('3330', '6655', '1620');
INSERT INTO modifiergroup_modifiers VALUES ('3330', '6656', '1621');
INSERT INTO modifiergroup_modifiers VALUES ('3330', '6657', '1622');
INSERT INTO modifiergroup_modifiers VALUES ('3330', '6658', '1623');
INSERT INTO modifiergroup_modifiers VALUES ('3330', '6659', '1624');
INSERT INTO modifiergroup_modifiers VALUES ('3331', '6660', '1625');
INSERT INTO modifiergroup_modifiers VALUES ('3331', '6661', '1626');
INSERT INTO modifiergroup_modifiers VALUES ('3331', '6662', '1627');
INSERT INTO modifiergroup_modifiers VALUES ('3332', '6663', '1628');
INSERT INTO modifiergroup_modifiers VALUES ('3332', '6664', '1629');
INSERT INTO modifiergroup_modifiers VALUES ('3332', '6665', '1630');
INSERT INTO modifiergroup_modifiers VALUES ('3332', '6666', '1631');
INSERT INTO modifiergroup_modifiers VALUES ('3332', '6667', '1632');
INSERT INTO modifiergroup_modifiers VALUES ('3332', '6668', '1633');
INSERT INTO modifiergroup_modifiers VALUES ('3333', '6669', '1634');
INSERT INTO modifiergroup_modifiers VALUES ('3333', '6670', '1635');
INSERT INTO modifiergroup_modifiers VALUES ('3333', '6671', '1636');
INSERT INTO modifiergroup_modifiers VALUES ('3333', '6672', '1637');
INSERT INTO modifiergroup_modifiers VALUES ('3333', '6673', '1638');
INSERT INTO modifiergroup_modifiers VALUES ('3333', '6674', '1639');
INSERT INTO modifiergroup_modifiers VALUES ('3333', '6675', '1640');
INSERT INTO modifiergroup_modifiers VALUES ('3333', '6676', '1641');
INSERT INTO modifiergroup_modifiers VALUES ('3333', '6677', '1642');
INSERT INTO modifiergroup_modifiers VALUES ('3334', '6678', '1643');
INSERT INTO modifiergroup_modifiers VALUES ('3334', '6679', '1644');
INSERT INTO modifiergroup_modifiers VALUES ('3334', '6680', '1645');
INSERT INTO modifiergroup_modifiers VALUES ('3334', '6681', '1646');
INSERT INTO modifiergroup_modifiers VALUES ('3334', '6682', '1647');
INSERT INTO modifiergroup_modifiers VALUES ('3335', '6683', '1648');
INSERT INTO modifiergroup_modifiers VALUES ('3335', '6684', '1649');
INSERT INTO modifiergroup_modifiers VALUES ('3335', '6685', '1650');
INSERT INTO modifiergroup_modifiers VALUES ('3335', '6686', '1651');
INSERT INTO modifiergroup_modifiers VALUES ('3335', '6687', '1652');
INSERT INTO modifiergroup_modifiers VALUES ('3335', '6688', '1653');
INSERT INTO modifiergroup_modifiers VALUES ('3335', '6689', '1654');
INSERT INTO modifiergroup_modifiers VALUES ('3335', '6690', '1655');
INSERT INTO modifiergroup_modifiers VALUES ('3335', '6691', '1656');
INSERT INTO modifiergroup_modifiers VALUES ('3335', '6692', '1657');
INSERT INTO modifiergroup_modifiers VALUES ('3335', '6693', '1658');
INSERT INTO modifiergroup_modifiers VALUES ('3336', '6694', '1659');
INSERT INTO modifiergroup_modifiers VALUES ('3336', '6695', '1660');
INSERT INTO modifiergroup_modifiers VALUES ('3336', '6696', '1661');
INSERT INTO modifiergroup_modifiers VALUES ('3336', '6697', '1662');
INSERT INTO modifiergroup_modifiers VALUES ('3336', '6698', '1663');
INSERT INTO modifiergroup_modifiers VALUES ('3336', '6699', '1664');
INSERT INTO modifiergroup_modifiers VALUES ('3336', '6700', '1665');
INSERT INTO modifiergroup_modifiers VALUES ('3337', '6701', '1666');
INSERT INTO modifiergroup_modifiers VALUES ('3337', '6702', '1667');
INSERT INTO modifiergroup_modifiers VALUES ('3337', '6703', '1668');
INSERT INTO modifiergroup_modifiers VALUES ('3337', '6704', '1669');
INSERT INTO modifiergroup_modifiers VALUES ('3337', '6705', '1670');
INSERT INTO modifiergroup_modifiers VALUES ('3337', '6706', '1671');
INSERT INTO modifiergroup_modifiers VALUES ('3337', '6707', '1672');
INSERT INTO modifiergroup_modifiers VALUES ('3337', '6708', '1673');
INSERT INTO modifiergroup_modifiers VALUES ('3337', '6709', '1674');
INSERT INTO modifiergroup_modifiers VALUES ('3337', '6710', '1675');
INSERT INTO modifiergroup_modifiers VALUES ('3337', '6711', '1676');
INSERT INTO modifiergroup_modifiers VALUES ('3337', '6712', '1677');
INSERT INTO modifiergroup_modifiers VALUES ('3337', '6713', '1678');
INSERT INTO modifiergroup_modifiers VALUES ('3338', '6714', '1679');
INSERT INTO modifiergroup_modifiers VALUES ('3338', '6715', '1680');
INSERT INTO modifiergroup_modifiers VALUES ('3338', '6716', '1681');
INSERT INTO modifiergroup_modifiers VALUES ('3338', '6717', '1682');
INSERT INTO modifiergroup_modifiers VALUES ('3338', '6718', '1683');
INSERT INTO modifiergroup_modifiers VALUES ('3339', '6719', '1684');
INSERT INTO modifiergroup_modifiers VALUES ('3339', '6720', '1685');
INSERT INTO modifiergroup_modifiers VALUES ('3339', '6721', '1686');
INSERT INTO modifiergroup_modifiers VALUES ('3340', '6722', '1687');
INSERT INTO modifiergroup_modifiers VALUES ('3340', '6723', '1688');
INSERT INTO modifiergroup_modifiers VALUES ('3340', '6724', '1689');
INSERT INTO modifiergroup_modifiers VALUES ('3340', '6725', '1690');
INSERT INTO modifiergroup_modifiers VALUES ('3340', '6726', '1691');
INSERT INTO modifiergroup_modifiers VALUES ('3340', '6727', '1692');
INSERT INTO modifiergroup_modifiers VALUES ('3340', '6728', '1693');
INSERT INTO modifiergroup_modifiers VALUES ('3340', '6729', '1694');
INSERT INTO modifiergroup_modifiers VALUES ('3340', '6730', '1695');
INSERT INTO modifiergroup_modifiers VALUES ('3340', '6731', '1696');
INSERT INTO modifiergroup_modifiers VALUES ('3340', '6732', '1697');
INSERT INTO modifiergroup_modifiers VALUES ('3340', '6733', '1698');
INSERT INTO modifiergroup_modifiers VALUES ('3340', '6734', '1699');
INSERT INTO modifiergroup_modifiers VALUES ('3341', '6735', '1700');
INSERT INTO modifiergroup_modifiers VALUES ('3341', '6736', '1701');
INSERT INTO modifiergroup_modifiers VALUES ('3341', '6737', '1702');
INSERT INTO modifiergroup_modifiers VALUES ('3341', '6738', '1703');
INSERT INTO modifiergroup_modifiers VALUES ('3342', '6739', '1704');
INSERT INTO modifiergroup_modifiers VALUES ('3342', '6740', '1705');
INSERT INTO modifiergroup_modifiers VALUES ('3342', '6741', '1706');
INSERT INTO modifiergroup_modifiers VALUES ('3342', '6742', '1707');
INSERT INTO modifiergroup_modifiers VALUES ('3342', '6743', '1708');
INSERT INTO modifiergroup_modifiers VALUES ('3343', '6744', '1709');
INSERT INTO modifiergroup_modifiers VALUES ('3343', '6745', '1710');
INSERT INTO modifiergroup_modifiers VALUES ('3343', '6746', '1711');
INSERT INTO modifiergroup_modifiers VALUES ('3343', '6747', '1712');
INSERT INTO modifiergroup_modifiers VALUES ('3343', '6748', '1713');
INSERT INTO modifiergroup_modifiers VALUES ('3343', '6749', '1714');
INSERT INTO modifiergroup_modifiers VALUES ('3344', '6750', '1715');
INSERT INTO modifiergroup_modifiers VALUES ('3344', '6751', '1716');
INSERT INTO modifiergroup_modifiers VALUES ('3344', '6752', '1717');
INSERT INTO modifiergroup_modifiers VALUES ('3344', '6753', '1718');
INSERT INTO modifiergroup_modifiers VALUES ('3344', '6754', '1719');
INSERT INTO modifiergroup_modifiers VALUES ('3344', '6755', '1720');
INSERT INTO modifiergroup_modifiers VALUES ('3344', '6756', '1721');
INSERT INTO modifiergroup_modifiers VALUES ('3344', '6757', '1722');
INSERT INTO modifiergroup_modifiers VALUES ('3344', '6758', '1723');
INSERT INTO modifiergroup_modifiers VALUES ('3344', '6759', '1724');
INSERT INTO modifiergroup_modifiers VALUES ('3344', '6760', '1725');
INSERT INTO modifiergroup_modifiers VALUES ('3344', '6761', '1726');
INSERT INTO modifiergroup_modifiers VALUES ('3344', '6762', '1727');
INSERT INTO modifiergroup_modifiers VALUES ('3344', '6763', '1728');
INSERT INTO modifiergroup_modifiers VALUES ('3345', '6764', '1729');
INSERT INTO modifiergroup_modifiers VALUES ('3345', '6765', '1730');
INSERT INTO modifiergroup_modifiers VALUES ('3345', '6766', '1731');
INSERT INTO modifiergroup_modifiers VALUES ('3345', '6767', '1732');
INSERT INTO modifiergroup_modifiers VALUES ('3345', '6768', '1733');
INSERT INTO modifiergroup_modifiers VALUES ('3345', '6769', '1734');
INSERT INTO modifiergroup_modifiers VALUES ('3346', '6770', '1735');
INSERT INTO modifiergroup_modifiers VALUES ('3346', '6771', '1736');
INSERT INTO modifiergroup_modifiers VALUES ('3346', '6772', '1737');
INSERT INTO modifiergroup_modifiers VALUES ('3346', '6773', '1738');
INSERT INTO modifiergroup_modifiers VALUES ('3347', '6774', '1739');
INSERT INTO modifiergroup_modifiers VALUES ('3347', '6775', '1740');
INSERT INTO modifiergroup_modifiers VALUES ('3347', '6776', '1741');
INSERT INTO modifiergroup_modifiers VALUES ('3347', '6777', '1742');
INSERT INTO modifiergroup_modifiers VALUES ('3347', '6778', '1743');
INSERT INTO modifiergroup_modifiers VALUES ('3347', '6779', '1744');
INSERT INTO modifiergroup_modifiers VALUES ('3347', '6780', '1745');
INSERT INTO modifiergroup_modifiers VALUES ('3347', '6781', '1746');
INSERT INTO modifiergroup_modifiers VALUES ('3347', '6782', '1747');
INSERT INTO modifiergroup_modifiers VALUES ('3348', '6783', '1748');
INSERT INTO modifiergroup_modifiers VALUES ('3348', '6784', '1749');
INSERT INTO modifiergroup_modifiers VALUES ('3348', '6785', '1750');
INSERT INTO modifiergroup_modifiers VALUES ('3348', '6786', '1751');
INSERT INTO modifiergroup_modifiers VALUES ('3348', '6787', '1752');
INSERT INTO modifiergroup_modifiers VALUES ('3348', '6788', '1753');
INSERT INTO modifiergroup_modifiers VALUES ('3348', '6789', '1754');
INSERT INTO modifiergroup_modifiers VALUES ('3348', '6790', '1755');
INSERT INTO modifiergroup_modifiers VALUES ('3348', '6791', '1756');
INSERT INTO modifiergroup_modifiers VALUES ('3349', '6792', '1757');
INSERT INTO modifiergroup_modifiers VALUES ('3349', '6793', '1758');
INSERT INTO modifiergroup_modifiers VALUES ('3349', '6794', '1759');
INSERT INTO modifiergroup_modifiers VALUES ('3349', '6795', '1760');
INSERT INTO modifiergroup_modifiers VALUES ('3349', '6796', '1761');
INSERT INTO modifiergroup_modifiers VALUES ('3349', '6797', '1762');
INSERT INTO modifiergroup_modifiers VALUES ('3349', '6798', '1763');
INSERT INTO modifiergroup_modifiers VALUES ('3349', '6799', '1764');
INSERT INTO modifiergroup_modifiers VALUES ('3349', '6800', '1765');
INSERT INTO modifiergroup_modifiers VALUES ('3350', '6801', '1766');
INSERT INTO modifiergroup_modifiers VALUES ('3350', '6802', '1767');
INSERT INTO modifiergroup_modifiers VALUES ('3350', '6803', '1768');
INSERT INTO modifiergroup_modifiers VALUES ('3350', '6804', '1769');
INSERT INTO modifiergroup_modifiers VALUES ('3350', '6805', '1770');
INSERT INTO modifiergroup_modifiers VALUES ('3350', '6806', '1771');
INSERT INTO modifiergroup_modifiers VALUES ('3350', '6807', '1772');
INSERT INTO modifiergroup_modifiers VALUES ('3351', '6808', '1773');
INSERT INTO modifiergroup_modifiers VALUES ('3351', '6809', '1774');
INSERT INTO modifiergroup_modifiers VALUES ('3351', '6810', '1775');
INSERT INTO modifiergroup_modifiers VALUES ('3351', '6811', '1776');
INSERT INTO modifiergroup_modifiers VALUES ('3351', '6812', '1777');
INSERT INTO modifiergroup_modifiers VALUES ('3351', '6813', '1778');
INSERT INTO modifiergroup_modifiers VALUES ('3351', '6814', '1779');
INSERT INTO modifiergroup_modifiers VALUES ('3351', '6815', '1780');
INSERT INTO modifiergroup_modifiers VALUES ('3351', '6816', '1781');
INSERT INTO modifiergroup_modifiers VALUES ('3351', '6817', '1782');
INSERT INTO modifiergroup_modifiers VALUES ('3351', '6818', '1783');
INSERT INTO modifiergroup_modifiers VALUES ('3351', '6819', '1784');
INSERT INTO modifiergroup_modifiers VALUES ('3351', '6820', '1785');
INSERT INTO modifiergroup_modifiers VALUES ('3352', '6821', '1786');
INSERT INTO modifiergroup_modifiers VALUES ('3352', '6822', '1787');
INSERT INTO modifiergroup_modifiers VALUES ('3352', '6823', '1788');
INSERT INTO modifiergroup_modifiers VALUES ('3352', '6824', '1789');
INSERT INTO modifiergroup_modifiers VALUES ('3352', '6825', '1790');
INSERT INTO modifiergroup_modifiers VALUES ('3352', '6826', '1791');
INSERT INTO modifiergroup_modifiers VALUES ('3352', '6827', '1792');
INSERT INTO modifiergroup_modifiers VALUES ('3352', '6828', '1793');
INSERT INTO modifiergroup_modifiers VALUES ('3352', '6829', '1794');
INSERT INTO modifiergroup_modifiers VALUES ('3353', '6830', '1795');
INSERT INTO modifiergroup_modifiers VALUES ('3353', '6831', '1796');
INSERT INTO modifiergroup_modifiers VALUES ('3353', '6832', '1797');
INSERT INTO modifiergroup_modifiers VALUES ('3353', '6833', '1798');
INSERT INTO modifiergroup_modifiers VALUES ('3354', '6834', '1799');
INSERT INTO modifiergroup_modifiers VALUES ('3354', '6835', '1800');
INSERT INTO modifiergroup_modifiers VALUES ('3354', '6836', '1801');
INSERT INTO modifiergroup_modifiers VALUES ('3354', '6837', '1802');
INSERT INTO modifiergroup_modifiers VALUES ('3354', '6838', '1803');
INSERT INTO modifiergroup_modifiers VALUES ('3355', '6839', '1804');
INSERT INTO modifiergroup_modifiers VALUES ('3355', '6840', '1805');
INSERT INTO modifiergroup_modifiers VALUES ('3355', '6841', '1806');
INSERT INTO modifiergroup_modifiers VALUES ('3355', '6842', '1807');
INSERT INTO modifiergroup_modifiers VALUES ('3355', '6843', '1808');
INSERT INTO modifiergroup_modifiers VALUES ('3356', '6844', '1809');
INSERT INTO modifiergroup_modifiers VALUES ('3356', '6845', '1810');
INSERT INTO modifiergroup_modifiers VALUES ('3356', '6846', '1811');
INSERT INTO modifiergroup_modifiers VALUES ('3357', '6847', '1812');
INSERT INTO modifiergroup_modifiers VALUES ('3357', '6848', '1813');
INSERT INTO modifiergroup_modifiers VALUES ('3357', '6849', '1814');
INSERT INTO modifiergroup_modifiers VALUES ('3357', '6850', '1815');
INSERT INTO modifiergroup_modifiers VALUES ('3357', '6851', '1816');
INSERT INTO modifiergroup_modifiers VALUES ('3357', '6852', '1817');
INSERT INTO modifiergroup_modifiers VALUES ('3357', '6853', '1818');
INSERT INTO modifiergroup_modifiers VALUES ('3357', '6854', '1819');
INSERT INTO modifiergroup_modifiers VALUES ('3357', '6855', '1820');
INSERT INTO modifiergroup_modifiers VALUES ('3358', '6856', '1821');
INSERT INTO modifiergroup_modifiers VALUES ('3358', '6857', '1822');
INSERT INTO modifiergroup_modifiers VALUES ('3358', '6858', '1823');
INSERT INTO modifiergroup_modifiers VALUES ('3358', '6859', '1824');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6860', '1825');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6861', '1826');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6862', '1827');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6863', '1828');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6864', '1829');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6865', '1830');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6866', '1831');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6867', '1832');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6868', '1833');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6869', '1834');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6870', '1835');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6871', '1836');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6872', '1837');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6873', '1838');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6874', '1839');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6875', '1840');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6876', '1841');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6877', '1842');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6878', '1843');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6879', '1844');
INSERT INTO modifiergroup_modifiers VALUES ('3359', '6880', '1845');
INSERT INTO modifiergroup_modifiers VALUES ('3361', '6881', '1846');
INSERT INTO modifiergroup_modifiers VALUES ('3361', '6882', '1847');
INSERT INTO modifiergroup_modifiers VALUES ('3361', '6883', '1848');
INSERT INTO modifiergroup_modifiers VALUES ('3362', '6884', '1849');
INSERT INTO modifiergroup_modifiers VALUES ('3362', '6885', '1850');
INSERT INTO modifiergroup_modifiers VALUES ('3362', '6886', '1851');
INSERT INTO modifiergroup_modifiers VALUES ('3362', '6887', '1852');
INSERT INTO modifiergroup_modifiers VALUES ('3362', '6888', '1853');
INSERT INTO modifiergroup_modifiers VALUES ('3363', '6889', '1854');
INSERT INTO modifiergroup_modifiers VALUES ('3363', '6890', '1855');
INSERT INTO modifiergroup_modifiers VALUES ('3363', '6891', '1856');
INSERT INTO modifiergroup_modifiers VALUES ('3363', '6892', '1857');
INSERT INTO modifiergroup_modifiers VALUES ('3363', '6893', '1858');
INSERT INTO modifiergroup_modifiers VALUES ('3363', '6894', '1859');
INSERT INTO modifiergroup_modifiers VALUES ('3363', '6895', '1860');
INSERT INTO modifiergroup_modifiers VALUES ('3364', '6896', '1861');
INSERT INTO modifiergroup_modifiers VALUES ('3364', '6897', '1862');
INSERT INTO modifiergroup_modifiers VALUES ('3365', '6898', '1863');
INSERT INTO modifiergroup_modifiers VALUES ('3365', '6899', '1864');
INSERT INTO modifiergroup_modifiers VALUES ('3365', '6900', '1865');
INSERT INTO modifiergroup_modifiers VALUES ('3365', '6901', '1866');
INSERT INTO modifiergroup_modifiers VALUES ('3365', '6902', '1867');
INSERT INTO modifiergroup_modifiers VALUES ('3366', '6903', '1868');
INSERT INTO modifiergroup_modifiers VALUES ('3366', '6904', '1869');
INSERT INTO modifiergroup_modifiers VALUES ('3366', '6905', '1870');
INSERT INTO modifiergroup_modifiers VALUES ('3366', '6906', '1871');
INSERT INTO modifiergroup_modifiers VALUES ('3367', '6907', '1872');
INSERT INTO modifiergroup_modifiers VALUES ('3367', '6908', '1873');
INSERT INTO modifiergroup_modifiers VALUES ('3367', '6909', '1874');
INSERT INTO modifiergroup_modifiers VALUES ('3367', '6910', '1875');
INSERT INTO modifiergroup_modifiers VALUES ('3367', '6911', '1876');
INSERT INTO modifiergroup_modifiers VALUES ('3367', '6912', '1877');
INSERT INTO modifiergroup_modifiers VALUES ('3367', '6913', '1878');
INSERT INTO modifiergroup_modifiers VALUES ('3367', '6914', '1879');
INSERT INTO modifiergroup_modifiers VALUES ('3367', '6915', '1880');
INSERT INTO modifiergroup_modifiers VALUES ('3368', '6916', '1881');
INSERT INTO modifiergroup_modifiers VALUES ('3368', '6917', '1882');
INSERT INTO modifiergroup_modifiers VALUES ('3368', '6918', '1883');
INSERT INTO modifiergroup_modifiers VALUES ('3368', '6919', '1884');
INSERT INTO modifiergroup_modifiers VALUES ('3368', '6920', '1885');
INSERT INTO modifiergroup_modifiers VALUES ('3368', '6921', '1886');
INSERT INTO modifiergroup_modifiers VALUES ('3368', '6922', '1887');
INSERT INTO modifiergroup_modifiers VALUES ('3368', '6923', '1888');
INSERT INTO modifiergroup_modifiers VALUES ('3368', '6924', '1889');
INSERT INTO modifiergroup_modifiers VALUES ('3368', '6925', '1890');
INSERT INTO modifiergroup_modifiers VALUES ('3368', '6926', '1891');
INSERT INTO modifiergroup_modifiers VALUES ('3368', '6927', '1892');
INSERT INTO modifiergroup_modifiers VALUES ('3368', '6928', '1893');
INSERT INTO modifiergroup_modifiers VALUES ('3369', '6929', '1894');
INSERT INTO modifiergroup_modifiers VALUES ('3369', '6930', '1895');
INSERT INTO modifiergroup_modifiers VALUES ('3369', '6931', '1896');
INSERT INTO modifiergroup_modifiers VALUES ('3369', '6932', '1897');
INSERT INTO modifiergroup_modifiers VALUES ('3370', '6933', '1898');
INSERT INTO modifiergroup_modifiers VALUES ('3370', '6934', '1899');
INSERT INTO modifiergroup_modifiers VALUES ('3370', '6935', '1900');
INSERT INTO modifiergroup_modifiers VALUES ('3370', '6936', '1901');
INSERT INTO modifiergroup_modifiers VALUES ('3371', '6937', '1902');
INSERT INTO modifiergroup_modifiers VALUES ('3371', '6938', '1903');
INSERT INTO modifiergroup_modifiers VALUES ('3371', '6939', '1904');
INSERT INTO modifiergroup_modifiers VALUES ('3371', '6940', '1905');
INSERT INTO modifiergroup_modifiers VALUES ('3371', '6941', '1906');
INSERT INTO modifiergroup_modifiers VALUES ('3372', '6942', '1907');
INSERT INTO modifiergroup_modifiers VALUES ('3372', '6943', '1908');
INSERT INTO modifiergroup_modifiers VALUES ('3372', '6944', '1909');
INSERT INTO modifiergroup_modifiers VALUES ('3374', '6945', '1910');
INSERT INTO modifiergroup_modifiers VALUES ('3374', '6946', '1911');
INSERT INTO modifiergroup_modifiers VALUES ('3374', '6947', '1912');
INSERT INTO modifiergroup_modifiers VALUES ('3374', '6948', '1913');
INSERT INTO modifiergroup_modifiers VALUES ('3374', '6949', '1914');
INSERT INTO modifiergroup_modifiers VALUES ('3374', '6950', '1915');
INSERT INTO modifiergroup_modifiers VALUES ('3374', '6951', '1916');
INSERT INTO modifiergroup_modifiers VALUES ('3374', '6952', '1917');
INSERT INTO modifiergroup_modifiers VALUES ('3374', '6953', '1918');
INSERT INTO modifiergroup_modifiers VALUES ('3375', '6954', '1919');
INSERT INTO modifiergroup_modifiers VALUES ('3375', '6955', '1920');
INSERT INTO modifiergroup_modifiers VALUES ('3375', '6956', '1921');
INSERT INTO modifiergroup_modifiers VALUES ('3375', '6957', '1922');
INSERT INTO modifiergroup_modifiers VALUES ('3375', '6958', '1923');
INSERT INTO modifiergroup_modifiers VALUES ('3375', '6959', '1924');
INSERT INTO modifiergroup_modifiers VALUES ('3375', '6960', '1925');
INSERT INTO modifiergroup_modifiers VALUES ('3375', '6961', '1926');
INSERT INTO modifiergroup_modifiers VALUES ('3375', '6962', '1927');
INSERT INTO modifiergroup_modifiers VALUES ('3375', '6963', '1928');
INSERT INTO modifiergroup_modifiers VALUES ('3375', '6964', '1929');
INSERT INTO modifiergroup_modifiers VALUES ('3375', '6965', '1930');
INSERT INTO modifiergroup_modifiers VALUES ('3375', '6966', '1931');
INSERT INTO modifiergroup_modifiers VALUES ('3375', '6967', '1932');
INSERT INTO modifiergroup_modifiers VALUES ('3376', '6968', '1933');
INSERT INTO modifiergroup_modifiers VALUES ('3376', '6969', '1934');
INSERT INTO modifiergroup_modifiers VALUES ('3376', '6970', '1935');
INSERT INTO modifiergroup_modifiers VALUES ('3377', '6971', '1936');
INSERT INTO modifiergroup_modifiers VALUES ('3377', '6972', '1937');
INSERT INTO modifiergroup_modifiers VALUES ('3377', '6973', '1938');
INSERT INTO modifiergroup_modifiers VALUES ('3377', '6974', '1939');
INSERT INTO modifiergroup_modifiers VALUES ('3377', '6975', '1940');
INSERT INTO modifiergroup_modifiers VALUES ('3377', '6976', '1941');
INSERT INTO modifiergroup_modifiers VALUES ('3378', '6977', '1942');
INSERT INTO modifiergroup_modifiers VALUES ('3378', '6978', '1943');
INSERT INTO modifiergroup_modifiers VALUES ('3378', '6979', '1944');
INSERT INTO modifiergroup_modifiers VALUES ('3378', '6980', '1945');
INSERT INTO modifiergroup_modifiers VALUES ('3378', '6981', '1946');
INSERT INTO modifiergroup_modifiers VALUES ('3378', '6982', '1947');
INSERT INTO modifiergroup_modifiers VALUES ('3378', '6983', '1948');
INSERT INTO modifiergroup_modifiers VALUES ('3379', '6984', '1949');
INSERT INTO modifiergroup_modifiers VALUES ('3379', '6985', '1950');
INSERT INTO modifiergroup_modifiers VALUES ('3379', '6986', '1951');
INSERT INTO modifiergroup_modifiers VALUES ('3379', '6987', '1952');
INSERT INTO modifiergroup_modifiers VALUES ('3379', '6988', '1953');
INSERT INTO modifiergroup_modifiers VALUES ('3379', '6989', '1954');
INSERT INTO modifiergroup_modifiers VALUES ('3379', '6990', '1955');
INSERT INTO modifiergroup_modifiers VALUES ('3380', '6991', '1956');
INSERT INTO modifiergroup_modifiers VALUES ('3380', '6992', '1957');
INSERT INTO modifiergroup_modifiers VALUES ('3380', '6993', '1958');
INSERT INTO modifiergroup_modifiers VALUES ('3380', '6994', '1959');
INSERT INTO modifiergroup_modifiers VALUES ('3382', '6995', '1960');
INSERT INTO modifiergroup_modifiers VALUES ('3382', '6996', '1961');
INSERT INTO modifiergroup_modifiers VALUES ('3382', '6997', '1962');
INSERT INTO modifiergroup_modifiers VALUES ('3382', '6998', '1963');
INSERT INTO modifiergroup_modifiers VALUES ('3382', '6999', '1964');
INSERT INTO modifiergroup_modifiers VALUES ('3382', '7000', '1965');
INSERT INTO modifiergroup_modifiers VALUES ('3382', '7001', '1966');
INSERT INTO modifiergroup_modifiers VALUES ('3383', '7002', '1967');
INSERT INTO modifiergroup_modifiers VALUES ('3383', '7003', '1968');
INSERT INTO modifiergroup_modifiers VALUES ('3383', '7004', '1969');
INSERT INTO modifiergroup_modifiers VALUES ('3383', '7005', '1970');
INSERT INTO modifiergroup_modifiers VALUES ('3383', '7006', '1971');
INSERT INTO modifiergroup_modifiers VALUES ('3384', '7007', '1972');
INSERT INTO modifiergroup_modifiers VALUES ('3384', '7008', '1973');
INSERT INTO modifiergroup_modifiers VALUES ('3384', '7009', '1974');
INSERT INTO modifiergroup_modifiers VALUES ('3384', '7010', '1975');
INSERT INTO modifiergroup_modifiers VALUES ('3384', '7011', '1976');
INSERT INTO modifiergroup_modifiers VALUES ('3384', '7012', '1977');
INSERT INTO modifiergroup_modifiers VALUES ('3384', '7013', '1978');
INSERT INTO modifiergroup_modifiers VALUES ('3384', '7014', '1979');
INSERT INTO modifiergroup_modifiers VALUES ('3384', '7015', '1980');
INSERT INTO modifiergroup_modifiers VALUES ('3385', '7016', '1981');
INSERT INTO modifiergroup_modifiers VALUES ('3385', '7017', '1982');
INSERT INTO modifiergroup_modifiers VALUES ('3385', '7018', '1983');
INSERT INTO modifiergroup_modifiers VALUES ('3386', '7019', '1984');
INSERT INTO modifiergroup_modifiers VALUES ('3386', '7020', '1985');
INSERT INTO modifiergroup_modifiers VALUES ('3386', '7021', '1986');
INSERT INTO modifiergroup_modifiers VALUES ('3386', '7022', '1987');
INSERT INTO modifiergroup_modifiers VALUES ('3386', '7023', '1988');
INSERT INTO modifiergroup_modifiers VALUES ('3386', '7024', '1989');
INSERT INTO modifiergroup_modifiers VALUES ('3387', '7025', '1990');
INSERT INTO modifiergroup_modifiers VALUES ('3387', '7026', '1991');
INSERT INTO modifiergroup_modifiers VALUES ('3387', '7027', '1992');
INSERT INTO modifiergroup_modifiers VALUES ('3387', '7028', '1993');
INSERT INTO modifiergroup_modifiers VALUES ('3387', '7029', '1994');
INSERT INTO modifiergroup_modifiers VALUES ('3387', '7030', '1995');
INSERT INTO modifiergroup_modifiers VALUES ('3387', '7031', '1996');
INSERT INTO modifiergroup_modifiers VALUES ('3387', '7032', '1997');
INSERT INTO modifiergroup_modifiers VALUES ('3387', '7033', '1998');
INSERT INTO modifiergroup_modifiers VALUES ('3387', '7034', '1999');
INSERT INTO modifiergroup_modifiers VALUES ('3387', '7035', '2000');
INSERT INTO modifiergroup_modifiers VALUES ('3387', '7036', '2001');
INSERT INTO modifiergroup_modifiers VALUES ('3387', '7037', '2002');
INSERT INTO modifiergroup_modifiers VALUES ('3388', '7038', '2003');
INSERT INTO modifiergroup_modifiers VALUES ('3388', '7039', '2004');
INSERT INTO modifiergroup_modifiers VALUES ('3388', '7040', '2005');
INSERT INTO modifiergroup_modifiers VALUES ('3388', '7041', '2006');
INSERT INTO modifiergroup_modifiers VALUES ('3388', '7042', '2007');
INSERT INTO modifiergroup_modifiers VALUES ('3389', '7043', '2008');
INSERT INTO modifiergroup_modifiers VALUES ('3389', '7044', '2009');
INSERT INTO modifiergroup_modifiers VALUES ('3389', '7045', '2010');
INSERT INTO modifiergroup_modifiers VALUES ('3389', '7046', '2011');
INSERT INTO modifiergroup_modifiers VALUES ('3389', '7047', '2012');
INSERT INTO modifiergroup_modifiers VALUES ('3389', '7048', '2013');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7049', '2014');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7050', '2015');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7051', '2016');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7052', '2017');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7053', '2018');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7054', '2019');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7055', '2020');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7056', '2021');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7057', '2022');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7058', '2023');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7059', '2024');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7060', '2025');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7061', '2026');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7062', '2027');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7063', '2028');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7064', '2029');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7065', '2030');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7066', '2031');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7067', '2032');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7068', '2033');
INSERT INTO modifiergroup_modifiers VALUES ('3390', '7069', '2034');
INSERT INTO modifiergroup_modifiers VALUES ('3392', '7070', '2035');
INSERT INTO modifiergroup_modifiers VALUES ('3392', '7071', '2036');
INSERT INTO modifiergroup_modifiers VALUES ('3392', '7072', '2037');
INSERT INTO modifiergroup_modifiers VALUES ('3392', '7073', '2038');
INSERT INTO modifiergroup_modifiers VALUES ('3393', '7074', '2039');
INSERT INTO modifiergroup_modifiers VALUES ('3393', '7075', '2040');
INSERT INTO modifiergroup_modifiers VALUES ('3393', '7076', '2041');
INSERT INTO modifiergroup_modifiers VALUES ('3393', '7077', '2042');
INSERT INTO modifiergroup_modifiers VALUES ('3393', '7078', '2043');
INSERT INTO modifiergroup_modifiers VALUES ('3393', '7079', '2044');
INSERT INTO modifiergroup_modifiers VALUES ('3393', '7080', '2045');
INSERT INTO modifiergroup_modifiers VALUES ('3393', '7081', '2046');
INSERT INTO modifiergroup_modifiers VALUES ('3393', '7082', '2047');
INSERT INTO modifiergroup_modifiers VALUES ('3394', '7083', '2048');
INSERT INTO modifiergroup_modifiers VALUES ('3394', '7084', '2049');
INSERT INTO modifiergroup_modifiers VALUES ('3394', '7085', '2050');
INSERT INTO modifiergroup_modifiers VALUES ('3394', '7086', '2051');
INSERT INTO modifiergroup_modifiers VALUES ('3394', '7087', '2052');
INSERT INTO modifiergroup_modifiers VALUES ('3394', '7088', '2053');
INSERT INTO modifiergroup_modifiers VALUES ('3394', '7089', '2054');
INSERT INTO modifiergroup_modifiers VALUES ('3394', '7090', '2055');
INSERT INTO modifiergroup_modifiers VALUES ('3394', '7091', '2056');
INSERT INTO modifiergroup_modifiers VALUES ('3395', '7092', '2057');
INSERT INTO modifiergroup_modifiers VALUES ('3395', '7093', '2058');
INSERT INTO modifiergroup_modifiers VALUES ('3395', '7094', '2059');
INSERT INTO modifiergroup_modifiers VALUES ('3396', '7095', '2060');
INSERT INTO modifiergroup_modifiers VALUES ('3396', '7096', '2061');
INSERT INTO modifiergroup_modifiers VALUES ('3396', '7097', '2062');
INSERT INTO modifiergroup_modifiers VALUES ('3396', '7098', '2063');
INSERT INTO modifiergroup_modifiers VALUES ('3396', '7099', '2064');
INSERT INTO modifiergroup_modifiers VALUES ('3396', '7100', '2065');
INSERT INTO modifiergroup_modifiers VALUES ('3397', '7101', '2066');
INSERT INTO modifiergroup_modifiers VALUES ('3397', '7102', '2067');
INSERT INTO modifiergroup_modifiers VALUES ('3397', '7103', '2068');
INSERT INTO modifiergroup_modifiers VALUES ('3397', '7104', '2069');
INSERT INTO modifiergroup_modifiers VALUES ('3397', '7105', '2070');
INSERT INTO modifiergroup_modifiers VALUES ('3398', '7106', '2071');
INSERT INTO modifiergroup_modifiers VALUES ('3398', '7107', '2072');
INSERT INTO modifiergroup_modifiers VALUES ('3398', '7108', '2073');
INSERT INTO modifiergroup_modifiers VALUES ('3398', '7109', '2074');
INSERT INTO modifiergroup_modifiers VALUES ('3398', '7110', '2075');
INSERT INTO modifiergroup_modifiers VALUES ('3398', '7111', '2076');
INSERT INTO modifiergroup_modifiers VALUES ('3398', '7112', '2077');
INSERT INTO modifiergroup_modifiers VALUES ('3398', '7113', '2078');
INSERT INTO modifiergroup_modifiers VALUES ('3398', '7114', '2079');
INSERT INTO modifiergroup_modifiers VALUES ('3399', '7115', '2080');
INSERT INTO modifiergroup_modifiers VALUES ('3399', '7116', '2081');
INSERT INTO modifiergroup_modifiers VALUES ('3399', '7117', '2082');
INSERT INTO modifiergroup_modifiers VALUES ('3399', '7118', '2083');
INSERT INTO modifiergroup_modifiers VALUES ('3399', '7119', '2084');
INSERT INTO modifiergroup_modifiers VALUES ('3399', '7120', '2085');
INSERT INTO modifiergroup_modifiers VALUES ('3399', '7121', '2086');
INSERT INTO modifiergroup_modifiers VALUES ('3399', '7122', '2087');
INSERT INTO modifiergroup_modifiers VALUES ('3399', '7123', '2088');
INSERT INTO modifiergroup_modifiers VALUES ('3399', '7124', '2089');
INSERT INTO modifiergroup_modifiers VALUES ('3399', '7125', '2090');
INSERT INTO modifiergroup_modifiers VALUES ('3400', '7126', '2091');
INSERT INTO modifiergroup_modifiers VALUES ('3400', '7127', '2092');
INSERT INTO modifiergroup_modifiers VALUES ('3400', '7128', '2093');
INSERT INTO modifiergroup_modifiers VALUES ('3400', '7129', '2094');
INSERT INTO modifiergroup_modifiers VALUES ('3400', '7130', '2095');
INSERT INTO modifiergroup_modifiers VALUES ('3400', '7131', '2096');
INSERT INTO modifiergroup_modifiers VALUES ('3400', '7132', '2097');
INSERT INTO modifiergroup_modifiers VALUES ('3400', '7133', '2098');
INSERT INTO modifiergroup_modifiers VALUES ('3400', '7134', '2099');
INSERT INTO modifiergroup_modifiers VALUES ('3400', '7135', '2100');
INSERT INTO modifiergroup_modifiers VALUES ('3400', '7136', '2101');
INSERT INTO modifiergroup_modifiers VALUES ('3400', '7137', '2102');
INSERT INTO modifiergroup_modifiers VALUES ('3400', '7138', '2103');
INSERT INTO modifiergroup_modifiers VALUES ('3401', '7139', '2104');
INSERT INTO modifiergroup_modifiers VALUES ('3402', '7140', '2105');
INSERT INTO modifiergroup_modifiers VALUES ('3402', '7141', '2106');
INSERT INTO modifiergroup_modifiers VALUES ('3402', '7142', '2107');
INSERT INTO modifiergroup_modifiers VALUES ('3402', '7143', '2108');
INSERT INTO modifiergroup_modifiers VALUES ('3402', '7144', '2109');
INSERT INTO modifiergroup_modifiers VALUES ('3402', '7145', '2110');
INSERT INTO modifiergroup_modifiers VALUES ('3403', '7146', '2111');
INSERT INTO modifiergroup_modifiers VALUES ('3403', '7147', '2112');
INSERT INTO modifiergroup_modifiers VALUES ('3403', '7148', '2113');
INSERT INTO modifiergroup_modifiers VALUES ('3403', '7149', '2114');
INSERT INTO modifiergroup_modifiers VALUES ('3403', '7150', '2115');
INSERT INTO modifiergroup_modifiers VALUES ('3403', '7151', '2116');
INSERT INTO modifiergroup_modifiers VALUES ('3403', '7152', '2117');
INSERT INTO modifiergroup_modifiers VALUES ('3403', '7153', '2118');
INSERT INTO modifiergroup_modifiers VALUES ('3403', '7154', '2119');
INSERT INTO modifiergroup_modifiers VALUES ('3404', '7155', '2120');
INSERT INTO modifiergroup_modifiers VALUES ('3404', '7156', '2121');
INSERT INTO modifiergroup_modifiers VALUES ('3404', '7157', '2122');
INSERT INTO modifiergroup_modifiers VALUES ('3404', '7158', '2123');
INSERT INTO modifiergroup_modifiers VALUES ('3404', '7159', '2124');
INSERT INTO modifiergroup_modifiers VALUES ('3404', '7160', '2125');
INSERT INTO modifiergroup_modifiers VALUES ('3404', '7161', '2126');
INSERT INTO modifiergroup_modifiers VALUES ('3404', '7162', '2127');
INSERT INTO modifiergroup_modifiers VALUES ('3404', '7163', '2128');
INSERT INTO modifiergroup_modifiers VALUES ('3404', '7164', '2129');
INSERT INTO modifiergroup_modifiers VALUES ('3404', '7165', '2130');
INSERT INTO modifiergroup_modifiers VALUES ('3405', '7166', '2131');
INSERT INTO modifiergroup_modifiers VALUES ('3405', '7167', '2132');
INSERT INTO modifiergroup_modifiers VALUES ('3405', '7168', '2133');
INSERT INTO modifiergroup_modifiers VALUES ('3405', '7169', '2134');
INSERT INTO modifiergroup_modifiers VALUES ('3405', '7170', '2135');
INSERT INTO modifiergroup_modifiers VALUES ('3405', '7171', '2136');
INSERT INTO modifiergroup_modifiers VALUES ('3405', '7172', '2137');
INSERT INTO modifiergroup_modifiers VALUES ('3406', '7173', '2138');
INSERT INTO modifiergroup_modifiers VALUES ('3406', '7174', '2139');
INSERT INTO modifiergroup_modifiers VALUES ('3406', '7175', '2140');
INSERT INTO modifiergroup_modifiers VALUES ('3406', '7176', '2141');
INSERT INTO modifiergroup_modifiers VALUES ('3406', '7177', '2142');
INSERT INTO modifiergroup_modifiers VALUES ('3407', '7178', '2143');
INSERT INTO modifiergroup_modifiers VALUES ('3407', '7179', '2144');
INSERT INTO modifiergroup_modifiers VALUES ('3407', '7180', '2145');
INSERT INTO modifiergroup_modifiers VALUES ('3407', '7181', '2146');

-- ----------------------------
-- Table structure for `modifiers`
-- ----------------------------
DROP TABLE IF EXISTS `modifiers`;
CREATE TABLE `modifiers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `price` float DEFAULT NULL,
  `pos_modifier_id` varchar(20) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `merchant_idfk_1` (`merchant_id`),
  CONSTRAINT `merchant_idfk_1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7182 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of modifiers
-- ----------------------------
INSERT INTO modifiers VALUES ('6626', 'Extra Chees', '50', 'P85RT3T0TAQJC', '16');
INSERT INTO modifiers VALUES ('6627', 'Meat- Bistec', '0', 'QBVTZMP5GYHMP', '16');
INSERT INTO modifiers VALUES ('6628', 'Meat- Carnitas', '0', 'A90TT1HKRRT0W', '16');
INSERT INTO modifiers VALUES ('6629', 'Meat- Steak', '0', 'ESPAD63AD2GFC', '16');
INSERT INTO modifiers VALUES ('6630', 'No cilantor', '0', 'XPHC7Y0N1V1R2', '16');
INSERT INTO modifiers VALUES ('6631', 'No onion', '0', 'N0Q5GPZCD4VZA', '16');
INSERT INTO modifiers VALUES ('6632', 'Meat- Pastor', '0', 'YCG30NKNTGG3J', '16');
INSERT INTO modifiers VALUES ('6633', 'Meat- Chicken', '0', '2BHTNRC6XPJ4J', '16');
INSERT INTO modifiers VALUES ('6634', 'Meat- Lengua', '0', 'GNCCR5AM1E0KC', '16');
INSERT INTO modifiers VALUES ('6635', 'Meat- Barbacoa', '0', '3JCHKJXXFTP7M', '16');
INSERT INTO modifiers VALUES ('6636', 'Meat- Lengua', '0', 'SABDV4PYD28W4', '16');
INSERT INTO modifiers VALUES ('6637', 'Meat- Barbacoa', '0', '9VG3RW6GJZ8S2', '16');
INSERT INTO modifiers VALUES ('6638', 'Meat- Carnitas', '0', '4MQJ1X0JXVG3C', '16');
INSERT INTO modifiers VALUES ('6639', 'No cilantor', '0', '0E0T8E3CQC6XP', '16');
INSERT INTO modifiers VALUES ('6640', 'Meat- Chicken', '0', 'RZS7HFB253FPT', '16');
INSERT INTO modifiers VALUES ('6641', 'Extra cheese', '0', 'KGP9T4M2Q1HG2', '16');
INSERT INTO modifiers VALUES ('6642', 'Extra grilled onion', '0', 'AA4FPNY46D55W', '16');
INSERT INTO modifiers VALUES ('6643', 'Meat- Bistec', '0', 'CVC5N2ZAZEMRY', '16');
INSERT INTO modifiers VALUES ('6644', 'Meat- Steak', '0', 'S5BKWXG10K5RR', '16');
INSERT INTO modifiers VALUES ('6645', 'Meat- Pastor', '0', '3EGQMRDXBMGN2', '16');
INSERT INTO modifiers VALUES ('6646', 'No onion', '0', '760FYW90Q1S3C', '16');
INSERT INTO modifiers VALUES ('6647', 'Add Ham', '0', 'EBCACZZZQ8M10', '16');
INSERT INTO modifiers VALUES ('6648', 'Add Chorizo', '0', 'ZM9H6MB2FWK6E', '16');
INSERT INTO modifiers VALUES ('6649', 'Add Sausage', '0', '10DEY5TCK4YFE', '16');
INSERT INTO modifiers VALUES ('6650', 'Add Potato', '0', 'A1GVEHXQVFSP6', '16');
INSERT INTO modifiers VALUES ('6651', 'Add Steak', '0', 'KAH00643HF024', '16');
INSERT INTO modifiers VALUES ('6652', 'Add Bacon', '0', '2NTFCY900G2H6', '16');
INSERT INTO modifiers VALUES ('6653', 'No tomato', '0', 'KRKAS26N7XFS6', '16');
INSERT INTO modifiers VALUES ('6654', 'Meat- Steak', '0', 'TF7SSYG2G7KK0', '16');
INSERT INTO modifiers VALUES ('6655', 'No lettuce', '0', 'KC2CXMDBM07TY', '16');
INSERT INTO modifiers VALUES ('6656', 'No cheese', '0', '2DH4VJ0B2W4KM', '16');
INSERT INTO modifiers VALUES ('6657', 'Meat- Chicken', '0', 'XA5EAD825MG9A', '16');
INSERT INTO modifiers VALUES ('6658', 'No gucamole', '0', '4GDN2QNRR2J4A', '16');
INSERT INTO modifiers VALUES ('6659', 'No sour cream', '0', 'MXC5N74H7VRXY', '16');
INSERT INTO modifiers VALUES ('6660', 'No onion', '0', 'CQZF8VMERPJ6E', '16');
INSERT INTO modifiers VALUES ('6661', 'No  cilantor', '0', '3XFKMHSE35KXC', '16');
INSERT INTO modifiers VALUES ('6662', 'Extra grilled onion', '0', 'ZVC18W4EMTMTT', '16');
INSERT INTO modifiers VALUES ('6663', 'Extra avacado', '0', 'E4R2XVD4SJJX2', '16');
INSERT INTO modifiers VALUES ('6664', 'Extra jack cheese', '0', '12ZEMN5T58934', '16');
INSERT INTO modifiers VALUES ('6665', 'No lettuce', '0', 'S0P7M6PY3QHNA', '16');
INSERT INTO modifiers VALUES ('6666', 'No avacado', '0', '5BSB65R0GYMJR', '16');
INSERT INTO modifiers VALUES ('6667', 'No jack cheese', '0', '76H2M21TTBYV0', '16');
INSERT INTO modifiers VALUES ('6668', 'Extra tomato', '0', 'G68G4RBFBV4QE', '16');
INSERT INTO modifiers VALUES ('6669', 'El Verde', '0', 'KNFFD7SQGZKS0', '16');
INSERT INTO modifiers VALUES ('6670', 'Capitan Fish', '0', 'P01ZYQZ6X5F58', '16');
INSERT INTO modifiers VALUES ('6671', 'Presidente', '0', 'G60E8AZ51RPKG', '16');
INSERT INTO modifiers VALUES ('6672', 'Monterrey', '0', '7TMSG8WSNETCG', '16');
INSERT INTO modifiers VALUES ('6673', 'Fajita Steak', '0', 'CH563Y92WFZH4', '16');
INSERT INTO modifiers VALUES ('6674', 'Marinero Shrimp', '0', '0BF39H24M4FKR', '16');
INSERT INTO modifiers VALUES ('6675', 'Pescador Shrimp', '0', 'JS3Z87AE6EGD0', '16');
INSERT INTO modifiers VALUES ('6676', 'Fajita Chicken', '0', 'JCSWW3J8JTV60', '16');
INSERT INTO modifiers VALUES ('6677', 'Chicken Chipotle', '0', 'JMECKHQM6PR1J', '16');
INSERT INTO modifiers VALUES ('6678', 'No lettuce', '0', '6EADCBRSYV3FT', '16');
INSERT INTO modifiers VALUES ('6679', 'Crispy ', '0', 'F721BT992AS0J', '16');
INSERT INTO modifiers VALUES ('6680', 'Meat- Grond Beef', '0', 'WYAXKTG6QQ9GJ', '16');
INSERT INTO modifiers VALUES ('6681', 'No tomato', '0', 'FSHH8YTTQWPAJ', '16');
INSERT INTO modifiers VALUES ('6682', 'No cheese', '0', '7855GVJBG9N0C', '16');
INSERT INTO modifiers VALUES ('6683', 'No Lettuce', '0', '3MDM59K2D9TZ0', '16');
INSERT INTO modifiers VALUES ('6684', 'No Onions', '0', 'H4AB64ZAR6B5Y', '16');
INSERT INTO modifiers VALUES ('6685', 'No Cilantro', '0', 'R5AKYZVGD20VP', '16');
INSERT INTO modifiers VALUES ('6686', 'No Beans', '0', 'S3XNXDV78T7EA', '16');
INSERT INTO modifiers VALUES ('6687', 'No Rice', '0', 'G5JE1XDH2422G', '16');
INSERT INTO modifiers VALUES ('6688', 'No Cheese', '0', 'BSCARBPPB5VYG', '16');
INSERT INTO modifiers VALUES ('6689', 'No Pico', '0', 'WQ2EA6GYQ46KR', '16');
INSERT INTO modifiers VALUES ('6690', 'No Mayo', '0', 'QHD0152TDQD0Y', '16');
INSERT INTO modifiers VALUES ('6691', 'No Tomato', '0', 'EEF3VE44WZENA', '16');
INSERT INTO modifiers VALUES ('6692', 'No Avocado', '0', 'VN1A2FNP598X4', '16');
INSERT INTO modifiers VALUES ('6693', 'No Cheddar Cheese', '0', 'QX64BSFHQSPMM', '16');
INSERT INTO modifiers VALUES ('6694', 'Bean & Cheewse Burrito', '0', 'ATDG6B1J4GXQA', '16');
INSERT INTO modifiers VALUES ('6695', 'No salsa', '0', 'XXBR33XZRERTW', '16');
INSERT INTO modifiers VALUES ('6696', 'Quesadilla', '0', '66S22F45A8WMM', '16');
INSERT INTO modifiers VALUES ('6697', 'Soft taco', '0', 'ZW3FDGQF6C7GA', '16');
INSERT INTO modifiers VALUES ('6698', 'No guacamole', '0', '8DS8CTH7FA5HA', '16');
INSERT INTO modifiers VALUES ('6699', 'Crispy taco', '0', '6KMEZE4DYZCC2', '16');
INSERT INTO modifiers VALUES ('6700', 'No cheese', '0', '3946M0BX46YVT', '16');
INSERT INTO modifiers VALUES ('6701', 'Bistec', '0', 'KT39DN6ARVP7E', '16');
INSERT INTO modifiers VALUES ('6702', 'Steak & Egg', '0', '73EG492F1MFDT', '16');
INSERT INTO modifiers VALUES ('6703', 'Bacon & Egg', '0', 'TS29KENMRJ8D0', '16');
INSERT INTO modifiers VALUES ('6704', 'Lengua', '0', 'XSKFN0EWW9KP4', '16');
INSERT INTO modifiers VALUES ('6705', 'Ham & Egg', '0', 'W0JTKBSF3AB7Y', '16');
INSERT INTO modifiers VALUES ('6706', 'Suasage & Egg', '0', 'Y1TPRKDRZY4ZE', '16');
INSERT INTO modifiers VALUES ('6707', 'Potato & Egg', '0', 'V9KSWMZ3FMEKJ', '16');
INSERT INTO modifiers VALUES ('6708', 'Chicken', '0', 'P8WQXHB29PRAG', '16');
INSERT INTO modifiers VALUES ('6709', 'Chorizo & Egg', '0', 'KD001QW0XAAFM', '16');
INSERT INTO modifiers VALUES ('6710', 'Barbacoa', '0', '5DMRQX9KCW8XG', '16');
INSERT INTO modifiers VALUES ('6711', 'Carnitas', '0', 'CRRSPWSNET5EP', '16');
INSERT INTO modifiers VALUES ('6712', 'Steak', '0', 'Q0RA8YQJNP63T', '16');
INSERT INTO modifiers VALUES ('6713', 'Pastor', '0', 'MES3QHC5ERAZ0', '16');
INSERT INTO modifiers VALUES ('6714', 'No lettuce', '0', '99YSQBW6WF4J0', '16');
INSERT INTO modifiers VALUES ('6715', 'No gucamole', '0', '0BFGKVP8V9DKE', '16');
INSERT INTO modifiers VALUES ('6716', 'No tomato', '0', 'C9M3RBP2Z4K1E', '16');
INSERT INTO modifiers VALUES ('6717', 'No jack cheese', '0', 'QM4HKPRZ1BSDE', '16');
INSERT INTO modifiers VALUES ('6718', 'Meat- Chicken', '0', '7EQ6SSKVFG9NA', '16');
INSERT INTO modifiers VALUES ('6719', 'No lettuce', '0', 'E5P1PFK5JZVXP', '16');
INSERT INTO modifiers VALUES ('6720', 'No jack cheese', '0', 'XWQ4D58VWAH2T', '16');
INSERT INTO modifiers VALUES ('6721', 'No avacado', '0', '9DYGTVB59KC7J', '16');
INSERT INTO modifiers VALUES ('6722', 'Sausage & Egg', '0', 'NV8VDB7ZXVX90', '16');
INSERT INTO modifiers VALUES ('6723', 'Bistec', '0', 'D3B5N3097TBB8', '16');
INSERT INTO modifiers VALUES ('6724', 'Chorizo & Egg', '0', 'G0Q9FRYDJN0SW', '16');
INSERT INTO modifiers VALUES ('6725', 'Lengua', '0', 'SB0H2JBCK2FXP', '16');
INSERT INTO modifiers VALUES ('6726', 'Bacon & Egg', '0', '4F49K8RB7NPRE', '16');
INSERT INTO modifiers VALUES ('6727', 'Potato & Egg', '0', '3Q087FGPPPN3A', '16');
INSERT INTO modifiers VALUES ('6728', 'Steak', '0', 'SJE69CTTTBNNE', '16');
INSERT INTO modifiers VALUES ('6729', 'Barbacoa', '0', 'VG0GMP55A170Y', '16');
INSERT INTO modifiers VALUES ('6730', 'Chicken', '0', 'WK2BHJGD7ZY1Y', '16');
INSERT INTO modifiers VALUES ('6731', 'Pastor', '0', 'V3Y20V5D474GA', '16');
INSERT INTO modifiers VALUES ('6732', 'Steak & Egg', '0', 'AZD67A3HBME5W', '16');
INSERT INTO modifiers VALUES ('6733', 'Ham & Egg', '0', '6AM3E6D4NRYRR', '16');
INSERT INTO modifiers VALUES ('6734', 'Carnitas', '0', 'SF7506DWPT3JA', '16');
INSERT INTO modifiers VALUES ('6735', 'No mushrooms', '0', 'WPA1E3BTW92KG', '16');
INSERT INTO modifiers VALUES ('6736', 'No bell peppers', '0', 'PK1CSP59E7FMC', '16');
INSERT INTO modifiers VALUES ('6737', 'Extra jack cheese', '0', '9KNP3KE6MJ4Q2', '16');
INSERT INTO modifiers VALUES ('6738', 'No jack cheese', '0', 'ES5Y7GQSF897W', '16');
INSERT INTO modifiers VALUES ('6739', 'Roja Sauce', '0', '24ZYHFSX9HECY', '16');
INSERT INTO modifiers VALUES ('6740', 'Beef', '0', '4WN314CCCM3YJ', '16');
INSERT INTO modifiers VALUES ('6741', 'Verde Sauce', '0', 'V6X0NZ6XW64GM', '16');
INSERT INTO modifiers VALUES ('6742', 'Cheese', '0', '0KRJF9R7NZ6NT', '16');
INSERT INTO modifiers VALUES ('6743', 'Chicken', '0', 'JBHF8B8WWWN0C', '16');
INSERT INTO modifiers VALUES ('6744', 'Meat- Chicken', '0', 'CZZ625SQ1NMDM', '16');
INSERT INTO modifiers VALUES ('6745', 'No lettuce', '0', '1CFZCXCA3Z6PJ', '16');
INSERT INTO modifiers VALUES ('6746', 'No tortillas stips', '0', '1HFBEE9ADS20G', '16');
INSERT INTO modifiers VALUES ('6747', 'No corn', '0', 'DA4PZ07S2695J', '16');
INSERT INTO modifiers VALUES ('6748', 'No black beans', '0', 'V4YG8MHDE1WY2', '16');
INSERT INTO modifiers VALUES ('6749', 'No avacado', '0', 'Y8RMT3QA8W7GA', '16');
INSERT INTO modifiers VALUES ('6750', 'Meat- Barbacoa', '0', '42CYV63F0WQNC', '16');
INSERT INTO modifiers VALUES ('6751', 'Meat- Bistec', '0', 'PMPQ5G2MBVX7Y', '16');
INSERT INTO modifiers VALUES ('6752', 'No gucamole', '0', 'G9BPVZ9PDE68A', '16');
INSERT INTO modifiers VALUES ('6753', 'No sour cream', '0', 'CP92GHK992588', '16');
INSERT INTO modifiers VALUES ('6754', 'Meat- Carnitas', '0', 'MTBW6073AJVA6', '16');
INSERT INTO modifiers VALUES ('6755', 'No jack cheese', '0', '48RQ4N4M2DVXR', '16');
INSERT INTO modifiers VALUES ('6756', 'Meat- Lengua', '0', 'WNX828R5S3X34', '16');
INSERT INTO modifiers VALUES ('6757', 'No lettuce', '0', '5AMTHQ02E56Q8', '16');
INSERT INTO modifiers VALUES ('6758', 'Meat- Chicken', '0', 'TM9WZ0RQ7VCA4', '16');
INSERT INTO modifiers VALUES ('6759', 'Meat- Pastor', '0', 'ZXW6HXWGWND1P', '16');
INSERT INTO modifiers VALUES ('6760', 'Meat- Steak', '0', '0MCYYRNGGV87W', '16');
INSERT INTO modifiers VALUES ('6761', 'No beans', '0', '63PR5WWK767Z4', '16');
INSERT INTO modifiers VALUES ('6762', 'No burrito sauce', '0', '95VDJY68DQE1C', '16');
INSERT INTO modifiers VALUES ('6763', 'No rice', '0', 'W6T322BB7QBCP', '16');
INSERT INTO modifiers VALUES ('6764', 'Roja', '0', 'B2F55BDKNKC9W', '16');
INSERT INTO modifiers VALUES ('6765', 'Chipotle', '0', 'TN49P4AFB1JY6', '16');
INSERT INTO modifiers VALUES ('6766', 'Ranchera', '0', '61QB2T6V2734Y', '16');
INSERT INTO modifiers VALUES ('6767', 'Brava', '0', 'DQRYQKT2S0QHG', '16');
INSERT INTO modifiers VALUES ('6768', 'Verde', '0', 'DRHH3GR6JYT7J', '16');
INSERT INTO modifiers VALUES ('6769', 'Bombero', '0', 'NY6JG5NX4APG4', '16');
INSERT INTO modifiers VALUES ('6770', 'Flour Tortilla', '0', 'KRQ4BGVS1GYWJ', '16');
INSERT INTO modifiers VALUES ('6771', 'Crispy', '0', 'SMKRTE6YQBJQW', '16');
INSERT INTO modifiers VALUES ('6772', 'Corn Tortilla', '0', '54E1VW1FR9KXG', '16');
INSERT INTO modifiers VALUES ('6773', 'Wheat Tortilla', '0', 'VG7G8MQRC7HQY', '16');
INSERT INTO modifiers VALUES ('6774', 'Brisket', '0', 'G3275JWAD34XE', '16');
INSERT INTO modifiers VALUES ('6775', 'Monterrey', '0', 'F6VPBK08BJWYR', '16');
INSERT INTO modifiers VALUES ('6776', 'Capiatn', '0', 'G16JJYWB5E8KC', '16');
INSERT INTO modifiers VALUES ('6777', 'Marinero', '0', 'XF8YJDSZVBGN8', '16');
INSERT INTO modifiers VALUES ('6778', 'Presidente', '0', 'YFKXJBM0MRSCW', '16');
INSERT INTO modifiers VALUES ('6779', 'Fajita Chicken', '0', 'PRVCX100Q2SYE', '16');
INSERT INTO modifiers VALUES ('6780', 'Chicken Chipotle', '0', '7KTYQD0DW8CVY', '16');
INSERT INTO modifiers VALUES ('6781', 'Fajita Steak', '0', 'D0KX2KRQQEAHW', '16');
INSERT INTO modifiers VALUES ('6782', 'Pescador', '0', 'S0S8TX3AR5WY0', '16');
INSERT INTO modifiers VALUES ('6783', 'Monterrey', '0', '2W9NFDWKV3NDA', '16');
INSERT INTO modifiers VALUES ('6784', 'Fajitas Chicken', '0', 'GG87WT2C2RZN0', '16');
INSERT INTO modifiers VALUES ('6785', 'Marinero Shrimp', '0', '4Z9FZ78YXSMN6', '16');
INSERT INTO modifiers VALUES ('6786', 'Presidente', '0', '6P5TW4V8V84WP', '16');
INSERT INTO modifiers VALUES ('6787', 'Chicken Chipotle', '0', 'AZERW644KPE9E', '16');
INSERT INTO modifiers VALUES ('6788', 'Fajitas Steak', '0', '9Y9T7B5XZY7N4', '16');
INSERT INTO modifiers VALUES ('6789', 'Capitan Fish', '0', 'PH45H3BJ88Z3J', '16');
INSERT INTO modifiers VALUES ('6790', 'Pescador Shrimp', '0', 'H5CY89DJPQC52', '16');
INSERT INTO modifiers VALUES ('6791', 'El Verde', '0', 'KAB2H8366MPWJ', '16');
INSERT INTO modifiers VALUES ('6792', 'No cheese', '0', 'XHVBRH9D927Z8', '16');
INSERT INTO modifiers VALUES ('6793', 'Meat- Bistec', '0', '6JRQS6TB19KMA', '16');
INSERT INTO modifiers VALUES ('6794', 'Meat- Chicken', '0', 'X8BSAV4B2Y0MG', '16');
INSERT INTO modifiers VALUES ('6795', 'Meat- Lengua', '0', 'D7AYF10S6TNCA', '16');
INSERT INTO modifiers VALUES ('6796', 'Meat- Barbacoa', '0', 'N6BA9YJYWYGJM', '16');
INSERT INTO modifiers VALUES ('6797', 'No roasted vegetable sauce', '0', 'W5GAYQKDZJZH6', '16');
INSERT INTO modifiers VALUES ('6798', 'Meat- Carnitas', '0', 'H07JR9EXT0P8E', '16');
INSERT INTO modifiers VALUES ('6799', 'Meat- Steak', '0', 'TXQK6ZKTBBA8E', '16');
INSERT INTO modifiers VALUES ('6800', 'Meat- Pastor', '0', 'GRT9QDCB32WF4', '16');
INSERT INTO modifiers VALUES ('6801', 'Meat- Migas', '0', 'YYF4K8WKKJVJR', '16');
INSERT INTO modifiers VALUES ('6802', 'Meat- Bacon', '0', 'DH1GA0NMAZZJR', '16');
INSERT INTO modifiers VALUES ('6803', 'Meat- Chorizo', '0', 'C232CP27XSWWM', '16');
INSERT INTO modifiers VALUES ('6804', 'Meat- Steak', '0', '5T76QRSAPSBH8', '16');
INSERT INTO modifiers VALUES ('6805', 'Meat- Sausage', '0', 'ZC8H39E0JVD8W', '16');
INSERT INTO modifiers VALUES ('6806', 'Meat- Ham', '0', 'ZKXCQCP71ZM68', '16');
INSERT INTO modifiers VALUES ('6807', 'Meat- Potato', '0', 'BHYY22PZEWZ90', '16');
INSERT INTO modifiers VALUES ('6808', 'Chorizo & Egg', '0', 'WGTHY0Y2CCQHR', '16');
INSERT INTO modifiers VALUES ('6809', 'Potato & Egg', '0', 'EE3EMX0TEVXZA', '16');
INSERT INTO modifiers VALUES ('6810', 'Carnitas', '0', 'NJFF9F7JWVGPC', '16');
INSERT INTO modifiers VALUES ('6811', 'Bistec', '0', 'CS5GF2BWTR6R6', '16');
INSERT INTO modifiers VALUES ('6812', 'Ham & Egg', '0', 'MDCDYFBDEBDY4', '16');
INSERT INTO modifiers VALUES ('6813', 'Chicken', '0', '4AE9QHZB2QVNR', '16');
INSERT INTO modifiers VALUES ('6814', 'Steak & Egg', '0', '2X9WT1HVGN8JT', '16');
INSERT INTO modifiers VALUES ('6815', 'Barbacoa', '0', 'NKV9QSZ9EXDFR', '16');
INSERT INTO modifiers VALUES ('6816', 'Steak', '0', 'FW3GYVND9JTNG', '16');
INSERT INTO modifiers VALUES ('6817', 'Lengua', '0', 'GP0RA5WBY54GC', '16');
INSERT INTO modifiers VALUES ('6818', 'Sausage & Egg', '0', 'ESFF911V7SSWP', '16');
INSERT INTO modifiers VALUES ('6819', 'Bacon & Egg', '0', 'EJBN5A53EZBTA', '16');
INSERT INTO modifiers VALUES ('6820', 'Pastor', '0', '7N90PR78F1MA6', '16');
INSERT INTO modifiers VALUES ('6821', 'pescador', '0', 'RPER98AP40X5P', '16');
INSERT INTO modifiers VALUES ('6822', 'st fajita', '0', '434BHDD80T2BA', '16');
INSERT INTO modifiers VALUES ('6823', 'capitan', '0', 'N5KJ83H9G3G4G', '16');
INSERT INTO modifiers VALUES ('6824', 'ck chipotle', '0', 'SA0WTGFZYMNDC', '16');
INSERT INTO modifiers VALUES ('6825', 'marinero', '0', '13NPS1PB2JVJC', '16');
INSERT INTO modifiers VALUES ('6826', 'monterrey', '0', 'W7QXZN7ABARP2', '16');
INSERT INTO modifiers VALUES ('6827', 'presidente', '0', '662YBDCAKW2QE', '16');
INSERT INTO modifiers VALUES ('6828', 'el verde', '0', '6C9YF2E8HN9P2', '16');
INSERT INTO modifiers VALUES ('6829', 'ck fajita', '0', 'N8YDFSG3BZYAM', '16');
INSERT INTO modifiers VALUES ('6830', 'No Ham', '0', 'SSR1WZ8EXNYT4', '16');
INSERT INTO modifiers VALUES ('6831', 'No Milanesa', '0', 'HQPEJ7S0YP97T', '16');
INSERT INTO modifiers VALUES ('6832', 'No Chicken', '0', 'NQWZTKRPRK3VA', '16');
INSERT INTO modifiers VALUES ('6833', 'No Hot dog meat', '0', 'GPQQCQ0642TP2', '16');
INSERT INTO modifiers VALUES ('6834', 'Extra Pico', '0', 'GB7APCVBJ6QZJ', '16');
INSERT INTO modifiers VALUES ('6835', 'Meat', '50', 'GJNC0X1R7X8MJ', '16');
INSERT INTO modifiers VALUES ('6836', 'Extra Cheese', '25', 'Y5EYMWSPV8ET6', '16');
INSERT INTO modifiers VALUES ('6837', 'Extra Grilled Onions', '0', 'JAQ7HTWHHEZDA', '16');
INSERT INTO modifiers VALUES ('6838', 'Extra Cheddar Cheese', '25', '44P3QPB9MJY02', '16');
INSERT INTO modifiers VALUES ('6839', 'Extra tomato', '0', 'GM29TVNW96WD2', '16');
INSERT INTO modifiers VALUES ('6840', 'Extra avacado', '0', '02BD6W09V2RWW', '16');
INSERT INTO modifiers VALUES ('6841', 'No lettuce', '0', '1ZQ2EC6AHFTXY', '16');
INSERT INTO modifiers VALUES ('6842', 'No jack cheese', '0', 'JB23PGK16SEN4', '16');
INSERT INTO modifiers VALUES ('6843', 'Extra jack cheese', '0', 'RY92JYY2VVRFE', '16');
INSERT INTO modifiers VALUES ('6844', 'No cheese', '0', 'B5CNGG80CA6Y2', '16');
INSERT INTO modifiers VALUES ('6845', 'No guacamole', '0', 'Z582FWSDG204P', '16');
INSERT INTO modifiers VALUES ('6846', 'No salsa', '0', '91HJN82BY45HY', '16');
INSERT INTO modifiers VALUES ('6847', 'Steak Milanesa', '0', 'VDQ0YY13JKSB6', '16');
INSERT INTO modifiers VALUES ('6848', 'Bistec', '0', 'FEMEP0MTK56AG', '16');
INSERT INTO modifiers VALUES ('6849', 'Chicken Milanesa', '0', '1C7CFXPWM69MG', '16');
INSERT INTO modifiers VALUES ('6850', 'Steak', '0', 'GWA71208DCFEP', '16');
INSERT INTO modifiers VALUES ('6851', 'Pastor', '0', 'CPW7J3DXZ3AE4', '16');
INSERT INTO modifiers VALUES ('6852', 'Carnitas', '0', 'AG3K957MFS3NY', '16');
INSERT INTO modifiers VALUES ('6853', 'Lengua', '0', '2RTYDX96ZBYX4', '16');
INSERT INTO modifiers VALUES ('6854', 'Chicken', '0', '1G7H10FBQK3BY', '16');
INSERT INTO modifiers VALUES ('6855', 'Barbacoa', '0', 'ECXCSKDTQ3GCR', '16');
INSERT INTO modifiers VALUES ('6856', 'Meat- Steak', '0', 'EKV5KAF2S9K2W', '16');
INSERT INTO modifiers VALUES ('6857', 'No roasted vegetable sauce', '0', 'JXXZ2W6Y1HEGY', '16');
INSERT INTO modifiers VALUES ('6858', 'Meat- Chicken', '0', 'DZP2J2DF9CFK0', '16');
INSERT INTO modifiers VALUES ('6859', 'No cheese', '0', 'CGYWS84HK939G', '16');
INSERT INTO modifiers VALUES ('6860', 'Meat- Bistec', '0', '7HKGPGG8A8GEA', '16');
INSERT INTO modifiers VALUES ('6861', 'Meat- Barbacoa', '0', 'AJCQK1039Q82P', '16');
INSERT INTO modifiers VALUES ('6862', 'No burrito sauce', '0', 'Y4MYTFF3JPQ6E', '16');
INSERT INTO modifiers VALUES ('6863', 'No jack cheese', '0', 'YM4243X42WDMJ', '16');
INSERT INTO modifiers VALUES ('6864', 'No rice', '0', 'R9N09CP2EFNGC', '16');
INSERT INTO modifiers VALUES ('6865', 'Meat- Chicken', '0', '7V0Z1351Q4PAE', '16');
INSERT INTO modifiers VALUES ('6866', 'Meat- Lengua', '0', 'DW8SCGG7WYJ7Y', '16');
INSERT INTO modifiers VALUES ('6867', 'No lettuce', '0', '12BQ210T56TSA', '16');
INSERT INTO modifiers VALUES ('6868', 'No sour cream', '0', 'BG8Q47Q0G8ZWM', '16');
INSERT INTO modifiers VALUES ('6869', 'Extra burrito sauce', '0', '2NHE1FFX4C3G0', '16');
INSERT INTO modifiers VALUES ('6870', 'Meat- Carnitas', '0', '6A2542K8VTRS8', '16');
INSERT INTO modifiers VALUES ('6871', 'No beans', '0', '8C0KZH886C8HE', '16');
INSERT INTO modifiers VALUES ('6872', 'Extra jack cheese', '0', '78JMVYQV6S6JE', '16');
INSERT INTO modifiers VALUES ('6873', 'Extra rice', '0', 'T6SXRWVCR8F1A', '16');
INSERT INTO modifiers VALUES ('6874', 'No gucamole', '0', 'T56M2PMQ8N50G', '16');
INSERT INTO modifiers VALUES ('6875', 'Extra lettuce', '0', 'P5QF3HY3E0VRY', '16');
INSERT INTO modifiers VALUES ('6876', 'Extra beans', '0', '0G4DA7BNVE210', '16');
INSERT INTO modifiers VALUES ('6877', 'Meat- Steak', '0', 'SC7XGNKJJS4GW', '16');
INSERT INTO modifiers VALUES ('6878', 'Extra sour cream', '0', '8KQ6HTZF6H9W8', '16');
INSERT INTO modifiers VALUES ('6879', 'Meat- Pastor', '0', 'KBK0E7D9VHVF0', '16');
INSERT INTO modifiers VALUES ('6880', 'Extra gucamole', '0', '4TRXYGWM1AV02', '16');
INSERT INTO modifiers VALUES ('6881', 'Corn', '0', '3546X1WJX9H0G', '16');
INSERT INTO modifiers VALUES ('6882', 'Flour', '0', 'HHNJBJ57ZGS64', '16');
INSERT INTO modifiers VALUES ('6883', 'Wheat', '0', 'K32GAD84PDKRT', '16');
INSERT INTO modifiers VALUES ('6884', 'No lettuce', '0', 'MB4N1W77ZB8PG', '16');
INSERT INTO modifiers VALUES ('6885', 'No jack cheese', '0', 'WB1HA5V9AGFAC', '16');
INSERT INTO modifiers VALUES ('6886', 'No tomato', '0', 'QTWQ1PMAECK68', '16');
INSERT INTO modifiers VALUES ('6887', 'Meat- Steak', '0', 'JN3NTEMRQPQTE', '16');
INSERT INTO modifiers VALUES ('6888', 'No gucamole', '0', 'ZVF1N4JSA7KC8', '16');
INSERT INTO modifiers VALUES ('6889', 'Meat- Bacon', '0', 'WZAR47W3ATES0', '16');
INSERT INTO modifiers VALUES ('6890', 'Meat- Chorizo', '0', '12GYEQZZ8WWEC', '16');
INSERT INTO modifiers VALUES ('6891', 'Meat- Ham', '0', '3FNS0D38GF8XP', '16');
INSERT INTO modifiers VALUES ('6892', 'Meat- Sausage', '0', '7BTKCF7ZTTE6P', '16');
INSERT INTO modifiers VALUES ('6893', 'Meat- Potato', '0', '9254Y6JM3HS4P', '16');
INSERT INTO modifiers VALUES ('6894', 'Meat- Steak', '0', 'SZT0MFJ51QNTJ', '16');
INSERT INTO modifiers VALUES ('6895', 'Meat- Migas', '0', 'FENBNXYFGM7EE', '16');
INSERT INTO modifiers VALUES ('6896', 'No jack cheese', '0', 'GBP2AVW5XH3X8', '17');
INSERT INTO modifiers VALUES ('6897', 'No avacado', '0', '98W9EMQB4BR94', '17');
INSERT INTO modifiers VALUES ('6898', 'No tomato', '0', '0MJPYTFG2Y3M4', '17');
INSERT INTO modifiers VALUES ('6899', 'Meat- Chicken', '0', '807B167XGMJMW', '17');
INSERT INTO modifiers VALUES ('6900', 'No lettuce', '0', '4VS9QJF1MP72T', '17');
INSERT INTO modifiers VALUES ('6901', 'No jack cheese', '0', 'F0CH2BNQQMDC6', '17');
INSERT INTO modifiers VALUES ('6902', 'No gucamole', '0', 'WJ1FSCAN2E87P', '17');
INSERT INTO modifiers VALUES ('6903', 'Crispy', '0', '1ET3TQ2X0XR40', '17');
INSERT INTO modifiers VALUES ('6904', 'Wheat Tortilla', '0', '3E0EXNSE6NWH0', '17');
INSERT INTO modifiers VALUES ('6905', 'Corn Tortilla', '0', '281956CYW0AJY', '17');
INSERT INTO modifiers VALUES ('6906', 'Flour Tortilla', '0', 'A4438JN0CSGZG', '17');
INSERT INTO modifiers VALUES ('6907', 'Chicken Chipotle', '0', 'PW82YVTZSJDTJ', '17');
INSERT INTO modifiers VALUES ('6908', 'Pescador Shrimp', '0', 'JRAF4T9YXFKNR', '17');
INSERT INTO modifiers VALUES ('6909', 'Fajitas Chicken', '0', 'P84FKR0SZF0P0', '17');
INSERT INTO modifiers VALUES ('6910', 'El Verde', '0', 'KARXVXHGK39PG', '17');
INSERT INTO modifiers VALUES ('6911', 'Marinero Shrimp', '0', 'KYY32WWK2MNHT', '17');
INSERT INTO modifiers VALUES ('6912', 'Capitan Fish', '0', 'FS9X01WPZPSNJ', '17');
INSERT INTO modifiers VALUES ('6913', 'Monterrey', '0', 'J8T4Q0C4V9CBC', '17');
INSERT INTO modifiers VALUES ('6914', 'Presidente', '0', '5FQPN78MWHGS8', '17');
INSERT INTO modifiers VALUES ('6915', 'Fajitas Steak', '0', 'A6KQM8Q9ZAYYP', '17');
INSERT INTO modifiers VALUES ('6916', 'Lengua', '0', '4F3JB5ZDBGY18', '17');
INSERT INTO modifiers VALUES ('6917', 'Ham & Egg', '0', '99SG9MVK1H0KW', '17');
INSERT INTO modifiers VALUES ('6918', 'Bacon & Egg', '0', 'HNJAF793B74KJ', '17');
INSERT INTO modifiers VALUES ('6919', 'Carnitas', '0', '71V4EKSJ5ND7A', '17');
INSERT INTO modifiers VALUES ('6920', 'Suasage & Egg', '0', '6RT3Y52B0Q9WW', '17');
INSERT INTO modifiers VALUES ('6921', 'Barbacoa', '0', '6RCGKQDS40PTA', '17');
INSERT INTO modifiers VALUES ('6922', 'Bistec', '0', 'WH5D18DYVXQ5Y', '17');
INSERT INTO modifiers VALUES ('6923', 'Potato & Egg', '0', 'NZ389SBVVWKTG', '17');
INSERT INTO modifiers VALUES ('6924', 'Chorizo & Egg', '0', 'K278KK01MY5YC', '17');
INSERT INTO modifiers VALUES ('6925', 'Pastor', '0', 'Y70T5GE5J0Y4E', '17');
INSERT INTO modifiers VALUES ('6926', 'Chicken', '0', 'KPSDM54B0S09Y', '17');
INSERT INTO modifiers VALUES ('6927', 'Steak & Egg', '0', 'DHQFZCKWPF5AY', '17');
INSERT INTO modifiers VALUES ('6928', 'Steak', '0', 'VYTSSGCGMVV0C', '17');
INSERT INTO modifiers VALUES ('6929', 'No roasted vegetable sauce', '0', 'TRRMWJ0RACM74', '17');
INSERT INTO modifiers VALUES ('6930', 'Meat- Steak', '0', 'YVF9RDAQ78AY4', '17');
INSERT INTO modifiers VALUES ('6931', 'No cheese', '0', 'VSN7FVJ2JGD4G', '17');
INSERT INTO modifiers VALUES ('6932', 'Meat- Chicken', '0', '4X54B528AJFZE', '17');
INSERT INTO modifiers VALUES ('6933', 'Meat- Migas', '0', '0MSJ3QG40A6YA', '17');
INSERT INTO modifiers VALUES ('6934', 'Meat- Chilaquilles', '0', '6K8AW4VD8M8SY', '17');
INSERT INTO modifiers VALUES ('6935', 'Meat- Rancheros', '0', 'XF25M24Q75BV4', '17');
INSERT INTO modifiers VALUES ('6936', 'Meat- Huevos', '0', 'RD20XX6TESPK2', '17');
INSERT INTO modifiers VALUES ('6937', 'Roja Sauce', '0', 'GMT8029QW7WRW', '17');
INSERT INTO modifiers VALUES ('6938', 'Verde Sauce', '0', 'HKQ7CQE9SQKGA', '17');
INSERT INTO modifiers VALUES ('6939', 'Beef', '0', 'ZCXAVRDQSSVWC', '17');
INSERT INTO modifiers VALUES ('6940', 'Cheese', '0', 'E8C080P5J0GDM', '17');
INSERT INTO modifiers VALUES ('6941', 'Chicken', '0', 'BWB9DWXZ4SZB6', '17');
INSERT INTO modifiers VALUES ('6942', 'No guacamole', '0', 'CCH2J4220X60R', '17');
INSERT INTO modifiers VALUES ('6943', 'No salsa', '0', 'V4TJXGK17FRXE', '17');
INSERT INTO modifiers VALUES ('6944', 'No cheese', '0', 'M5RNJFA4J0YQC', '17');
INSERT INTO modifiers VALUES ('6945', 'Meat- Lengua', '0', 'ZSH6N9AHTAVBA', '17');
INSERT INTO modifiers VALUES ('6946', 'Meat- Carnitas', '0', '0N64Q06XTAYQY', '17');
INSERT INTO modifiers VALUES ('6947', 'Meat- Steak', '0', 'XV43Q22F78QR0', '17');
INSERT INTO modifiers VALUES ('6948', 'Meat- Pastor', '0', '0B8CSMF8R0DS2', '17');
INSERT INTO modifiers VALUES ('6949', 'No cheese', '0', 'ZMWGETE4SAJPC', '17');
INSERT INTO modifiers VALUES ('6950', 'Meat- Chicken', '0', '7A6GTKSM65XSC', '17');
INSERT INTO modifiers VALUES ('6951', 'Meat- Bistec', '0', 'XQDJWSBM1SEKA', '17');
INSERT INTO modifiers VALUES ('6952', 'No roasted vegetable sauce', '0', 'H7ZM9RP229H5M', '17');
INSERT INTO modifiers VALUES ('6953', 'Meat- Barbacoa', '0', '5KJ7T10KPC7ZT', '17');
INSERT INTO modifiers VALUES ('6954', 'Meat- Barbacoa', '0', '0D2NEHGD4EWPY', '17');
INSERT INTO modifiers VALUES ('6955', 'Meat- Steak', '0', 'B11DYMK8WQNSA', '17');
INSERT INTO modifiers VALUES ('6956', 'No beans', '0', 'X22PXFYY8ST8G', '17');
INSERT INTO modifiers VALUES ('6957', 'No lettuce', '0', '1AFGJ42DX0PRY', '17');
INSERT INTO modifiers VALUES ('6958', 'No burrito sauce', '0', 'HWM74J4FNFHJ0', '17');
INSERT INTO modifiers VALUES ('6959', 'Meat- Chicken', '0', '38DMP20MQ4T34', '17');
INSERT INTO modifiers VALUES ('6960', 'No gucamole', '0', '7NJ42XHCHTXGE', '17');
INSERT INTO modifiers VALUES ('6961', 'No rice', '0', 'DFK38SE31RWE4', '17');
INSERT INTO modifiers VALUES ('6962', 'No sour cream', '0', '54B0035RCWNJ4', '17');
INSERT INTO modifiers VALUES ('6963', 'Meat- Lengua', '0', 'XB5714NYGVYPR', '17');
INSERT INTO modifiers VALUES ('6964', 'No jack cheese', '0', '6ZW6JNX2C5CM6', '17');
INSERT INTO modifiers VALUES ('6965', 'Meat- Pastor', '0', '7RYSBYC3RP7VR', '17');
INSERT INTO modifiers VALUES ('6966', 'Meat- Bistec', '0', '1F34KM5QYCGW6', '17');
INSERT INTO modifiers VALUES ('6967', 'Meat- Carnitas', '0', '59ZR4T7HS6GN0', '17');
INSERT INTO modifiers VALUES ('6968', 'No lettuce', '0', '95SR9AQP575E0', '17');
INSERT INTO modifiers VALUES ('6969', 'No jack cheese', '0', 'C0YGFZ5R4A6WP', '17');
INSERT INTO modifiers VALUES ('6970', 'No avacado', '0', '8W4141SJ5ZQJJ', '17');
INSERT INTO modifiers VALUES ('6971', 'Roja', '0', 'KB2K20HXH330Y', '17');
INSERT INTO modifiers VALUES ('6972', 'Chipotle', '0', '8TJPFBDV7XXZ2', '17');
INSERT INTO modifiers VALUES ('6973', 'Ranchera', '0', 'KB1W37KFDPFBM', '17');
INSERT INTO modifiers VALUES ('6974', 'Brava', '0', 'AC1KBR51JNXGT', '17');
INSERT INTO modifiers VALUES ('6975', 'Bombero', '0', 'JN29KJVNSXNF4', '17');
INSERT INTO modifiers VALUES ('6976', 'Verde', '0', 'JP4TAC52RGKHE', '17');
INSERT INTO modifiers VALUES ('6977', 'Meat- Steak', '0', 'KWHZQNYJEA156', '17');
INSERT INTO modifiers VALUES ('6978', 'Meat- Bacon', '0', '724J102B7WA66', '17');
INSERT INTO modifiers VALUES ('6979', 'Meat- Chorizo', '0', 'HEHTH68PK3DWT', '17');
INSERT INTO modifiers VALUES ('6980', 'Meat- Sausage', '0', 'XHCCANTGAMDWT', '17');
INSERT INTO modifiers VALUES ('6981', 'Meat- Ham', '0', 'ZV26KT3Q8E6R8', '17');
INSERT INTO modifiers VALUES ('6982', 'Meat- Migas', '0', 'C8ZTMVYN4Y3HJ', '17');
INSERT INTO modifiers VALUES ('6983', 'Meat- Potato', '0', 'KSTKFX95JHPWR', '17');
INSERT INTO modifiers VALUES ('6984', 'No guacamole', '0', '662Z4JRV41Z7E', '17');
INSERT INTO modifiers VALUES ('6985', 'Crispy taco', '0', '35KKES41X941T', '17');
INSERT INTO modifiers VALUES ('6986', 'Soft taco', '0', 'RFES5SVDCWBDE', '17');
INSERT INTO modifiers VALUES ('6987', 'Quesadilla', '0', 'EEPBB6JHN3Q02', '17');
INSERT INTO modifiers VALUES ('6988', 'Bean & Cheewse Burrito', '0', '8PXZM3SHHDK4Y', '17');
INSERT INTO modifiers VALUES ('6989', 'No cheese', '0', 'ZGBZG6DRGH92E', '17');
INSERT INTO modifiers VALUES ('6990', 'No salsa', '0', 'RMYBTAHTNMA0T', '17');
INSERT INTO modifiers VALUES ('6991', 'No Chicken', '0', 'DVQ4GRWSAE1NW', '17');
INSERT INTO modifiers VALUES ('6992', 'No Milanesa', '0', 'TTWK3HG9Y4EBY', '17');
INSERT INTO modifiers VALUES ('6993', 'No Ham', '0', '6BJHS9V42Y9BC', '17');
INSERT INTO modifiers VALUES ('6994', 'No Hot dog meat', '0', 'H3BH4YSXKJZ48', '17');
INSERT INTO modifiers VALUES ('6995', 'No gucamole', '0', 'DW7Q1K13AKMKA', '17');
INSERT INTO modifiers VALUES ('6996', 'Meat- Steak', '0', 'A48W24ZACRK66', '17');
INSERT INTO modifiers VALUES ('6997', 'Meat- Chicken', '0', '5AZ2GW5K6M8H8', '17');
INSERT INTO modifiers VALUES ('6998', 'No tomato', '0', 'QSPB3Y52339FE', '17');
INSERT INTO modifiers VALUES ('6999', 'No sour cream', '0', '3VH0HCS4RC96M', '17');
INSERT INTO modifiers VALUES ('7000', 'No cheese', '0', 'QG9HEJ8E8YFP2', '17');
INSERT INTO modifiers VALUES ('7001', 'No lettuce', '0', 'W3RD94E68KYHC', '17');
INSERT INTO modifiers VALUES ('7002', 'Extra Pico', '0', 'ZJDWCGZWQ37F8', '17');
INSERT INTO modifiers VALUES ('7003', 'Meat', '50', 'WHG2J2QMH33ST', '17');
INSERT INTO modifiers VALUES ('7004', 'Extra Cheddar Cheese', '25', '9KA7GZ0QM3W56', '17');
INSERT INTO modifiers VALUES ('7005', 'Extra Cheese', '25', '7T9BVF0MBE9B0', '17');
INSERT INTO modifiers VALUES ('7006', 'Extra Grilled Onions', '0', '1JTG72MMQ0P94', '17');
INSERT INTO modifiers VALUES ('7007', 'Steak Milanesa', '0', 'BTS6J4ST91KBT', '17');
INSERT INTO modifiers VALUES ('7008', 'Chicken', '0', 'XZZ7028EZXEGW', '17');
INSERT INTO modifiers VALUES ('7009', 'Lengua', '0', 'CYQCC5CF9FFCM', '17');
INSERT INTO modifiers VALUES ('7010', 'Chicken Milanesa', '0', '49J4ER4Y5QCN4', '17');
INSERT INTO modifiers VALUES ('7011', 'Steak', '0', '28QDQXGPYSWZM', '17');
INSERT INTO modifiers VALUES ('7012', 'Barbacoa', '0', 'PJX31DAP5CCV4', '17');
INSERT INTO modifiers VALUES ('7013', 'Bistec', '0', 'D1KA8EN9JV1E6', '17');
INSERT INTO modifiers VALUES ('7014', 'Pastor', '0', 'RJWGF849MQC54', '17');
INSERT INTO modifiers VALUES ('7015', 'Carnitas', '0', 'QB26S9XFSV6W0', '17');
INSERT INTO modifiers VALUES ('7016', 'Extra grilled onion', '0', 'A17N0QZ0QWPGY', '17');
INSERT INTO modifiers VALUES ('7017', 'No  cilantor', '0', 'K6BKPE3YM4HB2', '17');
INSERT INTO modifiers VALUES ('7018', 'No onion', '0', 'B81SV321JNGMY', '17');
INSERT INTO modifiers VALUES ('7019', 'Add Bacon', '0', 'NPDECTVMKBHGE', '17');
INSERT INTO modifiers VALUES ('7020', 'Add Chorizo', '0', '6QCG57B3TDRZM', '17');
INSERT INTO modifiers VALUES ('7021', 'Add Ham', '0', '60MX2RFFW4QNR', '17');
INSERT INTO modifiers VALUES ('7022', 'Add Sausage', '0', '8QXTXPV6MA4ET', '17');
INSERT INTO modifiers VALUES ('7023', 'Add Potato', '0', 'VQ9VKK7DTPBPM', '17');
INSERT INTO modifiers VALUES ('7024', 'Add Steak', '0', 'WW9VMSGBBMJM2', '17');
INSERT INTO modifiers VALUES ('7025', 'Potato & Egg', '0', '9CZQB6YP9MK9C', '17');
INSERT INTO modifiers VALUES ('7026', 'Bistec', '0', 'WX6QJ12QH2EWP', '17');
INSERT INTO modifiers VALUES ('7027', 'Steak', '0', 'RPKNRD1BDFRCG', '17');
INSERT INTO modifiers VALUES ('7028', 'Bacon & Egg', '0', 'FAZD88DCWNY1E', '17');
INSERT INTO modifiers VALUES ('7029', 'Lengua', '0', 'E6JKPEM46RBA4', '17');
INSERT INTO modifiers VALUES ('7030', 'Sausage & Egg', '0', 'A0QF8YNVBS47G', '17');
INSERT INTO modifiers VALUES ('7031', 'Barbacoa', '0', 'VKFDNAP7DD9RG', '17');
INSERT INTO modifiers VALUES ('7032', 'Chorizo & Egg', '0', '03BYWXNSHMJAM', '17');
INSERT INTO modifiers VALUES ('7033', 'Ham & Egg', '0', 'FTPFBMRGYHM3R', '17');
INSERT INTO modifiers VALUES ('7034', 'Carnitas', '0', '4YPYS8WPKY63M', '17');
INSERT INTO modifiers VALUES ('7035', 'Chicken', '0', 'ZWKC5CV7A7PKP', '17');
INSERT INTO modifiers VALUES ('7036', 'Pastor', '0', '1BKC6RGMSGC1W', '17');
INSERT INTO modifiers VALUES ('7037', 'Steak & Egg', '0', 'ZK1V90VZSYY76', '17');
INSERT INTO modifiers VALUES ('7038', 'No cheese', '0', 'KJNPN42E3J8B8', '17');
INSERT INTO modifiers VALUES ('7039', 'No tomato', '0', '0NCMFY11JMC2G', '17');
INSERT INTO modifiers VALUES ('7040', 'Meat- Grond Beef', '0', 'K62900AKF5K2C', '17');
INSERT INTO modifiers VALUES ('7041', 'Crispy ', '0', 'X8GFH3H7RE726', '17');
INSERT INTO modifiers VALUES ('7042', 'No lettuce', '0', '7XHTH9ZNJK1NM', '17');
INSERT INTO modifiers VALUES ('7043', 'No jack cheese', '0', 'KBJDTGJFXAKW6', '17');
INSERT INTO modifiers VALUES ('7044', 'Extra avacado', '0', 'Z87YYD7779A62', '17');
INSERT INTO modifiers VALUES ('7045', 'Extra tomato', '0', 'QGEDDK43CRSM6', '17');
INSERT INTO modifiers VALUES ('7046', 'No avacado', '0', 'G7VZBG17ZFV04', '17');
INSERT INTO modifiers VALUES ('7047', 'Extra jack cheese', '0', '22VW2BF9F1RV8', '17');
INSERT INTO modifiers VALUES ('7048', 'No lettuce', '0', 'M1NKV23RVN7NY', '17');
INSERT INTO modifiers VALUES ('7049', 'Meat- Pastor', '0', 'QAE0Y1CN84KS4', '17');
INSERT INTO modifiers VALUES ('7050', 'No gucamole', '0', '503SX2EPH29SG', '17');
INSERT INTO modifiers VALUES ('7051', 'No beans', '0', 'R3XDCRMHV251P', '17');
INSERT INTO modifiers VALUES ('7052', 'Extra sour cream', '0', '5KE1SCP8RY9E4', '17');
INSERT INTO modifiers VALUES ('7053', 'Meat- Steak', '0', 'K96M2J1EEP6MR', '17');
INSERT INTO modifiers VALUES ('7054', 'No rice', '0', '2P43NJF7K87EP', '17');
INSERT INTO modifiers VALUES ('7055', 'Extra jack cheese', '0', 'NT99FK06ZTPKM', '17');
INSERT INTO modifiers VALUES ('7056', 'Extra rice', '0', 'W87ASYAAG2Y44', '17');
INSERT INTO modifiers VALUES ('7057', 'Meat- Lengua', '0', 'VZJPBR305E78E', '17');
INSERT INTO modifiers VALUES ('7058', 'No burrito sauce', '0', 'EHQCWG8VE6D8P', '17');
INSERT INTO modifiers VALUES ('7059', 'Extra lettuce', '0', 'SGJF0BM6WH8KW', '17');
INSERT INTO modifiers VALUES ('7060', 'No sour cream', '0', '0H73PEEB5GK64', '17');
INSERT INTO modifiers VALUES ('7061', 'Meat- Chicken', '0', 'WETYHRCH1T468', '17');
INSERT INTO modifiers VALUES ('7062', 'Meat- Barbacoa', '0', '7B301TYYPG6F0', '17');
INSERT INTO modifiers VALUES ('7063', 'No jack cheese', '0', 'JCTCX53DBT54A', '17');
INSERT INTO modifiers VALUES ('7064', 'Extra burrito sauce', '0', 'WK9J580339PMY', '17');
INSERT INTO modifiers VALUES ('7065', 'Meat- Bistec', '0', '68Y1F524SZNHJ', '17');
INSERT INTO modifiers VALUES ('7066', 'No lettuce', '0', 'JNX13BJQNS02M', '17');
INSERT INTO modifiers VALUES ('7067', 'Meat- Carnitas', '0', '13MYGB0ZDX8HM', '17');
INSERT INTO modifiers VALUES ('7068', 'Extra gucamole', '0', 'VGR6VQ4ZSSZHW', '17');
INSERT INTO modifiers VALUES ('7069', 'Extra beans', '0', '5B4NZQMGKGDMT', '17');
INSERT INTO modifiers VALUES ('7070', 'No mushrooms', '0', 'QEF0K8ZEZGWCP', '17');
INSERT INTO modifiers VALUES ('7071', 'No jack cheese', '0', 'WRVVCN0F5QCZC', '17');
INSERT INTO modifiers VALUES ('7072', 'No bell peppers', '0', 'RMS2MX2KNTHSR', '17');
INSERT INTO modifiers VALUES ('7073', 'Extra jack cheese', '0', 'YHYXY62QQP4WR', '17');
INSERT INTO modifiers VALUES ('7074', 'Fajita Steak', '0', '4DWSWNBRKBSEJ', '17');
INSERT INTO modifiers VALUES ('7075', 'Brisket', '0', 'D9MJHY0JE3XV4', '17');
INSERT INTO modifiers VALUES ('7076', 'Presidente', '0', '9V99PQRYBQ6YC', '17');
INSERT INTO modifiers VALUES ('7077', 'Pescador', '0', 'B46PBBACVM13W', '17');
INSERT INTO modifiers VALUES ('7078', 'Chicken Chipotle', '0', 'SGDE1NBRHH3DP', '17');
INSERT INTO modifiers VALUES ('7079', 'Fajita Chicken', '0', 'J6KDWHKVN24NT', '17');
INSERT INTO modifiers VALUES ('7080', 'Monterrey', '0', 'QMMFV7GK2ABM4', '17');
INSERT INTO modifiers VALUES ('7081', 'Capiatn', '0', 'HAD3TZJPW50RC', '17');
INSERT INTO modifiers VALUES ('7082', 'Marinero', '0', 'N8VDBNM8E93WE', '17');
INSERT INTO modifiers VALUES ('7083', 'Meat- Bistec', '0', 'N2NYK49H2YRBG', '17');
INSERT INTO modifiers VALUES ('7084', 'Meat- Pastor', '0', 'BB1P60WTP1J7J', '17');
INSERT INTO modifiers VALUES ('7085', 'Meat- Barbacoa', '0', 'D0MSPSCWS2N9R', '17');
INSERT INTO modifiers VALUES ('7086', 'Meat- Lengua', '0', '6CVWE0TZPMSBY', '17');
INSERT INTO modifiers VALUES ('7087', 'Meat- Steak', '0', '3SZNMDWYNRWZA', '17');
INSERT INTO modifiers VALUES ('7088', 'No cilantor', '0', 'K1ZBRWCV6Y96M', '17');
INSERT INTO modifiers VALUES ('7089', 'No onion', '0', 'VTN9VXC2W6ESY', '17');
INSERT INTO modifiers VALUES ('7090', 'Meat- Carnitas', '0', 'Z08AM0HTK46WE', '17');
INSERT INTO modifiers VALUES ('7091', 'Meat- Chicken', '0', '3V0EBVHDG1Z7E', '17');
INSERT INTO modifiers VALUES ('7092', 'Flour', '0', 'G91WA4MYG32C8', '17');
INSERT INTO modifiers VALUES ('7093', 'Corn', '0', 'GM7BE4X4SA6GP', '17');
INSERT INTO modifiers VALUES ('7094', 'Wheat', '0', 'SGCNYX0NDMACM', '17');
INSERT INTO modifiers VALUES ('7095', 'No avacado', '0', 'THBEQEQWJR0JM', '17');
INSERT INTO modifiers VALUES ('7096', 'No tortillas stips', '0', 'TD26NDREV58JE', '17');
INSERT INTO modifiers VALUES ('7097', 'Meat- Chicken', '0', '746NRR5RFNAGT', '17');
INSERT INTO modifiers VALUES ('7098', 'No black beans', '0', '9VM6290PM2C8P', '17');
INSERT INTO modifiers VALUES ('7099', 'No corn', '0', 'QM68NMX2B907W', '17');
INSERT INTO modifiers VALUES ('7100', 'No lettuce', '0', '1CEXG55C1ZARP', '17');
INSERT INTO modifiers VALUES ('7101', 'Extra avacado', '0', 'KS0C1BT6VVQ0P', '17');
INSERT INTO modifiers VALUES ('7102', 'Extra jack cheese', '0', 'SK25YSC3N07JJ', '17');
INSERT INTO modifiers VALUES ('7103', 'No lettuce', '0', 'H5CN5BN8VSTWG', '17');
INSERT INTO modifiers VALUES ('7104', 'No jack cheese', '0', 'WTMQT99G9BQMY', '17');
INSERT INTO modifiers VALUES ('7105', 'Extra tomato', '0', 'PB6XM5ZYDD2BW', '17');
INSERT INTO modifiers VALUES ('7106', 'ck chipotle', '0', '42DZE7XK2Z17W', '17');
INSERT INTO modifiers VALUES ('7107', 'monterrey', '0', 'HRJ4KKZKR1YTR', '17');
INSERT INTO modifiers VALUES ('7108', 'ck fajita', '0', '9519VDT9PRHZM', '17');
INSERT INTO modifiers VALUES ('7109', 'capitan', '0', 'ACRCX9QFKYVBA', '17');
INSERT INTO modifiers VALUES ('7110', 'pescador', '0', 'YAXEPNRFR9Q12', '17');
INSERT INTO modifiers VALUES ('7111', 'marinero', '0', 'WHGSX5QRH1HZ6', '17');
INSERT INTO modifiers VALUES ('7112', 'st fajita', '0', '9V26EYBRNE0KY', '17');
INSERT INTO modifiers VALUES ('7113', 'presidente', '0', 'M6R9JPM5PPB76', '17');
INSERT INTO modifiers VALUES ('7114', 'el verde', '0', 'FMT5Y4D1X7XCP', '17');
INSERT INTO modifiers VALUES ('7115', 'Meat- Steak', '0', 'A37QPFM2B9S1P', '17');
INSERT INTO modifiers VALUES ('7116', 'Meat- Lengua', '0', 'YYS4B3QZD3B9E', '17');
INSERT INTO modifiers VALUES ('7117', 'Meat- Chicken', '0', '8WW0QWH3G0W2G', '17');
INSERT INTO modifiers VALUES ('7118', 'Meat- Pastor', '0', '8Z13A9C0HCTB6', '17');
INSERT INTO modifiers VALUES ('7119', 'Meat- Barbacoa', '0', 'T5RNJ66ZPGWYA', '17');
INSERT INTO modifiers VALUES ('7120', 'No cilantor', '0', '4PHDHNGWF5WE2', '17');
INSERT INTO modifiers VALUES ('7121', 'Meat- Carnitas', '0', 'VHVAJF9EHKRNR', '17');
INSERT INTO modifiers VALUES ('7122', 'Meat- Bistec', '0', '5TY75HJES0T9W', '17');
INSERT INTO modifiers VALUES ('7123', 'No onion', '0', 'ZC5TJKJ9ND1X2', '17');
INSERT INTO modifiers VALUES ('7124', 'Extra cheese', '0', 'JKNPWBQS0XE2E', '17');
INSERT INTO modifiers VALUES ('7125', 'Extra grilled onion', '0', 'WHHM5RRYEREMR', '17');
INSERT INTO modifiers VALUES ('7126', 'Steak & Egg', '0', '7QF6GJ00VH0NM', '17');
INSERT INTO modifiers VALUES ('7127', 'Sausage & Egg', '0', '0Z6H1D9C6MYYA', '17');
INSERT INTO modifiers VALUES ('7128', 'Chorizo & Egg', '0', '9TYDQCHDTX52M', '17');
INSERT INTO modifiers VALUES ('7129', 'Chicken', '0', 'CZH5JA0NTAWAW', '17');
INSERT INTO modifiers VALUES ('7130', 'Steak', '0', 'S6RXK9604HDV0', '17');
INSERT INTO modifiers VALUES ('7131', 'Lengua', '0', 'ES2QX19Q6Z312', '17');
INSERT INTO modifiers VALUES ('7132', 'Potato & Egg', '0', 'WP8585MR6X1NA', '17');
INSERT INTO modifiers VALUES ('7133', 'Pastor', '0', '3MDH446M7JVVM', '17');
INSERT INTO modifiers VALUES ('7134', 'Bacon & Egg', '0', '064P2TD233C4W', '17');
INSERT INTO modifiers VALUES ('7135', 'Carnitas', '0', 'C6AN9D94TD8CW', '17');
INSERT INTO modifiers VALUES ('7136', 'Ham & Egg', '0', 'ZSF74W8YXG2XT', '17');
INSERT INTO modifiers VALUES ('7137', 'Barbacoa', '0', 'P9TV7ZAT3Q0CW', '17');
INSERT INTO modifiers VALUES ('7138', 'Bistec', '0', '3MQ9ABZNJXDF6', '17');
INSERT INTO modifiers VALUES ('7139', 'No jack cheese', '0', 'V1MEA0W14RR6Y', '17');
INSERT INTO modifiers VALUES ('7140', 'No Ham', '0', '3BM9SBK8TGMEG', '17');
INSERT INTO modifiers VALUES ('7141', 'No Potato', '0', 'ZWA744PTBNVEP', '17');
INSERT INTO modifiers VALUES ('7142', 'No Bacon', '0', '05EX5D0H8GAQG', '17');
INSERT INTO modifiers VALUES ('7143', 'No Chorizo', '0', 'B817JP7CHMCA8', '17');
INSERT INTO modifiers VALUES ('7144', 'No Sausage', '0', 'S7PW365CZQWJ4', '17');
INSERT INTO modifiers VALUES ('7145', 'No Steak', '0', 'NW9W8HHYFS458', '17');
INSERT INTO modifiers VALUES ('7146', 'Fajita Steak', '0', '79ZH0XNJ9KHA0', '17');
INSERT INTO modifiers VALUES ('7147', 'Chicken Chipotle', '0', 'BC9GCTKZHQEVY', '17');
INSERT INTO modifiers VALUES ('7148', 'Marinero Shrimp', '0', 'F8YD6KGJWZWRW', '17');
INSERT INTO modifiers VALUES ('7149', 'Fajita Chicken', '0', '4B3SH5V3HJY3G', '17');
INSERT INTO modifiers VALUES ('7150', 'Pescador Shrimp', '0', '87WXR4AVKB47Y', '17');
INSERT INTO modifiers VALUES ('7151', 'El Verde', '0', '46GXGV3YJ8BFJ', '17');
INSERT INTO modifiers VALUES ('7152', 'Capitan Fish', '0', 'V318ZG703TY38', '17');
INSERT INTO modifiers VALUES ('7153', 'Presidente', '0', '3334ABBJJYETG', '17');
INSERT INTO modifiers VALUES ('7154', 'Monterrey', '0', 'PVEAEQ3PVVSA4', '17');
INSERT INTO modifiers VALUES ('7155', 'No Cheese', '0', 'C088EWTA1E65W', '17');
INSERT INTO modifiers VALUES ('7156', 'No Rice', '0', '1Z9P3326NY09E', '17');
INSERT INTO modifiers VALUES ('7157', 'No Onions', '0', 'VT2QH5KAHNP5R', '17');
INSERT INTO modifiers VALUES ('7158', 'No Beans', '0', 'H620FKG7VQZJR', '17');
INSERT INTO modifiers VALUES ('7159', 'No Cheddar Cheese', '0', 'W9P248R54N6VA', '17');
INSERT INTO modifiers VALUES ('7160', 'No Pico', '0', 'RZWC3Z8AG1KYY', '17');
INSERT INTO modifiers VALUES ('7161', 'No Avocado', '0', '9112S14G4JR8P', '17');
INSERT INTO modifiers VALUES ('7162', 'No Tomato', '0', 'FCG8WZC66KR34', '17');
INSERT INTO modifiers VALUES ('7163', 'No Mayo', '0', 'DSBGV556ZN0HJ', '17');
INSERT INTO modifiers VALUES ('7164', 'No Cilantro', '0', '22JQZ1VMHP4DT', '17');
INSERT INTO modifiers VALUES ('7165', 'No Lettuce', '0', 'P89ED7A03HZ22', '17');
INSERT INTO modifiers VALUES ('7166', 'Meat- Potato', '0', 'B8HPGGRW9N2EJ', '17');
INSERT INTO modifiers VALUES ('7167', 'Meat- Ham', '0', '96AX5E860S1X4', '17');
INSERT INTO modifiers VALUES ('7168', 'Meat- Sausage', '0', 'STMM3YW4E0DZ4', '17');
INSERT INTO modifiers VALUES ('7169', 'Meat- Chorizo', '0', 'A52CNJ4T7W9VA', '17');
INSERT INTO modifiers VALUES ('7170', 'Meat- Migas', '0', 'FESC10SZYES74', '17');
INSERT INTO modifiers VALUES ('7171', 'Meat- Bacon', '0', 'HG85DQWJ6D76G', '17');
INSERT INTO modifiers VALUES ('7172', 'Meat- Steak', '0', '216CVW4W88138', '17');
INSERT INTO modifiers VALUES ('7173', 'No gucamole', '0', 'JTH4Q1C5E4674', '17');
INSERT INTO modifiers VALUES ('7174', 'No lettuce', '0', 'VGP09E8GJQ9SC', '17');
INSERT INTO modifiers VALUES ('7175', 'No tomato', '0', 'V97V9PZE2TCZT', '17');
INSERT INTO modifiers VALUES ('7176', 'No jack cheese', '0', '3SP7NCEJ3MG7E', '17');
INSERT INTO modifiers VALUES ('7177', 'Meat- Steak', '0', '1DZR6BRCXC4JM', '17');
INSERT INTO modifiers VALUES ('7178', 'No jack cheese', '0', 'N968P95V38Q08', '17');
INSERT INTO modifiers VALUES ('7179', 'Extra jack cheese', '0', 'PMM4KRWQ3THHA', '17');
INSERT INTO modifiers VALUES ('7180', 'No bell peppers', '0', '0T9TMV2NVT8MP', '17');
INSERT INTO modifiers VALUES ('7181', 'No mushrooms', '0', 'QB29NVNNKJZ8P', '17');

-- ----------------------------
-- Table structure for `opening_closing_day`
-- ----------------------------
DROP TABLE IF EXISTS `opening_closing_day`;
CREATE TABLE `opening_closing_day` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` varchar(45) DEFAULT NULL,
  `opening_closing_pos_id` varchar(45) DEFAULT NULL,
  `isHoliday` tinyint(1) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `merchant_id` (`merchant_id`),
  CONSTRAINT `merchant_id` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of opening_closing_day
-- ----------------------------
INSERT INTO opening_closing_day VALUES ('11', 'sunday', 'E64D98ACW09E2', null, '16');
INSERT INTO opening_closing_day VALUES ('12', 'monday', 'E64D98ACW09E2', null, '16');
INSERT INTO opening_closing_day VALUES ('13', 'tuesday', 'E64D98ACW09E2', null, '16');
INSERT INTO opening_closing_day VALUES ('14', 'wednesday', 'E64D98ACW09E2', null, '16');
INSERT INTO opening_closing_day VALUES ('15', 'thursday', 'E64D98ACW09E2', null, '16');
INSERT INTO opening_closing_day VALUES ('16', 'friday', 'E64D98ACW09E2', null, '16');
INSERT INTO opening_closing_day VALUES ('17', 'saturday', 'E64D98ACW09E2', null, '16');
INSERT INTO opening_closing_day VALUES ('18', 'sunday', 'C7Y3YENSH5G7P', null, '17');
INSERT INTO opening_closing_day VALUES ('19', 'monday', 'C7Y3YENSH5G7P', null, '17');
INSERT INTO opening_closing_day VALUES ('20', 'tuesday', 'C7Y3YENSH5G7P', null, '17');
INSERT INTO opening_closing_day VALUES ('21', 'wednesday', 'C7Y3YENSH5G7P', null, '17');
INSERT INTO opening_closing_day VALUES ('22', 'thursday', 'C7Y3YENSH5G7P', null, '17');
INSERT INTO opening_closing_day VALUES ('23', 'friday', 'C7Y3YENSH5G7P', null, '17');
INSERT INTO opening_closing_day VALUES ('24', 'saturday', 'C7Y3YENSH5G7P', null, '17');

-- ----------------------------
-- Table structure for `opening_closing_time`
-- ----------------------------
DROP TABLE IF EXISTS `opening_closing_time`;
CREATE TABLE `opening_closing_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` varchar(45) DEFAULT NULL,
  `end_time` varchar(45) DEFAULT NULL,
  `day_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `day_id` (`day_id`),
  CONSTRAINT `opening_closing_time_ibfk_1` FOREIGN KEY (`day_id`) REFERENCES `opening_closing_day` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of opening_closing_time
-- ----------------------------
INSERT INTO opening_closing_time VALUES ('29', '9.0', '17.0', '11');
INSERT INTO opening_closing_time VALUES ('30', '9.0', '17.0', '12');
INSERT INTO opening_closing_time VALUES ('31', '9.0', '17.0', '13');
INSERT INTO opening_closing_time VALUES ('32', '9.0', '17.0', '14');
INSERT INTO opening_closing_time VALUES ('33', '9.0', '17.0', '15');
INSERT INTO opening_closing_time VALUES ('34', '9.0', '17.0', '16');
INSERT INTO opening_closing_time VALUES ('35', '9.0', '17.0', '17');
INSERT INTO opening_closing_time VALUES ('36', '8.0', '12.3', '18');
INSERT INTO opening_closing_time VALUES ('37', '13.0', '21.0', '18');
INSERT INTO opening_closing_time VALUES ('38', '0.0', '11.0', '19');
INSERT INTO opening_closing_time VALUES ('39', '11.3', '24.0', '19');
INSERT INTO opening_closing_time VALUES ('40', '9.0', '17.0', '20');
INSERT INTO opening_closing_time VALUES ('41', '9.0', '15.0', '21');
INSERT INTO opening_closing_time VALUES ('42', '9.0', '13.3', '22');
INSERT INTO opening_closing_time VALUES ('43', '9.0', '17.0', '23');
INSERT INTO opening_closing_time VALUES ('44', '9.0', '17.0', '24');

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `ordername` varchar(256) DEFAULT NULL,
  `ordernote` varchar(1024) DEFAULT NULL,
  `isTaxable` tinyint(1) DEFAULT NULL,
  `filtercategories` varchar(45) DEFAULT NULL,
  `fee` varchar(45) DEFAULT NULL,
  `avgOrdertime` varchar(45) DEFAULT NULL,
  `label` varchar(45) DEFAULT NULL,
  `isHidden` tinyint(1) DEFAULT NULL,
  `isDefault` tinyint(1) DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `merchant_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_11_idx` (`customer_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `ordertype`
-- ----------------------------
DROP TABLE IF EXISTS `ordertype`;
CREATE TABLE `ordertype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(45) DEFAULT NULL,
  `fee` float(20,0) DEFAULT NULL,
  `isTaxable` varchar(20) DEFAULT NULL,
  `ordertypecol` varchar(45) DEFAULT NULL,
  `filterCategories` varchar(45) DEFAULT NULL,
  `minOrderAmount` float DEFAULT NULL,
  `maxOrderAmount` float DEFAULT NULL,
  `averageOrderAmount` float DEFAULT NULL,
  `hoursAvailable` varchar(45) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `isHidden` varchar(20) DEFAULT NULL,
  `averageOrderTime` double DEFAULT NULL,
  `pos_ordertype_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `merchant_id` (`merchant_id`),
  CONSTRAINT `ordertype_ibfk_1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ordertype
-- ----------------------------
INSERT INTO ordertype VALUES ('107', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', 'H3XF2ENK32Q7J');
INSERT INTO ordertype VALUES ('108', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', '0F6DDQZFZRVWT');
INSERT INTO ordertype VALUES ('109', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', '9Y03SEW5SKK6W');
INSERT INTO ordertype VALUES ('110', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', 'RH9Y9XXY5Q6ZT');
INSERT INTO ordertype VALUES ('111', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', 'CFSM207M0KAX0');
INSERT INTO ordertype VALUES ('112', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', '4N3KTTFY5QX0W');
INSERT INTO ordertype VALUES ('113', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', '7CY8G8W6ZJ8VA');
INSERT INTO ordertype VALUES ('114', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', '2D1KWC0K020G4');
INSERT INTO ordertype VALUES ('115', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', 'KF8DKKXV8R3YE');
INSERT INTO ordertype VALUES ('116', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', 'CPZ97TWM2GG88');
INSERT INTO ordertype VALUES ('117', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', 'EYEB73M8P75NC');
INSERT INTO ordertype VALUES ('118', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', '3T7DT4F4NJ5VT');
INSERT INTO ordertype VALUES ('119', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', '1TR3573D6RBDJ');
INSERT INTO ordertype VALUES ('120', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', 'Y4VHP4SWR1R0Y');
INSERT INTO ordertype VALUES ('121', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', 'E0Y10WBNMNR7Y');
INSERT INTO ordertype VALUES ('122', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', '2NTXR7N1H7Z46');
INSERT INTO ordertype VALUES ('123', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', 'TWHHTW7E7ZX78');
INSERT INTO ordertype VALUES ('124', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', '4K06H7PM4RHQ4');
INSERT INTO ordertype VALUES ('125', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', 'NFV03WG4AJBPY');
INSERT INTO ordertype VALUES ('126', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', 'MXFG5AJ5YRKCT');
INSERT INTO ordertype VALUES ('127', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '16', '0', '0', 'WR94D6N6FCG4Y');
INSERT INTO ordertype VALUES ('128', 'Future Pickup', null, '1', null, null, null, null, null, 'BUSINESS', '17', '1', null, '4N8BPXPJWGN8G');
INSERT INTO ordertype VALUES ('129', 'Future Delivery', null, '1', null, null, null, null, null, 'BUSINESS', '17', '1', null, '6WXRXBHXZYF04');
INSERT INTO ordertype VALUES ('130', 'Online Delivery', null, '1', null, null, null, null, null, 'BUSINESS', '17', '1', null, '8BJEFHGGX48PA');
INSERT INTO ordertype VALUES ('131', 'Online Pickup', null, '1', null, null, null, null, null, 'BUSINESS', '17', '1', null, 'EER82ZB8ZR9GM');
INSERT INTO ordertype VALUES ('132', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', 'VV8THBV28E9J4');
INSERT INTO ordertype VALUES ('133', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', 'J6MCADW1QJ55P');
INSERT INTO ordertype VALUES ('134', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', 'EBS3A8KWJ3QTP');
INSERT INTO ordertype VALUES ('135', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', 'CZN7GNG2Z3S62');
INSERT INTO ordertype VALUES ('136', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', 'KZH2ZZ3DPJ3MG');
INSERT INTO ordertype VALUES ('137', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', 'V7VQZ9DPAZ2SM');
INSERT INTO ordertype VALUES ('138', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', 'T2J55054G0VV2');
INSERT INTO ordertype VALUES ('139', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', 'W2CBFB2QTVEEY');
INSERT INTO ordertype VALUES ('140', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', '322T3J92K224J');
INSERT INTO ordertype VALUES ('141', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', '139BSM9P39WK8');
INSERT INTO ordertype VALUES ('142', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', 'Z8S37P8RHXGBC');
INSERT INTO ordertype VALUES ('143', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', 'BVF49NN63XZAM');
INSERT INTO ordertype VALUES ('144', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', '0EJRB1RG4RF0R');
INSERT INTO ordertype VALUES ('145', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', 'K9J9JV2BPG65C');
INSERT INTO ordertype VALUES ('146', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', '979WGEPCBHNRM');
INSERT INTO ordertype VALUES ('147', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', 'JC3VHY7K9JPN0');
INSERT INTO ordertype VALUES ('148', 'Foodkonnekt Online Pickup', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', '0PZ5MMNJVK6HJ');
INSERT INTO ordertype VALUES ('149', 'Foodkonnekt Online Delivery', '0', '1', null, null, '0', '0', null, 'BUSINESS', '17', '0', '0', 'S7PB8H7B5ZF6Y');

-- ----------------------------
-- Table structure for `order_item`
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `quantity` int(50) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_r` (`id`),
  CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO order_item VALUES ('108', '92', '4208', '2', null);
INSERT INTO order_item VALUES ('109', '93', '4224', '2', null);
INSERT INTO order_item VALUES ('110', '93', '4232', '3', null);
INSERT INTO order_item VALUES ('111', '94', '4308', '3', null);
INSERT INTO order_item VALUES ('112', '94', '4309', '1', null);

-- ----------------------------
-- Table structure for `order_item_modifier`
-- ----------------------------
DROP TABLE IF EXISTS `order_item_modifier`;
CREATE TABLE `order_item_modifier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_item_id` int(11) DEFAULT NULL,
  `modifier_id` int(11) DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_item_id` (`order_item_id`),
  KEY `modifier_id` (`modifier_id`),
  CONSTRAINT `order_item_modifier_ibfk_1` FOREIGN KEY (`order_item_id`) REFERENCES `order_item` (`id`),
  CONSTRAINT `order_item_modifier_ibfk_2` FOREIGN KEY (`modifier_id`) REFERENCES `modifiers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item_modifier
-- ----------------------------
INSERT INTO order_item_modifier VALUES ('62', '108', '6847', '1');
INSERT INTO order_item_modifier VALUES ('63', '108', '6848', '1');
INSERT INTO order_item_modifier VALUES ('64', '109', '6669', '1');
INSERT INTO order_item_modifier VALUES ('65', '109', '6672', '1');
INSERT INTO order_item_modifier VALUES ('66', '110', '6884', '1');
INSERT INTO order_item_modifier VALUES ('67', '110', '6885', '1');
INSERT INTO order_item_modifier VALUES ('68', '111', '7008', '1');
INSERT INTO order_item_modifier VALUES ('69', '111', '7011', '1');
INSERT INTO order_item_modifier VALUES ('70', '112', '6984', '1');
INSERT INTO order_item_modifier VALUES ('71', '112', '6987', '1');

-- ----------------------------
-- Table structure for `order_r`
-- ----------------------------
DROP TABLE IF EXISTS `order_r`;
CREATE TABLE `order_r` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ordername` varchar(256) DEFAULT NULL,
  `ordernote` varchar(1024) DEFAULT NULL,
  `isTaxable` tinyint(1) DEFAULT NULL,
  `isDefaults` tinyint(1) DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `merchant_id` int(20) DEFAULT NULL,
  `orderPrice` double DEFAULT NULL,
  `taxable` varchar(50) DEFAULT NULL,
  `orderPosId` varchar(55) DEFAULT NULL,
  `subTotal` varchar(55) DEFAULT NULL,
  `tax` varchar(55) DEFAULT NULL,
  `posPaymentId` varchar(55) DEFAULT NULL,
  `payment_method` varchar(55) DEFAULT NULL,
  `order_type` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_11_idx` (`customer_id`) USING BTREE,
  KEY `order_ibfk_2` (`merchant_id`),
  CONSTRAINT `order_r_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `order_r_ibfk_2` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of order_r
-- ----------------------------
INSERT INTO order_r VALUES ('92', 'This is first order after long time by Praveen', 'This is first order after long time by Praveen', null, '0', null, '2016-06-24 23:50:51', '1', '16', '15.13', null, '4V2FF55AEN6MG', '13.98', '1.15', '8CCGNB2G3AKE8', 'Credit Card', 'Pickup');
INSERT INTO order_r VALUES ('93', 'scond order', 'scond order', null, '0', null, '2016-06-24 23:54:35', '1', '16', '40', null, 'AQWHEHY1K3YF8', null, null, null, null, null);
INSERT INTO order_r VALUES ('94', 'This order by case for testing purpose', 'This order by case for testing purpose', null, '0', null, '2016-06-25 00:00:38', '5', '17', '26.48', null, '4FM6EQ7QKBCHC', '24.46', '2.02', 'KM34WH3E20PV8', 'Cash', 'Pickup');

-- ----------------------------
-- Table structure for `paymentmode`
-- ----------------------------
DROP TABLE IF EXISTS `paymentmode`;
CREATE TABLE `paymentmode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instructions` varchar(1024) DEFAULT NULL,
  `visible` varchar(20) DEFAULT NULL,
  `editable` varchar(45) DEFAULT NULL,
  `label` varchar(45) DEFAULT NULL,
  `labelKey` varchar(45) DEFAULT NULL,
  `enabled` varchar(20) DEFAULT NULL,
  `pos_payment_mode_id` varchar(20) NOT NULL,
  `merchant_id` int(20) NOT NULL,
  `openCashDrawer` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_paymendmode_1` (`merchant_id`),
  CONSTRAINT `fk_paymendmode_1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of paymentmode
-- ----------------------------
INSERT INTO paymentmode VALUES ('64', null, null, null, 'Cash', 'com.clover.tender.cash', null, 'S9XNPAB5ZXYCR', '16', null);
INSERT INTO paymentmode VALUES ('65', null, null, null, 'Credit Card', 'com.clover.tender.credit_card', null, 'NPH5FTTJEKBXY', '16', null);
INSERT INTO paymentmode VALUES ('66', null, null, null, 'Check', 'com.clover.tender.check', null, 'Z1W7C06EY8E0J', '16', null);
INSERT INTO paymentmode VALUES ('67', null, null, null, 'External Gift Card', 'com.clover.tender.external_gift_card', null, '79S6KDJ5CKPCM', '16', null);
INSERT INTO paymentmode VALUES ('68', null, null, null, 'LevelUp', 'com.clover.tender.level_up', null, 'XZ6E7PAT0B4W8', '16', null);
INSERT INTO paymentmode VALUES ('69', null, null, null, 'External Payment', 'com.clover.tender.external_payment', null, 'Z32FT8BWY2N4E', '16', null);
INSERT INTO paymentmode VALUES ('70', null, null, null, 'Gift Card', 'com.clover.tender.gift_card', null, 'W7ZC06G4WJA8P', '16', null);
INSERT INTO paymentmode VALUES ('71', null, null, null, 'External PIN Debit', 'com.clover.tender.external_pin_debit', null, 'FXEEK77KSYYR4', '16', null);
INSERT INTO paymentmode VALUES ('72', null, null, null, 'Debit Card', 'com.clover.tender.debit_card', null, 'V2G2KQ2ME7EQ8', '16', null);
INSERT INTO paymentmode VALUES ('73', null, null, null, 'Cash', 'com.clover.tender.cash', null, '2KKNT04PXH770', '17', null);
INSERT INTO paymentmode VALUES ('74', null, null, null, 'Credit Card', 'com.clover.tender.credit_card', null, 'FHJ3J54T4SSYT', '17', null);
INSERT INTO paymentmode VALUES ('75', null, null, null, 'Check', 'com.clover.tender.check', null, '6TZX8RD07C2KG', '17', null);
INSERT INTO paymentmode VALUES ('76', null, null, null, 'External Gift Card', 'com.clover.tender.external_gift_card', null, 'CMZ6JN8TW2W30', '17', null);
INSERT INTO paymentmode VALUES ('77', null, null, null, 'LevelUp', 'com.clover.tender.level_up', null, 'CDW37XA3AYBSY', '17', null);
INSERT INTO paymentmode VALUES ('78', null, null, null, 'External Payment', 'com.clover.tender.external_payment', null, 'GPZJKZRCRW9VW', '17', null);
INSERT INTO paymentmode VALUES ('79', null, null, null, 'Gift Card', 'com.clover.tender.gift_card', null, '4MDV5Z2V47SEC', '17', null);
INSERT INTO paymentmode VALUES ('80', null, null, null, 'External PIN Debit', 'com.clover.tender.external_pin_debit', null, 'GCK0PBKWREWCJ', '17', null);
INSERT INTO paymentmode VALUES ('81', null, null, null, 'Debit Card', 'com.clover.tender.debit_card', null, 'WMBKKGP0MWPEY', '17', null);

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL,
  `permissionName` varchar(45) DEFAULT NULL,
  `permissionDescription` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for `personalinfo`
-- ----------------------------
DROP TABLE IF EXISTS `personalinfo`;
CREATE TABLE `personalinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdTime` datetime DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personalinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `pizzasize`
-- ----------------------------
DROP TABLE IF EXISTS `pizzasize`;
CREATE TABLE `pizzasize` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(20) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `posPizzaSizeId` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_merchant_id1` (`merchant_id`),
  CONSTRAINT `fk_merchant_id1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pizzasize
-- ----------------------------

-- ----------------------------
-- Table structure for `pizzasize_category`
-- ----------------------------
DROP TABLE IF EXISTS `pizzasize_category`;
CREATE TABLE `pizzasize_category` (
  `category_id` int(11) DEFAULT NULL,
  `pizza_size_id` int(11) DEFAULT NULL,
  KEY `fk_category_id` (`category_id`),
  KEY `fk_pizza_size_id` (`pizza_size_id`),
  CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_pizza_size_id` FOREIGN KEY (`pizza_size_id`) REFERENCES `pizzasize` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pizzasize_category
-- ----------------------------

-- ----------------------------
-- Table structure for `pizzasize_pizzatemplate`
-- ----------------------------
DROP TABLE IF EXISTS `pizzasize_pizzatemplate`;
CREATE TABLE `pizzasize_pizzatemplate` (
  `pizza_size_id` int(11) NOT NULL,
  `pizzatemplate_id` int(11) NOT NULL,
  KEY `fk_pizza_size_id1` (`pizza_size_id`),
  KEY `fk_pizzatemplate_id1` (`pizzatemplate_id`),
  CONSTRAINT `fk_pizza_size_id1` FOREIGN KEY (`pizza_size_id`) REFERENCES `pizzasize` (`id`),
  CONSTRAINT `fk_pizzatemplate_id1` FOREIGN KEY (`pizzatemplate_id`) REFERENCES `pizzatemplate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pizzasize_pizzatemplate
-- ----------------------------

-- ----------------------------
-- Table structure for `pizzatemplate`
-- ----------------------------
DROP TABLE IF EXISTS `pizzatemplate`;
CREATE TABLE `pizzatemplate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(20) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `category_id` int(20) DEFAULT NULL,
  `posPizzaTemplateId` varchar(20) DEFAULT NULL,
  `merchant_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_id1` (`category_id`),
  KEY `fk_merchant_id2` (`merchant_id`),
  CONSTRAINT `fk_category_id1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_merchant_id2` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pizzatemplate
-- ----------------------------

-- ----------------------------
-- Table structure for `pizzatemplatesize`
-- ----------------------------
DROP TABLE IF EXISTS `pizzatemplatesize`;
CREATE TABLE `pizzatemplatesize` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(30) DEFAULT NULL,
  `price` double(10,0) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `pizzasize_id` int(10) DEFAULT NULL,
  `pizzatemplate_id` int(10) DEFAULT NULL,
  `posPizzaTemplateSizeId` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pizzasize_id` (`pizzasize_id`),
  KEY `fk_pizzatemplate_id` (`pizzatemplate_id`),
  CONSTRAINT `fk_pizzasize_id` FOREIGN KEY (`pizzasize_id`) REFERENCES `pizzasize` (`id`),
  CONSTRAINT `fk_pizzatemplate_id` FOREIGN KEY (`pizzatemplate_id`) REFERENCES `pizzatemplate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pizzatemplatesize
-- ----------------------------

-- ----------------------------
-- Table structure for `pizzatopping`
-- ----------------------------
DROP TABLE IF EXISTS `pizzatopping`;
CREATE TABLE `pizzatopping` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `description` varchar(20) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `posPizzaToppingId` varchar(20) DEFAULT NULL,
  `merchant_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk-_merchant_id_fk_2` (`merchant_id`),
  CONSTRAINT `fk-_merchant_id_fk_2` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pizzatopping
-- ----------------------------

-- ----------------------------
-- Table structure for `pizzatoppingsize`
-- ----------------------------
DROP TABLE IF EXISTS `pizzatoppingsize`;
CREATE TABLE `pizzatoppingsize` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `price` double(20,0) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `posPizzaToppingSizeId` varchar(20) DEFAULT NULL,
  `pizzasize_id` int(10) DEFAULT NULL,
  `pizzatopping_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pizzasize_id_1` (`pizzasize_id`),
  KEY `fk_pizzatopping_id_1` (`pizzatopping_id`),
  CONSTRAINT `fk_pizzasize_id_1` FOREIGN KEY (`pizzasize_id`) REFERENCES `pizzasize` (`id`),
  CONSTRAINT `fk_pizzatopping_id_1` FOREIGN KEY (`pizzatopping_id`) REFERENCES `pizzatopping` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pizzatoppingsize
-- ----------------------------

-- ----------------------------
-- Table structure for `pos`
-- ----------------------------
DROP TABLE IF EXISTS `pos`;
CREATE TABLE `pos` (
  `pos_id` int(11) NOT NULL,
  `name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`pos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pos
-- ----------------------------
INSERT INTO pos VALUES ('1', 'Clover');
INSERT INTO pos VALUES ('2', 'FoodTronix');

-- ----------------------------
-- Table structure for `registration`
-- ----------------------------
DROP TABLE IF EXISTS `registration`;
CREATE TABLE `registration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` varchar(255) DEFAULT NULL,
  `updated_date` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_deleted` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6h7hgj3mk9bvv3uanpvgpqlg4` (`role_id`),
  CONSTRAINT `FK_6h7hgj3mk9bvv3uanpvgpqlg4` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of registration
-- ----------------------------

-- ----------------------------
-- Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `rolename` varchar(45) DEFAULT NULL,
  `roleDescription` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO roles VALUES ('1', 'Super Admin', null);
INSERT INTO roles VALUES ('2', 'ADMIN', null);
INSERT INTO roles VALUES ('3', 'Employee', null);
INSERT INTO roles VALUES ('4', 'Customer', null);

-- ----------------------------
-- Table structure for `subscriptions`
-- ----------------------------
DROP TABLE IF EXISTS `subscriptions`;
CREATE TABLE `subscriptions` (
  `id` int(10) NOT NULL,
  `subscriptionplan` varchar(50) DEFAULT NULL,
  `price` float(10,0) DEFAULT NULL,
  `orderlimit` int(10) DEFAULT NULL,
  `metered_id` varchar(20) DEFAULT NULL,
  `metered_price` float(10,0) DEFAULT NULL,
  `subscriptionplan_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subscriptions
-- ----------------------------
INSERT INTO subscriptions VALUES ('1', 'FREE', '0', '5', 'P0SF42C5C66RT', '1', 'ZCMS9SPQM9XMC');
INSERT INTO subscriptions VALUES ('2', 'Free Account', '0', '5', 'P0SF42C5C66RT', '1', '3F1V2YDQT7PHC');
INSERT INTO subscriptions VALUES ('3', 'Appetizer', '20', '30', 'KHCGE7XKN10DR', '1', '6V792E3FQVJ4T');
INSERT INTO subscriptions VALUES ('4', 'Entree Plan', '50', '100', 'CHAQVDFBSQHZ0', '0', 'PTQHQZENQZT20');
INSERT INTO subscriptions VALUES ('5', 'Buffet Plan', '100', '-1', '69VG2C5JC5F06', '0', '69VG2C5JC5F06');

-- ----------------------------
-- Table structure for `taxrate`
-- ----------------------------
DROP TABLE IF EXISTS `taxrate`;
CREATE TABLE `taxrate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `rate` float DEFAULT NULL,
  `isDefault` tinyint(1) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `pos_taxrate_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `merchant_id` (`merchant_id`),
  CONSTRAINT `taxrate_ibfk_1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of taxrate
-- ----------------------------
INSERT INTO taxrate VALUES ('37', '', '4.35', '0', '16', 'CJ2G4EH7C4M20');
INSERT INTO taxrate VALUES ('38', 'Food & Beverage', '8.25', '1', '16', '0GTXJR4GBJAN8');
INSERT INTO taxrate VALUES ('39', 'NO_TAX_APPLIED', '0', '0', '16', 'ZGRCFYJQP74FM');
INSERT INTO taxrate VALUES ('40', 'service tax', '5', '0', '17', 'X5GDZ07EKKQYM');
INSERT INTO taxrate VALUES ('41', 'service tax', '5', '0', '17', 'YCQ35SB9GWAZ4');
INSERT INTO taxrate VALUES ('42', 'Food & Beverage', '8.25', '1', '17', 'X43XYFGAMQSEY');
INSERT INTO taxrate VALUES ('43', 'NO_TAX_APPLIED', '0', '0', '17', '4WM974VPDKW24');

-- ----------------------------
-- Table structure for `tender`
-- ----------------------------
DROP TABLE IF EXISTS `tender`;
CREATE TABLE `tender` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `editable` varchar(100) DEFAULT NULL,
  `instructions` varchar(100) DEFAULT NULL,
  `isEnabled` tinyint(1) DEFAULT NULL,
  `label` varchar(100) DEFAULT NULL,
  `labelKey` varchar(100) DEFAULT NULL,
  `opensCashDrawer` varchar(100) DEFAULT NULL,
  `visible` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tender
-- ----------------------------

-- ----------------------------
-- Table structure for `vendor`
-- ----------------------------
DROP TABLE IF EXISTS `vendor`;
CREATE TABLE `vendor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `roleid` int(20) NOT NULL,
  `pos_id` int(11) DEFAULT NULL,
  `companyId` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vendor_1_idx` (`pos_id`),
  KEY `fk_roleid` (`roleid`),
  CONSTRAINT `fk_roleid` FOREIGN KEY (`roleid`) REFERENCES `roles` (`id`) ON UPDATE NO ACTION,
  CONSTRAINT `fk_vendor_1` FOREIGN KEY (`pos_id`) REFERENCES `pos` (`pos_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of vendor
-- ----------------------------
INSERT INTO vendor VALUES ('3', 'Seshu Madabushi', 'mobile@mkonnekt.com', '2', null, null);
INSERT INTO vendor VALUES ('4', 'Seshu Madabushi', 'mobile@mkonnekt.com', '2', '1', null);
INSERT INTO vendor VALUES ('5', 'Seshu Madabushi', 'mobile@mkonnekt.com', '2', '1', null);

-- ----------------------------
-- Table structure for `vouchar`
-- ----------------------------
DROP TABLE IF EXISTS `vouchar`;
CREATE TABLE `vouchar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vouchar_code` varchar(80) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `discount` double(20,2) DEFAULT NULL,
  `validity` varchar(60) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `from_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `start_time` varchar(60) DEFAULT NULL,
  `end_time` varchar(60) DEFAULT NULL,
  `created_on` date DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `merchant_id` (`merchant_id`),
  CONSTRAINT `vouchar_ibfk_1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vouchar
-- ----------------------------
INSERT INTO vouchar VALUES ('1', 'june-13', 'Amount', '12.00', 'Fixed', '2016-06-23', null, null, '5:15AM', '16:35PM', null, null);

-- ----------------------------
-- Table structure for `zone`
-- ----------------------------
DROP TABLE IF EXISTS `zone`;
CREATE TABLE `zone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `zone_name` varchar(255) DEFAULT NULL,
  `delivery_fee` double(50,9) DEFAULT NULL,
  `zone_distance` double(10,9) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `is_deliveryzone_taxable` int(3) DEFAULT NULL,
  `avg_delivery_time` varchar(10) DEFAULT NULL,
  `min_dollar_amount` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `location_id` (`location_id`),
  KEY `merchant_id` (`merchant_id`),
  CONSTRAINT `zone_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  CONSTRAINT `zone_ibfk_2` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zone
-- ----------------------------
INSERT INTO zone VALUES ('1', null, null, 'rrrr', '4.000000000', '2.000000000', '18', '16', '1', '34', '3');
