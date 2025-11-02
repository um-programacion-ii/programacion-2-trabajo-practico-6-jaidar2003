package com.example.dataservice.controller;

import com.example.dataservice.entity.Product;
import com.example.dataservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST controller for managing products.
 */
@RestController
@RequestMapping("/data/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * GET /data/products : Get all products.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of products in the body
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * GET /data/products/{id} : Get the product with the specified ID.
     *
     * @param id the ID of the product to retrieve
     * @return the ResponseEntity with status 200 (OK) and the product in the body,
     *         or with status 404 (Not Found) if the product is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * GET /data/products/search : Search products by name.
     *
     * @param name the name pattern to search for
     * @return the ResponseEntity with status 200 (OK) and the list of matching products in the body
     */
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        List<Product> products = productService.findProductsByNameContaining(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * GET /data/products/category/{categoryId} : Get products by category ID.
     *
     * @param categoryId the category ID
     * @return the ResponseEntity with status 200 (OK) and the list of products in the specified category in the body,
     *         or with status 404 (Not Found) if the category is not found
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<Product> products = productService.findProductsByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * GET /data/products/category/name/{categoryName} : Get products by category name.
     *
     * @param categoryName the category name
     * @return the ResponseEntity with status 200 (OK) and the list of products in the specified category in the body
     */
    @GetMapping("/category/name/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategoryName(@PathVariable String categoryName) {
        List<Product> products = productService.findProductsByCategoryName(categoryName);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * GET /data/products/price/max/{maxPrice} : Get products with price less than or equal to the given value.
     *
     * @param maxPrice the maximum price
     * @return the ResponseEntity with status 200 (OK) and the list of products with price less than or equal to the given value in the body
     */
    @GetMapping("/price/max/{maxPrice}")
    public ResponseEntity<List<Product>> getProductsByMaxPrice(@PathVariable BigDecimal maxPrice) {
        List<Product> products = productService.findProductsByPriceLessThanEqual(maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * GET /data/products/price/min/{minPrice} : Get products with price greater than or equal to the given value.
     *
     * @param minPrice the minimum price
     * @return the ResponseEntity with status 200 (OK) and the list of products with price greater than or equal to the given value in the body
     */
    @GetMapping("/price/min/{minPrice}")
    public ResponseEntity<List<Product>> getProductsByMinPrice(@PathVariable BigDecimal minPrice) {
        List<Product> products = productService.findProductsByPriceGreaterThanEqual(minPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * GET /data/products/price/range : Get products with price between the given values.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return the ResponseEntity with status 200 (OK) and the list of products with price between the given values in the body
     */
    @GetMapping("/price/range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        List<Product> products = productService.findProductsByPriceBetween(minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * POST /data/products : Create a new product.
     *
     * @param product the product to create
     * @return the ResponseEntity with status 201 (Created) and the new product in the body,
     *         or with status 400 (Bad Request) if the product data is invalid,
     *         or with status 404 (Not Found) if the referenced category is not found
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product newProduct = productService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    /**
     * PUT /data/products/{id} : Update an existing product.
     *
     * @param id the ID of the product to update
     * @param product the updated product data
     * @return the ResponseEntity with status 200 (OK) and the updated product in the body,
     *         or with status 400 (Bad Request) if the product data is invalid,
     *         or with status 404 (Not Found) if the product or referenced category is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    /**
     * DELETE /data/products/{id} : Delete a product.
     *
     * @param id the ID of the product to delete
     * @return the ResponseEntity with status 204 (No Content),
     *         or with status 404 (Not Found) if the product is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * PUT /data/products/{productId}/category/{categoryId} : Assign a category to a product.
     *
     * @param productId the product ID
     * @param categoryId the category ID
     * @return the ResponseEntity with status 200 (OK) and the updated product in the body,
     *         or with status 404 (Not Found) if the product or category is not found
     */
    @PutMapping("/{productId}/category/{categoryId}")
    public ResponseEntity<Product> assignCategoryToProduct(
            @PathVariable Long productId, @PathVariable Long categoryId) {
        Product product = productService.assignCategoryToProduct(productId, categoryId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * DELETE /data/products/{productId}/category : Remove the category from a product.
     *
     * @param productId the product ID
     * @return the ResponseEntity with status 200 (OK) and the updated product in the body,
     *         or with status 404 (Not Found) if the product is not found
     */
    @DeleteMapping("/{productId}/category")
    public ResponseEntity<Product> removeCategoryFromProduct(@PathVariable Long productId) {
        Product product = productService.removeCategoryFromProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}