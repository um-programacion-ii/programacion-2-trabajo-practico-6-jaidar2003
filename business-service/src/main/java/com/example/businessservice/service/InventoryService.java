package com.example.businessservice.service;

import com.example.businessservice.dto.InventoryDTO;

import java.util.List;

/**
 * Service interface for managing inventory items in the business service.
 */
public interface InventoryService {
    
    /**
     * Get all inventory items.
     *
     * @return list of all inventory items
     */
    List<InventoryDTO> getAllInventoryItems();
    
    /**
     * Get an inventory item by its ID.
     *
     * @param id the inventory item ID
     * @return the inventory item if found
     */
    InventoryDTO getInventoryItemById(Long id);
    
    /**
     * Find inventory items by product ID.
     *
     * @param productId the product ID
     * @return list of inventory items for the specified product
     */
    List<InventoryDTO> findInventoryItemsByProductId(Long productId);
    
    /**
     * Find inventory items by location.
     *
     * @param location the location
     * @return list of inventory items at the specified location
     */
    List<InventoryDTO> findInventoryItemsByLocation(String location);
    
    /**
     * Find inventory items with quantity less than the given value.
     *
     * @param quantity the quantity threshold
     * @return list of inventory items with quantity less than the given value
     */
    List<InventoryDTO> findInventoryItemsByQuantityLessThan(Integer quantity);
    
    /**
     * Find inventory items with quantity greater than the given value.
     *
     * @param quantity the quantity threshold
     * @return list of inventory items with quantity greater than the given value
     */
    List<InventoryDTO> findInventoryItemsByQuantityGreaterThan(Integer quantity);
    
    /**
     * Find inventory items with quantity between the given values.
     *
     * @param minQuantity the minimum quantity
     * @param maxQuantity the maximum quantity
     * @return list of inventory items with quantity between the given values
     */
    List<InventoryDTO> findInventoryItemsByQuantityBetween(Integer minQuantity, Integer maxQuantity);
    
    /**
     * Find inventory items by product name.
     *
     * @param productName the product name
     * @return list of inventory items for products matching the given name
     */
    List<InventoryDTO> findInventoryItemsByProductName(String productName);
    
    /**
     * Find inventory items by product category.
     *
     * @param categoryId the category ID
     * @return list of inventory items for products in the specified category
     */
    List<InventoryDTO> findInventoryItemsByProductCategory(Long categoryId);
    
    /**
     * Find out-of-stock inventory items.
     *
     * @return list of out-of-stock inventory items
     */
    List<InventoryDTO> findOutOfStockItems();
    
    /**
     * Create a new inventory item.
     *
     * @param inventoryDTO the inventory item to create
     * @return the created inventory item
     */
    InventoryDTO createInventoryItem(InventoryDTO inventoryDTO);
    
    /**
     * Update an existing inventory item.
     *
     * @param id the inventory item ID
     * @param inventoryDTO the updated inventory data
     * @return the updated inventory item
     */
    InventoryDTO updateInventoryItem(Long id, InventoryDTO inventoryDTO);
    
    /**
     * Update the quantity of an inventory item.
     *
     * @param id the inventory item ID
     * @param quantity the new quantity
     * @return the updated inventory item
     */
    InventoryDTO updateInventoryQuantity(Long id, Integer quantity);
    
    /**
     * Delete an inventory item by its ID.
     *
     * @param id the inventory item ID
     */
    void deleteInventoryItem(Long id);
}