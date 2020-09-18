package com.walmart.shopping.product.repository;

import com.walmart.shopping.product.Product;

public class MongoInit {

    private static boolean INIT = false;

    public static void init (ProductRepository productRepository){
        if (!INIT) {
            Product product = new Product();
            product.setId(181);
            product.setPrice(500);
            product.setDescription("Bebida");
            product.setBrand("Coca");
            product.setImage("image");
            productRepository.save(product);

            Product product1 = new Product();
            product1.setId(182);
            product1.setPrice(700);
            product1.setDescription("Bebida");
            product1.setBrand("Pensy");
            product1.setImage("image");
            productRepository.save(product1);

            Product product2 = new Product();
            product2.setId(183);
            product2.setPrice(250);
            product2.setDescription("Bebida");
            product2.setBrand("Bilz");
            product2.setImage("image");
            productRepository.save(product2);

            initComplete();
        }
    }

    private static void initComplete() {
        INIT = true;
    }
}
