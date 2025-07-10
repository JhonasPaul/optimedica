# Guía paso a paso: Proyecto OptiMedica - Backend

Este documento registra en orden lo que se ha implementado en el backend de OptiMedica, incluyendo cómo se hizo cada parte y observaciones para reutilizar en futuros proyectos.

---

## ✅ Paso 1: Configuración inicial

### ¿Qué se hizo?

* Proyecto creado con Spring Boot.
* Se agregaron las siguientes dependencias:

    * `spring-boot-starter-web`
    * `spring-boot-starter-data-jpa`
    * `mysql-connector-j`
    * `spring-boot-starter-validation`
    * `lombok`
    * `flyway-core`
* Se creó archivo `docker-compose.yml` para levantar un contenedor con MySQL.
* Se configuró Flyway para manejar migraciones.
* Se agregó archivo `application.properties` en `src/main/resources`.

---

## ✅ Paso 2: CRUD de productos

### ¿Qué se hizo?

* Entidad `Product` creada con los siguientes campos:

    * `name`, `description`, `price`, `stock`, `brand`, `imageURL`, `activeFlag`, `createdAt`
* Se extendió de una clase `BaseId` que contiene el campo `id`.
* Se creó `ProductDto` como `record` con los mismos campos.
* Se implementó `ProductMapper` usando MapStruct para:

    * Convertir entre `Product` y `ProductDto`
    * Actualizar entidad desde DTO ignorando `id` y `createdAt`
* Se implementó `ProductController` con los siguientes endpoints:

    * `GET /api/listar`: lista completa
    * `GET /api/page/{page}`: lista paginada
    * `GET /api/{id}`: obtener por ID
    * `POST /api/guardar`: guardar nuevo producto
    * `PUT /api/{id}`: actualizar producto
    * `DELETE /api/{id}`: eliminar producto

---

## ✅ Paso 3: Manejo de errores globales

### ¿Qué se hizo?

* Se creó clase `GlobalExceptionHandler` con `@ControllerAdvice`.
* Se capturan las siguientes excepciones:

    * `ResourceNotFoundException`: 404 - Recurso no encontrado
    * `DataIntegrityViolationException`: 400 - Violación de integridad de datos
    * `Exception`: 500 - Error interno del servidor
* Se usa la clase `ErrorResponse` con los campos:

    * `timestamp`, `status`, `error`, `message`, `path`

---

## ✅ Paso 4: Integración de MapStruct

### ¿Qué se hizo?

* Se agregó la interfaz `ProductMapper` con `componentModel = "spring"`.
* Métodos incluidos:

    * `toDto`, `toProduct`, `toListDto`, `updateProductFromDto`
* Ignora campos sensibles al actualizar (`id` y `createdAt`).

---

## ✅ Paso 5: Paginación

### ¿Qué se hizo?

* Se agregó endpoint `/api/page/{page}` en `ProductController`.
* Usa `PageRequest.of(page, 5)` para paginación de 5 elementos por página.

---

## ⏳ Paso 6: Validaciones con @Valid (en progreso)

### ¿Qué falta hacer?

* Agregar anotaciones como `@NotBlank`, `@Positive`, etc., en `ProductDto`
* Usar `@Valid` en el controlador:

  ```java
  public ResponseEntity<?> saveProduct(@RequestBody @Valid ProductDto dto);
  ```
* Agregar método en `GlobalExceptionHandler` para capturar `MethodArgumentNotValidException` y devolver errores personalizados.

---

## ✅ Estado actual

* CRUD funcional
* Excepciones manejadas globalmente (excepto validaciones)
* MapStruct funcionando correctamente
* Falta completar validaciones y luego iniciar frontend

---

## ✈ Próximos pasos sugeridos

1. ✅ Terminar validaciones con `@Valid` y manejar `MethodArgumentNotValidException`
2. ✅ Agregar Swagger para documentar la API
3. ⏳ Comenzar frontend en Angular + PrimeNG
4. ⏳ Subida de imágenes (AWS S3 u otra solución)
5. ⏳ Configurar integración continua con GitHub Actions
