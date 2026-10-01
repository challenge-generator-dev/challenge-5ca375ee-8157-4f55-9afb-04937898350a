package com.bankapi.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";
    private static final String API_TITLE = "Bank API - Gestión de Cuentas Bancarias";
    private static final String API_VERSION = "1.0.0";
    private static final String API_DESCRIPTION = """
            API REST para la gestión de cuentas bancarias en un entorno de banca y fintech.
            
            Esta API permite realizar las siguientes operaciones:
            - Creación de nuevas cuentas bancarias
            - Consulta de información de cuentas
            - Actualización de estado y saldo de cuentas
            - Consulta de clientes asociados
            
            La API implementa idempotencia en la creación de cuentas mediante headers X-Idempotency-Key.
            """;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(API_TITLE)
                        .version(API_VERSION)
                        .description(API_DESCRIPTION)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("soporte@bankapi.com")
                                .url("https://www.bankapi.com/soporte"))
                        .license(new License()
                                .name("Licencia Proprietaria")
                                .url("https://www.bankapi.com/licencia")))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("""
                                        Token JWT obtenido mediante el endpoint de autenticación.
                                        El token debe ser enviado en el header Authorization con el formato:
                                        Bearer <token_jwt>
                                        """)));
    }
}