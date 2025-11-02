package com.example.businessservice.service.impl;

import com.example.businessservice.client.ProductClient;
import com.example.businessservice.dto.ProductDTO;
import com.example.businessservice.dto.CategoryDTO;
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
        if (productData == null) {
            return null;
        }
        try {
            Long id = null;
            String name = null;
            String description = null;
            BigDecimal price = null;
            CategoryDTO categoryDTO = null;

            // Case 1: The response is a Map coming from Jackson default mapping
            if (productData instanceof java.util.Map<?, ?> map) {
                Object idObj = map.get("id");
                if (idObj instanceof Number) {
                    id = ((Number) idObj).longValue();
                }
                Object nameObj = map.get("name");
                if (nameObj instanceof String) {
                    name = (String) nameObj;
                }
                Object descObj = map.get("description");
                if (descObj instanceof String) {
                    description = (String) descObj;
                }
                Object priceObj = map.get("price");
                if (priceObj instanceof Number) {
                    price = java.math.BigDecimal.valueOf(((Number) priceObj).doubleValue());
                } else if (priceObj instanceof String) {
                    try { price = new BigDecimal((String) priceObj); } catch (Exception ignored) {}
                }
                Object categoryObj = map.get("category");
                categoryDTO = convertToCategoryDTO(categoryObj);
                return new ProductDTO(id, name, description, price, categoryDTO);
            }

            // Case 2: Try reflection on a POJO
            Class<?> clazz = productData.getClass();
            try {
                java.lang.reflect.Method getId = clazz.getMethod("getId");
                Object idObj = getId.invoke(productData);
                if (idObj instanceof Number) {
                    id = ((Number) idObj).longValue();
                }
            } catch (Exception ignored) { }
            try {
                java.lang.reflect.Method getName = clazz.getMethod("getName");
                Object nameObj = getName.invoke(productData);
                if (nameObj instanceof String) {
                    name = (String) nameObj;
                }
            } catch (Exception ignored) { }
            try {
                java.lang.reflect.Method getDescription = clazz.getMethod("getDescription");
                Object descObj = getDescription.invoke(productData);
                if (descObj instanceof String) {
                    description = (String) descObj;
                }
            } catch (Exception ignored) { }
            try {
                java.lang.reflect.Method getPrice = clazz.getMethod("getPrice");
                Object priceObj = getPrice.invoke(productData);
                if (priceObj instanceof BigDecimal) {
                    price = (BigDecimal) priceObj;
                } else if (priceObj instanceof Number) {
                    price = BigDecimal.valueOf(((Number) priceObj).doubleValue());
                } else if (priceObj instanceof String) {
                    try { price = new BigDecimal((String) priceObj); } catch (Exception ignored) {}
                }
            } catch (Exception ignored) { }
            try {
                java.lang.reflect.Method getCategory = clazz.getMethod("getCategory");
                Object categoryObj = getCategory.invoke(productData);
                categoryDTO = convertToCategoryDTO(categoryObj);
            } catch (Exception ignored) { }

            return new ProductDTO(id, name, description, price, categoryDTO);
        } catch (Exception e) {
            System.err.println("Error converting product data: " + e.getMessage());
            return null;
        }
    }

    private CategoryDTO convertToCategoryDTO(Object categoryData) {
        if (categoryData == null) {
            return null;
        }
        try {
            Long id = null;
            String name = null;
            String description = null;

            if (categoryData instanceof java.util.Map<?, ?> map) {
                Object idObj = map.get("id");
                if (idObj instanceof Number) { id = ((Number) idObj).longValue(); }
                Object nameObj = map.get("name");
                if (nameObj instanceof String) { name = (String) nameObj; }
                Object descObj = map.get("description");
                if (descObj instanceof String) { description = (String) descObj; }
                return new CategoryDTO(id, name, description);
            }

            Class<?> clazz = categoryData.getClass();
            try {
                java.lang.reflect.Method getId = clazz.getMethod("getId");
                Object idObj = getId.invoke(categoryData);
                if (idObj instanceof Number) { id = ((Number) idObj).longValue(); }
            } catch (Exception ignored) { }
            try {
                java.lang.reflect.Method getName = clazz.getMethod("getName");
                Object nameObj = getName.invoke(categoryData);
                if (nameObj instanceof String) { name = (String) nameObj; }
            } catch (Exception ignored) { }
            try {
                java.lang.reflect.Method getDescription = clazz.getMethod("getDescription");
                Object descObj = getDescription.invoke(categoryData);
                if (descObj instanceof String) { description = (String) descObj; }
            } catch (Exception ignored) { }
            return new CategoryDTO(id, name, description);
        } catch (Exception e) {
            System.err.println("Error converting category data: " + e.getMessage());
            return null;
        }
    }
    
    private List<ProductDTO> convertToProductDTOList(List<Object> productDataList) {
        if (productDataList == null) {
            return Collections.emptyList();
        }
        java.util.List<ProductDTO> result = new java.util.ArrayList<>();
        for (Object item : productDataList) {
            ProductDTO dto = convertToProductDTO(item);
            if (dto != null) {
                result.add(dto);
            }
        }
        return result;
    }
    
    private Object convertFromProductDTO(ProductDTO productDTO) {
        // Minimal conversion: send the DTO as-is; the data-service can accept compatible JSON
        return productDTO;
    }
}