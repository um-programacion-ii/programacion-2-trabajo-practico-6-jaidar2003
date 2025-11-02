package com.example.dataservice.service;

import com.example.dataservice.entity.Category;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing categories.
 */
public interface CategoryService {
    
    /**
     * Get all categories.
     *
     * @return list of all categories
     */
    List<Category> getAllCategories();
    
    /**
     * Get a category by its ID.
     *
     * @param id the category ID
     * @return the category if found
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the category is not found
     */
    Category getCategoryById(Long id);
    
    /**
     * Get a category by its name.
     *
     * @param name the category name
     * @return the category if found
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the category is not found
     */
    Category getCategoryByName(String name);
    
    /**
     * Find categories by name containing the given string (case insensitive).
     *
     * @param name the name pattern to search for
     * @return list of categories matching the name pattern
     */
    List<Category> findCategoriesByNameContaining(String name);
    
    /**
     * Create a new category.
     *
     * @param category the category to create
     * @return the created category
     * @throws com.example.dataservice.exception.DuplicateResourceException if a category with the same name already exists
     * @throws com.example.dataservice.exception.ValidationException if the category data is invalid
     */
    Category createCategory(Category category);
    
    /**
     * Update an existing category.
     *
     * @param id the category ID
     * @param categoryDetails the updated category data
     * @return the updated category
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the category is not found
     * @throws com.example.dataservice.exception.DuplicateResourceException if the new name conflicts with an existing category
     * @throws com.example.dataservice.exception.ValidationException if the category data is invalid
     */
    Category updateCategory(Long id, Category categoryDetails);
    
    /**
     * Delete a category by its ID.
     *
     * @param id the category ID
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the category is not found
     */
    void deleteCategory(Long id);
    
    /**
     * Check if a category exists with the given ID.
     *
     * @param id the category ID
     * @return true if the category exists
     */
    boolean existsById(Long id);
    
    /**
     * Check if a category exists with the given name.
     *
     * @param name the category name
     * @return true if the category exists
     */
    boolean existsByName(String name);
}