CREATE TABLE `feedback_question_category` (
`id`  int(10) NOT NULL AUTO_INCREMENT ,
`feedback_question_category`  varchar(200) NULL ,
PRIMARY KEY (`id`)
)
;

CREATE TABLE `feedback_question` (
`id`  int(10) NOT NULL AUTO_INCREMENT ,
`question`  varchar(300) NULL ,
`question_category`  int(10) NULL ,
PRIMARY KEY (`id`),
CONSTRAINT `fk_fb_question-category` FOREIGN KEY (`question_category`) REFERENCES `feedback_question_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
;

CREATE TABLE `customer_feedback` (
`id`  int(10) NOT NULL AUTO_INCREMENT ,
`customer_comments`  varchar(300) NULL ,
`customer_id`  int(10) NULL ,
PRIMARY KEY (`id`),
CONSTRAINT `fk_cust_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
;


CREATE TABLE `customer_feedback_answer` (
`id`  int(10) NOT NULL AUTO_INCREMENT,
`feedback_question_id`  int(10) NULL ,
`answer`  int(10) NULL ,
`customer_feedback_id`  int(10) NULL ,
PRIMARY KEY (`id`),
CONSTRAINT `fk_feedback_question_id` FOREIGN KEY (`feedback_question_id`) REFERENCES `feedback_question` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
CONSTRAINT `fk_customer_feedback_id` FOREIGN KEY (`customer_feedback_id`) REFERENCES `customer_feedback` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
;

ALTER TABLE `customer_feedback`
ADD COLUMN `order_id`  int(10) NULL AFTER `customer_id`;

ALTER TABLE `customer_feedback` ADD CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `order_r` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `feedback_question`
ADD COLUMN `isrequired`  tinyint(1) NULL DEFAULT 0 AFTER `question_category`;









