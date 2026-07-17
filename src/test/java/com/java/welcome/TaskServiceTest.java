package com.java.welcome.service;

import com.java.welcome.dto.CreateTaskRequest;
import com.java.welcome.dto.UpdateTaskRequest;
import com.java.welcome.model.Task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService service;

    @Test
    void createTask() {

        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("Finish Homework");

        Task savedTask = service.createTask(request);

        assertNotNull(savedTask.getId());
        assertEquals("Finish Homework", savedTask.getTitle());
        assertFalse(savedTask.isCompleted());
    }

    @Test
    void getTask() {

        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("Wash Dishes");

        Task saved = service.createTask(request);

        Task found = service.getTask(saved.getId());

        assertEquals(saved.getId(), found.getId());
        assertEquals("Wash Dishes", found.getTitle());
        assertFalse(found.isCompleted());
    }

    @Test
    void getAllTasks() {

        CreateTaskRequest request1 = new CreateTaskRequest();
        request1.setTitle("Laundry");

        CreateTaskRequest request2 = new CreateTaskRequest();
        request2.setTitle("Groceries");

        service.createTask(request1);
        service.createTask(request2);

        List<Task> tasks = service.getAllTasks();

        assertTrue(tasks.size() >= 2);
    }

    @Test
    void updateTask() {

        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("Homework");

        Task saved = service.createTask(request);

        UpdateTaskRequest updateRequest = new UpdateTaskRequest();
        updateRequest.setTitle("Homework Finished");
        updateRequest.setCompleted(true);

        Task updated = service.updateTask(saved.getId(), updateRequest);

        assertEquals("Homework Finished", updated.getTitle());
        assertTrue(updated.isCompleted());
    }

    @Test
    void deleteTask() {

        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("Delete Me");

        Task saved = service.createTask(request);

        service.deleteTask(saved.getId());

        assertThrows(RuntimeException.class,
                () -> service.getTask(saved.getId()));
    }
}