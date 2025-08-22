package com.codewithmosh.store.services;

import com.codewithmosh.store.entities.*;
import com.codewithmosh.store.repositories.*;
import com.codewithmosh.store.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("John")
                .email("john@example.com")
                .password("password")
                .build();

        System.out.println(entityManager.contains(user) ? "Persistent" : "Transient / Detached"); // Transient / Detached

        userRepository.save(user);

        System.out.println(entityManager.contains(user) ? "Persistent" : "Transient / Detached"); // Persistent
    }

    @Transactional
    public void showRelatedEntities() {
        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    @Transactional
    public void showRelatedAddress() {
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address.getUser().getEmail());
    }

    public void persistRelated() {
        var user = User.builder()
                .name("John")
                .email("john@example.com")
                .password("password")
                .build();

        var address = Address.builder()
                .street("street")
                .city("city")
                .state("state")
                .zip("zip")
                .build();

        user.addAddress(address);

        userRepository.save(user);
        System.out.println(entityManager.contains(user) ? "Persistent" : "Transient / Detached");
    }

    @Transactional
    public void deleteRelated() {
        var user = userRepository.findById(8L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void fetchUser() {
        var user = userRepository.findByEmail("john.doe@example.com").orElseThrow();
        System.out.println(user);
    }

    @Transactional
    public void fetchUsers() {
        var users = userRepository.findAllWithAddresses();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    public void populateUsers() {
        var user = User.builder()
                .name("Twenty")
                .email("twenty@example.com")
                .password("password")
                .build();

        var profile = new Profile(user);
        profile.setLoyaltyPoints(20);

        profileRepository.save(profile);
    }

    @Transactional
    public void findLoyaltyPoints() {
        var users = userRepository.findLoyalUsers(2);
        users.forEach(u -> {
            System.out.println(u.getId() + ": " + u.getEmail());
        });
    }

    @Transactional
    public void fetchProducts() {
        var product = new Product();
        product.setName("product");

        var matcher = ExampleMatcher.matching()
                .withIncludeNullValues()
                .withIgnorePaths("id", "description")
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);

        var products = productRepository.findAll(example);
        products.forEach(System.out::println);
    }

    public void fetchProductByCriteria() {
        var category = categoryRepository.findById((byte) 1).orElseThrow();
        var products = productRepository.findProductsByCriteria(null, null, null, category);
        products.forEach(System.out::println);
    }

    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice, Category category) {
        Specification<Product> spec = Specification.allOf();

        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }

        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }

        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }

        if (category != null) {
            spec = spec.and(ProductSpec.hasCategory(category));
        }

        productRepository.findAll(spec).forEach(System.out::println);
    }

    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        fetchProductsBySpecifications(name, minPrice, maxPrice, null);
    }

    public void fetchProductsBySpecifications() {
        var category = categoryRepository.findById((byte) 1).orElseThrow();
        fetchProductsBySpecifications(null, null, null, category);
    }
}
