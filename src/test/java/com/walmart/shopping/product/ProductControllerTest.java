package com.walmart.shopping.product;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.walmart.shopping.utils.LoggerUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

class ProductControllerTest {

	@Test
    void shouldReturnProductWhenIdFormatNumberIsCorrect() {
        ProductController productController = new ProductController(Mockito.mock(ProductService.class));
        ResponseEntity<Product> product = productController.getProductById("123");
        Assertions.assertThat(product.getBody().getId()).isEqualTo(123);
    }

    @Test
    void shouldReturnErrorWhenIdFormatNumberIsNotCorrect() {
        ProductController productController = new ProductController(Mockito.mock(ProductService.class));
        ResponseEntity<Product> product = productController.getProductById("123a");
        Assertions.assertThat(product.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void shouldReturnLogsServiceInitAndServiceEnd() {
        LoggerUtil loggerUtil = new LoggerUtil();
        ProductController productController = new ProductController(loggerUtil.getLogger());
        productController.getProductById("123");

        List<ILoggingEvent> logsList = loggerUtil.getList();

        Assertions.assertThat(logsList.size()).isEqualTo(2);
        Assertions.assertThat(logsList.get(0).toString())
                .isEqualTo("[INFO] requestId=, action=getProductById, description=service init, message=product id 123");
        Assertions.assertThat(logsList.get(1).toString())
                .isEqualTo("[INFO] requestId=, action=getProductById, description=service end, message=product id 123");
    }
}
