package com.walmart.shopping.product.service;

import com.walmart.shopping.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductValidate {

    @Test
    void shouldReturnPriceDiscountFiftyPercentWhenSearchIsPalindrome() {
       Product product = new Product();
       product.setPrice(100);
       Product productResult = ProductDiscount.validateDiscount(product, "xxaxx");
       Assertions.assertThat(productResult.getPrice()).isEqualTo(product.getPrice()/2);
    }

}