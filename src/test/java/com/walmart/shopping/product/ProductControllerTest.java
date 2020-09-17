package com.walmart.shopping.product;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.walmart.shopping.product.service.ProductService;
import com.walmart.shopping.utils.LoggerUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ProductControllerTest {

	@Test
    void shouldReturnProductWhenIdFormatNumberIsCorrect() {
        ProductService productService = mock(ProductService.class);
        Product product = new Product();
        product.setId(123);
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(productService.findProductByString(anyString())).thenReturn(products);
        ProductController productController = new ProductController(productService);
        ResponseEntity<List<Product>> response = productController.findProductByString("123");
        Assertions.assertThat(response.getBody().get(0).getId()).isEqualTo(123);
    }

    @Test
    void shouldReturnLogsServiceInitAndServiceEnd() {
        LoggerUtil loggerUtil = new LoggerUtil();
        ProductService productService = mock(ProductService.class);
        List<Product> products = Collections.emptyList();
        when(productService.findProductByString(anyString())).thenReturn(products);
        ProductController productController = new ProductController(loggerUtil.getLogger(), productService);
        List<ILoggingEvent> logsList = loggerUtil.getList();

        productController.findProductByString("123");
        Assertions.assertThat(logsList.size()).isEqualTo(2);
        Assertions.assertThat(logsList.get(0).toString())
                .isEqualTo("[INFO] requestId=, action=findProductByString, description=service init, message=product search 123");
        Assertions.assertThat(logsList.get(1).toString())
                .isEqualTo("[INFO] requestId=, action=findProductByString, description=service end, message=product search 123");
    }
}
