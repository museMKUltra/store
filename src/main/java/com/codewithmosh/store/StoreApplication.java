package com.codewithmosh.store;

import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.entities.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        var user = User.builder()
                .id(1L)
                .name("John")
                .email("john@example.com")
                .password("password")
                .build();

        user.getWishlist().add(new Product());

        System.out.println(user);
    }

}
