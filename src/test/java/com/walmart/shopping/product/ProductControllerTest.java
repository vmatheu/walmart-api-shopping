package com.walmart.shopping.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductControllerTest {

	@Test
    public void getProductById() {
        ProductController productController = new ProductController();
        Assertions.assertThat(productController.getProductById()).isEqualTo("OK");
    }
}
