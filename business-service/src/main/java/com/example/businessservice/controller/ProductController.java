package com.example.businessservice.controller;

import com.example.businessservice.dto.ProductDTO;
import com.example.businessservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST controller for managing products in the business service.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * GET /api/products : Get all products.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of products in the body
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * GET /api/products/{id} : Get the product with the specified ID.
     *
     * @param id the ID of the product to retrieve
     * @return the ResponseEntity with status 200 (OK) and the product in the body,
     *         or with status 404 (Not Found) if the product is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET /api/products/search : Search products by name.
     *
     * @param name the name pattern to search for
     * @return the ResponseEntity with status 200 (OK) and the list of matching products in the body
     */
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProductsByName(@RequestParam String name) {
        List<ProductDTO> products = productService.findProductsByNameContaining(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * GET /api/products/category/{categoryId} : Get products by category ID.
     *
     * @param categoryId the category ID
     * @return the ResponseEntity with status 200 (OK) and the list of products in the specified category in the body
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<ProductDTO> products = productService.findProductsByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * GET /api/products/category/name/{categoryName} : Get products by category name.
     *
     * @param categoryName the category name
     * @return the ResponseEntity with status 200 (OK) and the list of products in the specified category in the body
     */
    @GetMapping("/category/name/{categoryName}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategoryName(@PathVariable String categoryName) {
        List<ProductDTO> products = productService.findProductsByCategoryName(categoryName);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * GET /api/products/price/max/{maxPrice} : Get products with price less than or equal to the given value.
     *
     * @param maxPrice the maximum price
     * @return the ResponseEntity with status 200 (OK) and the list of products with price less than or equal to the given value in the body
     */
    @GetMapping("/price/max/{maxPrice}")
    public ResponseEntity<List<ProductDTO>> getProductsByMaxPrice(@PathVariable BigDecimal maxPrice) {
        List<ProductDTO> products = productService.findProductsByMaxPrice(maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * GET /api/products/price/min/{minPrice} : Get products with price greater than or equal to the given value.
     *
     * @param minPrice the minimum price
     * @return the ResponseEntity with status 200 (OK) and the list of products with price greater than or equal to the given value in the body
     */
    @GetMapping("/price/min/{minPrice}")
    public ResponseEntity<List<ProductDTO>> getProductsByMinPrice(@PathVariable BigDecimal minPrice) {
        List<ProductDTO> products = productService.findProductsByMinPrice(minPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * GET /api/products/price/range : Get products with price between the given values.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return the ResponseEntity with status 200 (OK) and the list of products with price between the given values in the body
     */
    @GetMapping("/price/range")
    public ResponseEntity<List<ProductDTO>> getProductsByPriceRange(
            @RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        List<ProductDTO> products = productService.findProductsByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * POST /api/products : Create a new product.
     *
     * @param productDTO the product to create
     * @return the ResponseEntity with status 201 (Created) and the new product in the body
     */
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO newProduct = productService.createProduct(productDTO);
        if (newProduct != null) {
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * PUT /api/products/{id} : Update an existing product.
     *
     * @param id the ID of the product to update
     * @param productDTO the updated product data
     * @return the ResponseEntity with status 200 (OK) and the updated product in the body,
     *         or with status 404 (Not Found) if the product is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE /api/products/{id} : Delete a product.
     *
     * @param id the ID of the product to delete
     * @return the ResponseEntity with status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * PUT /api/products/{productId}/category/{categoryId} : Assign a category to a product.
     *
     * @param productId the product ID
     * @param categoryId the category ID
     * @return the ResponseEntity with status 200 (OK) and the updated product in the body,
     *         or with status 404 (Not Found) if the product or category is not found
     */
    @PutMapping("/{productId}/category/{categoryId}")
    public ResponseEntity<ProductDTO> assignCategoryToProduct(
            @PathVariable Long productId, @PathVariable Long categoryId) {
        ProductDTO product = productService.assignCategoryToProduct(productId, categoryId);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE /api/products/{productId}/category : Remove the category from a product.
     *
     * @param productId the product ID
     * @return the ResponseEntity with status 200 (OK) and the updated product in the body,
     *         or with status 404 (Not Found) if the product is not found
     */
    @DeleteMapping("/{productId}/category")
    public ResponseEntity<ProductDTO> removeCategoryFromProduct(@PathVariable Long productId) {
        ProductDTO product = productService.removeCategoryFromProduct(productId);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}