package com.chuidiang.examples.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fjabellan
 * @date 21/05/2022
 */
@RestController
public class WebService2 {
    @GetMapping(path = "/greeting2")
    @Operation(
            description = "Devuelve saludo 2"
    )
    public String greeting2(
            @Parameter(
                    description = "A quien saludar 2"
            )
            Data data) {
        return "Hello "+data;
    }
}
