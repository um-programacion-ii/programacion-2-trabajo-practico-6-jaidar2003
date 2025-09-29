package com.example.dataservice.service.impl;

import com.example.dataservice.entity.Category;
import com.example.dataservice.entity.Product;
import com.example.dataservice.exception.ResourceNotFoundException;
import com.example.dataservice.exception.ValidationException;
import com.example.dataservice.repository.ProductRepository;
import com.example.dataservice.service.CategoryService;
import com.example.dataservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation of the ProductService interface.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    @Override
    public List<Product> findProductsByNameContaining(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> findProductsByCategoryId(Long categoryId) {
        // Verify that the category exists
        if (!categoryService.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category", "id", categoryId);
        }
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> findProductsByCategoryName(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<Product> findProductsByPriceLessThanEqual(BigDecimal price) {
        return productRepository.findByPriceLessThanEqual(price);
    }

    @Override
    public List<Product> findProductsByPriceGreaterThanEqual(BigDecimal price) {
        return productRepository.findByPriceGreaterThanEqual(price);
    }

    @Override
    public List<Product> findProductsByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        validateProduct(product);
        
        // If a category is specified, verify that it exists
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category category = categoryService.getCategoryById(product.getCategory().getId());
            product.setCategory(category);
        }
        
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        
        validateProduct(productDetails);
        
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        
        // If a category is specified, verify that it exists
        if (productDetails.getCategory() != null && productDetails.getCategory().getId() != null) {
            Category category = categoryService.getCategoryById(productDetails.getCategory().getId());
            product.setCategory(category);
        } else {
            product.setCategory(null);
        }
        
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product", "id", id);
        }
        
        productRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    @Transactional
    public Product assignCategoryToProduct(Long productId, Long categoryId) {
        Product product = getProductById(productId);
        Category category = categoryService.getCategoryById(categoryId);
        
        product.setCategory(category);
        
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product removeCategoryFromProduct(Long productId) {
        Product product = getProductById(productId);
        
        product.setCategory(null);
        
        return productRepository.save(product);
    }
    
    /**
     * Validate product data.
     *
     * @param product the product to validate
     * @throws ValidationException if the product data is invalid
     */
    private void validateProduct(Product product) {
        ValidationException validationException = new ValidationException("Product validation failed");
        
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            validationException.addError("name", "Product name is required");
        }
        
        if (product.getPrice() == null) {
            validationException.addError("price", "Product price is required");
        } else if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            validationException.addError("price", "Product price must be positive");
        }
        
        if (validationException.hasErrors()) {
            throw validationException;
        }
    }
}