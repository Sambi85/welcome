package com.java.welcome.dto;

import com.java.welcome.model.Task;

public class TaskResponse {

    private Long id;
    private String title;
    private boolean completed;
    private Long userId;


    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.completed = task.isCompleted();

        if (task.getUserAccount() != null) {
            this.userId = task.getUserAccount().getId();
        }
    }


    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public boolean isCompleted() {
        return completed;
    }


    public Long getUserId() {
        return userId;
    }
}