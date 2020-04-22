create schema if not exists thriftshop collate latin1_swedish_ci;
GRANT ALL PRIVILEGES ON thriftshop.* TO 'c24'@'%';
UPDATE `mysql`.`user`
SET `Grant_priv` = 'Y'
WHERE `User` = 'c24';
FLUSH PRIVILEGES;