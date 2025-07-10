package com.optimedica.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// Esta clase configura cómo se verá la documentación Swagger de tu API.
@Configuration
public class SwaggerConfig {

    // ✅ Este método crea y personaliza el objeto OpenAPI con información de contacto, título y descripción.
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API OptiMedica") // Título que se muestra en la UI de Swagger
                        .version("1.0") // Versión de tu API
                        .description("Documentación de la API del backend de OptiMedica") // Breve descripción
                        .contact(new Contact()
                                .name("Jonathan") // Nombre del desarrollador o contacto técnico
                                .email("soporte@optimedica.com") // Correo de contacto
                        )
                );
    }
}