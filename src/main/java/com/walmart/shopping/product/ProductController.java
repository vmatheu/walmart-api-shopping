package com.walmart.shopping.product;

import com.walmart.shopping.product.exception.FormatErrorInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.walmart.shopping.core.IntelLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ProductController {
    private Logger log;
    private ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.log = LoggerFactory.getLogger(ProductController.class.getName());
        this.productService = productService;
    }

    ProductController(Logger log, ProductService productService) {
        this.log = log;
        this.productService = productService;
    }

    @RequestMapping("/product/{productId}")
    @CrossOrigin
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        IntelLogger.info("getProductById").description("service init")
                .message(String.format("product id %s", productId)).to(log);
        try {
            Product product = productService.getProductById(validateProductId(productId));
            IntelLogger.info("getProductById").description("service end")
                    .message(String.format("product id %d", product.getId())).to(log);

            return ok(product);
        } catch (Exception exception) {
            IntelLogger.error("getProductById").description("service end")
                .message(String.format("product id %s error [%s]", productId, exception.getMessage()))
                .to(log);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
    }

    private Integer validateProductId(String productId) {
        try {
            return Integer.parseInt(productId);
        } catch (NumberFormatException exception) {
            throw new FormatErrorInputException(exception, "invalid format product id");
        }
    }
}
