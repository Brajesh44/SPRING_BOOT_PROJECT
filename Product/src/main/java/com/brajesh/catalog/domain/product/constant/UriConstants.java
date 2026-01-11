package com.brajesh.catalog.domain.product.constant;

public class UriConstants {
    private UriConstants() {
    }

    public static final String API_PRODUCT = "/api/v1";
    public static final String PRODUCTS = "/products";
    public static final String API_CREATE_PRODUCT = PRODUCTS;
    public static final String API_CREATE_BULK_PRODUCT = PRODUCTS+"/bulk";
    public static final String PRODUCTS_BY_ID = "/products/productId";

}
