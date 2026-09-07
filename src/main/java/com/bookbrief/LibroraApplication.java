package com.bookbrief;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Librora — a reading and knowledge application.
 * Entry point for the Spring Boot 2.7.18 backend.
 *
 * Architecture: decoupled REST API consumed by a React 19 SPA.
 * Layering per feature package: Controller -> Service -> Repository -> Entity/DTO.
 */
@SpringBootApplication
public class LibroraApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibroraApplication.class, args);
    }

}
