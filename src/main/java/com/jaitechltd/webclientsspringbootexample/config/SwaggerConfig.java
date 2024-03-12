package com.jaitechltd.webclientsspringbootexample.config;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Webclients Spring Boot Example",
                version = "1.0",
                description = "This is a simple Spring Boot application to demonstrate the usage of WebClients to consume RESTful services."
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Local server"
                ),
                @Server(
                        url = "https://dev.jaitechltd.com",
                        description = "Development server"
                ),
                @Server(
                        url = "https://qa.jaitechltd.com",
                        description = "QA server"
                )
        },
        tags = {
                @Tag(name = "Postcode API", description = "Retrieve lat long from postcode.io"),
                @Tag(name = "Star Wars API", description = "Retrieve Star Wars data from swapi.dev"),
                @Tag(name = "Weather API", description = "Retrieve weather data from openweathermap.org"),
                @Tag(name = "Currency API", description = "Retrieve currency data from exchangeratesapi.io")
        },
        externalDocs = @ExternalDocumentation(
                description = "Find out more about Webclients Spring Boot Example",
                url = "https://start.spring.io/",
                extensions = {}
        )
)
@Configuration
public class SwaggerConfig {
}

