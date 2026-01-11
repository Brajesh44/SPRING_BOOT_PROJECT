package com.brajesh.catalog.domain.product.mapper;

import com.brajesh.catalog.domain.product.model.api.requestDto.ProductRequestDto;
import com.brajesh.catalog.domain.product.model.api.responseDto.ProductResponseDto;
import com.brajesh.catalog.domain.product.model.entity.Product;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ProductMapper {

    public static Product toEntity(ProductRequestDto productRequestDto) {

        return Product.builder()
                .productId(productRequestDto.getProductId())
                .title(productRequestDto.getTitle())
                .brand(productRequestDto.getBrand())
                .manufacturer(productRequestDto.getManufacturer())
                .price(productRequestDto.getPrice())
                .build();
    }

    public static ProductResponseDto toApiDto(Product productResponse) {

        return ProductResponseDto.builder()
                .productId(productResponse.getProductId())
                .title(productResponse.getTitle())
                .brand(productResponse.getBrand())
                .manufacturer(productResponse.getManufacturer())
                .price(productResponse.getPrice())
                .build();
    }

    public static List<Product> toBulkEntity(List<ProductRequestDto> productRequestDto) {

        return productRequestDto
                .parallelStream()
                .map(pr -> Product.builder()
                        .productId(pr.getProductId())
                        .title(pr.getTitle())
                        .brand(pr.getBrand())
                        .manufacturer(pr.getManufacturer())
                        .price(pr.getPrice())
                        .build())
                .toList();
    }

    public static List<ProductResponseDto> toApiBulkDto(List<Product> productResponse) {

        return productResponse
                .parallelStream()
                .map(product -> ProductResponseDto.builder()
                        .productId(product.getProductId())
                        .title(product.getTitle())
                        .brand(product.getBrand())
                        .manufacturer(product.getManufacturer())
                        .price(product.getPrice())
                        .build()).toList();


    }
}
