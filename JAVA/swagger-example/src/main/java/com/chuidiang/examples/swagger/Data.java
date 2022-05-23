package com.chuidiang.examples.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Una estructura de datos cualquiera para ver cómo queda la documentación swagger/open-api
 * @author fjabellan
 * @date 21/05/2022
 */
@Getter @Setter
@ToString
public class Data {
    @Schema(minimum = "0", maximum = "10", description = "Entre 0 y 10")
    private int value;
    @Schema(minLength = 0, maxLength = 20, description = "maximo 20")
    private String string;
    @Schema(description = "un dia guay")
    private Date date;


}
