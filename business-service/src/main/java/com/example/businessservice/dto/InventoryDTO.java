package com.example.businessservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for Inventory entity.
 */
public class InventoryDTO {

    private Long id;

    @NotNull(message = "Product is required")
    private ProductDTO product;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @NotNull(message = "Location is required")
    private String location;

    // Default constructor
    public InventoryDTO() {
    }

    // Constructor with fields
    public InventoryDTO(Long id, ProductDTO product, Integer quantity, String location) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.location = location;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "InventoryDTO{" +
                "id=" + id +
                ", product=" + (product != null ? product.getName() : null) +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                '}';
    }
}