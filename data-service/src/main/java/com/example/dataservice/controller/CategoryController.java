package com.example.dataservice.controller;

import com.example.dataservice.entity.Category;
import com.example.dataservice.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing categories.
 */
@RestController
@RequestMapping("/data/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * GET /data/categories : Get all categories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of categories in the body
     */
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     * GET /data/categories/{id} : Get the category with the specified ID.
     *
     * @param id the ID of the category to retrieve
     * @return the ResponseEntity with status 200 (OK) and the category in the body,
     *         or with status 404 (Not Found) if the category is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     * GET /data/categories/name/{name} : Get the category with the specified name.
     *
     * @param name the name of the category to retrieve
     * @return the ResponseEntity with status 200 (OK) and the category in the body,
     *         or with status 404 (Not Found) if the category is not found
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        Category category = categoryService.getCategoryByName(name);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     * GET /data/categories/search : Search categories by name.
     *
     * @param name the name pattern to search for
     * @return the ResponseEntity with status 200 (OK) and the list of matching categories in the body
     */
    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategoriesByName(@RequestParam String name) {
        List<Category> categories = categoryService.findCategoriesByNameContaining(name);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     * POST /data/categories : Create a new category.
     *
     * @param category the category to create
     * @return the ResponseEntity with status 201 (Created) and the new category in the body,
     *         or with status 400 (Bad Request) if the category data is invalid,
     *         or with status 409 (Conflict) if a category with the same name already exists
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        Category newCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    /**
     * PUT /data/categories/{id} : Update an existing category.
     *
     * @param id the ID of the category to update
     * @param category the updated category data
     * @return the ResponseEntity with status 200 (OK) and the updated category in the body,
     *         or with status 400 (Bad Request) if the category data is invalid,
     *         or with status 404 (Not Found) if the category is not found,
     *         or with status 409 (Conflict) if the new name conflicts with an existing category
     */
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    /**
     * DELETE /data/categories/{id} : Delete a category.
     *
     * @param id the ID of the category to delete
     * @return the ResponseEntity with status 204 (No Content),
     *         or with status 404 (Not Found) if the category is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}