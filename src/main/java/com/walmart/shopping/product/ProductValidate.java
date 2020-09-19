package com.walmart.shopping.product;

public abstract class ProductValidate {

    public static boolean isNumeric(String str) {
        return str.matches("^[0-9]{1,}");
    }

    public static boolean isAlphanumeric(String str) {
        return str.matches("[0-9a-zA-Zñ]{1,}");
    }
}

