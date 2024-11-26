USE `spring_boot_jpa`;

DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS Payment;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Profile;


CREATE TABLE Profile (
    profile_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    zip_code VARCHAR(10)
);

CREATE TABLE Customer (
    customer_id BIGINT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (customer_id),
    CONSTRAINT fk_profile FOREIGN KEY (customer_id) REFERENCES Profile(profile_id)
);

CREATE TABLE Orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_number VARCHAR(255) NOT NULL,
    customer_id BIGINT,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Payment (
    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    payment_status ENUM('SUCCESS', 'FAILURE', 'PROCESSING') NOT NULL,
    order_id BIGINT,
    CONSTRAINT fk_order_payment FOREIGN KEY (order_id) REFERENCES Orders(order_id)
);

CREATE TABLE Product (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE,
    order_id BIGINT,
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES Orders(order_id)
);

CREATE TABLE Category (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE product_category (
    product_id BIGINT,
    category_id BIGINT,
    PRIMARY KEY (product_id, category_id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES Product(product_id),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES Category(category_id)
);
