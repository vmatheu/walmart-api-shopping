package com.walmart.shopping.product.service;

import com.walmart.shopping.core.IntelLogger;
import com.walmart.shopping.product.Product;
import com.walmart.shopping.product.exception.MinimumSizeSearchException;
import com.walmart.shopping.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Slf4j
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
        IntelLogger.info("findProducts").description("ByBrandOrDescription")
                .message(String.format("product search %s", search)).to(log);
        if (search.length() >= this.searchMinimumSize) {

            return this.productRepository.findByDescriptionLikeOrBrandLike(search, search);
        } else {
            IntelLogger.info("findProducts").description("ByBrandOrDescription")
                    .message("searchMinimumSize alert").to(log);

            throw new MinimumSizeSearchException(
                    String.format("minimim size for search is %d", this.searchMinimumSize));
        }
    }
}
