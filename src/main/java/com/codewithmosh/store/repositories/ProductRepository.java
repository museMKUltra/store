package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Category;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.projections.ProductSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCriteriaRepository, JpaSpecificationExecutor<Product> {
    // String
    List<Product> findByName(String name); // select * from product where name = ?

    List<Product> findByNameLike(String name); // select * from product where name like ?

    List<Product> findByNameNotLike(String name);

    List<Product> findByNameContaining(String name);

    List<Product> findByNameStartingWith(String name);

    List<Product> findByNameEndingWith(String name);

    List<Product> findByNameEndingWithIgnoreCase(String name);

    // Numbers
    List<Product> findByPrice(BigDecimal price); // select * from product where price = ?

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceGreaterThanEqual(BigDecimal price);

    List<Product> findByPriceLessThanEqual(BigDecimal price);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    // Null
    List<Product> findByDescriptionNull();

    List<Product> findByDescriptionNotNull();

    // Multiple conditions
    List<Product> findByDescriptionNullAndNameNull();

    // Sort (OrderBy)
    List<Product> findByNameOrderByPrice(String name);

    // Limit (Top/First)
    List<Product> findTop5ByNameOrderByPrice(String name);

    List<Product> findFirst5ByNameLikeOrderByPrice(String name);

    // Find products whose price are in a given range and sort by name
    @Procedure("findProductsByPrice")
    List<Product> findProducts(BigDecimal min, BigDecimal max);

    @Query("select count(*) from Product p where p.price between :min and :max")
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

    //    @Query("select new com.codewithmosh.store.projections.ProductSummaryDto(p.id, p.name) from Product p where p.category = :category")
    @Query("select p.id, p.name from Product p where p.category = :category")
    List<ProductSummaryDto> findByCategory(@Param("category") Category category);
}
