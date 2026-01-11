package com.brajesh.catalog.domain.product.resources;


import com.brajesh.catalog.domain.product.mapper.ProductMapper;
import com.brajesh.catalog.domain.product.model.api.requestDto.ProductRequestDto;
import com.brajesh.catalog.domain.product.model.api.responseDto.ProductResponseDto;
import com.brajesh.catalog.domain.product.services.ProductService;
import com.brajesh.catalog.domain.product.utils.ValidationUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.brajesh.catalog.domain.product.constant.UriConstants.*;
import static org.slf4j.LoggerFactory.getLogger;


@RestController
@RequestMapping(API_PRODUCT)
public class ProductResource {

    private static final Logger logger= getLogger(ProductResource.class);
    private final ProductService productService;

    public ProductResource(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(API_CREATE_PRODUCT)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductResponseDto> createProducts(@RequestBody ProductRequestDto requestDto) {
        return Mono.just(requestDto)
                .map(ProductMapper::toEntity)
                .flatMap(productService::createProductService)
                .map(ProductMapper::toApiDto)
                .doOnNext(response -> logger.info("Response of Product : {}", response));

    }

    @PostMapping(API_CREATE_BULK_PRODUCT)
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<ProductResponseDto> createBulkProduct(@RequestBody List<ProductRequestDto> productRequestDtoList){
        return ValidationUtils.validateAndReturnList(productRequestDtoList) // Mono<List<ProductRequestDto>>
                .map(ProductMapper::toBulkEntity)                                                         // Mono<List<Product>>
                .flatMapMany(productService::createBulkProductService)      // Flux<Product>
                .map(ProductMapper::toApiDto)                               // Flux<ProductResponseDto>
                .doOnNext(response ->
                        logger.info("Response of list Product : {}", response));
    }

    @GetMapping(PRODUCTS)
    public Mono<List<ProductResponseDto>> listProduct() {
        return productService.listOfAllProduct().
                map(ProductMapper::toApiDto)
                .collectList();
    }

    @GetMapping(PRODUCTS_BY_ID)
    public Mono<ProductResponseDto> retrieveProduct(@RequestParam(required = false) String productId) {

        return Mono.just(productId)
                .filter(ValidationUtils::isMissingOrValidProductId)
                .flatMap(productService::getProduct)
                .map(ProductMapper::toApiDto)
                ;

    }
}
