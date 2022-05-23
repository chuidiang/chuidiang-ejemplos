package com.chuidiang.examples.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

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
        Server server = new Server().url("http://localhost:8080").description("El servidor");
        List<Server> servers = new ArrayList<>();
        servers.add(server);

        return new OpenAPI()
            .info(new Info()
                .title("Open API chuidiang example")
                .description("El ejemplo maravilloso")
                .version("1.0")
                .contact(new Contact()
                        .name("Chuidiang")
                        .url("http://chuwiki.chuidiang.org")))
            .servers(servers);
    }
}
