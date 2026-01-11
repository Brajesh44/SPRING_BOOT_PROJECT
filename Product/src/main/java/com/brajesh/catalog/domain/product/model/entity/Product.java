package com.brajesh.catalog.domain.product.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "products")

public class Product {
    @Id
    private String Id;
    private String productId;
    private String title;
    private String brand;
    private String manufacturer;
    private BigDecimal price;
}
