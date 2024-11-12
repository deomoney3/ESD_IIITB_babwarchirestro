package com.deomani.babarchirestro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BabarchirestroApplication {

    public static void main(String[] args) {
        SpringApplication.run(BabarchirestroApplication.class, args);
    }

}
