package com.walmart.shopping.product.service;

import com.walmart.shopping.product.Product;
import com.walmart.shopping.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
class ProductBrandOrDescriptionSearch implements Search {

    private ProductRepository productRepository;
    private Integer searchMinimumSize;

    @Autowired
    public ProductBrandOrDescriptionSearch(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.searchMinimumSize = 3;
    }

    @Override
    public List<Product> findProducts(String search) {
        if (search.length() >= this.searchMinimumSize) {
            return this.productRepository.findByDescriptionLikeOrBrandLike(search, search);
        } else {
            return Collections.emptyList();
        }
    }
}
