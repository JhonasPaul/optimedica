package com.optimedica.common.exception;

import java.time.LocalDateTime;

/**
 * Clase que representa la estructura común de las respuestas de error
 * que se enviarán al cliente cuando ocurra una excepción controlada.
 */
public class ErrorResponse {

    private LocalDateTime timestamp;  // Fecha y hora del error
    private int status;               // Código HTTP (por ejemplo, 404, 400)
    private String error;             // Breve descripción del error (ej: "Recurso no encontrado")
    private String message;           // Mensaje más detallado o personalizado
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
