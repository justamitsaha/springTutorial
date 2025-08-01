package com.saha.amit;

public class AppConstants {
    public static final String NO_PRODUCT_EXCEPTION_MESSAGE = "Order Can't be created without Products";
    public static final String INVALID_PRODUCT_EXCEPTION_MESSAGE = "Orders can't be created for invalid products";
    public static final String CREATE_ORDER_DESCRIPTION = """
            Schema has properly Map<Long, ProductDto> products  which is not getting formatted properly by swagger and
            swagger is generating sample like additionalProp which will throw error hence @Schema() is providing a sample
            as per proper request
            """;
}
