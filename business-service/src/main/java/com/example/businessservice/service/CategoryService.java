package com.example.businessservice.service;

import com.example.businessservice.dto.CategoryDTO;

import java.util.List;

/**
 * Service interface for managing categories in the business service.
 */
public interface CategoryService {
    
    /**
     * Get all categories.
     *
     * @return list of all categories
     */
    List<CategoryDTO> getAllCategories();
    
    /**
     * Get a category by its ID.
     *
     * @param id the category ID
     * @return the category if found
     */
    CategoryDTO getCategoryById(Long id);
    
    /**
     * Get a category by its name.
     *
     * @param name the category name
     * @return the category if found
     */
    CategoryDTO getCategoryByName(String name);
    
    /**
     * Find categories by name containing the given string.
     *
     * @param name the name pattern to search for
     * @return list of categories matching the name pattern
     */
    List<CategoryDTO> findCategoriesByNameContaining(String name);
    
    /**
     * Create a new category.
     *
     * @param categoryDTO the category to create
     * @return the created category
     */
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    
    /**
     * Update an existing category.
     *
     * @param id the category ID
     * @param categoryDTO the updated category data
     * @return the updated category
     */
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    
    /**
     * Delete a category by its ID.
     *
     * @param id the category ID
     */
    void deleteCategory(Long id);
}