package com.example.businessservice.controller;

import com.example.businessservice.dto.CategoryDTO;
import com.example.businessservice.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing categories in the business service.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * GET /api/categories : Get all categories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of categories in the body
     */
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     * GET /api/categories/{id} : Get the category with the specified ID.
     *
     * @param id the ID of the category to retrieve
     * @return the ResponseEntity with status 200 (OK) and the category in the body,
     *         or with status 404 (Not Found) if the category is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET /api/categories/name/{name} : Get the category with the specified name.
     *
     * @param name the name of the category to retrieve
     * @return the ResponseEntity with status 200 (OK) and the category in the body,
     *         or with status 404 (Not Found) if the category is not found
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
        CategoryDTO category = categoryService.getCategoryByName(name);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET /api/categories/search : Search categories by name.
     *
     * @param name the name pattern to search for
     * @return the ResponseEntity with status 200 (OK) and the list of matching categories in the body
     */
    @GetMapping("/search")
    public ResponseEntity<List<CategoryDTO>> searchCategoriesByName(@RequestParam String name) {
        List<CategoryDTO> categories = categoryService.findCategoriesByNameContaining(name);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     * POST /api/categories : Create a new category.
     *
     * @param categoryDTO the category to create
     * @return the ResponseEntity with status 201 (Created) and the new category in the body
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO newCategory = categoryService.createCategory(categoryDTO);
        if (newCategory != null) {
            return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * PUT /api/categories/{id} : Update an existing category.
     *
     * @param id the ID of the category to update
     * @param categoryDTO the updated category data
     * @return the ResponseEntity with status 200 (OK) and the updated category in the body,
     *         or with status 404 (Not Found) if the category is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
        if (updatedCategory != null) {
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE /api/categories/{id} : Delete a category.
     *
     * @param id the ID of the category to delete
     * @return the ResponseEntity with status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}