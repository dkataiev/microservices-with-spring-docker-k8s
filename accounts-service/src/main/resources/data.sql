DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS accounts;

CREATE TABLE `customers`
(
    `customer_id`   bigint AUTO_INCREMENT PRIMARY KEY,
    `name`          varchar(100) NOT NULL,
    `email`         varchar(100) NOT NULL,
    `mobile_number` varchar(20)  NOT NULL,
    `create_dt`     date DEFAULT NULL
);

CREATE TABLE `accounts`
(
    `account_number` bigint AUTO_INCREMENT PRIMARY KEY,
    `customer_id`    bigint       NOT NULL,
    `account_type`   varchar(100) NOT NULL,
    `branch_address` varchar(200) NOT NULL,
    `create_dt`      date DEFAULT NULL
);

INSERT INTO `customers` (`name`, `email`, `mobile_number`, `create_dt`)
VALUES ('Eazy Bytes', 'tutor@eazybytes.com', '9876548337', CURDATE());

INSERT INTO `accounts` (`account_number`, `customer_id`, `account_type`, `branch_address`, `create_dt`)
VALUES (186576453, 1, 'Savings', '123 Main Street, New York', CURDATE());