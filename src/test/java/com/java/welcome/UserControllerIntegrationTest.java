package com.java.welcome.controller;

import com.java.welcome.repository.UserAccountRepository;
import com.java.welcome.model.UserAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserAccountRepository repository;

    @BeforeEach
    void cleanDatabase() {
        repository.deleteAll();
    }

    @Test //POST request Test
    void createUser() throws Exception {

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "username":"pam",
                        "email":"pam@example.com"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("pam"))
                .andExpect(jsonPath("$.email").value("pam@example.com"));
    }

    @Test // GET by ID test
    void getUser() throws Exception { 
    
        UserAccount user = new UserAccount();
        user.setUsername("alice");
        user.setEmail("alice@example.com");
    
        UserAccount saved = repository.save(user);
    
        mockMvc.perform(get("/users/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(saved.getId()))
                .andExpect(jsonPath("$.username").value("alice"))
                .andExpect(jsonPath("$.email").value("alice@example.com"));
    }

    @Test // GET all users test
    void getAllUsers() throws Exception { 
    
        UserAccount user1 = new UserAccount();
        user1.setUsername("joe");
        user1.setEmail("joe@example.com");
    
        UserAccount user2 = new UserAccount();
        user2.setUsername("jane");
        user2.setEmail("jane@example.com");
    
        repository.save(user1);
        repository.save(user2);
    
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].username").exists())
                .andExpect(jsonPath("$[1].username").exists());
    }

    @Test // PUTS by ID test
    void updateUser() throws Exception {
    
        UserAccount user = new UserAccount();
        user.setUsername("alvin");
        user.setEmail("alvin@example.com");
    
        UserAccount saved = repository.save(user);
    
        mockMvc.perform(put("/users/" + saved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "username":"Alvin",
                        "email":"alvin@example.com"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Alvin"))
                .andExpect(jsonPath("$.email").value("alvin@example.com"));
    }

    @Test // DELETE by ID test
    void deleteUser() throws Exception {
    
        UserAccount user = new UserAccount();
        user.setUsername("deleteMe");
        user.setEmail("delete@example.com");
    
        UserAccount saved = repository.save(user);
    
        mockMvc.perform(delete("/users/" + saved.getId()))
                .andExpect(status().isOk());
    
        assertFalse(repository.findById(saved.getId()).isPresent());
    }
}