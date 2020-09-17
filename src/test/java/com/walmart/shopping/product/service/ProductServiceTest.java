package com.walmart.shopping.product.service;

import com.walmart.shopping.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Test
    void shouldReturnCollectionWithProductIdSearch() {
        ProductIdSearch search = mock(ProductIdSearch.class);
        List<Product> products = new ArrayList<>();
        when(search.findProducts(anyString())).thenReturn(products);
        ProductService productService = new ProductService(search, null);
        Assertions.assertThat(productService.findProductByString("1")).asList();
        verify(search).findProducts(any(String.class));
    }

    @Test
    void shouldReturnCollectionWithProductBrandOrDescriptionSearch() {
        ProductBrandOrDescriptionSearch search = mock(ProductBrandOrDescriptionSearch.class);
        List<Product> products = new ArrayList<>();
        when(search.findProducts(anyString())).thenReturn(products);
        ProductService productService = new ProductService(null, search);
        Assertions.assertThat(productService.findProductByString("1a")).asList();
        verify(search).findProducts(any(String.class));
    }
}