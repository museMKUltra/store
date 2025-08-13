package com.codewithmosh.store;

import com.codewithmosh.store.entities.Product;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        var product = Product.builder()
                .name("product")
                .price(BigDecimal.valueOf(123))
                .build();

        product.addCategory("category");
    }

}
