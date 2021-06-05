package com.softper.customerservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig
{

    @Bean(name= "bloggingOpenApi")
    public OpenAPI tsOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Tropsmart Customer API").description("Tropsmart API for customer bound"));
    }
}
