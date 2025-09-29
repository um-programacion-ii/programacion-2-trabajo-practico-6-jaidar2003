package com.example.dataservice.service;

import com.example.dataservice.entity.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service interface for managing products.
 */
public interface ProductService {
    
    /**
     * Get all products.
     *
     * @return list of all products
     */
    List<Product> getAllProducts();
    
    /**
     * Get a product by its ID.
     *
     * @param id the product ID
     * @return the product if found
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the product is not found
     */
    Product getProductById(Long id);
    
    /**
     * Find products by name containing the given string (case insensitive).
     *
     * @param name the name pattern to search for
     * @return list of products matching the name pattern
     */
    List<Product> findProductsByNameContaining(String name);
    
    /**
     * Find products by category ID.
     *
     * @param categoryId the category ID
     * @return list of products in the specified category
     */
    List<Product> findProductsByCategoryId(Long categoryId);
    
    /**
     * Find products by category name.
     *
     * @param categoryName the category name
     * @return list of products in the specified category
     */
    List<Product> findProductsByCategoryName(String categoryName);
    
    /**
     * Find products with price less than or equal to the given value.
     *
     * @param price the maximum price
     * @return list of products with price less than or equal to the given value
     */
    List<Product> findProductsByPriceLessThanEqual(BigDecimal price);
    
    /**
     * Find products with price greater than or equal to the given value.
     *
     * @param price the minimum price
     * @return list of products with price greater than or equal to the given value
     */
    List<Product> findProductsByPriceGreaterThanEqual(BigDecimal price);
    
    /**
     * Find products with price between the given values.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return list of products with price between the given values
     */
    List<Product> findProductsByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    /**
     * Create a new product.
     *
     * @param product the product to create
     * @return the created product
     * @throws com.example.dataservice.exception.ValidationException if the product data is invalid
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the referenced category is not found
     */
    Product createProduct(Product product);
    
    /**
     * Update an existing product.
     *
     * @param id the product ID
     * @param productDetails the updated product data
     * @return the updated product
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the product is not found
     * @throws com.example.dataservice.exception.ValidationException if the product data is invalid
     */
    Product updateProduct(Long id, Product productDetails);
    
    /**
     * Delete a product by its ID.
     *
     * @param id the product ID
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the product is not found
     */
    void deleteProduct(Long id);
    
    /**
     * Check if a product exists with the given ID.
     *
     * @param id the product ID
     * @return true if the product exists
     */
    boolean existsById(Long id);
    
    /**
     * Assign a category to a product.
     *
     * @param productId the product ID
     * @param categoryId the category ID
     * @return the updated product
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the product or category is not found
     */
    Product assignCategoryToProduct(Long productId, Long categoryId);
    
    /**
     * Remove the category from a product.
     *
     * @param productId the product ID
     * @return the updated product
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the product is not found
     */
    Product removeCategoryFromProduct(Long productId);
}