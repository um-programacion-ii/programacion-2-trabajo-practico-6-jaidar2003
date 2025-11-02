package com.example.dataservice.repository;

import com.example.dataservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Inventory entity.
 * Provides CRUD operations and custom queries for inventory items.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    
    /**
     * Find inventory items by product id.
     *
     * @param productId the product id
     * @return list of inventory items for the specified product
     */
    List<Inventory> findByProductId(Long productId);
    
    /**
     * Find inventory items by location.
     *
     * @param location the location
     * @return list of inventory items at the specified location
     */
    List<Inventory> findByLocationIgnoreCase(String location);
    
    /**
     * Find inventory items with quantity less than the given value.
     *
     * @param quantity the quantity threshold
     * @return list of inventory items with quantity less than the given value
     */
    List<Inventory> findByQuantityLessThan(Integer quantity);
    
    /**
     * Find inventory items with quantity greater than the given value.
     *
     * @param quantity the quantity threshold
     * @return list of inventory items with quantity greater than the given value
     */
    List<Inventory> findByQuantityGreaterThan(Integer quantity);
    
    /**
     * Find inventory items with quantity between the given values.
     *
     * @param minQuantity the minimum quantity
     * @param maxQuantity the maximum quantity
     * @return list of inventory items with quantity between the given values
     */
    List<Inventory> findByQuantityBetween(Integer minQuantity, Integer maxQuantity);
    
    /**
     * Custom query to find inventory items by product name.
     *
     * @param productName the product name
     * @return list of inventory items for products matching the given name
     */
    @Query("SELECT i FROM Inventory i JOIN i.product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :productName, '%'))")
    List<Inventory> findByProductNameContaining(@Param("productName") String productName);
    
    /**
     * Custom query to find inventory items by product category.
     *
     * @param categoryId the category id
     * @return list of inventory items for products in the specified category
     */
    @Query("SELECT i FROM Inventory i JOIN i.product p WHERE p.category.id = :categoryId")
    List<Inventory> findByProductCategoryId(@Param("categoryId") Long categoryId);
    
    /**
     * Custom query to find inventory items with zero quantity (out of stock).
     *
     * @return list of out-of-stock inventory items
     */
    List<Inventory> findByQuantityEquals(Integer quantity);
}