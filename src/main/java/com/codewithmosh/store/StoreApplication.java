package com.codewithmosh.store;

import com.codewithmosh.store.entities.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        var user = User.builder()
                .name("John")
                .password("password")
                .email("john@example.com")
                .build();
    }

}
