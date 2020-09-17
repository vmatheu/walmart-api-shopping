package com.walmart.shopping.product.service;

import com.walmart.shopping.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService {

    private Search productIdSearch;
    private Search productBrandOrDescriptionSearch;

    @Autowired
    public ProductService(ProductIdSearch idSearch, ProductBrandOrDescriptionSearch brandOrDescriptionSearch) {
        this.productIdSearch = idSearch;
        this.productBrandOrDescriptionSearch = brandOrDescriptionSearch;
    }

    public List<Product> findProductByString(String search) {
        return getSearch(search)
                .findProducts(search)
                .stream().map( product -> ProductDiscount.validateDiscount(product, search))
                .collect(toList());
    }

    private Search getSearch(String search) {
        return isNumeric(search)? productIdSearch : productBrandOrDescriptionSearch;
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }


}
