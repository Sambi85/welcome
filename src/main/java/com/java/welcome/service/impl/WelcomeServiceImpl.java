package com.java.welcome.service.impl;

import com.java.welcome.repository.WelcomeRepository;
import com.java.welcome.service.WelcomeService;

import org.springframework.stereotype.Service;

@Service
public class WelcomeServiceImpl implements WelcomeService {

    private final WelcomeRepository repository;

    public WelcomeServiceImpl(WelcomeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String welcome() {
        return repository.getWelcomeMessage();
    }

}