package com.java.welcome.config;

import com.java.welcome.model.Task;
import com.java.welcome.model.UserAccount;
import com.java.welcome.repository.TaskRepository;
import com.java.welcome.repository.UserAccountRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataLoader {

    @Bean // Runs after Spring Boot starts, Loads DB with Test Data
    CommandLineRunner initDatabase( 
            UserAccountRepository userRepository,
            TaskRepository taskRepository) {

        return args -> {

            if (userRepository.count() == 0) { //guard against dupe emails...

                UserAccount kirby =
                    new UserAccount(
                        "Kirby",
                        "Kirby@email.com",
                        "pinkball123"
                    );
                UserAccount link =
                    new UserAccount(
                        "Link",
                        "Link@email.com",
                        "sword123"
                    );
                UserAccount samus =
                    new UserAccount(
                        "Samus",
                        "Samus@email.com",
                        "blaster123"
                    );
    
                userRepository.save(kirby);
                userRepository.save(link);
                userRepository.save(samus);
    
                Task task1 = new Task("Learn JPA");
                task1.setUserAccount(kirby);
                
                Task task2 = new Task("Build REST API");
                task2.setUserAccount(link);
                
                Task task3 = new Task("Write Unit Tests");
                task3.setUserAccount(samus);
    
                taskRepository.save(task1);
                taskRepository.save(task2);
                taskRepository.save(task3);
            };
        };
    }
}