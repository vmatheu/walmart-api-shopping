package com.walmart.shopping.product;

import com.walmart.shopping.product.exception.NotFoundProductException;
import com.walmart.shopping.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Integer productId) {
        return this.productRepository.findById(productId)
                .orElseThrow(notFoundProductSupplier(productId));
    }

    private Supplier<RuntimeException> notFoundProductSupplier(Integer productId) {
        return () -> {throw new NotFoundProductException(String.format("product id %d is not found", productId));};
    }
}
