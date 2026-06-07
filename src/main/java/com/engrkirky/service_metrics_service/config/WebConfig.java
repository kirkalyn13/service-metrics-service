package com.engrkirky.service_metrics_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC configuration.
 */
@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String[] LOCALHOST_URLS = {
            "http://localhost:*",
    };

    /**
     * Configures CORS settings for API endpoints.
     *
     * @return web MVC configuration
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/**")
                        .allowedOrigins(LOCALHOST_URLS)
                        .allowedMethods("GET", "POST")
                        .maxAge(3600);
            }
        };
    }
}
