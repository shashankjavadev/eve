CREATE TABLE `merchant_kritiq` (
`id`  int(11) NOT NULL ,
`merchant_id`  int(11) NULL ,
`create_date`  varchar(60) NULL ,
`update_date`  varchar(60) NULL ,
`validity_date`  varchar(60) NULL ,
`active`  tinyint(1) NULL DEFAULT 0 ,
PRIMARY KEY (`id`),
CONSTRAINT `frk_1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
;


ALTER TABLE `merchant_kritiq`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `subscriptions`
MODIFY COLUMN `price`  float(10,3) NULL DEFAULT NULL AFTER `subscriptionplan`;