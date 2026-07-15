package com.java.welcome.service;

import com.java.welcome.model.Task;
import com.java.welcome.repository.TaskRepository;
import com.java.welcome.dto.UpdateTaskRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTask(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Task createTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(Long id, UpdateTaskRequest request) {

        Task task = repository.findById(id)
                .orElseThrow();

        task.setTitle(request.getTitle());
        task.setCompleted(request.isCompleted());

        return repository.save(task);
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}