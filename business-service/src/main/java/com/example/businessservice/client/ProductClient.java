package com.example.businessservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Feign client for communicating with the product endpoints in the data service.
 */
@FeignClient(name = "product-service", url = "${data-service.url}/data/products")
public interface ProductClient {

    /**
     * Get all products.
     *
     * @return ResponseEntity containing a list of products
     */
    @GetMapping
    ResponseEntity<List<Object>> getAllProducts();

    /**
     * Get a product by its ID.
     *
     * @param id the product ID
     * @return ResponseEntity containing the product
     */
    @GetMapping("/{id}")
    ResponseEntity<Object> getProductById(@PathVariable("id") Long id);

    /**
     * Search products by name.
     *
     * @param name the name pattern to search for
     * @return ResponseEntity containing a list of matching products
     */
    @GetMapping("/search")
    ResponseEntity<List<Object>> searchProductsByName(@RequestParam("name") String name);

    /**
     * Get products by category ID.
     *
     * @param categoryId the category ID
     * @return ResponseEntity containing a list of products in the specified category
     */
    @GetMapping("/category/{categoryId}")
    ResponseEntity<List<Object>> getProductsByCategoryId(@PathVariable("categoryId") Long categoryId);

    /**
     * Get products by category name.
     *
     * @param categoryName the category name
     * @return ResponseEntity containing a list of products in the specified category
     */
    @GetMapping("/category/name/{categoryName}")
    ResponseEntity<List<Object>> getProductsByCategoryName(@PathVariable("categoryName") String categoryName);

    /**
     * Get products with price less than or equal to the given value.
     *
     * @param maxPrice the maximum price
     * @return ResponseEntity containing a list of products with price less than or equal to the given value
     */
    @GetMapping("/price/max/{maxPrice}")
    ResponseEntity<List<Object>> getProductsByMaxPrice(@PathVariable("maxPrice") BigDecimal maxPrice);

    /**
     * Get products with price greater than or equal to the given value.
     *
     * @param minPrice the minimum price
     * @return ResponseEntity containing a list of products with price greater than or equal to the given value
     */
    @GetMapping("/price/min/{minPrice}")
    ResponseEntity<List<Object>> getProductsByMinPrice(@PathVariable("minPrice") BigDecimal minPrice);

    /**
     * Get products with price between the given values.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return ResponseEntity containing a list of products with price between the given values
     */
    @GetMapping("/price/range")
    ResponseEntity<List<Object>> getProductsByPriceRange(
            @RequestParam("minPrice") BigDecimal minPrice, 
            @RequestParam("maxPrice") BigDecimal maxPrice);

    /**
     * Create a new product.
     *
     * @param product the product to create
     * @return ResponseEntity containing the created product
     */
    @PostMapping
    ResponseEntity<Object> createProduct(@RequestBody Object product);

    /**
     * Update an existing product.
     *
     * @param id the product ID
     * @param product the updated product data
     * @return ResponseEntity containing the updated product
     */
    @PutMapping("/{id}")
    ResponseEntity<Object> updateProduct(@PathVariable("id") Long id, @RequestBody Object product);

    /**
     * Delete a product.
     *
     * @param id the product ID
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id);

    /**
     * Assign a category to a product.
     *
     * @param productId the product ID
     * @param categoryId the category ID
     * @return ResponseEntity containing the updated product
     */
    @PutMapping("/{productId}/category/{categoryId}")
    ResponseEntity<Object> assignCategoryToProduct(
            @PathVariable("productId") Long productId, 
            @PathVariable("categoryId") Long categoryId);

    /**
     * Remove the category from a product.
     *
     * @param productId the product ID
     * @return ResponseEntity containing the updated product
     */
    @DeleteMapping("/{productId}/category")
    ResponseEntity<Object> removeCategoryFromProduct(@PathVariable("productId") Long productId);
}