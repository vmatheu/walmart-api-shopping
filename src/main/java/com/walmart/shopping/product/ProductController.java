package com.walmart.shopping.product;

import com.walmart.shopping.product.exception.FormatErrorInputException;
import com.walmart.shopping.product.service.ProductService;
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

import java.util.List;

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

    @RequestMapping("/product/filter/{search}")
    @CrossOrigin
    public ResponseEntity<List<Product>> findProductByString(@PathVariable String search) {
        IntelLogger.info("findProductByString").description("service init")
                .message(String.format("product search %s", search)).to(log);
        try {
            List<Product> products = productService.findProductByString(validateSearchString(search));
            IntelLogger.info("findProductByString").description("service end")
                    .message(String.format("product search %s", search)).to(log);

            return ok(products);
        } catch (Exception exception) {
            IntelLogger.error("findProductByString").description("service end")
                .message(String.format("product search %s error [%s]", search, exception.getMessage()))
                .to(log);
           throw exception;
        }
    }

    private String validateSearchString(String search) {
      if (!ProductValidate.isAlphanumeric(search)) {
          throw new FormatErrorInputException(String.format("search %s is invalid", search));
      }
      return search;
    }
}
