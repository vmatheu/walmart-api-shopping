package com.walmart.shopping.product.service;

import com.walmart.shopping.product.Product;

public class ProductDiscount {
    public static Product validateDiscount(Product product, String search){
        if (isPalindrome(search)) {
            Product productDiscount = product.clone();
            productDiscount.setPriceWithOutDiscount(product.getPrice());
            productDiscount.setPrice(product.getPrice()/2);
            productDiscount.setPriceHaveDiscount(true);
            return productDiscount;
        }
        return product;
    }

    private static boolean isPalindrome(String brandOrDescription) {
        String reverse=new StringBuilder(brandOrDescription).reverse().toString();
        return reverse.equals(brandOrDescription);
    }
}
