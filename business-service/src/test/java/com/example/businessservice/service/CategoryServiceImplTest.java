package com.example.businessservice.service;

import com.example.businessservice.client.CategoryClient;
import com.example.businessservice.dto.CategoryDTO;
import com.example.businessservice.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    @Mock
    private CategoryClient categoryClient;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories_shouldMapResponseList() {
        // Arrange
        List<Object> body = new ArrayList<>();
        Map<String, Object> c1 = new HashMap<>();
        c1.put("id", 1);
        c1.put("name", "Electronics");
        c1.put("description", "Devices");
        body.add(c1);

        when(categoryClient.getAllCategories()).thenReturn(new ResponseEntity<>(body, HttpStatus.OK));

        // Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Electronics", result.get(0).getName());
    }

    @Test
    void getCategoryById_shouldMapSingleObject() {
        // Arrange
        Map<String, Object> cat = new HashMap<>();
        cat.put("id", 5);
        cat.put("name", "Books");
        cat.put("description", "Reading");

        when(categoryClient.getCategoryById(5L)).thenReturn(new ResponseEntity<>(cat, HttpStatus.OK));

        // Act
        CategoryDTO dto = categoryService.getCategoryById(5L);

        // Assert
        assertNotNull(dto);
        assertEquals(5L, dto.getId());
        assertEquals("Books", dto.getName());
        assertEquals("Reading", dto.getDescription());
    }
}
