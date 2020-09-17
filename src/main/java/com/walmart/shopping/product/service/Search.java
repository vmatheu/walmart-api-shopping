package com.walmart.shopping.product.service;

import com.walmart.shopping.product.Product;

import java.util.List;

interface Search {
    List<Product> findProducts(String search);
}
