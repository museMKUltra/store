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
        var user = userRepository.findById(5L).orElseThrow();
        var products = productRepository.findAll();

        products.forEach(user::addWishlist);
        userRepository.save(user);
    }

    @Transactional
    public void deleteProduct() {
        productRepository.deleteById(2L);
    }

    @Transactional
    public void updateProductPrices() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10), (byte) 1);
    }

    public void fetchProducts() {
        var products = productRepository.findByCategory(new Category((byte) 1));
        products.forEach(System.out::println);
    }

    @Transactional
    public void findProducts() {
        var products = productRepository.findProducts(BigDecimal.valueOf(0), BigDecimal.valueOf(200));
        products.forEach(System.out::println);
    }
}
