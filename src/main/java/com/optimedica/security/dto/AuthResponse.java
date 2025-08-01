package com.optimedica.security.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Respuesta de autenticación con el token JWT")
public class AuthResponse {
    @Schema(description = "Token JWT generado tras el login exitoso", example = "eyJhbGciOiJIUzI1NiIsInR...")
    private String token;
}
