package com.chuidiang.examples.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author fjabellan
 * @date 21/05/2022
 */
@RestController
public class WebService2 {
    @PostMapping(path = "/data")
    @Operation(
            method = "cambia data",
            description = "Si me das una data, te lo devuelvo incrementado"
    )
    public Data greeting2(
            @RequestBody
            @Parameter(
                    description = "un data"
            )
            Data data) {
        data.setValue(data.getValue()+1);
        data.setString(data.getString()+".....?");
        data.setDate(new Date());
        return data;
    }
}
