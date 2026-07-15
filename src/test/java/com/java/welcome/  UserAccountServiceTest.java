package com.java.welcome.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.java.welcome.model.UserAccount;
import com.java.welcome.dto.UpdateUserAccountRequest;
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
    
        UserAccount user = new UserAccount();
        user.setUsername("sam");
        user.setEmail("sam@example.com");
    
        UserAccount savedUser = service.createUser(user);
    
        assertNotNull(savedUser.getId());
        assertEquals("sam", savedUser.getUsername());
        assertEquals("sam@example.com", savedUser.getEmail());
    }

    @Test
    void getUser() {
        UserAccount user = new UserAccount();
        user.setUsername("alice");
        user.setEmail("alice@example.com");

        UserAccount saved = service.createUser(user);
        UserAccount found = service.getUser(saved.getId());

        assertEquals(saved.getId(), found.getId());
        assertEquals("alice", found.getUsername());
        assertEquals("alice@example.com", found.getEmail());
    }

    @Test
    void getAllUsers() {
        UserAccount user1 = new UserAccount();
        user1.setUsername("diego");
        user1.setEmail("diego@example.com");

        UserAccount user2 = new UserAccount();
        user2.setUsername("tomas");
        user2.setEmail("tomas@example.com");

        service.createUser(user1);
        service.createUser(user2);

        List<UserAccount> users = service.getAllUsers();

        assertTrue(users.size() >= 2);
    }

    @Test
    void updateUser() {

        UserAccount user = new UserAccount();
        user.setUsername("tomato");
        user.setEmail("tomato@example.com");

        UserAccount saved = service.createUser(user);

        UpdateUserAccountRequest request = new UpdateUserAccountRequest();
        request.setUsername("Ketchup");
        request.setEmail("ketchup@example.com");

        UserAccount updated = service.updateUser(saved.getId(), request);

        assertEquals("Ketchup", updated.getUsername());
        assertEquals("ketchup@example.com", updated.getEmail());
    }

    @Test
    void deleteUser() {
        UserAccount user = new UserAccount();
        user.setUsername("deleteMe");
        user.setEmail("delete@example.com");

        UserAccount saved = service.createUser(user);

        service.deleteUser(saved.getId());

        assertThrows(
            RuntimeException.class,
            () -> service.getUser(saved.getId())
        );
    }

}