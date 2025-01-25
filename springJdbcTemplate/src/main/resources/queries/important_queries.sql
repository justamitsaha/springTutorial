--Join customer profile and order with customer_id, group them with customer_id and filter where order count is more than 2 and customer_name like input
SELECT c.customer_uuid AS customer_uuid, c.customer_name AS customer_name, p.profile_uuid AS profile_uuid, p.email AS email, p.name AS profile_name,
    p.phone_number AS phone_number, p.street AS street, p.city AS city, p.state AS state, p.zip_code AS zip_code,
    COUNT(o.order_uuid) AS order_count
FROM Customer c
JOIN Profile p ON c.customer_uuid = p.profile_uuid
LEFT JOIN Orders o ON c.customer_uuid = o.customer_id
WHERE c.customer_name LIKE '%Customer%'
GROUP BY c.customer_uuid HAVING COUNT(o.order_uuid) >= 2;

--Join customer profile and order with customer_id, group them with customer_id and filter where order count is more than 2 and customer_name like input
--and concat order_uuid and order_number
SELECT c.customer_uuid AS customer_uuid, c.customer_name AS customer_name, p.profile_uuid AS profile_uuid, p.email AS email, COUNT(o.order_uuid) AS order_count,
GROUP_CONCAT(o.order_uuid) AS order_uuids, GROUP_CONCAT(o.order_number) AS order_numbers
FROM Customer c
JOIN Profile p ON c.customer_uuid = p.profile_uuid
LEFT JOIN Orders o ON c.customer_uuid = o.customer_id
WHERE c.customer_name LIKE '%Customer%'
GROUP BY c.customer_uuid HAVING COUNT(o.order_uuid) >= 2;




SELECT customer_uuid, customer_name, profile_uuid, email, profile_name, phone_number, street, city, state, zip_code, order_count, order_uuid, order_number
FROM (
    SELECT c.customer_uuid AS customer_uuid, c.customer_name AS customer_name, p.profile_uuid AS profile_uuid, p.email AS email,
    p.name AS profile_name, p.phone_number AS phone_number, p.street AS street, p.city AS city, p.state AS state, p.zip_code AS zip_code, COUNT(o.order_uuid)
    OVER (PARTITION BY c.customer_uuid) AS order_count, o.order_uuid AS order_uuid, o.order_number AS order_number FROM Customer c
    JOIN Profile p ON c.customer_uuid = p.profile_uuid
    LEFT JOIN Orders o ON c.customer_uuid = o.customer_id
    WHERE c.customer_name LIKE '%Customer%' )
subquery
WHERE order_count >= 2;



WITH RankedOrders AS (
    SELECT c.customer_uuid AS customer_uuid, c.customer_name AS customer_name, p.profile_uuid AS profile_uuid, p.email AS email, p.name AS profile_name,
     p.phone_number AS phone_number, p.street AS street, p.city AS city, p.state AS state, p.zip_code AS zip_code, COUNT(o.order_uuid)
     OVER (PARTITION BY c.customer_uuid) AS order_count, o.order_uuid AS order_uuid, o.order_number AS order_number
    FROM Customer c
    JOIN Profile p ON c.customer_uuid = p.profile_uuid
    LEFT JOIN Orders o ON c.customer_uuid = o.customer_id
    WHERE c.customer_name LIKE '%Customer%'
)
SELECT customer_uuid, customer_name, profile_uuid, email, profile_name, phone_number, street, city, state, zip_code, order_count, order_uuid, order_number
FROM RankedOrders WHERE order_count >= 2;








