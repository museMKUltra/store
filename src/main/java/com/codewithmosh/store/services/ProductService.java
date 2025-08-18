package com.codewithmosh.store.services;

import com.codewithmosh.store.entities.Category;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.repositories.CategoryRepository;
import com.codewithmosh.store.repositories.ProductRepository;
import com.codewithmosh.store.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public void createProductWithNewCategory() {
        var category = new Category("New Category");

        var product = Product.builder()
                .name("New Product")
                .description("New Product")
                .price(BigDecimal.valueOf(123))
                .category(category)
                .build();

        productRepository.save(product);
    }

    @Transactional
    public void createProductWithExistingCategory() {
        var category = categoryRepository.findById((byte) 1).orElseThrow();

        var product = Product.builder()
                .name("New Product")
                .description("New Product")
                .price(BigDecimal.valueOf(123))
                .category(category)
                .build();

        productRepository.save(product);
    }

    @Transactional
    public void addProductsToWishlist() {
        var user = userRepository.findById(4L).orElseThrow();

        productRepository.findAll().forEach(product -> {
            user.getWishlist().add(product);
        });

        userRepository.save(user);
    }

    @Transactional
    public void deleteProduct() {
        var product = productRepository.findById(1L).orElseThrow();

        userRepository.findAll().forEach(user -> {
            user.removeWishlist(product);
            userRepository.save(user);
        });

        productRepository.delete(product);
    }
}
