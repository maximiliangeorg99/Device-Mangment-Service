CREATE USER 'c24'@'localhost' IDENTIFIED BY 'Chrono24!';
create schema if not exists thriftshop collate latin1_swedish_ci;
GRANT ALL PRIVILEGES ON thriftshop.* TO 'c24'@'localhost';
UPDATE `mysql`.`user`
SET `Grant_priv` = 'Y'
WHERE `User` = 'c24';
FLUSH PRIVILEGES;