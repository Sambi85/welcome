package com.java.welcome.controller;

import com.java.welcome.model.UserAccount;
import com.java.welcome.repository.UserAccountRepository;
import com.java.welcome.dto.CreateUserAccountRequest;
import com.java.welcome.dto.UpdateUserAccountRequest;
import com.java.welcome.dto.UserAccountResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users") //Base URL for endpoints...
public class UserController {
    private final UserAccountRepository repository;

    public UserController(UserAccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping  // GET all Users, using Response DTO
    public List<UserAccountResponse> getAllUsers() {

        return repository.findAll()
        .stream()
        .map(user -> new UserAccountResponse( //select only what we want exposed...
                    user.getId(),
                    user.getUsername(),
                    user.getEmail()))
        .toList();
    }


    @GetMapping("/{id}") // GET User by ID in URI, Using Response DTO
    public UserAccountResponse getUser(@PathVariable Long id) { //@PathVariable handles variables in the request URI 

        UserAccount user = repository.findById(id).orElseThrow(); // Error Handling

        return new UserAccountResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail()
        );
    }

    @PostMapping // POST create a single User, Using Create DTO + Response DTO
    public UserAccountResponse createUser(@RequestBody CreateUserAccountRequest request) { //@Request body handles incoming payload, coverts to JSON
    
        UserAccount user = new UserAccount();
    
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
    
        UserAccount savedUser = repository.save(user);
    
        return new UserAccountResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail()
        );
    }

    @PutMapping("/{id}") // PUTS update a user by ID in URI, Using Update DTO + Response DTO
    public UserAccountResponse updateUser( @PathVariable Long id, @RequestBody UpdateUserAccountRequest request) {
        UserAccount user = repository.findById(id).orElseThrow();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        UserAccount savedUser = repository.save(user);

        return new UserAccountResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail()
        );
    }

    @DeleteMapping("/{id}") // PUTS user by ID in URI
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}