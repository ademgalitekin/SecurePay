package com.firisbe.SecurePay.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class RestPathConstants {

    /*********ROOT******/
    public static final String ROOT_PATH = "/api/demo";

    /*********CUSTOMER******/
    public static final String ROOT_CUSTOMER_PATH = ROOT_PATH + "/customer";
    public static final String SIGN_IN_PATH =  "/signin";
    public static final String SIGN_UP_PATH =  "/signup";

    /*********PAYMENT******/
    public static final String ROOT_PAYMENT_PATH = ROOT_PATH + "/payment";
    public static final String PATH_SEARCH = "/search";
    public static final String PATH_CUSTOMER_EMAIL_VARIABLE = "/{customerEmail}";
    public static final String PATH_DATE_INTERVAL = "/date-interval";

    /*********SWAGGER******/
    public static final String SWAGGER =  "/swagger-ui.html";
    public static final String SWAGGER_UI =  "/swagger-ui";
    public static final String SWAGGER_RESOURCES =  "/swagger-resources";

    /*********CONFIGURATION******/
    public static final String ANY_OTHER =  "/**";
    public static final String V2_API_DOCS =  "/v2/api-docs";
    public static final String V3_API_DOCS =  "/v3/api-docs";
}
