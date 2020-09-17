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

    @Autowired
    public ProductBrandOrDescriptionSearch(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findProducts(String search) {
        if (search.length() >= 3) {
            return this.productRepository.findByDescriptionLikeOrBrandLike(search, search);
        } else {
            return Collections.emptyList();
        }
    }
}
