package com.example.businessservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Feign client for communicating with the inventory endpoints in the data service.
 */
@FeignClient(name = "inventory-service", url = "${data-service.url}/data/inventory")
public interface InventoryClient {

    /**
     * Get all inventory items.
     *
     * @return ResponseEntity containing a list of inventory items
     */
    @GetMapping
    ResponseEntity<List<Object>> getAllInventoryItems();

    /**
     * Get an inventory item by its ID.
     *
     * @param id the inventory item ID
     * @return ResponseEntity containing the inventory item
     */
    @GetMapping("/{id}")
    ResponseEntity<Object> getInventoryItemById(@PathVariable("id") Long id);

    /**
     * Get inventory items by product ID.
     *
     * @param productId the product ID
     * @return ResponseEntity containing a list of inventory items for the specified product
     */
    @GetMapping("/product/{productId}")
    ResponseEntity<List<Object>> getInventoryItemsByProductId(@PathVariable("productId") Long productId);

    /**
     * Get inventory items by location.
     *
     * @param location the location
     * @return ResponseEntity containing a list of inventory items at the specified location
     */
    @GetMapping("/location/{location}")
    ResponseEntity<List<Object>> getInventoryItemsByLocation(@PathVariable("location") String location);

    /**
     * Get inventory items with quantity less than the given value.
     *
     * @param quantity the quantity threshold
     * @return ResponseEntity containing a list of inventory items with quantity less than the given value
     */
    @GetMapping("/quantity/less/{quantity}")
    ResponseEntity<List<Object>> getInventoryItemsByQuantityLessThan(@PathVariable("quantity") Integer quantity);

    /**
     * Get inventory items with quantity greater than the given value.
     *
     * @param quantity the quantity threshold
     * @return ResponseEntity containing a list of inventory items with quantity greater than the given value
     */
    @GetMapping("/quantity/greater/{quantity}")
    ResponseEntity<List<Object>> getInventoryItemsByQuantityGreaterThan(@PathVariable("quantity") Integer quantity);

    /**
     * Get inventory items with quantity between the given values.
     *
     * @param minQuantity the minimum quantity
     * @param maxQuantity the maximum quantity
     * @return ResponseEntity containing a list of inventory items with quantity between the given values
     */
    @GetMapping("/quantity/range")
    ResponseEntity<List<Object>> getInventoryItemsByQuantityBetween(
            @RequestParam("minQuantity") Integer minQuantity, 
            @RequestParam("maxQuantity") Integer maxQuantity);

    /**
     * Get inventory items by product name.
     *
     * @param productName the product name
     * @return ResponseEntity containing a list of inventory items for products matching the given name
     */
    @GetMapping("/product/name/{productName}")
    ResponseEntity<List<Object>> getInventoryItemsByProductName(@PathVariable("productName") String productName);

    /**
     * Get inventory items by product category.
     *
     * @param categoryId the category ID
     * @return ResponseEntity containing a list of inventory items for products in the specified category
     */
    @GetMapping("/category/{categoryId}")
    ResponseEntity<List<Object>> getInventoryItemsByProductCategory(@PathVariable("categoryId") Long categoryId);

    /**
     * Get out-of-stock inventory items.
     *
     * @return ResponseEntity containing a list of out-of-stock inventory items
     */
    @GetMapping("/out-of-stock")
    ResponseEntity<List<Object>> getOutOfStockItems();

    /**
     * Create a new inventory item.
     *
     * @param inventory the inventory item to create
     * @return ResponseEntity containing the created inventory item
     */
    @PostMapping
    ResponseEntity<Object> createInventoryItem(@RequestBody Object inventory);

    /**
     * Update an existing inventory item.
     *
     * @param id the inventory item ID
     * @param inventory the updated inventory data
     * @return ResponseEntity containing the updated inventory item
     */
    @PutMapping("/{id}")
    ResponseEntity<Object> updateInventoryItem(@PathVariable("id") Long id, @RequestBody Object inventory);

    /**
     * Update the quantity of an inventory item.
     *
     * @param id the inventory item ID
     * @param quantity the new quantity
     * @return ResponseEntity containing the updated inventory item
     */
    @PatchMapping("/{id}/quantity/{quantity}")
    ResponseEntity<Object> updateInventoryQuantity(
            @PathVariable("id") Long id, 
            @PathVariable("quantity") Integer quantity);

    /**
     * Delete an inventory item.
     *
     * @param id the inventory item ID
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteInventoryItem(@PathVariable("id") Long id);
}