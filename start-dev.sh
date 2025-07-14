#!/bin/bash

# Cargar variables desde el archivo .env
export $(grep -v '^#' .env | xargs)

# Ejecutar la app con Spring Boot
./mvnw spring-boot:run
