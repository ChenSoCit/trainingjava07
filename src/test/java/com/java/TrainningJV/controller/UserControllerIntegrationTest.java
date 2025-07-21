package com.java.TrainningJV.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void testCreateUser_ThenCheckDb() throws Exception {
        String json = """
        {
            "firstName": "Tra",
            "lastName": "Nguyen",
            "email": "tra@gmail.com"
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
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Integer count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM users WHERE email = ?",
            Integer.class,
            "tra@gmail.com"
        );

        assertEquals(1, count); // DB phải có đúng 1 user được insert
    }
}
