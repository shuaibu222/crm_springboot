package com.shuaibu.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "Shuaibu",
            email = "shuaibuabdulkadir222@gmail.com"
        ),
        description = "OpenApi documentation for crm-springboot REST API",
        title = "OpenApi Documentation - Shuaibu",
        version = "1.0",
        termsOfService = "Terms of Service"
    ),
    servers = {
        @Server(
            description = "Local ENV",
            url = "localhost:8080"
        ),
        @Server(
            description = "DEV ENV",
            url = "https://dev.crm-springboot.com"
        )
    }
)
public class OpenApiConfig {
    
}
