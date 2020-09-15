package com.walmart.shopping.product;

import java.io.Serializable;

public class Product implements Serializable {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
