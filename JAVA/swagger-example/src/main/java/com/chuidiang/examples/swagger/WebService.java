package com.chuidiang.examples.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fjabellan
 * @date 21/05/2022
 */
@RestController
@Tag(name="Saludos", description = "Metodos de saludo")
public class WebService {
    @GetMapping(path = "/greeting")
    @Operation(
            method = "saluda a alguien",
            description = "Se le pasa un nombre y devuelve saludo a ese nombre"
    )
    public String greeting(
            @Parameter(
                    description = "A quien saludar"
            )
            String name) {
        return "Hello "+name;
    }
}
