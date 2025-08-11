package com.java.TrainningJV.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class UserControllerIntegrationTest  {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setup(){

         jdbcTemplate.update("DELETE FROM users");

        jdbcTemplate.update("""
            INSERT INTO users (id, first_name, last_name, email, password, date_of_birth, gender, phone, address, role_id)
            VALUES (1, 'Tra', 'Nguyen', 'tra@gmail.com', '12345667', '1995-05-20', 'Male', '0912345678', '123 HN', 3)
        """);
    }

    @Test
    void testCreateUser_ThenCheckDb() throws Exception {
        String json = """
        {
            "firstName": "Tra",
            "lastName": "Nguyen",
            "email": "tra1@gmail.com",
            "password": "12345667",
            "dateOfBirth": "1995-05-20",
            "gender": "Male",
            "phoneNumber": "0912345678",
            "address": "123 Đường ABC, Quận Ha Dong, TP.HN",
            "roleId": 3
        }
        """;

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
            

        Integer count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM users WHERE email = ?",
            Integer.class,
            "tra1@gmail.com"
        );

        assertEquals(1, count); // DB phải có đúng 1 user được insert
    }

    @Test
    void testCreateUser_InvalidEmailFormat() throws Exception {
    String json = """
        {
            "firstName": "Tra",
            "lastName": "Nguyen",
            "email": "invalid-email",
            "password": "12345667",
            "dateOfBirth": "1995-05-20",
            "gender": "Male",
            "phoneNumber": "0912345678",
            "address": "123 Đường ABC, Quận Ha Dong, TP.HN",
            "roleId": 3
        }
    """;

    mockMvc.perform(MockMvcRequestBuilders
            .post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("email: Invalid email format"));
    }

    @Test
    void testUpdateUser_Success() throws Exception {
    String json = """
        {
            "firstName": "Tra Updated",
            "lastName": "Nguyen",
            "email": "tra_updated@gmail.com",
            "password": "12345667",
            "dateOfBirth": "1995-05-20",
            "gender": "Male",
            "phoneNumber": "0999999999",
            "address": "456 HN",
            "roleId": 3
        }
    """;

    mockMvc.perform(MockMvcRequestBuilders  
            .put("/api/users/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User updated successfully"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(1));

    String email = jdbcTemplate.queryForObject("SELECT email FROM users WHERE id = ?", String.class, 1);
    assertEquals("tra_updated@gmail.com", email);
    }

    @Test
    void testDeleteUser_Success() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
        .delete("/api/users/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User deleted successfully"));

    Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users WHERE id = ?", Integer.class, 1);
    assertEquals(0, count);
    }
    
}
