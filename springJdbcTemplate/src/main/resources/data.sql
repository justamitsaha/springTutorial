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
('Product10', 24.99);           -- order associated with this

INSERT INTO Profile (email, name, phone_number, street, city, state, zip_code) VALUES
('john.doe@yahoo.com', 'John Doe', '555-1234', '123 Main St', 'Any town', 'Any state', '12345'),
('jane.doe@gmail.com', 'Jane Doe', '555-5678', '456 Elm St', 'Other town', 'Other state', '67890'),
('customer1@yahoo.com', 'Customer One', '555-0001', 'Street 1', 'City 1', 'State 1', '10001'),
('customer2@yahoo.com', 'Customer Two', '555-0002', 'Street 2', 'City 2', 'State 2', '10002'),
('customer3@yahoo.com', 'Customer Three', '555-0003', 'Street 3', 'City 3', 'State 3', '10003'),
('customer4@yahoo.com', 'Customer Four', '555-0004', 'Street 4', 'City 4', 'State 4', '10004'),
('customer5@yahoo.com', 'Customer Five', '555-0005', 'Street 5', 'City 5', 'State 5', '10005'),
('customer6@gmail.com', 'Customer Six', '555-0006', 'Street 6', 'City 6', 'State 6', '10006'),
('customer7@gmail.com', 'Customer Seven', '555-0007', 'Street 7', 'City 7', 'State 7', '10007'),
('customer8@gmail.com', 'Customer Eight', '555-0008', 'Street 8', 'City 8', 'State 8', '10008'),
('customer9@gmail.com', 'Customer Nine', '555-0009', 'Street 9', 'City 9', 'State 9', '10009'),
('customer10@gmail.com', 'Customer Ten', '555-0010', 'Street 10', 'City 10', 'State 10', '10010');

INSERT INTO Customer (customer_uuid, customer_name) VALUES
(1, 'John Doe'),
(2, 'Jane Doe'),
(3, 'Customer One'),
(4, 'Customer Two'),
(5, 'Customer Three'),
(6, 'Customer Four'),
(7, 'Customer Five'),
(8, 'Customer Six'),
(9, 'Customer Seven'),
(10, 'Customer Eight'),
(11, 'Customer Nine'),
(12, 'Customer Ten');

INSERT INTO Orders (order_uuid, order_number, customer_id) VALUES
('order1', '10001', 1),
('order2', '10002', 1),
('order3', '10003', 2),
('order4', '10004', 2),
('order5', '10005', 2),
('order6', '10005', 3),
('order7', '10007', 2),
('order8', '10008', 3),
('order9', '10009', 3),
('order10', '10010', 4),
('order11', '10011', 5),        -- order with no products
('order12', '10012', 6),        -- order with no products
('order13', '10013', 6);        -- order with no products

INSERT INTO Payment (payment_uuid, payment_status, order_id) VALUES
(1, 'SUCCESS', 'order1'),
(2, 'FAILURE', 'order2'),
(3, 'PROCESSING', 'order3'),
(4, 'SUCCESS', 'order4'),
(5, 'FAILURE', 'order5'),
(6, 'PROCESSING', 'order6'),
(7, 'PROCESSING', 'order7'),
(8, 'PROCESSING', 'order8'),
(9, 'PROCESSING', 'order9'),
(10, 'PROCESSING', 'order10'),
(11, 'PROCESSING', 'order11'),
(12, 'PROCESSING', 'order12'),
(13, 'PROCESSING', 'order13');

INSERT INTO Order_Product (order_uuid, product_uuid) VALUES
('order1', 1),('order1', 2),
('order2', 3),
('order3', 4),('order3', 5),
('order4', 4),('order4', 6),
('order5', 5),('order5', 8),('order5', 9),
('order6', 1),
('order7', 7),
('order8', 8),('order8', 6),('order8', 9),
('order9', 1),('order9', 7),
('order10', 1),('order10', 2),('order10', 9);

INSERT INTO product_category (product_id, category_id) VALUES
(1, 1),(1, 2),(1, 3),
(2, 2),(2, 3),(2, 7),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);
