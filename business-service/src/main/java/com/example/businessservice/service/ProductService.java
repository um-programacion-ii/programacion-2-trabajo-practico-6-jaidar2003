package com.example.businessservice.service;

import com.example.businessservice.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service interface for managing products in the business service.
 */
public interface ProductService {
    
    /**
     * Get all products.
     *
     * @return list of all products
     */
    List<ProductDTO> getAllProducts();
    
    /**
     * Get a product by its ID.
     *
     * @param id the product ID
     * @return the product if found
     */
    ProductDTO getProductById(Long id);
    
    /**
     * Find products by name containing the given string.
     *
     * @param name the name pattern to search for
     * @return list of products matching the name pattern
     */
    List<ProductDTO> findProductsByNameContaining(String name);
    
    /**
     * Find products by category ID.
     *
     * @param categoryId the category ID
     * @return list of products in the specified category
     */
    List<ProductDTO> findProductsByCategoryId(Long categoryId);
    
    /**
     * Find products by category name.
     *
     * @param categoryName the category name
     * @return list of products in the specified category
     */
    List<ProductDTO> findProductsByCategoryName(String categoryName);
    
    /**
     * Find products with price less than or equal to the given value.
     *
     * @param maxPrice the maximum price
     * @return list of products with price less than or equal to the given value
     */
    List<ProductDTO> findProductsByMaxPrice(BigDecimal maxPrice);
    
    /**
     * Find products with price greater than or equal to the given value.
     *
     * @param minPrice the minimum price
     * @return list of products with price greater than or equal to the given value
     */
    List<ProductDTO> findProductsByMinPrice(BigDecimal minPrice);
    
    /**
     * Find products with price between the given values.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return list of products with price between the given values
     */
    List<ProductDTO> findProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    
    /**
     * Create a new product.
     *
     * @param productDTO the product to create
     * @return the created product
     */
    ProductDTO createProduct(ProductDTO productDTO);
    
    /**
     * Update an existing product.
     *
     * @param id the product ID
     * @param productDTO the updated product data
     * @return the updated product
     */
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    
    /**
     * Delete a product by its ID.
     *
     * @param id the product ID
     */
    void deleteProduct(Long id);
    
    /**
     * Assign a category to a product.
     *
     * @param productId the product ID
     * @param categoryId the category ID
     * @return the updated product
     */
    ProductDTO assignCategoryToProduct(Long productId, Long categoryId);
    
    /**
     * Remove the category from a product.
     *
     * @param productId the product ID
     * @return the updated product
     */
    ProductDTO removeCategoryFromProduct(Long productId);
}