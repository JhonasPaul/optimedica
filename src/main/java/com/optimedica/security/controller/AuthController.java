package com.optimedica.security.controller;


import com.optimedica.security.dto.AuthResponse;
import com.optimedica.security.dto.LoginRequest;
import com.optimedica.security.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Login y obtención de token JWT")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Iniciar sesión",
            description = "Autentica al usuario y devuelve un token JWT si las credenciales son válidas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login exitoso",
                            content = @Content(schema = @Schema(implementation = AuthResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authenticationService.login(request);
        return ResponseEntity.ok(response);
    }
}
