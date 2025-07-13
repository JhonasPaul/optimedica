package com.optimedica.common.exception;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Clase que representa la estructura común de las respuestas de error
 * que se enviarán al cliente cuando ocurra una excepción controlada.
 */
public class ErrorResponse {

    @Schema(description = "Fecha y hora del error", example = "2025-07-09T12:34:56")
    private LocalDateTime timestamp;
    @Schema(description = "Código HTTP de estado", example = "404")// Fecha y hora del error
    private int status;
    @Schema(description = "Tipo de error", example = "Not Found")// Código HTTP (por ejemplo, 404, 400)
    private String error;
    @Schema(description = "Mensaje detallado del error", example = "El producto con ID 10 no fue encontrado")// Breve descripción del error (ej: "Recurso no encontrado")
    private String message;
    @Schema(description = "Ruta que generó el error", example = "/api/10")// Mensaje más detallado o personalizado
    private String path;              // Ruta de la solicitud que causó el error (opcional pero útil)

    // Constructor completo
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // Getters y setters (puedes generarlos automáticamente en tu IDE)
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
