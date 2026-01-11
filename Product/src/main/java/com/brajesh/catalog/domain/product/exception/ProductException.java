package com.brajesh.catalog.domain.product.exception;

import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {

    private final ProductDetailsException code;

    public ProductException(ProductDetailsException code) {
        super(code.getLabel());
        this.code = code;
    }

}
