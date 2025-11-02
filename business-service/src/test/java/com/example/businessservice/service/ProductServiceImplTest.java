package com.example.businessservice.service;

import com.example.businessservice.client.ProductClient;
import com.example.businessservice.dto.CategoryDTO;
import com.example.businessservice.dto.ProductDTO;
import com.example.businessservice.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductClient productClient;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts_shouldMapListFromDataService() {
        // Arrange: simulate data-service sending list of maps
        List<Object> body = new ArrayList<>();
        Map<String, Object> p = new HashMap<>();
        p.put("id", 10L);
        p.put("name", "Phone");
        p.put("description", "An awesome phone");
        p.put("price", 499.99);
        Map<String, Object> cat = new HashMap<>();
        cat.put("id", 1L);
        cat.put("name", "Electronics");
        cat.put("description", "Devices");
        p.put("category", cat);
        body.add(p);

        when(productClient.getAllProducts()).thenReturn(new ResponseEntity<>(body, HttpStatus.OK));

        // Act
        List<ProductDTO> result = productService.getAllProducts();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        ProductDTO dto = result.get(0);
        assertEquals(10L, dto.getId());
        assertEquals("Phone", dto.getName());
        assertEquals(new BigDecimal("499.99"), dto.getPrice());
        assertNotNull(dto.getCategory());
        assertEquals("Electronics", dto.getCategory().getName());
    }

    @Test
    void getProductById_shouldMapSingleObject() {
        // Arrange
        Map<String, Object> p = new HashMap<>();
        p.put("id", 5);
        p.put("name", "Book");
        p.put("description", "A great book");
        p.put("price", "39.99");

        when(productClient.getProductById(5L)).thenReturn(new ResponseEntity<>(p, HttpStatus.OK));

        // Act
        ProductDTO dto = productService.getProductById(5L);

        // Assert
        assertNotNull(dto);
        assertEquals(5L, dto.getId());
        assertEquals("Book", dto.getName());
        assertEquals(new BigDecimal("39.99"), dto.getPrice());
    }

    @Test
    void createProduct_shouldReturnMappedDTO() {
        // Arrange: create request DTO
        ProductDTO req = new ProductDTO(null, "Laptop", "Gaming", new BigDecimal("1299.99"), new CategoryDTO(1L, "Electronics", ""));

        // Simulate data-service response
        Map<String, Object> p = new HashMap<>();
        p.put("id", 99);
        p.put("name", "Laptop");
        p.put("description", "Gaming");
        p.put("price", 1299.99);
        Map<String, Object> cat = new HashMap<>();
        cat.put("id", 1);
        cat.put("name", "Electronics");
        cat.put("description", "");
        p.put("category", cat);

        when(productClient.createProduct(any())).thenReturn(new ResponseEntity<>(p, HttpStatus.CREATED));

        // Act
        ProductDTO dto = productService.createProduct(req);

        // Assert
        assertNotNull(dto);
        assertEquals(99L, dto.getId());
        assertEquals("Laptop", dto.getName());
        assertEquals(new BigDecimal("1299.99"), dto.getPrice());
        assertNotNull(dto.getCategory());
        assertEquals(1L, dto.getCategory().getId());
    }
}
