package com.java.welcome.config;

import com.java.welcome.repository.WelcomeRepository;
import com.java.welcome.repository.impl.WelcomeRepositoryImpl;
import com.java.welcome.service.WelcomeService;
import com.java.welcome.service.impl.WelcomeServiceImpl;
import com.java.welcome.controller.WelcomeController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class AppConfig {

    @Value("${app.author}")
    private String author;

    @Value("${app.version}")
    private String version;

}
