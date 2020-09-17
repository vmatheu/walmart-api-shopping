package com.walmart.shopping.product.service;

import com.walmart.shopping.product.Product;
import com.walmart.shopping.product.exception.NotFoundProductException;
import com.walmart.shopping.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductBrandOrDescriptionSearchTest {

    @Test
    void shouldReturnEmptyListWhenNotHaveMatch() {
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findByDescriptionLikeOrBrandLike(anyString(), anyString()))
                .thenReturn(Collections.emptyList());
        Search search = new ProductBrandOrDescriptionSearch(productRepository);
        Assertions.assertThat(search.findProducts("1ad")).isEmpty();
    }

    @Test
    void shouldReturnListWithResultWhenHaveMatch() {
        ProductRepository productRepository = mock(ProductRepository.class);
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());
        when(productRepository.findByDescriptionLikeOrBrandLike(anyString(), anyString()))
                .thenReturn(products);
        Search search = new ProductBrandOrDescriptionSearch(productRepository);
        Assertions.assertThat(search.findProducts("1ad").size()).isEqualTo(3);
    }
}