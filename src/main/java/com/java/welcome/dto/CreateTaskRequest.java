package com.java.welcome.dto;

public class CreateTaskRequest { //Don't allow user to populate status field on create, Completed...

    private String title;


    public CreateTaskRequest() {}


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
}