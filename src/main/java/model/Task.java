package com.java.welcome.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //automatically generate/increment an ID
    private Long id;


    @Column(nullable = false)
    private String title;


    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY) //Lazy load parent related data (User Account)
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;


    public Task() {}

    public Task(String title) {
        this.title = title;
        this.completed = false;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() { 
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) { // setter for associating a User Account
        this.userAccount = userAccount;
    }
}