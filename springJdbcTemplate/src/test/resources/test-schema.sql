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

INSERT INTO Profile (email, name, phone_number, street, city, state, zip_code) VALUES
("test@example.com", "Test User", "1234567890", "123 Street", "City", "State", "12345");

CREATE TABLE Product (
    product_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    price DOUBLE,
    created_date DATE,
    modified_date TIMESTAMP
);

CREATE TABLE Category (
    category_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE product_category (
    product_id BIGINT,
    category_id BIGINT,
    PRIMARY KEY (product_id, category_id),
    CONSTRAINT fk_join_product FOREIGN KEY (product_id) REFERENCES Product(product_uuid),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES Category(category_uuid)
);

