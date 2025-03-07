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

    private int priceWithOutDiscount = 0;

    private boolean priceHaveDiscount = false;

    @Override
    public Product clone() {
        Product product = new Product();
        product.setPrice(this.price);
        product.setBrand(this.brand);
        product.setId(this.id);
        product.setDescription(this.description);
        product.setPriceWithOutDiscount(this.priceWithOutDiscount);
        product.setImage(this.image);
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (price != product.price) return false;
        if (priceWithOutDiscount != product.priceWithOutDiscount) return false;
        if (priceHaveDiscount != product.priceHaveDiscount) return false;
        if (brand != null ? !brand.equals(product.brand) : product.brand != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        return image != null ? image.equals(product.image) : product.image == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + priceWithOutDiscount;
        result = 31 * result + (priceHaveDiscount ? 1 : 0);
        return result;
    }
}