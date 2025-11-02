package com.example.businessservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Feign client for communicating with the category endpoints in the data service.
 */
@FeignClient(name = "category-service", url = "${data-service.url}/data/categories")
public interface CategoryClient {

    /**
     * Get all categories.
     *
     * @return ResponseEntity containing a list of categories
     */
    @GetMapping
    ResponseEntity<List<Object>> getAllCategories();

    /**
     * Get a category by its ID.
     *
     * @param id the category ID
     * @return ResponseEntity containing the category
     */
    @GetMapping("/{id}")
    ResponseEntity<Object> getCategoryById(@PathVariable("id") Long id);

    /**
     * Get a category by its name.
     *
     * @param name the category name
     * @return ResponseEntity containing the category
     */
    @GetMapping("/name/{name}")
    ResponseEntity<Object> getCategoryByName(@PathVariable("name") String name);

    /**
     * Search categories by name.
     *
     * @param name the name pattern to search for
     * @return ResponseEntity containing a list of matching categories
     */
    @GetMapping("/search")
    ResponseEntity<List<Object>> searchCategoriesByName(@RequestParam("name") String name);

    /**
     * Create a new category.
     *
     * @param category the category to create
     * @return ResponseEntity containing the created category
     */
    @PostMapping
    ResponseEntity<Object> createCategory(@RequestBody Object category);

    /**
     * Update an existing category.
     *
     * @param id the category ID
     * @param category the updated category data
     * @return ResponseEntity containing the updated category
     */
    @PutMapping("/{id}")
    ResponseEntity<Object> updateCategory(@PathVariable("id") Long id, @RequestBody Object category);

    /**
     * Delete a category.
     *
     * @param id the category ID
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id);
}