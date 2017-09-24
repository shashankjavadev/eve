CREATE TABLE `Social_Media_Links` (
`id`  int(10) NOT NULL AUTO_INCREMENT ,
`facebook_link`  varchar(1000) NULL ,
`yelp_link`  varchar(1000) NULL ,
`instagram_link`  varchar(1000) NULL ,
`merchant_id`  int(10) NULL ,
PRIMARY KEY (`id`),
CONSTRAINT `fk_sm_merchant_id` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
;

