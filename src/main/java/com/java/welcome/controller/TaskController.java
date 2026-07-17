package com.java.welcome.controller;

import com.java.welcome.model.Task;
import com.java.welcome.service.TaskService;
import com.java.welcome.dto.CreateTaskRequest;
import com.java.welcome.dto.UpdateTaskRequest;
import com.java.welcome.dto.TaskResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks") //Base URL for everything in this class
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }

    @GetMapping // GET all Tasks
    public List<TaskResponse> getAllTasks() {
    
        return service.getAllTasks()
                .stream()
                .map(TaskResponse::new) //give me everything...
                .toList();
    }

    @GetMapping("/{id}") // GET Task by ID in URI
    public TaskResponse getTask(@PathVariable Long id) { //@PathVariable handles variables in the request URI 

        Task task = service.getTask(id);

        return new TaskResponse(task);
    }

    @PostMapping //POST create a single Task
    public ResponseEntity<TaskResponse> createTask(@RequestBody CreateTaskRequest request) {
    
        Task task = service.createTask(request);

        TaskResponse response = new TaskResponse(task);
    
        return ResponseEntity
            .created(URI.create("/tasks/" + task.getId()))
            .body(response);
    }

    @PutMapping("/{id}") //PUTS update a Task by ID in URI
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {

        Task updatedTask = service.updateTask(id, request);

        return new TaskResponse(updatedTask);
    }

    @DeleteMapping("/{id}")  //DELETE delete a Task by ID in URI
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }

}