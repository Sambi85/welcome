package com.java.welcome.controller;

import com.java.welcome.model.Task;
import com.java.welcome.repository.TaskRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository repository;

    @BeforeEach
    void cleanDatabase() {
        repository.deleteAll();
    }

    @Test // POST task test
    void createTask() throws Exception { 
        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "title":"Finish Homework",
                        "completed":false
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Finish Homework"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test // GET task by ID test
    void getTask() throws Exception {
    
        Task task = new Task();
        task.setTitle("Wash Dishes");
        task.setCompleted(false);
    
        Task saved = repository.save(task);
    
        mockMvc.perform(get("/tasks/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(saved.getId()))
                .andExpect(jsonPath("$.title").value("Wash Dishes"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test // GET all tasks test
    void getAllTasks() throws Exception {
    
        Task task1 = new Task();
        task1.setTitle("Laundry");
        task1.setCompleted(false);
    
        Task task2 = new Task();
        task2.setTitle("Groceries");
        task2.setCompleted(true);
    
        repository.save(task1);
        repository.save(task2);
    
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").exists())
                .andExpect(jsonPath("$[1].title").exists());
    }

    @Test // PUTS task by ID test
    void updateTask() throws Exception {
    
        Task task = new Task();
        task.setTitle("Homework");
        task.setCompleted(false);
    
        Task saved = repository.save(task);
    
        mockMvc.perform(put("/tasks/" + saved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "title":"Homework Finished",
                        "completed":true
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Homework Finished"))
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test //DELETE task by ID test
    void deleteTask() throws Exception {
    
        Task task = new Task();
        task.setTitle("Delete Me");
        task.setCompleted(false);
    
        Task saved = repository.save(task);
    
        mockMvc.perform(delete("/tasks/" + saved.getId()))
                .andExpect(status().isOk());
    
        assertFalse(repository.findById(saved.getId()).isPresent());
    }
}