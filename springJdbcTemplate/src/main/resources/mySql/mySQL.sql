USE `spring_boot_jpa`;

DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS Order_Product;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS Payment;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Profile;
DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    birth_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

    -- Adding some basic constraints
--    CONSTRAINT chk_email CHECK (email LIKE '%@%.%'),
--    CONSTRAINT chk_name_length CHECK (LENGTH(name) >= 2)
);

CREATE TABLE Profile (
    profile_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
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
    customer_uuid BIGINT,
    customer_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (customer_uuid),
    CONSTRAINT fk_profile FOREIGN KEY (customer_uuid) REFERENCES Profile(profile_uuid) ON DELETE CASCADE
);

-- customer_uuid is the primary key, ensuring that each customer has a unique identifier.
-- customer_uuid is also a foreign key referencing profile_uuid in the Profile table.
-- since customer_id is not unique in order  it is one to many relationship
-- To prevent orphaned orders, the fk_customer foreign key constraint in the Orders table  includes ON DELETE CASCADE

CREATE TABLE Orders (
    -- order_uuid VARCHAR(255) NOT NULL PRIMARY KEY,
    order_uuid CHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL PRIMARY KEY,
    order_number VARCHAR(255) NOT NULL,
    customer_id BIGINT,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES Customer(customer_uuid) ON DELETE CASCADE
);

-- payment_uuid is the primary key.  order_id is a foreign key referencing order_uuid in the Orders table.
-- order_id is also unique, ensuring that each order can have at most one corresponding payment.
-- The ON DELETE CASCADE option ensures that if an Order is deleted, the corresponding Payment is also deleted, maintaining referential integrity.

CREATE TABLE Payment (
    payment_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    payment_status ENUM('SUCCESS', 'FAILURE', 'PROCESSING') NOT NULL,
    order_id CHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci UNIQUE,
    -- order_id VARCHAR(36) UNIQUE,
    CONSTRAINT fk_order_payment FOREIGN KEY (order_id) REFERENCES Orders(order_uuid) ON DELETE CASCADE
);

-- The Product table defines each product with a unique identifier and its attributes.
-- Products do not have a direct foreign key to Orders since products can be part of multiple orders.

CREATE TABLE Product (
    product_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    price DOUBLE,
    -- created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_date DATE DEFAULT (CURRENT_DATE),

    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Join table to manage the many-to-many relationship between Orders and Products.
-- Each entry links one order to one product.

CREATE TABLE Order_Product (
    -- order_uuid VARCHAR(36) NOT NULL,
    order_uuid CHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    product_uuid BIGINT NOT NULL,
    PRIMARY KEY (order_uuid, product_uuid),
    CONSTRAINT fk_order FOREIGN KEY (order_uuid) REFERENCES Orders(order_uuid) ON DELETE CASCADE,
    CONSTRAINT fk_product FOREIGN KEY (product_uuid) REFERENCES Product(product_uuid) ON DELETE CASCADE
);

-- The Category table defines each category with a unique identifier and its attributes.

CREATE TABLE Category (
    category_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Join table to manage the many-to-many relationship between Products and Categories.
-- Each entry links one product to one category.

CREATE TABLE product_category (
    product_id BIGINT,
    category_id BIGINT,
    PRIMARY KEY (product_id, category_id),
    CONSTRAINT fk_join_product FOREIGN KEY (product_id) REFERENCES Product(product_uuid) ON DELETE CASCADE,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES Category(category_uuid) ON DELETE CASCADE
);
