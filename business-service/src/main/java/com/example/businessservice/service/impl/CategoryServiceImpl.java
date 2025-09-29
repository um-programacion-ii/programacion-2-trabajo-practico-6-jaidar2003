package com.example.businessservice.service.impl;

import com.example.businessservice.client.CategoryClient;
import com.example.businessservice.dto.CategoryDTO;
import com.example.businessservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of the CategoryService interface.
 * Uses Feign client to communicate with the data service.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryClient categoryClient;

    @Autowired
    public CategoryServiceImpl(CategoryClient categoryClient) {
        this.categoryClient = categoryClient;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        try {
            ResponseEntity<List<Object>> response = categoryClient.getAllCategories();
            if (response.getBody() != null) {
                return convertToCategoryDTOList(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error getting all categories: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        try {
            ResponseEntity<Object> response = categoryClient.getCategoryById(id);
            if (response.getBody() != null) {
                return convertToCategoryDTO(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error getting category by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        try {
            ResponseEntity<Object> response = categoryClient.getCategoryByName(name);
            if (response.getBody() != null) {
                return convertToCategoryDTO(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error getting category by name: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<CategoryDTO> findCategoriesByNameContaining(String name) {
        try {
            ResponseEntity<List<Object>> response = categoryClient.searchCategoriesByName(name);
            if (response.getBody() != null) {
                return convertToCategoryDTOList(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error finding categories by name: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        try {
            Object categoryRequest = convertFromCategoryDTO(categoryDTO);
            ResponseEntity<Object> response = categoryClient.createCategory(categoryRequest);
            if (response.getBody() != null) {
                return convertToCategoryDTO(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error creating category: " + e.getMessage());
        }
        return null;
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        try {
            Object categoryRequest = convertFromCategoryDTO(categoryDTO);
            ResponseEntity<Object> response = categoryClient.updateCategory(id, categoryRequest);
            if (response.getBody() != null) {
                return convertToCategoryDTO(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error updating category: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteCategory(Long id) {
        try {
            categoryClient.deleteCategory(id);
        } catch (Exception e) {
            System.err.println("Error deleting category: " + e.getMessage());
        }
    }

    // Helper methods for conversion between data service objects and DTOs
    
    private CategoryDTO convertToCategoryDTO(Object categoryData) {
        // This is a simplified implementation
        // In a real application, you would use a proper mapping library
        
        // For now, we'll return a dummy category
        return new CategoryDTO(1L, "Sample Category", "Sample Description");
    }
    
    private List<CategoryDTO> convertToCategoryDTOList(List<Object> categoryDataList) {
        // This is a simplified implementation
        
        // For now, we'll return a list with a single dummy category
        return Collections.singletonList(
                new CategoryDTO(1L, "Sample Category", "Sample Description")
        );
    }
    
    private Object convertFromCategoryDTO(CategoryDTO categoryDTO) {
        // This is a simplified implementation
        
        // For now, we'll just return the DTO itself
        return categoryDTO;
    }
}