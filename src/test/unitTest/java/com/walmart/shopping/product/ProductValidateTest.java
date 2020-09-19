package com.walmart.shopping.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class ProductValidateTest {
    @Test
    void ShouldReturnTrueWhenStringIsNumber() {
        Assertions.assertThat(ProductValidate.isNumeric("181")).isTrue();
    }

    @Test
    void ShouldReturnFalseWhenStringIsNegativeNumber() {
        Assertions.assertThat(ProductValidate.isNumeric("-181")).isFalse();
    }

    @Test
    void ShouldReturnFalseWhenStringNotIsNumber() {
        Assertions.assertThat(ProductValidate.isNumeric("a")).isFalse();
    }

    @Test
    void ShouldReturnTrueWhenStringIsAlphanumericString() {
        Assertions.assertThat(ProductValidate.isAlphanumeric("aañ123")).isTrue();
    }

    @Test
    void ShouldReturnFalseWhenStringHaveNotPermitedCharacter() {
        Assertions.assertThat(ProductValidate.isAlphanumeric("-1")).isFalse();
    }
}