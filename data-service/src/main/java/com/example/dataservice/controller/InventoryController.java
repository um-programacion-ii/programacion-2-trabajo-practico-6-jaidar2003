package com.example.dataservice.controller;

import com.example.dataservice.entity.Inventory;
import com.example.dataservice.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing inventory items.
 */
@RestController
@RequestMapping("/data/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * GET /data/inventory : Get all inventory items.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items in the body
     */
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventoryItems() {
        List<Inventory> inventoryItems = inventoryService.getAllInventoryItems();
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /data/inventory/{id} : Get the inventory item with the specified ID.
     *
     * @param id the ID of the inventory item to retrieve
     * @return the ResponseEntity with status 200 (OK) and the inventory item in the body,
     *         or with status 404 (Not Found) if the inventory item is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryItemById(@PathVariable Long id) {
        Inventory inventoryItem = inventoryService.getInventoryItemById(id);
        return new ResponseEntity<>(inventoryItem, HttpStatus.OK);
    }

    /**
     * GET /data/inventory/product/{productId} : Get inventory items by product ID.
     *
     * @param productId the product ID
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items for the specified product in the body,
     *         or with status 404 (Not Found) if the product is not found
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Inventory>> getInventoryItemsByProductId(@PathVariable Long productId) {
        List<Inventory> inventoryItems = inventoryService.findInventoryItemsByProductId(productId);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /data/inventory/location/{location} : Get inventory items by location.
     *
     * @param location the location
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items at the specified location in the body
     */
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Inventory>> getInventoryItemsByLocation(@PathVariable String location) {
        List<Inventory> inventoryItems = inventoryService.findInventoryItemsByLocation(location);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /data/inventory/quantity/less/{quantity} : Get inventory items with quantity less than the given value.
     *
     * @param quantity the quantity threshold
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items with quantity less than the given value in the body
     */
    @GetMapping("/quantity/less/{quantity}")
    public ResponseEntity<List<Inventory>> getInventoryItemsByQuantityLessThan(@PathVariable Integer quantity) {
        List<Inventory> inventoryItems = inventoryService.findInventoryItemsByQuantityLessThan(quantity);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /data/inventory/quantity/greater/{quantity} : Get inventory items with quantity greater than the given value.
     *
     * @param quantity the quantity threshold
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items with quantity greater than the given value in the body
     */
    @GetMapping("/quantity/greater/{quantity}")
    public ResponseEntity<List<Inventory>> getInventoryItemsByQuantityGreaterThan(@PathVariable Integer quantity) {
        List<Inventory> inventoryItems = inventoryService.findInventoryItemsByQuantityGreaterThan(quantity);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /data/inventory/quantity/range : Get inventory items with quantity between the given values.
     *
     * @param minQuantity the minimum quantity
     * @param maxQuantity the maximum quantity
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items with quantity between the given values in the body
     */
    @GetMapping("/quantity/range")
    public ResponseEntity<List<Inventory>> getInventoryItemsByQuantityBetween(
            @RequestParam Integer minQuantity, @RequestParam Integer maxQuantity) {
        List<Inventory> inventoryItems = inventoryService.findInventoryItemsByQuantityBetween(minQuantity, maxQuantity);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /data/inventory/product/name/{productName} : Get inventory items by product name.
     *
     * @param productName the product name
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items for products matching the given name in the body
     */
    @GetMapping("/product/name/{productName}")
    public ResponseEntity<List<Inventory>> getInventoryItemsByProductName(@PathVariable String productName) {
        List<Inventory> inventoryItems = inventoryService.findInventoryItemsByProductName(productName);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /data/inventory/category/{categoryId} : Get inventory items by product category.
     *
     * @param categoryId the category ID
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items for products in the specified category in the body
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Inventory>> getInventoryItemsByProductCategory(@PathVariable Long categoryId) {
        List<Inventory> inventoryItems = inventoryService.findInventoryItemsByProductCategory(categoryId);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /data/inventory/out-of-stock : Get out-of-stock inventory items.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of out-of-stock inventory items in the body
     */
    @GetMapping("/out-of-stock")
    public ResponseEntity<List<Inventory>> getOutOfStockItems() {
        List<Inventory> inventoryItems = inventoryService.findOutOfStockItems();
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * POST /data/inventory : Create a new inventory item.
     *
     * @param inventory the inventory item to create
     * @return the ResponseEntity with status 201 (Created) and the new inventory item in the body,
     *         or with status 400 (Bad Request) if the inventory data is invalid,
     *         or with status 404 (Not Found) if the referenced product is not found
     */
    @PostMapping
    public ResponseEntity<Inventory> createInventoryItem(@Valid @RequestBody Inventory inventory) {
        Inventory newInventory = inventoryService.createInventoryItem(inventory);
        return new ResponseEntity<>(newInventory, HttpStatus.CREATED);
    }

    /**
     * PUT /data/inventory/{id} : Update an existing inventory item.
     *
     * @param id the ID of the inventory item to update
     * @param inventory the updated inventory data
     * @return the ResponseEntity with status 200 (OK) and the updated inventory item in the body,
     *         or with status 400 (Bad Request) if the inventory data is invalid,
     *         or with status 404 (Not Found) if the inventory item or referenced product is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventoryItem(@PathVariable Long id, @Valid @RequestBody Inventory inventory) {
        Inventory updatedInventory = inventoryService.updateInventoryItem(id, inventory);
        return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
    }

    /**
     * PATCH /data/inventory/{id}/quantity/{quantity} : Update the quantity of an inventory item.
     *
     * @param id the ID of the inventory item to update
     * @param quantity the new quantity
     * @return the ResponseEntity with status 200 (OK) and the updated inventory item in the body,
     *         or with status 400 (Bad Request) if the quantity is invalid,
     *         or with status 404 (Not Found) if the inventory item is not found
     */
    @PatchMapping("/{id}/quantity/{quantity}")
    public ResponseEntity<Inventory> updateInventoryQuantity(@PathVariable Long id, @PathVariable Integer quantity) {
        Inventory updatedInventory = inventoryService.updateInventoryQuantity(id, quantity);
        return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
    }

    /**
     * DELETE /data/inventory/{id} : Delete an inventory item.
     *
     * @param id the ID of the inventory item to delete
     * @return the ResponseEntity with status 204 (No Content),
     *         or with status 404 (Not Found) if the inventory item is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id) {
        inventoryService.deleteInventoryItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}