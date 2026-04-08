
# OptiMedica - Sistema de gestión de productos para óptica

Este proyecto es parte de una aplicación web para un cliente. Permite administrar productos como lunas, 
monturas y lentes oftálmicos.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Flyway
- MapStruct
- Docker & Docker Compose

---

## ⚙️ ¿Qué incluye este backend?

- CRUD de productos
- DTOs con `record`
- Mapeo con MapStruct
- Manejo de errores con `@ControllerAdvice`
- Pruebas con `@DataJpaTest`
- Migraciones con Flyway
- Paginación
- Preparado para integrar frontend y CI/CD

---

## 📦 Instalación y ejecución local

### 1. Clonar el repositorio

```bash
git clone https://github.com/JhonasPaul/optimedica.git
cd optimedica/backend
```

### 2. Levantar MySQL con Docker

```bash
docker-compose up -d
```

### 3. Ejecutar la app

```bash
./mvnw spring-boot:run
```

---

## 📫 Contacto

Este proyecto es desarrollado por Jonathan por temas laborales.
