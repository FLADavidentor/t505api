package com.example.t505esports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class T505EsportsApplication {

    public static void main(String[] args) {
        SpringApplication.run(T505EsportsApplication.class, args);
    }
}

