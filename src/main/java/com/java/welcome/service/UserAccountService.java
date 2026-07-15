package com.java.welcome.service;

import com.java.welcome.model.UserAccount;
import com.java.welcome.repository.UserAccountRepository;
import com.java.welcome.dto.UpdateUserAccountRequest;
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

    public UserAccount createUser(UserAccount user) {
        return repository.save(user);
    }

    public UserAccount updateUser(Long id, UpdateUserAccountRequest request) {

        UserAccount user = repository.findById(id).orElseThrow();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        return repository.save(user);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}