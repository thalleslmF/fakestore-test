package org.samsung.fakestoreapi.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Slf4j
@Configuration
public class SwaggerConfig {
    private static final String API_KEY = "x-api-key";

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .components(new Components())
                .info(
                        new Info()
                                .title("FakeStoreApi")
                                .description(""" 
                                        Serviço responsável por orquestrar chamadas para API de produtos""")
                );
    }

}
