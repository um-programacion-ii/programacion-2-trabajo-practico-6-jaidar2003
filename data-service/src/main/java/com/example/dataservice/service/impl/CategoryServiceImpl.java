package com.example.dataservice.service.impl;

import com.example.dataservice.entity.Category;
import com.example.dataservice.exception.DuplicateResourceException;
import com.example.dataservice.exception.ResourceNotFoundException;
import com.example.dataservice.exception.ValidationException;
import com.example.dataservice.repository.CategoryRepository;
import com.example.dataservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the CategoryService interface.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "name", name));
    }

    @Override
    public List<Category> findCategoriesByNameContaining(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        validateCategory(category);
        
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new DuplicateResourceException("Category", "name", category.getName());
        }
        
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = getCategoryById(id);
        
        validateCategory(categoryDetails);
        
        // Check if the new name conflicts with an existing category (excluding the current one)
        if (!category.getName().equalsIgnoreCase(categoryDetails.getName()) && 
                categoryRepository.existsByNameIgnoreCase(categoryDetails.getName())) {
            throw new DuplicateResourceException("Category", "name", categoryDetails.getName());
        }
        
        category.setName(categoryDetails.getName());
        category.setDescription(categoryDetails.getDescription());
        
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category", "id", id);
        }
        
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByNameIgnoreCase(name);
    }
    
    /**
     * Validate category data.
     *
     * @param category the category to validate
     * @throws ValidationException if the category data is invalid
     */
    private void validateCategory(Category category) {
        ValidationException validationException = new ValidationException("Category validation failed");
        
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            validationException.addError("name", "Category name is required");
        }
        
        if (validationException.hasErrors()) {
            throw validationException;
        }
    }
}