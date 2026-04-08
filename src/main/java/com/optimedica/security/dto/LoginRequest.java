package com.optimedica.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Schema(description = "Datos que el usuario debe enviar para autenticarse")
public class LoginRequest {

    @Schema(description = "Nombre de usuario o correo electrónico", example = "admin")
    private String email;

    @Schema(description = "Contraseña del usuario", example = "123456")
    private String password;
}
