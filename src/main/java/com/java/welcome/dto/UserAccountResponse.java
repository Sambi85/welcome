package com.java.welcome.dto;

public class UserAccountResponse {

    private Long id;
    private String username;
    private String email;
    private String password;

    public UserAccountResponse(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }
}