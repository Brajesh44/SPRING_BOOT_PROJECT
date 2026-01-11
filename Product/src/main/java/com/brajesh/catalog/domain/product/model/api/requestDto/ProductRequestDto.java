package com.brajesh.catalog.domain.product.model.api.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class ProductRequestDto {

    private String productId;
    private String title;
    private String brand;
    private String manufacturer;
    private BigDecimal price;
}
