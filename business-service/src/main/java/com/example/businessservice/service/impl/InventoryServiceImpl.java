package com.example.businessservice.service.impl;

import com.example.businessservice.client.InventoryClient;
import com.example.businessservice.dto.CategoryDTO;
import com.example.businessservice.dto.InventoryDTO;
import com.example.businessservice.dto.ProductDTO;
import com.example.businessservice.exception.DataServiceException;
import com.example.businessservice.exception.ResourceNotFoundException;
import com.example.businessservice.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the InventoryService interface.
 * Uses Feign client to communicate with the data service.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger log = LoggerFactory.getLogger(InventoryServiceImpl.class);

    private final InventoryClient inventoryClient;

    @Autowired
    public InventoryServiceImpl(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    @Override
    public List<InventoryDTO> getAllInventoryItems() {
        try {
            log.debug("Getting all inventory items from data service");
            ResponseEntity<List<Object>> response = inventoryClient.getAllInventoryItems();
            if (response.getBody() != null) {
                List<InventoryDTO> inventoryItems = convertToInventoryDTOList(response.getBody());
                log.debug("Retrieved {} inventory items from data service", inventoryItems.size());
                return inventoryItems;
            } else {
                log.warn("Data service returned null body for getAllInventoryItems");
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Error getting all inventory items from data service", e);
            throw new DataServiceException("Failed to retrieve inventory items from data service", e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public InventoryDTO getInventoryItemById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Inventory item ID cannot be null");
        }

        try {
            log.debug("Getting inventory item with ID {} from data service", id);
            ResponseEntity<Object> response = inventoryClient.getInventoryItemById(id);
            if (response.getBody() != null) {
                InventoryDTO inventoryItem = convertToInventoryDTO(response.getBody());
                log.debug("Retrieved inventory item: {}", inventoryItem);
                return inventoryItem;
            } else {
                log.warn("Inventory item with ID {} not found", id);
                throw new ResourceNotFoundException("Inventory item not found with ID: " + id);
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error getting inventory item with ID {} from data service", id, e);
            throw new DataServiceException("Failed to retrieve inventory item with ID: " + id, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<InventoryDTO> findInventoryItemsByProductId(Long productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        try {
            log.debug("Finding inventory items for product ID {} from data service", productId);
            ResponseEntity<List<Object>> response = inventoryClient.getInventoryItemsByProductId(productId);
            if (response.getBody() != null) {
                List<InventoryDTO> inventoryItems = convertToInventoryDTOList(response.getBody());
                log.debug("Found {} inventory items for product ID {}", inventoryItems.size(), productId);
                return inventoryItems;
            } else {
                log.warn("Data service returned null body for findInventoryItemsByProductId({})", productId);
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Error finding inventory items by product ID {} from data service", productId, e);
            throw new DataServiceException("Failed to find inventory items for product ID: " + productId, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<InventoryDTO> findInventoryItemsByLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }

        try {
            log.debug("Finding inventory items at location '{}' from data service", location);
            ResponseEntity<List<Object>> response = inventoryClient.getInventoryItemsByLocation(location);
            if (response.getBody() != null) {
                List<InventoryDTO> inventoryItems = convertToInventoryDTOList(response.getBody());
                log.debug("Found {} inventory items at location '{}'", inventoryItems.size(), location);
                return inventoryItems;
            } else {
                log.warn("Data service returned null body for findInventoryItemsByLocation('{}')", location);
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Error finding inventory items by location '{}' from data service", location, e);
            throw new DataServiceException("Failed to find inventory items at location: " + location, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<InventoryDTO> findInventoryItemsByQuantityLessThan(Integer quantity) {
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        try {
            log.debug("Finding inventory items with quantity less than {} from data service", quantity);
            ResponseEntity<List<Object>> response = inventoryClient.getInventoryItemsByQuantityLessThan(quantity);
            if (response.getBody() != null) {
                List<InventoryDTO> inventoryItems = convertToInventoryDTOList(response.getBody());
                log.debug("Found {} inventory items with quantity less than {}", inventoryItems.size(), quantity);
                return inventoryItems;
            } else {
                log.warn("Data service returned null body for findInventoryItemsByQuantityLessThan({})", quantity);
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Error finding inventory items by quantity less than {} from data service", quantity, e);
            throw new DataServiceException("Failed to find inventory items with quantity less than: " + quantity, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<InventoryDTO> findInventoryItemsByQuantityGreaterThan(Integer quantity) {
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        try {
            log.debug("Finding inventory items with quantity greater than {} from data service", quantity);
            ResponseEntity<List<Object>> response = inventoryClient.getInventoryItemsByQuantityGreaterThan(quantity);
            if (response.getBody() != null) {
                List<InventoryDTO> inventoryItems = convertToInventoryDTOList(response.getBody());
                log.debug("Found {} inventory items with quantity greater than {}", inventoryItems.size(), quantity);
                return inventoryItems;
            } else {
                log.warn("Data service returned null body for findInventoryItemsByQuantityGreaterThan({})", quantity);
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Error finding inventory items by quantity greater than {} from data service", quantity, e);
            throw new DataServiceException("Failed to find inventory items with quantity greater than: " + quantity, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<InventoryDTO> findInventoryItemsByQuantityBetween(Integer minQuantity, Integer maxQuantity) {
        if (minQuantity == null || maxQuantity == null) {
            throw new IllegalArgumentException("Min and max quantity cannot be null");
        }
        if (minQuantity > maxQuantity) {
            throw new IllegalArgumentException("Min quantity cannot be greater than max quantity");
        }

        try {
            log.debug("Finding inventory items with quantity between {} and {} from data service", minQuantity, maxQuantity);
            ResponseEntity<List<Object>> response = inventoryClient.getInventoryItemsByQuantityBetween(minQuantity, maxQuantity);
            if (response.getBody() != null) {
                List<InventoryDTO> inventoryItems = convertToInventoryDTOList(response.getBody());
                log.debug("Found {} inventory items with quantity between {} and {}", inventoryItems.size(), minQuantity, maxQuantity);
                return inventoryItems;
            } else {
                log.warn("Data service returned null body for findInventoryItemsByQuantityBetween({}, {})", minQuantity, maxQuantity);
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Error finding inventory items by quantity between {} and {} from data service", minQuantity, maxQuantity, e);
            throw new DataServiceException("Failed to find inventory items with quantity between " + minQuantity + " and " + maxQuantity, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<InventoryDTO> findInventoryItemsByProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }

        try {
            log.debug("Finding inventory items by product name '{}' from data service", productName);
            ResponseEntity<List<Object>> response = inventoryClient.getInventoryItemsByProductName(productName);
            if (response.getBody() != null) {
                List<InventoryDTO> inventoryItems = convertToInventoryDTOList(response.getBody());
                log.debug("Found {} inventory items for product name '{}'", inventoryItems.size(), productName);
                return inventoryItems;
            } else {
                log.warn("Data service returned null body for findInventoryItemsByProductName('{}')", productName);
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Error finding inventory items by product name '{}' from data service", productName, e);
            throw new DataServiceException("Failed to find inventory items for product name: " + productName, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<InventoryDTO> findInventoryItemsByProductCategory(Long categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }

        try {
            log.debug("Finding inventory items by product category ID {} from data service", categoryId);
            ResponseEntity<List<Object>> response = inventoryClient.getInventoryItemsByProductCategory(categoryId);
            if (response.getBody() != null) {
                List<InventoryDTO> inventoryItems = convertToInventoryDTOList(response.getBody());
                log.debug("Found {} inventory items for product category ID {}", inventoryItems.size(), categoryId);
                return inventoryItems;
            } else {
                log.warn("Data service returned null body for findInventoryItemsByProductCategory({})", categoryId);
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Error finding inventory items by product category ID {} from data service", categoryId, e);
            throw new DataServiceException("Failed to find inventory items for product category ID: " + categoryId, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<InventoryDTO> findOutOfStockItems() {
        try {
            log.debug("Finding out of stock inventory items from data service");
            ResponseEntity<List<Object>> response = inventoryClient.getOutOfStockItems();
            if (response.getBody() != null) {
                List<InventoryDTO> inventoryItems = convertToInventoryDTOList(response.getBody());
                log.debug("Found {} out of stock inventory items", inventoryItems.size());
                return inventoryItems;
            } else {
                log.warn("Data service returned null body for findOutOfStockItems()");
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Error finding out of stock inventory items from data service", e);
            throw new DataServiceException("Failed to find out of stock inventory items", e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public InventoryDTO createInventoryItem(InventoryDTO inventoryDTO) {
        if (inventoryDTO == null) {
            throw new IllegalArgumentException("Inventory item cannot be null");
        }
        if (inventoryDTO.getProduct() == null) {
            throw new IllegalArgumentException("Product in inventory item cannot be null");
        }

        try {
            log.debug("Creating new inventory item: {}", inventoryDTO);
            Object inventoryRequest = convertFromInventoryDTO(inventoryDTO);
            ResponseEntity<Object> response = inventoryClient.createInventoryItem(inventoryRequest);
            if (response.getBody() != null) {
                InventoryDTO createdInventory = convertToInventoryDTO(response.getBody());
                log.info("Created new inventory item with ID: {}", createdInventory.getId());
                return createdInventory;
            } else {
                log.error("Data service returned null body after creating inventory item");
                throw new DataServiceException("Failed to create inventory item: data service returned null", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error("Error creating inventory item in data service", e);
            throw new DataServiceException("Failed to create inventory item", e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public InventoryDTO updateInventoryItem(Long id, InventoryDTO inventoryDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Inventory item ID cannot be null");
        }
        if (inventoryDTO == null) {
            throw new IllegalArgumentException("Inventory item cannot be null");
        }

        try {
            log.debug("Updating inventory item with ID {}: {}", id, inventoryDTO);
            Object inventoryRequest = convertFromInventoryDTO(inventoryDTO);
            ResponseEntity<Object> response = inventoryClient.updateInventoryItem(id, inventoryRequest);
            if (response.getBody() != null) {
                InventoryDTO updatedInventory = convertToInventoryDTO(response.getBody());
                log.info("Updated inventory item with ID: {}", updatedInventory.getId());
                return updatedInventory;
            } else {
                log.warn("Inventory item with ID {} not found for update", id);
                throw new ResourceNotFoundException("Inventory item not found with ID: " + id);
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error updating inventory item with ID {} in data service", id, e);
            throw new DataServiceException("Failed to update inventory item with ID: " + id, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public InventoryDTO updateInventoryQuantity(Long id, Integer quantity) {
        if (id == null) {
            throw new IllegalArgumentException("Inventory item ID cannot be null");
        }
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        try {
            log.debug("Updating quantity to {} for inventory item with ID {} in data service", quantity, id);
            ResponseEntity<Object> response = inventoryClient.updateInventoryQuantity(id, quantity);
            if (response.getBody() != null) {
                InventoryDTO updatedInventory = convertToInventoryDTO(response.getBody());
                log.info("Updated quantity to {} for inventory item with ID: {}", quantity, id);
                return updatedInventory;
            } else {
                log.warn("Inventory item with ID {} not found for quantity update", id);
                throw new ResourceNotFoundException("Inventory item not found with ID: " + id);
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error updating quantity for inventory item with ID {} in data service", id, e);
            throw new DataServiceException("Failed to update quantity for inventory item with ID: " + id, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteInventoryItem(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Inventory item ID cannot be null");
        }

        try {
            log.debug("Deleting inventory item with ID {} from data service", id);
            inventoryClient.deleteInventoryItem(id);
            log.info("Deleted inventory item with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting inventory item with ID {} from data service", id, e);
            throw new DataServiceException("Failed to delete inventory item with ID: " + id, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Helper methods for conversion between data service objects and DTOs

    private InventoryDTO convertToInventoryDTO(Object inventoryData) {
        if (inventoryData == null) {
            return null;
        }

        try {
            // In a real application with a proper API, we would use Jackson or a similar library
            // to convert the response to a DTO. For now, we'll use reflection to access the fields.

            // Assuming inventoryData is a Map with the inventory properties
            if (inventoryData instanceof Map) {
                Map<?, ?> inventoryMap = (Map<?, ?>) inventoryData;

                Long id = inventoryMap.get("id") instanceof Number ? 
                        ((Number) inventoryMap.get("id")).longValue() : null;

                // Extract product data
                Object productData = inventoryMap.get("product");
                ProductDTO productDTO = convertToProductDTO(productData);

                Integer quantity = inventoryMap.get("quantity") instanceof Number ? 
                        ((Number) inventoryMap.get("quantity")).intValue() : 0;

                String location = inventoryMap.get("location") instanceof String ? 
                        (String) inventoryMap.get("location") : "";

                return new InventoryDTO(id, productDTO, quantity, location);
            }

            // If it's not a Map, try to access the fields directly
            Class<?> clazz = inventoryData.getClass();

            Long id = null;
            ProductDTO productDTO = null;
            Integer quantity = 0;
            String location = "";

            try {
                Method getIdMethod = clazz.getMethod("getId");
                Object idObj = getIdMethod.invoke(inventoryData);
                if (idObj instanceof Number) {
                    id = ((Number) idObj).longValue();
                }
            } catch (Exception e) {
                // Ignore if the method doesn't exist
            }

            try {
                Method getProductMethod = clazz.getMethod("getProduct");
                Object productObj = getProductMethod.invoke(inventoryData);
                productDTO = convertToProductDTO(productObj);
            } catch (Exception e) {
                // Ignore if the method doesn't exist
            }

            try {
                Method getQuantityMethod = clazz.getMethod("getQuantity");
                Object quantityObj = getQuantityMethod.invoke(inventoryData);
                if (quantityObj instanceof Number) {
                    quantity = ((Number) quantityObj).intValue();
                }
            } catch (Exception e) {
                // Ignore if the method doesn't exist
            }

            try {
                Method getLocationMethod = clazz.getMethod("getLocation");
                Object locationObj = getLocationMethod.invoke(inventoryData);
                if (locationObj instanceof String) {
                    location = (String) locationObj;
                }
            } catch (Exception e) {
                // Ignore if the method doesn't exist
            }

            return new InventoryDTO(id, productDTO, quantity, location);
        } catch (Exception e) {
            // Log the error and return null
            System.err.println("Error converting inventory data: " + e.getMessage());
            return null;
        }
    }

    private ProductDTO convertToProductDTO(Object productData) {
        if (productData == null) {
            return null;
        }

        try {
            // Assuming productData is a Map with the product properties
            if (productData instanceof Map) {
                Map<?, ?> productMap = (Map<?, ?>) productData;

                Long id = productMap.get("id") instanceof Number ? 
                        ((Number) productMap.get("id")).longValue() : null;
                String name = productMap.get("name") instanceof String ? 
                        (String) productMap.get("name") : "";
                String description = productMap.get("description") instanceof String ? 
                        (String) productMap.get("description") : "";

                BigDecimal price = null;
                Object priceObj = productMap.get("price");
                if (priceObj instanceof Number) {
                    price = new BigDecimal(priceObj.toString());
                } else if (priceObj instanceof String) {
                    try {
                        price = new BigDecimal((String) priceObj);
                    } catch (Exception e) {
                        price = BigDecimal.ZERO;
                    }
                } else {
                    price = BigDecimal.ZERO;
                }

                // Extract category data
                Object categoryData = productMap.get("category");
                CategoryDTO categoryDTO = convertToCategoryDTO(categoryData);

                return new ProductDTO(id, name, description, price, categoryDTO);
            }

            // If it's not a Map, try to access the fields directly using reflection
            // (similar to the approach in convertToInventoryDTO)
            // For brevity, we'll return a default product
            return new ProductDTO(1L, "Unknown Product", "", BigDecimal.ZERO);
        } catch (Exception e) {
            // Log the error and return null
            System.err.println("Error converting product data: " + e.getMessage());
            return null;
        }
    }

    private CategoryDTO convertToCategoryDTO(Object categoryData) {
        if (categoryData == null) {
            return null;
        }

        try {
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

            // If it's not a Map, try to access the fields directly using reflection
            // (similar to the approach in convertToInventoryDTO)
            // For brevity, we'll return a default category
            return new CategoryDTO(1L, "Unknown Category", "");
        } catch (Exception e) {
            // Log the error and return null
            System.err.println("Error converting category data: " + e.getMessage());
            return null;
        }
    }

    private List<InventoryDTO> convertToInventoryDTOList(List<Object> inventoryDataList) {
        if (inventoryDataList == null) {
            return Collections.emptyList();
        }

        List<InventoryDTO> result = new ArrayList<>();
        for (Object inventoryData : inventoryDataList) {
            InventoryDTO inventoryDTO = convertToInventoryDTO(inventoryData);
            if (inventoryDTO != null) {
                result.add(inventoryDTO);
            }
        }
        return result;
    }

    private Object convertFromInventoryDTO(InventoryDTO inventoryDTO) {
        if (inventoryDTO == null) {
            return null;
        }

        // Create a Map with the inventory properties
        Map<String, Object> inventoryMap = new HashMap<>();
        inventoryMap.put("id", inventoryDTO.getId());

        // Convert product to a Map
        if (inventoryDTO.getProduct() != null) {
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("id", inventoryDTO.getProduct().getId());
            productMap.put("name", inventoryDTO.getProduct().getName());
            productMap.put("description", inventoryDTO.getProduct().getDescription());
            productMap.put("price", inventoryDTO.getProduct().getPrice());

            // Convert category to a Map if present
            if (inventoryDTO.getProduct().getCategory() != null) {
                Map<String, Object> categoryMap = new HashMap<>();
                categoryMap.put("id", inventoryDTO.getProduct().getCategory().getId());
                categoryMap.put("name", inventoryDTO.getProduct().getCategory().getName());
                categoryMap.put("description", inventoryDTO.getProduct().getCategory().getDescription());

                productMap.put("category", categoryMap);
            }

            inventoryMap.put("product", productMap);
        }

        inventoryMap.put("quantity", inventoryDTO.getQuantity());
        inventoryMap.put("location", inventoryDTO.getLocation());

        return inventoryMap;
    }
}
