package com.java.welcome.repository.impl;
import com.java.welcome.repository.WelcomeRepository;

import org.springframework.stereotype.Repository;

@Repository
public class WelcomeRepositoryImpl implements WelcomeRepository {

    @Override
    public String getWelcomeMessage() {
        return "Welcome Back to Spring, Valued Developer!";
    }

}