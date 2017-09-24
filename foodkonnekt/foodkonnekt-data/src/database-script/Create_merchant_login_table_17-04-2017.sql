CREATE TABLE `merchant_login` (
`id`  int(20) NOT NULL AUTO_INCREMENT ,
`email_id`  varchar(50) NULL ,
`password`  varchar(50) NULL ,
`merchant_id`  int(20) NULL ,
PRIMARY KEY (`id`),
CONSTRAINT `merchant_id_fk_login` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`) ON DELETE RESTRICT ON UPDATE NO ACTION
)
;

ALTER TABLE `merchant_login`
MODIFY COLUMN `password`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `email_id`;


