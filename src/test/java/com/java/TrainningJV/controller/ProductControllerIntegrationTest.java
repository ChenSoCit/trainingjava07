package com.java.TrainningJV.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setup(){
        // Chuẩn bị dữ liệu giả vào DB
        jdbcTemplate.update("""
        INSERT INTO categories (id, name)
        VALUES (?, ?)
         """, 2, "Electronics");

        // Thêm product gắn với category (dùng LocalDateTime.now())
        jdbcTemplate.update("""
        INSERT INTO products
        (id, name, description, price, stock_quantity, created_at, updated_at, category_id)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """, 1, "iPhone 15", "Latest model", 999.99, 50,
         LocalDateTime.now(), LocalDateTime.now(), 2);
    }
    
    @Test
    void createProduct_Success() throws Exception {
    // Tạo category cần thiết
    jdbcTemplate.update("INSERT INTO categories (id, name) VALUES (?, ?)", 5, "Electronics1");

    // JSON body request
    String json = """
        {
            "nameProduct": "iPhone 16",
            "description": "Flagship model",
            "price": 1299.99,
            "stockQuantity": 100,
            "categoryId": 2
        }

    """;

    // Gọi API và kiểm tra phản hồi
    mockMvc.perform(MockMvcRequestBuilders
            .post("/api/v1/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
       

    // Kiểm tra lại DB
    String name = jdbcTemplate.queryForObject(
        "SELECT name FROM products WHERE name = ?", String.class, "iPhone 16");

    assertEquals("iPhone 16", name);
    }

    @Test
    void getUser_Success() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/1")
    .contentType(MediaType.APPLICATION_JSON_VALUE))

        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("iPhone 15"));
    }

    @Test
    void getUser_Notfound() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/999"))

        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Product User not found with : = 999"));

    }
}
