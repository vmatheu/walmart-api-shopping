package com.walmart.shopping.product;

import com.walmart.shopping.product.exception.NotFoundProductException;
import com.walmart.shopping.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyInt;

class ProductServiceTest {

    @Test
    void shouldReturnProductObject() {
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(new Product()));
        ProductService productService = new ProductService(productRepository);
        Assertions.assertThat(productService.getProductById(1)).isInstanceOf(Product.class);
    }

    @Test
    void shouldReturnExceptionWhenNotHaveIdProduct() {
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        ProductService productService = new ProductService(productRepository);

        Exception exception = assertThrows(NotFoundProductException.class, () -> {
            productService.getProductById(1);
        });

        String expectedMessage = "product id 1 is not found";
        Assertions.assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }
}