package com.optimedica.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


//cuando un recurso no existe (404)
/*Esta clase va a encargarse de capturar las excepciones globalmente en toda tu aplicación y retornar una respuesta JSON uniforme.*/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        // Creamos un objeto ErrorResponse con todos los datos relevantes
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),                      // Marca de tiempo actual
                HttpStatus.NOT_FOUND.value(),             // Código de estado HTTP (404)
                "Recurso no encontrado",                  // Título del error
                ex.getMessage(),                          // Mensaje personalizado que viene desde la excepción
                request.getRequestURI()                   // Ruta que generó el error (extraída del request)
        );

        // Retornamos el error con el status 404
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }



// cuando hay conflictos con la base de datos (400)
    /*
    * Esta excepción ocurre cuando, por ejemplo:
    Intentas insertar un registro que viola una restricción única (como un email duplicado).
    Borras un registro que tiene relaciones con otras tablas (clave foránea).
    Esto es útil para validar integridad referencial y errores de base de datos.*/
    @ExceptionHandler(DataIntegrityViolationException.class) // Captura errores de integridad de datos
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {

        // Creamos una respuesta estructurada de error
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),                        // Fecha y hora actual
                HttpStatus.BAD_REQUEST.value(),             // Código 400
                "Error de integridad de datos",             // Descripción del tipo de error
                ex.getMostSpecificCause().getMessage(),     // Detalle técnico de la causa raíz
                request.getRequestURI()                     // Ruta de la petición que generó el error
        );

        // Retornamos la respuesta con código 400
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



    /*✅Paso 5: Manejar excepciones no controladas (genéricas)
    Este tipo de manejo captura cualquier error inesperado que no haya sido tratado por métodos anteriores.*/
    @ExceptionHandler(Exception.class) // Captura cualquier excepción no manejada explícitamente
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {

        // Creamos una respuesta genérica para cualquier excepción no prevista
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),                  // Fecha y hora actual
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // Código 500 (Error interno del servidor)
                "Error interno del servidor",         // Mensaje genérico
                ex.getMessage(),                      // Mensaje específico (opcionalmente podrías ocultarlo en producción)
                request.getRequestURI()               // Ruta que causó el error
        );

        // Devolvemos la respuesta con estado 500
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
