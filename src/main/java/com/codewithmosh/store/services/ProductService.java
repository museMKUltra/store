package com.codewithmosh.store.services;

import com.codewithmosh.store.entities.Category;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.repositories.CategoryRepository;
import com.codewithmosh.store.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public void createProductWithNewCategory() {
        var product = Product.builder()
                .name("New Product")
                .description("New Product")
                .price(BigDecimal.valueOf(123))
                .build();

        var category = Category.builder()
                .name("New Category")
                .build();

        product.addCategory(category);

        productRepository.save(product);
    }

    @Transactional
    public void createProductWithExistingCategory() {
        var product = Product.builder()
                .name("New Product")
                .description("New Product")
                .price(BigDecimal.valueOf(123))
                .build();

        var category = categoryRepository.findById(1L).orElseThrow();

        product.addCategory(category);

        productRepository.save(product);
    }
}
