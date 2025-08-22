package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Category;
import com.codewithmosh.store.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice, Category category) {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            // name like %name%
            predicates.add(cb.like(root.get("name"), "%" + name + "%"));
        }
        if (minPrice != null) {
            // price >= minPrice
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            // price <= maxPrice
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }
        if (category != null) {
            predicates.add(cb.equal(root.get("category"), category));
        }

        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }
}
