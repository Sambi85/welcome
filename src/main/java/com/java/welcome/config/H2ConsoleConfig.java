package com.java.welcome.config;

import org.h2.server.web.JakartaWebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2ConsoleConfig {

    @Bean
    public ServletRegistrationBean<JakartaWebServlet> h2Console() {

        ServletRegistrationBean<JakartaWebServlet> servlet =
                new ServletRegistrationBean<>(
                        new JakartaWebServlet(),
                        "/h2-console/*"
                );

        servlet.addInitParameter(
                "webAllowOthers",
                "true"
        );

        return servlet;
    }
}