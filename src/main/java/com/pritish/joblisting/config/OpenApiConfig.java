package com.pritish.joblisting.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot MongoDB",
                description = "Spring Boot API with MongoDB",
                summary = "MongoDB Atlas is used for a database and Spring Boot 3 is used",
                termsOfService = "T&C"
        )
)
public class OpenApiConfig {
}
