package com.codewithmosh.store;

import com.codewithmosh.store.entities.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        var user = new User(1L, "name", "email", "password");
        user.setName("John");
        user.setEmail("john@example.com");
        user.setPassword("password");
    }

}
