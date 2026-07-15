package com.java.welcome.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.java.welcome.model.UserAccount;
import com.java.welcome.dto.UpdateUserAccountRequest;
import com.java.welcome.dto.CreateUserAccountRequest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserAccountServiceTest {

    @Autowired //let spring build dependences...
    private UserAccountService service;

    @Test
    void createUser() {
    
        CreateUserAccountRequest request = new CreateUserAccountRequest();

        request.setUsername("sam");
        request.setEmail("sam@example.com");
        request.setPassword("password");  // WIP - encrypt password with something like BCryptPasswordEncoder

        UserAccount savedUser = service.createUser(request);

        assertNotNull(savedUser.getId());
        assertEquals("sam", savedUser.getUsername());
        assertEquals("sam@example.com", savedUser.getEmail());
        assertEquals("password", savedUser.getPassword());
    }

    @Test
    void getUser() {
        CreateUserAccountRequest request = new CreateUserAccountRequest();
        request.setUsername("alice");
        request.setEmail("alice@example.com");
        request.setPassword("password");

        UserAccount saved = service.createUser(request);
        UserAccount found = service.getUser(saved.getId()); 

        assertEquals(saved.getId(), found.getId());
        assertEquals("alice", found.getUsername());
        assertEquals("alice@example.com", found.getEmail());
        assertEquals("password", found.getPassword());
    }

    @Test
    void getAllUsers() {
        CreateUserAccountRequest request1 = new CreateUserAccountRequest();
        request1.setUsername("diego");
        request1.setEmail("diego@example.com");
        request1.setPassword("password1");

        CreateUserAccountRequest request2 = new CreateUserAccountRequest();
        request2.setUsername("tomas");
        request2.setEmail("tomas@example.com");
        request2.setPassword("password2");

        service.createUser(request1);
        service.createUser(request2);

        List<UserAccount> users = service.getAllUsers();

        assertTrue(users.size() >= 2);
    }

    @Test
    void updateUser() {

        CreateUserAccountRequest createRequest = new CreateUserAccountRequest();
        createRequest.setUsername("tomato");
        createRequest.setEmail("tomato@example.com");
        createRequest.setPassword("password"); // WIP - encrypt password with something like BCryptPasswordEncoder

        UserAccount saved = service.createUser(createRequest);

        UpdateUserAccountRequest updateRequest = new UpdateUserAccountRequest();
        updateRequest.setUsername("Ketchup");
        updateRequest.setEmail("ketchup@example.com");

        UserAccount updated = service.updateUser(saved.getId(), updateRequest);

        assertEquals("Ketchup", updated.getUsername());
        assertEquals("ketchup@example.com", updated.getEmail());
    }

    @Test
    void deleteUser() {
        CreateUserAccountRequest request = new CreateUserAccountRequest();
        request.setUsername("deleteMe");
        request.setEmail("delete@example.com");
        request.setPassword("password");

        UserAccount saved = service.createUser(request);

        service.deleteUser(saved.getId());

        assertThrows(
            RuntimeException.class,
            () -> service.getUser(saved.getId())
        );
    }

}