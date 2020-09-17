package com.walmart.shopping.product.service;

import com.walmart.shopping.product.Product;
import com.walmart.shopping.product.exception.NotFoundProductException;
import com.walmart.shopping.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Component
class ProductIdSearch implements Search{

    private ProductRepository productRepository;

    @Autowired
    public ProductIdSearch(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findProducts(String search) {
        List<Product> results = new ArrayList<>();
        results.add(this.productRepository.findById(Integer.parseInt(search))
                .orElseThrow(notFoundProductSupplier(search)));
        return results;
    }

    private Supplier<RuntimeException> notFoundProductSupplier(String search) {
        return () -> {throw new NotFoundProductException(String.format("product id %s is not found", search));};
    }
}
