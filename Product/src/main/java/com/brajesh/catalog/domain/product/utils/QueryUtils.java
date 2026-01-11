package com.brajesh.catalog.domain.product.utils;

import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@NoArgsConstructor
public class QueryUtils {

    public static Query findByProductID(String ProductId) {

        Query q = new Query();
        q.addCriteria(where("ProductId").is(ProductId));
        return q;
    }
}
