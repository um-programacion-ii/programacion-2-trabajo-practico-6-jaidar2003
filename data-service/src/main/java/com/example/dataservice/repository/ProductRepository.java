package com.example.dataservice.repository;

import com.example.dataservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository interface for Product entity.
 * Provides CRUD operations and custom queries for products.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * Find products by name containing the given string (case insensitive).
     *
     * @param name the name pattern to search for
     * @return list of products matching the name pattern
     */
    List<Product> findByNameContainingIgnoreCase(String name);
    
    /**
     * Find products by category id.
     *
     * @param categoryId the category id
     * @return list of products in the specified category
     */
    List<Product> findByCategoryId(Long categoryId);
    
    /**
     * Find products with price less than or equal to the given value.
     *
     * @param price the maximum price
     * @return list of products with price less than or equal to the given value
     */
    List<Product> findByPriceLessThanEqual(BigDecimal price);
    
    /**
     * Find products with price greater than or equal to the given value.
     *
     * @param price the minimum price
     * @return list of products with price greater than or equal to the given value
     */
    List<Product> findByPriceGreaterThanEqual(BigDecimal price);
    
    /**
     * Find products with price between the given values.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return list of products with price between the given values
     */
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    /**
     * Custom query to find products by category name.
     *
     * @param categoryName the category name
     * @return list of products in the specified category
     */
    @Query("SELECT p FROM Product p JOIN p.category c WHERE LOWER(c.name) = LOWER(:categoryName)")
    List<Product> findByCategoryName(@Param("categoryName") String categoryName);
}