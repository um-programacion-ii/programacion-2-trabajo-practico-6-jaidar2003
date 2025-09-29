package com.example.dataservice.repository;

import com.example.dataservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Category entity.
 * Provides CRUD operations and custom queries for categories.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    /**
     * Find a category by its name (case insensitive).
     *
     * @param name the category name
     * @return an Optional containing the category if found
     */
    Optional<Category> findByNameIgnoreCase(String name);
    
    /**
     * Check if a category exists with the given name (case insensitive).
     *
     * @param name the category name
     * @return true if a category exists with the given name
     */
    boolean existsByNameIgnoreCase(String name);
    
    /**
     * Find categories by name containing the given string (case insensitive).
     *
     * @param name the name pattern to search for
     * @return list of categories matching the name pattern
     */
    List<Category> findByNameContainingIgnoreCase(String name);
    
    /**
     * Find categories by description containing the given string (case insensitive).
     *
     * @param description the description pattern to search for
     * @return list of categories matching the description pattern
     */
    List<Category> findByDescriptionContainingIgnoreCase(String description);
}