USE `spring_boot_jpa`;

DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS Payment;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Profile;




CREATE TABLE Profile (
    profile_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
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
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (customer_uuid),
    CONSTRAINT fk_profile FOREIGN KEY (customer_uuid) REFERENCES Profile(profile_uuid) ON DELETE CASCADE
);

-- customer_uuid is the primary key, ensuring that each customer has a unique identifier.
-- customer_uuid is also a foreign key referencing profile_uuid in the Profile table.
-- since customer_id is not unique in order  it is one to many relationship
-- To prevent orphaned orders, the fk_customer foreign key constraint in the Orders table  includes ON DELETE CASCADE



CREATE TABLE Orders (
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
    CONSTRAINT fk_order_payment FOREIGN KEY (order_id) REFERENCES Orders(order_uuid) ON DELETE CASCADE
);


CREATE TABLE Product (
    product_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE,
    order_id CHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES Orders(order_uuid)
);

-- The Product table has a foreign key order_id referencing order_uuid in the Orders table. This establishes a one-to-many
--  relationship where one order can have multiple products, but each product is associated with only one order.
-- No cascading delete; deleting an order will not delete associated products, keeping them in the database.

CREATE TABLE Category (
    category_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- product_id references product_uuid in the Product table. category_id references category_uuid in the Category table.
-- Ensures that each entry in the product_category table is linked to valid products and categories.


CREATE TABLE product_category (
    product_id BIGINT,
    category_id BIGINT,
    PRIMARY KEY (product_id, category_id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES Product(product_uuid) ON DELETE CASCADE,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES Category(category_uuid) ON DELETE CASCADE
);

-- product_id references product_uuid in the Product table.
-- category_id references category_uuid in the Category table.
-- Composite primary key ensures uniqueness of product-category pairs.
-- ON DELETE CASCADE ensures that deleting a product or category also deletes associated entries in the product_category table.
