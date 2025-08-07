package com.saha.amit.constants;

public class AppConstants {
    public static final String GET_CUSTOMER_PROFILE_WITH_ID_SUMMARY = "Join CUSTOMER PROFILE based on customer id";
    public static final String GET_CUSTOMER_PROFILE_WITH_ID_DESCRIPTION = "It will join customer with profile via JPQL or use projections based on customer Id. Both " +
            "will do it with single query. Optional request param flag when true will trigger JPQL, when false will trigger projections, if not send will trigger JPQL";

    public static final String GET_CUSTOMER_PROFILE_ORDER_WITH_ID_SUMMARY = "Join customer profile, and order based on customer id";
    public static final String GET_CUSTOMER_PROFILE_ORDER_WITH_ID_DESCRIPTION = "It will join customer with profile and orders with primary key customer id." +
            "Join can happen using JPQL or native query, while native query will do it with single query, JPQL will trigger unwanted payment query. " +
            "Optional request param flag when <b>true</b> will trigger <b>JPQL</b>, when <b>false</b> will trigger <b>native query</b>, if not send will trigger randomly ";
}
