package com.java.welcome.controller;

import com.java.welcome.model.Task;
import com.java.welcome.dto.CreateTaskRequest;
import com.java.welcome.dto.UpdateTaskRequest;
import com.java.welcome.dto.TaskResponse;
import com.java.welcome.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks") //Base URL for everything in this class
public class TaskController {
    private final TaskRepository repository;

    public TaskController(TaskRepository repository){
        this.repository = repository;
    }

    @GetMapping // GET all Tasks
    public List<TaskResponse> getAllTasks() {
    
        return repository.findAll()
                .stream()
                .map(TaskResponse::new) //give me everything...
                .toList();
    }

    @GetMapping("/{id}") // GET Task by ID in URI
    public TaskResponse getTask(@PathVariable Long id) { //@PathVariable handles variables in the request URI 

        Task task = repository.findById(id).orElseThrow();

        return new TaskResponse(task);
    }

    @PostMapping //POST create a single Task
    public TaskResponse createTask(@RequestBody CreateTaskRequest request) {
    
        Task task = new Task(request.getTitle());
        Task savedTask = repository.save(task);
    
        return new TaskResponse(savedTask);
    }

    @PutMapping("/{id}") //PUTS update a Task by ID in URI
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
    
        Task task = repository.findById(id).orElseThrow();
    
        task.setTitle(request.getTitle());
        task.setCompleted(request.isCompleted());
    
        Task updatedTask = repository.save(task);
    
        return new TaskResponse(updatedTask);
    }

    @DeleteMapping("/{id}")  //DELETE delete a Task by ID in URI
    public void deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
    }

}