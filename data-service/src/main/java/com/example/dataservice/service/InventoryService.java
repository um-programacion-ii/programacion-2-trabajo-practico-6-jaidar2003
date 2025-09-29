package com.example.dataservice.service;

import com.example.dataservice.entity.Inventory;

import java.util.List;

/**
 * Service interface for managing inventory items.
 */
public interface InventoryService {
    
    /**
     * Get all inventory items.
     *
     * @return list of all inventory items
     */
    List<Inventory> getAllInventoryItems();
    
    /**
     * Get an inventory item by its ID.
     *
     * @param id the inventory item ID
     * @return the inventory item if found
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the inventory item is not found
     */
    Inventory getInventoryItemById(Long id);
    
    /**
     * Find inventory items by product ID.
     *
     * @param productId the product ID
     * @return list of inventory items for the specified product
     */
    List<Inventory> findInventoryItemsByProductId(Long productId);
    
    /**
     * Find inventory items by location.
     *
     * @param location the location
     * @return list of inventory items at the specified location
     */
    List<Inventory> findInventoryItemsByLocation(String location);
    
    /**
     * Find inventory items with quantity less than the given value.
     *
     * @param quantity the quantity threshold
     * @return list of inventory items with quantity less than the given value
     */
    List<Inventory> findInventoryItemsByQuantityLessThan(Integer quantity);
    
    /**
     * Find inventory items with quantity greater than the given value.
     *
     * @param quantity the quantity threshold
     * @return list of inventory items with quantity greater than the given value
     */
    List<Inventory> findInventoryItemsByQuantityGreaterThan(Integer quantity);
    
    /**
     * Find inventory items with quantity between the given values.
     *
     * @param minQuantity the minimum quantity
     * @param maxQuantity the maximum quantity
     * @return list of inventory items with quantity between the given values
     */
    List<Inventory> findInventoryItemsByQuantityBetween(Integer minQuantity, Integer maxQuantity);
    
    /**
     * Find inventory items by product name.
     *
     * @param productName the product name
     * @return list of inventory items for products matching the given name
     */
    List<Inventory> findInventoryItemsByProductName(String productName);
    
    /**
     * Find inventory items by product category.
     *
     * @param categoryId the category ID
     * @return list of inventory items for products in the specified category
     */
    List<Inventory> findInventoryItemsByProductCategory(Long categoryId);
    
    /**
     * Find out-of-stock inventory items (quantity = 0).
     *
     * @return list of out-of-stock inventory items
     */
    List<Inventory> findOutOfStockItems();
    
    /**
     * Create a new inventory item.
     *
     * @param inventory the inventory item to create
     * @return the created inventory item
     * @throws com.example.dataservice.exception.ValidationException if the inventory data is invalid
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the referenced product is not found
     */
    Inventory createInventoryItem(Inventory inventory);
    
    /**
     * Update an existing inventory item.
     *
     * @param id the inventory item ID
     * @param inventoryDetails the updated inventory data
     * @return the updated inventory item
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the inventory item is not found
     * @throws com.example.dataservice.exception.ValidationException if the inventory data is invalid
     */
    Inventory updateInventoryItem(Long id, Inventory inventoryDetails);
    
    /**
     * Update the quantity of an inventory item.
     *
     * @param id the inventory item ID
     * @param quantity the new quantity
     * @return the updated inventory item
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the inventory item is not found
     * @throws com.example.dataservice.exception.ValidationException if the quantity is invalid
     */
    Inventory updateInventoryQuantity(Long id, Integer quantity);
    
    /**
     * Delete an inventory item by its ID.
     *
     * @param id the inventory item ID
     * @throws com.example.dataservice.exception.ResourceNotFoundException if the inventory item is not found
     */
    void deleteInventoryItem(Long id);
    
    /**
     * Check if an inventory item exists with the given ID.
     *
     * @param id the inventory item ID
     * @return true if the inventory item exists
     */
    boolean existsById(Long id);
}