package com.example.businessservice.service.impl;

import com.example.businessservice.client.CategoryClient;
import com.example.businessservice.dto.CategoryDTO;
import com.example.businessservice.exception.DataServiceException;
import com.example.businessservice.exception.ResourceNotFoundException;
import com.example.businessservice.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the CategoryService interface.
 * Uses Feign client to communicate with the data service.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryClient categoryClient;

    @Autowired
    public CategoryServiceImpl(CategoryClient categoryClient) {
        this.categoryClient = categoryClient;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        try {
            log.debug("Getting all categories from data service");
            ResponseEntity<List<Object>> response = categoryClient.getAllCategories();
            if (response.getBody() != null) {
                List<CategoryDTO> categories = convertToCategoryDTOList(response.getBody());
                log.debug("Retrieved {} categories from data service", categories.size());
                return categories;
            } else {
                log.warn("Data service returned null body for getAllCategories");
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Error getting all categories from data service", e);
            throw new DataServiceException("Failed to retrieve categories from data service", e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }

        try {
            log.debug("Getting category with ID {} from data service", id);
            ResponseEntity<Object> response = categoryClient.getCategoryById(id);
            if (response.getBody() != null) {
                CategoryDTO category = convertToCategoryDTO(response.getBody());
                log.debug("Retrieved category: {}", category);
                return category;
            } else {
                log.warn("Category with ID {} not found", id);
                throw new ResourceNotFoundException("Category not found with ID: " + id);
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error getting category with ID {} from data service", id, e);
            throw new DataServiceException("Failed to retrieve category with ID: " + id, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        try {
            log.debug("Getting category with name '{}' from data service", name);
            ResponseEntity<Object> response = categoryClient.getCategoryByName(name);
            if (response.getBody() != null) {
                CategoryDTO category = convertToCategoryDTO(response.getBody());
                log.debug("Retrieved category: {}", category);
                return category;
            } else {
                log.warn("Category with name '{}' not found", name);
                throw new ResourceNotFoundException("Category not found with name: " + name);
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error getting category with name '{}' from data service", name, e);
            throw new DataServiceException("Failed to retrieve category with name: " + name, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CategoryDTO> findCategoriesByNameContaining(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Search name cannot be null");
        }

        try {
            log.debug("Searching categories containing name '{}' from data service", name);
            ResponseEntity<List<Object>> response = categoryClient.searchCategoriesByName(name);
            if (response.getBody() != null) {
                List<CategoryDTO> categories = convertToCategoryDTOList(response.getBody());
                log.debug("Found {} categories matching '{}'", categories.size(), name);
                return categories;
            } else {
                log.warn("Data service returned null body for findCategoriesByNameContaining('{}')", name);
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Error searching categories by name '{}' from data service", name, e);
            throw new DataServiceException("Failed to search categories by name: " + name, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        try {
            log.debug("Creating new category: {}", categoryDTO);
            Object categoryRequest = convertFromCategoryDTO(categoryDTO);
            ResponseEntity<Object> response = categoryClient.createCategory(categoryRequest);
            if (response.getBody() != null) {
                CategoryDTO createdCategory = convertToCategoryDTO(response.getBody());
                log.info("Created new category with ID: {}", createdCategory.getId());
                return createdCategory;
            } else {
                log.error("Data service returned null body after creating category");
                throw new DataServiceException("Failed to create category: data service returned null", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error("Error creating category in data service", e);
            throw new DataServiceException("Failed to create category", e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }
        if (categoryDTO == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        try {
            log.debug("Updating category with ID {}: {}", id, categoryDTO);
            Object categoryRequest = convertFromCategoryDTO(categoryDTO);
            ResponseEntity<Object> response = categoryClient.updateCategory(id, categoryRequest);
            if (response.getBody() != null) {
                CategoryDTO updatedCategory = convertToCategoryDTO(response.getBody());
                log.info("Updated category with ID: {}", updatedCategory.getId());
                return updatedCategory;
            } else {
                log.warn("Category with ID {} not found for update", id);
                throw new ResourceNotFoundException("Category not found with ID: " + id);
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error updating category with ID {} in data service", id, e);
            throw new DataServiceException("Failed to update category with ID: " + id, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }

        try {
            log.debug("Deleting category with ID {}", id);
            categoryClient.deleteCategory(id);
            log.info("Deleted category with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting category with ID {} from data service", id, e);
            throw new DataServiceException("Failed to delete category with ID: " + id, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Helper methods for conversion between data service objects and DTOs

    private CategoryDTO convertToCategoryDTO(Object categoryData) {
        if (categoryData == null) {
            return null;
        }

        try {
            // In a real application with a proper API, we would use Jackson or a similar library
            // to convert the response to a DTO. For now, we'll use reflection to access the fields.
            // TODO: Replace reflection-based conversion with a proper mapping library (e.g., ModelMapper, MapStruct)

            // Assuming categoryData is a Map with the category properties
            if (categoryData instanceof Map) {
                Map<?, ?> categoryMap = (Map<?, ?>) categoryData;

                Long id = categoryMap.get("id") instanceof Number ? 
                        ((Number) categoryMap.get("id")).longValue() : null;
                String name = categoryMap.get("name") instanceof String ? 
                        (String) categoryMap.get("name") : "";
                String description = categoryMap.get("description") instanceof String ? 
                        (String) categoryMap.get("description") : "";

                return new CategoryDTO(id, name, description);
            }

            // If it's not a Map, try to access the fields directly
            Class<?> clazz = categoryData.getClass();

            Long id = null;
            String name = "";
            String description = "";

            try {
                Method getIdMethod = clazz.getMethod("getId");
                Object idObj = getIdMethod.invoke(categoryData);
                if (idObj instanceof Number) {
                    id = ((Number) idObj).longValue();
                }
            } catch (Exception e) {
                // Ignore if the method doesn't exist
            }

            try {
                Method getNameMethod = clazz.getMethod("getName");
                Object nameObj = getNameMethod.invoke(categoryData);
                if (nameObj instanceof String) {
                    name = (String) nameObj;
                }
            } catch (Exception e) {
                // Ignore if the method doesn't exist
            }

            try {
                Method getDescriptionMethod = clazz.getMethod("getDescription");
                Object descObj = getDescriptionMethod.invoke(categoryData);
                if (descObj instanceof String) {
                    description = (String) descObj;
                }
            } catch (Exception e) {
                // Ignore if the method doesn't exist
            }

            return new CategoryDTO(id, name, description);
        } catch (Exception e) {
            // Log the error and return null
            log.error("Error converting category data: {}", e.getMessage(), e);
            return null;
        }
    }

    private List<CategoryDTO> convertToCategoryDTOList(List<Object> categoryDataList) {
        if (categoryDataList == null) {
            return Collections.emptyList();
        }

        List<CategoryDTO> result = new ArrayList<>();
        for (Object categoryData : categoryDataList) {
            CategoryDTO categoryDTO = convertToCategoryDTO(categoryData);
            if (categoryDTO != null) {
                result.add(categoryDTO);
            }
        }
        return result;
    }

    private Object convertFromCategoryDTO(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }

        try {
            // Create a Map with the category properties
            Map<String, Object> categoryMap = new HashMap<>();
            categoryMap.put("id", categoryDTO.getId());
            categoryMap.put("name", categoryDTO.getName() != null ? categoryDTO.getName() : "");
            categoryMap.put("description", categoryDTO.getDescription() != null ? categoryDTO.getDescription() : "");

            return categoryMap;
        } catch (Exception e) {
            log.error("Error converting CategoryDTO to request object: {}", e.getMessage(), e);
            throw new IllegalArgumentException("Failed to convert CategoryDTO to request object", e);
        }
    }
}
