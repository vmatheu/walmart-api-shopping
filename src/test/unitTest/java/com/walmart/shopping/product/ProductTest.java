package com.walmart.shopping.product;

import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;

class ProductTest {

    @Test
    void shouldBeCloneProduct() {
        Product product = new Product();
        product.setImage("image");
        product.setPriceWithOutDiscount(50);
        product.setDescription("desc");
        product.setId(1);
        product.setBrand("brand");
        product.setPrice(100);

        Product productClone = product.clone();
        Assertions.assertThat(product.equals(productClone)).isTrue();
    }
}