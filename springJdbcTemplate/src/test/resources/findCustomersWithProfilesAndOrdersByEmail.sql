-- test-schema.sql
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Profile;

CREATE TABLE Profile (
    profile_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    zip_code VARCHAR(10)
);

CREATE TABLE Customer (
    customer_uuid BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_profile FOREIGN KEY (customer_uuid) REFERENCES Profile(profile_uuid) ON DELETE CASCADE
);

CREATE TABLE Orders (
    order_uuid VARCHAR(255) NOT NULL PRIMARY KEY,
    order_number VARCHAR(255) NOT NULL,
    customer_id BIGINT,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES Customer(customer_uuid) ON DELETE CASCADE
);

-- Insert test data
INSERT INTO Profile (email, name, phone_number, street, city, state, zip_code) VALUES
('john.doe@example.com', 'John Doe', '555-1234', '123 Main St', 'Anytown', 'Anystate', '12345'),
('jane.doe@example.com', 'Jane Doe', '555-5678', '456 Elm St', 'Othertown', 'Otherstate', '67890');

INSERT INTO Customer (customer_uuid, customer_name) VALUES
(1, 'John Doe'),
(2, 'Jane Doe');

INSERT INTO Orders (order_uuid, order_number, customer_id) VALUES
('order1', '10001', 1),
('order2', '10002', 1),
('order3', '10003', 2);
