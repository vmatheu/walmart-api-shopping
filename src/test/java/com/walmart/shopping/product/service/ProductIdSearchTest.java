package com.walmart.shopping.product.service;

import com.walmart.shopping.product.Product;
import com.walmart.shopping.product.exception.NotFoundProductException;
import com.walmart.shopping.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductIdSearchTest {

    @Test
    void shouldReturnExceptionWhenNotHaveIdProduct() {
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        ProductIdSearch productIdSearch = new ProductIdSearch(productRepository);

        Exception exception = assertThrows(NotFoundProductException.class, () -> {
            productIdSearch.findProducts("1");
        });

        String expectedMessage = "product id 1 is not found";
        Assertions.assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void shouldReturnListWhitOnlyHaveOneItem() {
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(new Product()));
        ProductIdSearch productIdSearch = new ProductIdSearch(productRepository);
        Assertions.assertThat(productIdSearch.findProducts("1")).containsOnlyOnce(new Product());
    }
}