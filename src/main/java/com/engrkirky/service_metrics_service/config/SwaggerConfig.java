package com.engrkirky.service_metrics_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI and Swagger configuration.
 */
@Configuration
public class SwaggerConfig {
    private static final String TITLE = "Service Metrics Service API";
    private static final String VERSION = "Version 1.0";

    /**
     * Creates the public API group.
     *
     * @return grouped OpenAPI configuration
     */
    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-apis")
                .pathsToMatch("/**")
                .build();
    }

    /**
     * Creates the OpenAPI documentation configuration.
     *
     * @return OpenAPI configuration
     */
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(TITLE).version(VERSION));
    }
}
