package com.optimedica.security.service;

import com.optimedica.security.dto.AuthResponse;
import com.optimedica.security.dto.LoginRequest;
import com.optimedica.security.entity.User;
import com.optimedica.security.jwt.JwtService;
import com.optimedica.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
        try {
            var authToke = new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            );

            authenticationManager.authenticate(authToke);

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            String jwtToken = jwtService.generateToken(user);

            return new AuthResponse(jwtToken);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales invlálidas");
        }
    }
}
