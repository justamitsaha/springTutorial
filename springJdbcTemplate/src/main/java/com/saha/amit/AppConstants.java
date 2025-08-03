package com.saha.amit;

public class AppConstants {
    public static final String NO_PRODUCT_EXCEPTION_MESSAGE = "Order Can't be created without Products";
    public static final String INVALID_PRODUCT_EXCEPTION_MESSAGE = "Orders can't be created for invalid products";
    public static final String CREATE_ORDER_DESCRIPTION = """
            Creates a new order for a given customer.
            1. Validates that all provided product UUIDs exist in the database.
            2. Generates a new order UUID and saves the order details.
            3. Creates entries in the Order_Product join table for each associated product.
            4. Creates a default payment record with status 'PROCESSING' linked to the order.
            """;
    public static final String CREATE_PRODUCT_DESCRIPTION = """
            Operations performed:
            1. Inserts the product into the `Product` table with the provided name and price.
            2. Automatically sets `created_date` and `modified_date`.
            3. Retrieves the generated product UUID (primary key).
            4. Inserts entries into the `product_category` join table to associate the product with each given category ID.
            """;
    public static final String CREATE_CUSTOMER_DESCRIPTION = """
            Creates a new entry in the Profile table and then links it to a new Customer entry
            The profile UUID is used as the primary key for the Customer. Returns the generated UUID.
            """;
}
