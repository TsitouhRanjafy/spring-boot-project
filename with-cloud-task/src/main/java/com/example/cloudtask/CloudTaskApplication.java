package com.example.cloudtask;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudTaskApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CloudTaskService cloudTaskService) {
        return args -> cloudTaskService.createHttpTask();
    }

}
