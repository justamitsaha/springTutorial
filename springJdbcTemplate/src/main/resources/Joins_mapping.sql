DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS Order_Product;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS Payment;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Profile;

--  1-1 mapping

CREATE TABLE Profile (
    profile_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    zip_code VARCHAR(10),
    CONSTRAINT uq_profile UNIQUE (profile_uuid)
);

-- The profile_uuid is the primary key and also has a unique constraint, ensuring it is unique across the Profile table.
-- The customer_uuid is the primary key and a foreign key referencing profile_uuid in the Profile table.
-- The ON DELETE CASCADE clause ensures that deleting a Profile will automatically delete the associated Customer, maintaining referential integrity.

CREATE TABLE Customer (
    customer_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_profile FOREIGN KEY (customer_uuid) REFERENCES Profile(profile_uuid) ON DELETE CASCADE
);

INSERT INTO Profile (email, name, phone_number, street, city, state, zip_code) VALUES
('john.doe@yahoo.com', 'John Doe', '555-1234', '123 Main St', 'Any town', 'Any state', '12345'),
('jane.doe@gmail.com', 'Jane Doe', '555-5678', '456 Elm St', 'Other town', 'Other state', '67890'),
('customer1@yahoo.com', 'Customer One', '555-0001', 'Street 1', 'City 1', 'State 1', '10001'),
('customer2@yahoo.com', 'Customer Two', '555-0002', 'Street 2', 'City 2', 'State 2', '10002');


INSERT INTO Customer (customer_uuid, customer_name) VALUES(1, 'John Doe'),(2, 'Jane Doe'),(3, 'Customer One'),(4, 'Customer Two');

-- No difference in any join since both sides are mapped with a keys

SELECT c.customer_uuid, c.customer_name, p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code
FROM Customer c INNER JOIN Profile p ON c.customer_uuid = p.profile_uuid;

SELECT c.customer_uuid, c.customer_name, p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code
FROM Customer c LEFT JOIN Profile p ON c.customer_uuid = p.profile_uuid;

SELECT c.customer_uuid, c.customer_name, p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code
FROM Customer c RIGHT JOIN Profile p ON c.customer_uuid = p.profile_uuid;

SELECT c.customer_uuid, c.customer_name, p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code
FROM Customer c LEFT JOIN Profile p ON c.customer_uuid = p.profile_uuid
UNION 
SELECT c.customer_uuid, c.customer_name, p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code
FROM Customer c RIGHT JOIN Profile p ON c.customer_uuid = p.profile_uuid;

-- 1: N mapping

CREATE TABLE Profile (
    profile_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    zip_code VARCHAR(10),
    CONSTRAINT uq_profile UNIQUE (profile_uuid)
);

CREATE TABLE Customer (
    customer_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_profile FOREIGN KEY (customer_uuid) REFERENCES Profile(profile_uuid) ON DELETE CASCADE
);

CREATE TABLE Orders (
    order_uuid VARCHAR(255) NOT NULL PRIMARY KEY,
    -- order_uuid CHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL PRIMARY KEY,
    order_number VARCHAR(255) NOT NULL,
    customer_id BIGINT,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES Customer(customer_uuid) ON DELETE CASCADE
);

INSERT INTO Profile (email, name, phone_number, street, city, state, zip_code) VALUES
('john.doe@yahoo.com', 'John Doe', '555-1234', '123 Main St', 'Any town', 'Any state', '12345'),
('jane.doe@gmail.com', 'Jane Doe', '555-5678', '456 Elm St', 'Other town', 'Other state', '67890'),
('customer1@yahoo.com', 'Customer One', '555-0001', 'Street 1', 'City 1', 'State 1', '10001'),
('customer2@yahoo.com', 'Customer Two', '555-0002', 'Street 2', 'City 2', 'State 2', '10002'),
('customer3@yahoo.com', 'Customer Three', '555-0003', 'Street 3', 'City 3', 'State 3', '10003');

INSERT INTO Customer (customer_uuid, customer_name) VALUES
(1, 'John Doe'),
(2, 'Jane Doe'),
(3, 'Customer One'),
(4, 'Customer Two'),
(5, 'Customer Three');


INSERT INTO Orders (order_uuid, order_number, customer_id) VALUES
('order1', '10001', 1),
('order2', '10002', 1),
('order3', '10003', 2),
('order4', '10004', 2),
('order5', '10005', 2),
('order6', '10005', 3);

 -- Can see full join and Left join , Right join we can't see as Orders can't exist without customers
SELECT c.customer_uuid, c.customer_name, p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code, o.order_uuid, o.order_number
FROM Customer c
INNER JOIN Profile p ON c.customer_uuid = p.profile_uuid INNER JOIN Orders o ON c.customer_uuid = o.customer_id;

SELECT c.customer_uuid, c.customer_name, p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code, o.order_uuid, o.order_number
FROM Customer c
LEFT JOIN Profile p ON c.customer_uuid = p.profile_uuid LEFT JOIN Orders o ON c.customer_uuid = o.customer_id;

SELECT c.customer_uuid, c.customer_name, p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code, o.order_uuid, o.order_number
FROM Customer c
RIGHT JOIN Orders o ON c.customer_uuid = o.customer_id LEFT JOIN Profile p ON c.customer_uuid = p.profile_uuid;

SELECT c.customer_uuid, c.customer_name, p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code, o.order_uuid, o.order_number
FROM Customer c LEFT JOIN Orders o ON c.customer_uuid = o.customer_id LEFT JOIN Profile p ON c.customer_uuid = p.profile_uuid
UNION 
SELECT c.customer_uuid, c.customer_name, p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code, o.order_uuid, o.order_number
FROM Orders o LEFT JOIN Customer c ON o.customer_id = c.customer_uuid LEFT JOIN Profile p ON c.customer_uuid = p.profile_uuid;


-- M:N

CREATE TABLE Profile (
    profile_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    zip_code VARCHAR(10),
    CONSTRAINT uq_profile UNIQUE (profile_uuid)
);

CREATE TABLE Customer (
    customer_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_profile FOREIGN KEY (customer_uuid) REFERENCES Profile(profile_uuid) ON DELETE CASCADE
);

CREATE TABLE Orders (
    order_uuid VARCHAR(255) NOT NULL PRIMARY KEY,
    -- order_uuid CHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL PRIMARY KEY,
    order_number VARCHAR(255) NOT NULL,
    customer_id BIGINT,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES Customer(customer_uuid) ON DELETE CASCADE
);

CREATE TABLE Product (
    product_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    price DOUBLE
);

CREATE TABLE Order_Product (
    order_uuid VARCHAR(36) NOT NULL,
    -- order_uuid CHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    product_uuid BIGINT NOT NULL,
    PRIMARY KEY (order_uuid, product_uuid),
    CONSTRAINT fk_order FOREIGN KEY (order_uuid) REFERENCES Orders(order_uuid) ON DELETE CASCADE,
    CONSTRAINT fk_product FOREIGN KEY (product_uuid) REFERENCES Product(product_uuid) ON DELETE CASCADE
);


INSERT INTO Profile (email, name, phone_number, street, city, state, zip_code) VALUES
('john.doe@yahoo.com', 'John Doe', '555-1234', '123 Main St', 'Any town', 'Any state', '12345'),
('jane.doe@gmail.com', 'Jane Doe', '555-5678', '456 Elm St', 'Other town', 'Other state', '67890'),
('customer1@yahoo.com', 'Customer One', '555-0001', 'Street 1', 'City 1', 'State 1', '10001'),
('customer2@yahoo.com', 'Customer Two', '555-0002', 'Street 2', 'City 2', 'State 2', '10002'),
('customer3@yahoo.com', 'Customer Three', '555-0003', 'Street 3', 'City 3', 'State 3', '10003');

INSERT INTO Customer (customer_uuid, customer_name) VALUES
(1, 'John Doe'),
(2, 'Jane Doe'),
(3, 'Customer One'),
(4, 'Customer Two'),
(5, 'Customer Three');


INSERT INTO Orders (order_uuid, order_number, customer_id) VALUES
('order1', '10001', 1),
('order2', '10002', 1),
('order3', '10003', 2),
('order4', '10004', 2),
('order5', '10005', 2),
('order6', '10005', 3);

INSERT INTO Product (name, price) VALUES
('Product1', 29.99),
('Product2', 49.99),
('Product3', 19.99),
('Product4', 9.99),
('Product5', 99.99),
('Product6', 14.99),
('Product7', 39.99);

INSERT INTO Order_Product (order_uuid, product_uuid) VALUES
('order1', 1),
('order1', 2),
('order2', 3),
('order3', 4),
('order3', 5),
('order4', 4),
('order4', 6);

SELECT o.order_uuid AS order_id, o.order_number, o.customer_id, prod.product_uuid, prod.name AS product_name, prod.price
FROM Orders o
INNER JOIN Order_Product op ON o.order_uuid = op.order_uuid
INNER JOIN Product prod ON op.product_uuid = prod.product_uuid;

SELECT o.order_uuid AS order_id, o.order_number, o.customer_id, prod.product_uuid, prod.name AS product_name, prod.price
FROM Orders o
LEFT JOIN Order_Product op ON o.order_uuid = op.order_uuid
LEFT JOIN Product prod ON op.product_uuid = prod.product_uuid;

SELECT o.order_uuid AS order_id, o.order_number, o.customer_id, prod.product_uuid, prod.name AS product_name, prod.price
FROM Orders o
RIGHT JOIN Order_Product op ON o.order_uuid = op.order_uuid
RIGHT JOIN Product prod ON op.product_uuid = prod.product_uuid;

SELECT o.order_uuid AS order_id, o.order_number, o.customer_id, prod.product_uuid, prod.name AS product_name, prod.price
FROM Orders o
LEFT JOIN Order_Product op ON o.order_uuid = op.order_uuid
LEFT JOIN Product prod ON op.product_uuid = prod.product_uuid
UNION
SELECT o.order_uuid AS order_id, o.order_number, o.customer_id, prod.product_uuid, prod.name AS product_name, prod.price
FROM Product prod
LEFT JOIN Order_Product op ON prod.product_uuid = op.product_uuid
LEFT JOIN Orders o ON op.order_uuid = o.order_uuid;





