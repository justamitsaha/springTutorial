package com.saha.amit.constants;

public class AppConstants {
    public static final String GET_CUSTOMER_PROFILE_WITH_ID_SUMMARY = "Join CUSTOMER PROFILE based on customer id";
    public static final String GET_CUSTOMER_PROFILE_WITH_ID_DESCRIPTION = """
            1. It will join customer with profile via JPQL or use projections based on customer Id.
            2. Query param flag will decide if will use JPA named query or JPQL
                - flag = true will use JPQL
                - Else will use JPQL
            3. Returned data will be returned of type CustomerDto
            """;

    public static final String GET_CUSTOMER_PROFILE_ORDER_WITH_ID_SUMMARY = "Join customer profile, and order based on customer id";
    public static final String GET_CUSTOMER_PROFILE_ORDER_WITH_ID_DESCRIPTION = "It will join customer with profile and orders with primary key customer id." +
            "Join can happen using JPQL or native query, while native query will do it with single query, JPQL will trigger unwanted payment query. " +
            "Optional request param flag when true will trigger JPQL, when false will trigger native query, if not send will trigger randomly ";
}
