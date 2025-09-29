package com.example.businessservice.service.impl;

import com.example.businessservice.client.ProductClient;
import com.example.businessservice.dto.ProductDTO;
import com.example.businessservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the ProductService interface.
 * Uses Feign client to communicate with the data service.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductClient productClient;

    @Autowired
    public ProductServiceImpl(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        try {
            ResponseEntity<List<Object>> response = productClient.getAllProducts();
            if (response.getBody() != null) {
                // Convert the response body to List<ProductDTO>
                // In a real implementation, you would use a mapper like ModelMapper or MapStruct
                return convertToProductDTOList(response.getBody());
            }
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error getting all products: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public ProductDTO getProductById(Long id) {
        try {
            ResponseEntity<Object> response = productClient.getProductById(id);
            if (response.getBody() != null) {
                // Convert the response body to ProductDTO
                return convertToProductDTO(response.getBody());
            }
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error getting product by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ProductDTO> findProductsByNameContaining(String name) {
        try {
            ResponseEntity<List<Object>> response = productClient.searchProductsByName(name);
            if (response.getBody() != null) {
                return convertToProductDTOList(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error finding products by name: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProductDTO> findProductsByCategoryId(Long categoryId) {
        try {
            ResponseEntity<List<Object>> response = productClient.getProductsByCategoryId(categoryId);
            if (response.getBody() != null) {
                return convertToProductDTOList(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error finding products by category ID: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProductDTO> findProductsByCategoryName(String categoryName) {
        try {
            ResponseEntity<List<Object>> response = productClient.getProductsByCategoryName(categoryName);
            if (response.getBody() != null) {
                return convertToProductDTOList(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error finding products by category name: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProductDTO> findProductsByMaxPrice(BigDecimal maxPrice) {
        try {
            ResponseEntity<List<Object>> response = productClient.getProductsByMaxPrice(maxPrice);
            if (response.getBody() != null) {
                return convertToProductDTOList(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error finding products by max price: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProductDTO> findProductsByMinPrice(BigDecimal minPrice) {
        try {
            ResponseEntity<List<Object>> response = productClient.getProductsByMinPrice(minPrice);
            if (response.getBody() != null) {
                return convertToProductDTOList(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error finding products by min price: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProductDTO> findProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        try {
            ResponseEntity<List<Object>> response = productClient.getProductsByPriceRange(minPrice, maxPrice);
            if (response.getBody() != null) {
                return convertToProductDTOList(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error finding products by price range: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        try {
            // Convert ProductDTO to the format expected by the data service
            Object productRequest = convertFromProductDTO(productDTO);
            ResponseEntity<Object> response = productClient.createProduct(productRequest);
            if (response.getBody() != null) {
                return convertToProductDTO(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error creating product: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        try {
            Object productRequest = convertFromProductDTO(productDTO);
            ResponseEntity<Object> response = productClient.updateProduct(id, productRequest);
            if (response.getBody() != null) {
                return convertToProductDTO(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error updating product: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            productClient.deleteProduct(id);
        } catch (Exception e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }
    }

    @Override
    public ProductDTO assignCategoryToProduct(Long productId, Long categoryId) {
        try {
            ResponseEntity<Object> response = productClient.assignCategoryToProduct(productId, categoryId);
            if (response.getBody() != null) {
                return convertToProductDTO(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error assigning category to product: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ProductDTO removeCategoryFromProduct(Long productId) {
        try {
            ResponseEntity<Object> response = productClient.removeCategoryFromProduct(productId);
            if (response.getBody() != null) {
                return convertToProductDTO(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error removing category from product: " + e.getMessage());
        }
        return null;
    }

    // Helper methods for conversion between data service objects and DTOs
    // In a real implementation, you would use a mapper like ModelMapper or MapStruct
    
    private ProductDTO convertToProductDTO(Object productData) {
        // This is a simplified implementation
        // In a real application, you would use a proper mapping library
        // or implement a more robust conversion logic
        
        // For now, we'll return a dummy product
        return new ProductDTO(1L, "Sample Product", "Sample Description", new BigDecimal("9.99"));
    }
    
    private List<ProductDTO> convertToProductDTOList(List<Object> productDataList) {
        // This is a simplified implementation
        // In a real application, you would convert each item in the list
        
        // For now, we'll return a list with a single dummy product
        return Collections.singletonList(
                new ProductDTO(1L, "Sample Product", "Sample Description", new BigDecimal("9.99"))
        );
    }
    
    private Object convertFromProductDTO(ProductDTO productDTO) {
        // This is a simplified implementation
        // In a real application, you would convert the DTO to the format expected by the data service
        
        // For now, we'll just return the DTO itself
        return productDTO;
    }
}