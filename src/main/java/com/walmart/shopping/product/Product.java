package com.walmart.shopping.product;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Document(collection = "products")
@Data
public class Product implements Serializable {
    @Field("id")
    private int id;

    @Field("brand")
    private String brand;

    @Field("description")
    private String description;

    @Field("image")
    private String image;

    @Field("price")
    private int price;
}