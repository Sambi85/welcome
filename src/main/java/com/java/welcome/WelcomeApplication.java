package com.java.welcome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import com.java.welcome.service.WelcomeService;

@SpringBootApplication
public class WelcomeApplication implements CommandLineRunner {

	private final WelcomeService service;

	public WelcomeApplication(WelcomeService service) {
        this.service = service;
    }
	public static void main(String[] args) {
		SpringApplication.run(WelcomeApplication.class, args);
	}

	@Override
    public void run(String... args) {

        System.out.println(service.welcome());
    }

}
