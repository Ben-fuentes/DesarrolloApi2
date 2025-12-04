package com.example.miapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.miapp")
public class MiappApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiappApplication.class, args);
    }
}