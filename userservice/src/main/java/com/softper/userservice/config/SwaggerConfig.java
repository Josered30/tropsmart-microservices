package com.softper.userservice.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI tsOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Tropsmart Driver API").description("Tropsmart API for driver bound"));
    }

}
