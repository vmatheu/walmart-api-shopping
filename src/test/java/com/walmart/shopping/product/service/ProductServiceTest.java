package com.walmart.shopping.product.service;

import com.walmart.shopping.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Test
    void shouldReturnCollection() {
        ProductIdSearch search = mock(ProductIdSearch.class);
        List<Product> products = new ArrayList<>();
        when(search.findProducts(anyString())).thenReturn(products);
        ProductService productService = new ProductService(search, null);
        Assertions.assertThat(productService.findProductByString("1")).asList();
    }
}