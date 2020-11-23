package com.example.AtmMachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class AtmMachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtmMachineApplication.class, args);
    }

}
