package com.brajesh.catalog.domain.product.model.api.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class ProductResponseDto {

    private String productId;        // ASIN / SKU
    private String title;
    private String brand;
    private String manufacturer;
    private BigDecimal price;
}
