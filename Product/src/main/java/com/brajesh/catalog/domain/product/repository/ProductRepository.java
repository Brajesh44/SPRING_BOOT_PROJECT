package com.brajesh.catalog.domain.product.repository;

import com.brajesh.catalog.domain.product.model.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
