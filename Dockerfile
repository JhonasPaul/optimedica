# Imagen base con JDK 21
FROM eclipse-temurin:21-jdk-jammy

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiar archivos de Maven y el POM (solo lo necesario para descargar dependencias)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Descargar dependencias (esto se almacenará en caché mientras no cambie pom.xml)
RUN ./mvnw dependency:go-offline

# Copiar el código fuente (esto se hace después para no invalidar la caché anterior)
COPY src ./src

# Compilar el proyecto sin tests
RUN ./mvnw clean package -DskipTests

# Expone el puerto de la app
EXPOSE 8080

# Ejecuta el JAR generado
ENTRYPOINT ["java", "-jar", "target/optimedica-backend-0.0.1-SNAPSHOT.jar"]
