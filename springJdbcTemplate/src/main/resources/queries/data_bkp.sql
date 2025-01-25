-- Insert categories
INSERT INTO Category (name) VALUES
('Electronics'),
('Clothing'),
('Books'),
('Home'),
('Garden'),
('Toys'),
('Sports'),
('Health'),
('Beauty'),
('Automotive');

-- Insert products
INSERT INTO Product (name, price) VALUES
('Product1', 29.99),
('Product2', 49.99),
('Product3', 19.99),
('Product4', 9.99),
('Product5', 99.99),
('Product6', 14.99),
('Product7', 39.99),
('Product8', 59.99),
('Product9', 79.99),
('Product10', 24.99);

-- Add more products to total 100
DO $$
DECLARE
    i INT;
BEGIN
    FOR i IN 11..100 LOOP
        EXECUTE 'INSERT INTO Product (name, price) VALUES (''Product' || i || ''', ' || ROUND(RANDOM() * 100 + 10, 2) || ')';
    END LOOP;
END $$;

-- Insert profiles and customers
INSERT INTO Profile (email, name, phone_number, street, city, state, zip_code) VALUES
('customer1@example.com', 'Customer One', '555-0001', 'Street 1', 'City 1', 'State 1', '10001'),
('customer2@example.com', 'Customer Two', '555-0002', 'Street 2', 'City 2', 'State 2', '10002'),
('customer3@example.com', 'Customer Three', '555-0003', 'Street 3', 'City 3', 'State 3', '10003'),
('customer4@example.com', 'Customer Four', '555-0004', 'Street 4', 'City 4', 'State 4', '10004'),
('customer5@example.com', 'Customer Five', '555-0005', 'Street 5', 'City 5', 'State 5', '10005'),
('customer6@example.com', 'Customer Six', '555-0006', 'Street 6', 'City 6', 'State 6', '10006'),
('customer7@example.com', 'Customer Seven', '555-0007', 'Street 7', 'City 7', 'State 7', '10007'),
('customer8@example.com', 'Customer Eight', '555-0008', 'Street 8', 'City 8', 'State 8', '10008'),
('customer9@example.com', 'Customer Nine', '555-0009', 'Street 9', 'City 9', 'State 9', '10009'),
('customer10@example.com', 'Customer Ten', '555-0010', 'Street 10', 'City 10', 'State 10', '10010');

INSERT INTO Customer (customer_uuid, customer_name) VALUES
(1, 'Customer One'),
(2, 'Customer Two'),
(3, 'Customer Three'),
(4, 'Customer Four'),
(5, 'Customer Five'),
(6, 'Customer Six'),
(7, 'Customer Seven'),
(8, 'Customer Eight'),
(9, 'Customer Nine'),
(10, 'Customer Ten');

-- Insert orders and payments
DO $$
DECLARE
    i INT;
    j INT;
    order_count INT;
    order_id VARCHAR(255);
    payment_status VARCHAR(15);
BEGIN
    FOR i IN 1..10 LOOP
        order_count := FLOOR(RANDOM() * 6); -- 0 to 5 orders per customer
        FOR j IN 1..order_count LOOP
            order_id := 'order' || i || '_' || j;
            EXECUTE 'INSERT INTO Orders (order_uuid, order_number, customer_id) VALUES (''' || order_id || ''', ''' || (i*1000 + j) || ''', ' || i || ')';
            payment_status := CASE WHEN RANDOM() < 0.33 THEN 'SUCCESS' WHEN RANDOM() < 0.66 THEN 'FAILURE' ELSE 'PROCESSING' END;
            EXECUTE 'INSERT INTO Payment (payment_uuid, payment_status, order_id) VALUES (' || (i*10 + j) || ', ''' || payment_status || ''', ''' || order_id || ''')';

            -- Insert products into Order_Product
            EXECUTE 'INSERT INTO Order_Product (order_uuid, product_uuid) SELECT ''' || order_id || ''', product_uuid FROM Product ORDER BY RANDOM() LIMIT ' || FLOOR(RANDOM() * 5 + 1);
        END LOOP;
    END LOOP;
END $$;

-- Insert product categories
DO $$
DECLARE
    product_id INT;
    category_id INT;
BEGIN
    FOR product_id IN 1..100 LOOP
        category_id := FLOOR(RANDOM() * 10 + 1); -- Random category for each product
        EXECUTE 'INSERT INTO product_category (product_id, category_id) VALUES (' || product_id || ', ' || category_id || ')';
    END LOOP;
END $$;
