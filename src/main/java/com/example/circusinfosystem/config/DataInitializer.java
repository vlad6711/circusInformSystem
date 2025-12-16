package com.example.circusinfosystem.config;

import com.example.circusinfosystem.models.User;
import com.example.circusinfosystem.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User(
                        "admin",
                        "admin@circus.ru",
                        "Администратор",
                        "admin"
                );
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }
        };
    }
}
