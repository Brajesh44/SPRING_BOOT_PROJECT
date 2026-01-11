package com.brajesh.catalog.domain.product.services;

import com.brajesh.catalog.domain.product.exception.ProductDetailsException;
import com.brajesh.catalog.domain.product.exception.ProductException;
import com.brajesh.catalog.domain.product.model.entity.Product;
import com.brajesh.catalog.domain.product.repository.ProductRepository;
import com.brajesh.catalog.domain.product.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static com.brajesh.catalog.domain.product.exception.ProductDetailsException.INVALID_PRODUCT_ID;


@Service
public class ProductService {
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;


    private final ProductRepository productRepository;

    public ProductService(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Product> createProductService(Product product) {

        return productRepository.save(product);
    }

    public Flux<Product> createBulkProductService(List<Product> productList){

        return productRepository.saveAll(productList);
    }

    public Flux<Product> listOfAllProduct(){
        return productRepository.findAll();
    }

    public Mono<Product> getProduct(String productId) {

        return Mono.just(productId)
                .map(QueryUtils::findByProductID)
                .flatMap(q -> mongoTemplate.findOne(q, Product.class))
                .switchIfEmpty(Mono.error(new ProductException(INVALID_PRODUCT_ID)));
    }




}
