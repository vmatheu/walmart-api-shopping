package com.walmart.shopping.product;

import com.walmart.shopping.product.exception.NotFoundProductException;
import com.walmart.shopping.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findProductByString(String search) {
        if (isNumeric(search)) {
            List<Product> results = new ArrayList<>();
            results.add(this.productRepository.findById(Integer.parseInt(search))
                    .orElseThrow(notFoundProductSupplier(search)));
            return results;
        } else {
            return Collections.emptyList();
        }
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private Supplier<RuntimeException> notFoundProductSupplier(String search) {
        return () -> {throw new NotFoundProductException(String.format("product id %s is not found", search));};
    }
}
