package com.walmart.shopping.product.service;

import com.walmart.shopping.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductIdSearch idSearch;
    private ProductBrandOrDescriptionSearch brandOrDescriptionSearch;

    @Autowired
    public ProductService(ProductIdSearch idSearch, ProductBrandOrDescriptionSearch brandOrDescriptionSearch) {
        this.idSearch = idSearch;
        this.brandOrDescriptionSearch = brandOrDescriptionSearch;
    }

    public List<Product> findProductByString(String search) {
        return getSearch(search).findProducts(search);
    }

    private Search getSearch(String search) {
        return isNumeric(search)?idSearch:brandOrDescriptionSearch;
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }


}
