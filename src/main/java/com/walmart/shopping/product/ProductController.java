package com.walmart.shopping.product;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ProductController {

	@RequestMapping("/product")
    public String getProductById() {
        return "OK";
    }
}
