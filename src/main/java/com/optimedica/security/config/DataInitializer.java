package com.optimedica.security.config;

import com.optimedica.security.entity.Role;
import com.optimedica.security.entity.User;
import com.optimedica.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmail("admin@optimedica.com").isEmpty()) {
                User user = User.builder()
                        .email("admin@optimedica.com")
                        .password(passwordEncoder.encode("123456")) // ✅ Contraseña encriptada
                        .role(Role.ADMIN)
                        .build();

                userRepository.save(user);
                System.out.println("Usuario admin creado.");
            }
        };
    }
}
