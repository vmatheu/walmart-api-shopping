package com.walmart.shopping.product.service;

import com.walmart.shopping.product.Product;
import com.walmart.shopping.product.exception.MinimumSizeSearchException;
import com.walmart.shopping.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductBrandOrDescriptionSearchTest {

    private static final Integer searchMinimumSize = 3;

    @Test
    void shouldReturnEmptyListWhenNotHaveMatch() {
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findByDescriptionLikeOrBrandLike(anyString(), anyString()))
                .thenReturn(Collections.emptyList());
        Search search = new ProductBrandOrDescriptionSearch(productRepository, searchMinimumSize);
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
        Search search = new ProductBrandOrDescriptionSearch(productRepository, searchMinimumSize);
        Assertions.assertThat(search.findProducts("1ad").size()).isEqualTo(3);
    }

    @Test
    void shouldReturnEmptyListWhenStringSearchIsLessThanThreeCharacter() {
        ProductRepository productRepository = mock(ProductRepository.class);
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        when(productRepository.findByDescriptionLikeOrBrandLike(anyString(), anyString()))
                .thenReturn(products);
        Search search = new ProductBrandOrDescriptionSearch(productRepository, searchMinimumSize);

        Exception exception = assertThrows(MinimumSizeSearchException.class, () -> {
            search.findProducts("1a");
        });

        String expectedMessage = "minimim size for search is 3";
        Assertions.assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }
}