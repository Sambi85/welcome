package com.java.welcome.repository;

import com.java.welcome.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository 
        extends JpaRepository<Task, Long> {

}