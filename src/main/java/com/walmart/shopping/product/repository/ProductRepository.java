package com.walmart.shopping.product.repository;

import com.walmart.shopping.product.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {
    List<Product> findByDescriptionLikeOrBrandLike(String description, String brand);
}