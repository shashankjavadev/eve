ALTER TABLE `paymentmode`
ADD COLUMN `allow_payment_mode`  int(1) ZEROFILL NOT NULL AFTER `openCashDrawer`;

