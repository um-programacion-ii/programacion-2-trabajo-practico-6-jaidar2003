package com.example.businessservice.controller;

import com.example.businessservice.dto.InventoryDTO;
import com.example.businessservice.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing inventory items in the business service.
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * GET /api/inventory : Get all inventory items.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items in the body
     */
    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventoryItems() {
        List<InventoryDTO> inventoryItems = inventoryService.getAllInventoryItems();
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /api/inventory/{id} : Get the inventory item with the specified ID.
     *
     * @param id the ID of the inventory item to retrieve
     * @return the ResponseEntity with status 200 (OK) and the inventory item in the body,
     *         or with status 404 (Not Found) if the inventory item is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventoryItemById(@PathVariable Long id) {
        InventoryDTO inventoryItem = inventoryService.getInventoryItemById(id);
        if (inventoryItem != null) {
            return new ResponseEntity<>(inventoryItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET /api/inventory/product/{productId} : Get inventory items by product ID.
     *
     * @param productId the product ID
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items for the specified product in the body
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<InventoryDTO>> getInventoryItemsByProductId(@PathVariable Long productId) {
        List<InventoryDTO> inventoryItems = inventoryService.findInventoryItemsByProductId(productId);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /api/inventory/location/{location} : Get inventory items by location.
     *
     * @param location the location
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items at the specified location in the body
     */
    @GetMapping("/location/{location}")
    public ResponseEntity<List<InventoryDTO>> getInventoryItemsByLocation(@PathVariable String location) {
        List<InventoryDTO> inventoryItems = inventoryService.findInventoryItemsByLocation(location);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /api/inventory/quantity/less/{quantity} : Get inventory items with quantity less than the given value.
     *
     * @param quantity the quantity threshold
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items with quantity less than the given value in the body
     */
    @GetMapping("/quantity/less/{quantity}")
    public ResponseEntity<List<InventoryDTO>> getInventoryItemsByQuantityLessThan(@PathVariable Integer quantity) {
        List<InventoryDTO> inventoryItems = inventoryService.findInventoryItemsByQuantityLessThan(quantity);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /api/inventory/quantity/greater/{quantity} : Get inventory items with quantity greater than the given value.
     *
     * @param quantity the quantity threshold
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items with quantity greater than the given value in the body
     */
    @GetMapping("/quantity/greater/{quantity}")
    public ResponseEntity<List<InventoryDTO>> getInventoryItemsByQuantityGreaterThan(@PathVariable Integer quantity) {
        List<InventoryDTO> inventoryItems = inventoryService.findInventoryItemsByQuantityGreaterThan(quantity);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /api/inventory/quantity/range : Get inventory items with quantity between the given values.
     *
     * @param minQuantity the minimum quantity
     * @param maxQuantity the maximum quantity
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items with quantity between the given values in the body
     */
    @GetMapping("/quantity/range")
    public ResponseEntity<List<InventoryDTO>> getInventoryItemsByQuantityBetween(
            @RequestParam Integer minQuantity, @RequestParam Integer maxQuantity) {
        List<InventoryDTO> inventoryItems = inventoryService.findInventoryItemsByQuantityBetween(minQuantity, maxQuantity);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /api/inventory/product/name/{productName} : Get inventory items by product name.
     *
     * @param productName the product name
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items for products matching the given name in the body
     */
    @GetMapping("/product/name/{productName}")
    public ResponseEntity<List<InventoryDTO>> getInventoryItemsByProductName(@PathVariable String productName) {
        List<InventoryDTO> inventoryItems = inventoryService.findInventoryItemsByProductName(productName);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /api/inventory/category/{categoryId} : Get inventory items by product category.
     *
     * @param categoryId the category ID
     * @return the ResponseEntity with status 200 (OK) and the list of inventory items for products in the specified category in the body
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<InventoryDTO>> getInventoryItemsByProductCategory(@PathVariable Long categoryId) {
        List<InventoryDTO> inventoryItems = inventoryService.findInventoryItemsByProductCategory(categoryId);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * GET /api/inventory/out-of-stock : Get out-of-stock inventory items.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of out-of-stock inventory items in the body
     */
    @GetMapping("/out-of-stock")
    public ResponseEntity<List<InventoryDTO>> getOutOfStockItems() {
        List<InventoryDTO> inventoryItems = inventoryService.findOutOfStockItems();
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    /**
     * POST /api/inventory : Create a new inventory item.
     *
     * @param inventoryDTO the inventory item to create
     * @return the ResponseEntity with status 201 (Created) and the new inventory item in the body
     */
    @PostMapping
    public ResponseEntity<InventoryDTO> createInventoryItem(@Valid @RequestBody InventoryDTO inventoryDTO) {
        InventoryDTO newInventory = inventoryService.createInventoryItem(inventoryDTO);
        if (newInventory != null) {
            return new ResponseEntity<>(newInventory, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * PUT /api/inventory/{id} : Update an existing inventory item.
     *
     * @param id the ID of the inventory item to update
     * @param inventoryDTO the updated inventory data
     * @return the ResponseEntity with status 200 (OK) and the updated inventory item in the body,
     *         or with status 404 (Not Found) if the inventory item is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateInventoryItem(@PathVariable Long id, @Valid @RequestBody InventoryDTO inventoryDTO) {
        InventoryDTO updatedInventory = inventoryService.updateInventoryItem(id, inventoryDTO);
        if (updatedInventory != null) {
            return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * PATCH /api/inventory/{id}/quantity/{quantity} : Update the quantity of an inventory item.
     *
     * @param id the ID of the inventory item to update
     * @param quantity the new quantity
     * @return the ResponseEntity with status 200 (OK) and the updated inventory item in the body,
     *         or with status 404 (Not Found) if the inventory item is not found
     */
    @PatchMapping("/{id}/quantity/{quantity}")
    public ResponseEntity<InventoryDTO> updateInventoryQuantity(@PathVariable Long id, @PathVariable Integer quantity) {
        InventoryDTO updatedInventory = inventoryService.updateInventoryQuantity(id, quantity);
        if (updatedInventory != null) {
            return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE /api/inventory/{id} : Delete an inventory item.
     *
     * @param id the ID of the inventory item to delete
     * @return the ResponseEntity with status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id) {
        inventoryService.deleteInventoryItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}