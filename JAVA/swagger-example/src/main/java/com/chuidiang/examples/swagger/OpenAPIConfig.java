package com.chuidiang.examples.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fjabellan
 * @date 21/05/2022
 */
@Configuration
public class OpenAPIConfig {
    /**
     * Para la cabecera del documento generado par openapi / swagger.
     * Datos generales del servidor, desarrolladores, contacto, etc.
     * @return
     */
    @Bean
    public OpenAPI getOpenApiDefinition(){
        return new OpenAPI()
            .info(new Info()
                .title("Open API chuidiang example")
                .version("1.0")
            );
    }
}
