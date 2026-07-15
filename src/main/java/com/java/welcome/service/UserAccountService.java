package com.java.welcome.service;

import com.java.welcome.model.UserAccount;
import com.java.welcome.repository.UserAccountRepository;
import com.java.welcome.dto.UpdateUserAccountRequest;
import com.java.welcome.dto.CreateUserAccountRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {

    private final UserAccountRepository repository;

    public UserAccountService(UserAccountRepository repository) {
        this.repository = repository;
    }

    public List<UserAccount> getAllUsers() {
        return repository.findAll();
    }

    public UserAccount getUser(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public UserAccount createUser(CreateUserAccountRequest request) {

        UserAccount user = new UserAccount(request.getUsername(), request.getEmail(), request.getPassword());

        return repository.save(user);
    }

    public UserAccount updateUser(Long id, UpdateUserAccountRequest request) {

        UserAccount user = repository.findById(id).orElseThrow();

        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        if (request.getPassword() != null) {
            user.setPassword(request.getPassword());
        }

        return repository.save(user);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}