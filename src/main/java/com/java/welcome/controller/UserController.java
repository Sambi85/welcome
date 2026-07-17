package com.java.welcome.controller;

import com.java.welcome.model.UserAccount;
import com.java.welcome.service.UserAccountService;
import com.java.welcome.dto.CreateUserAccountRequest;
import com.java.welcome.dto.UpdateUserAccountRequest;
import com.java.welcome.dto.UserAccountResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users") //Base URL for endpoints...
public class UserController {
    private final UserAccountService service;

    public UserController(UserAccountService service) {
        this.service = service;
    }

    @GetMapping  // GET all Users, + DTO + service
    public List<UserAccountResponse> getAllUsers() {

        return service.getAllUsers()
        .stream()
        .map(user -> new UserAccountResponse( //select only what we want exposed...
                    user.getId(),
                    user.getUsername(),
                    user.getEmail()))
        .toList();
    }

    @GetMapping("/{id}") // GET User by ID in URI, DTO + service
    public UserAccountResponse getUser(@PathVariable Long id) { //@PathVariable handles variables in the request URI 

        UserAccount user = service.getUser(id);

        return new UserAccountResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    @PostMapping // POST create a single User + DTOs + service
    public ResponseEntity<UserAccountResponse> createUser(
            @RequestBody CreateUserAccountRequest request) {
    
        UserAccount user = service.createUser(request);
    
        UserAccountResponse response = new UserAccountResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail()
        );
    
        return ResponseEntity
                .created(URI.create("/users/" + user.getId()))
                .body(response);
    }

    @PutMapping("/{id}") // PUTS update a user by ID in URI + DTOs + service
    public UserAccountResponse updateUser( @PathVariable Long id, @RequestBody UpdateUserAccountRequest request) {

        UserAccount updatedUser = service.updateUser(id, request);

        return new UserAccountResponse(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail());
    }

    @DeleteMapping("/{id}") // DELETE delete a user by ID in URI
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}