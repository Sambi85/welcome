package com.java.welcome.service;

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

        Task task = new Task();
        task.setTitle("Finish Homework");
        task.setCompleted(false);

        Task savedTask = service.createTask(task);

        assertNotNull(savedTask.getId());
        assertEquals("Finish Homework", savedTask.getTitle());
        assertFalse(savedTask.isCompleted());
    }

    @Test
    void getTask() {

        Task task = new Task();
        task.setTitle("Wash Dishes");
        task.setCompleted(false);

        Task saved = service.createTask(task);

        Task found = service.getTask(saved.getId());

        assertEquals(saved.getId(), found.getId());
        assertEquals("Wash Dishes", found.getTitle());
        assertFalse(found.isCompleted());
    }

    @Test
    void getAllTasks() {

        Task task1 = new Task();
        task1.setTitle("Laundry");
        task1.setCompleted(false);

        Task task2 = new Task();
        task2.setTitle("Groceries");
        task2.setCompleted(true);

        service.createTask(task1);
        service.createTask(task2);

        List<Task> tasks = service.getAllTasks();

        assertTrue(tasks.size() >= 2);
    }

    @Test
    void updateTask() {

        Task task = new Task();
        task.setTitle("Homework");
        task.setCompleted(false);

        Task saved = service.createTask(task);

        UpdateTaskRequest request = new UpdateTaskRequest();
        request.setTitle("Homework Finished");
        request.setCompleted(true);

        Task updated = service.updateTask(saved.getId(), request);

        assertEquals("Homework Finished", updated.getTitle());
        assertTrue(updated.isCompleted());
    }

    @Test
    void deleteTask() {

        Task task = new Task();
        task.setTitle("Delete Me");
        task.setCompleted(false);

        Task saved = service.createTask(task);

        service.deleteTask(saved.getId());

        assertThrows(RuntimeException.class,
                () -> service.getTask(saved.getId()));
    }
}