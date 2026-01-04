package com.brajesh.catalog.domain.product.resources;


import com.brajesh.catalog.domain.product.api.responseDto.ProductResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.brajesh.catalog.domain.product.constant.UriConstants.API_CREATE_PRODUCT;
import static com.brajesh.catalog.domain.product.constant.UriConstants.API_PRODUCT;

@RestController
@RequestMapping(API_PRODUCT)
public class ProductResource {

    @PostMapping(API_CREATE_PRODUCT)
    @ResponseStatus()
    public Mono<List<ProductResponseDto>> createProducts(){
        return null;
    }
}
