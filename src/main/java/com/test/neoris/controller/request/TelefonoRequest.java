package com.test.neoris.controller.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefonoRequest {

    @ApiModelProperty(position = 0,dataType = "String", required = true, name = "number", example = "1234567")
    @NotEmpty(message = "Ingrese su número de teléfono")
    @Size(min = 6, max = 9, message = "debe ingresar un número de teléfono")
    @Pattern(regexp = "[0-9]{7,9}+$", message = "número de teléfono Invalido")
    private String number;
    
    @ApiModelProperty(position = 1,dataType = "String", required = true, name = "citycode", example = "1")
    @NotEmpty(message = "Ingrese su código de ciudad")
    @Size(min = 1, message = "debe ingresar su código de ciudad")
    @Pattern(regexp = "[0-9]{1}+$", message = "código de ciudad Invalido")
    private String citycode;
    
    @ApiModelProperty(position = 2,dataType = "String", required = true, name = "contrycode", example = "51")
    @NotEmpty(message = "Ingrese su código de pais")
    @Size(min = 2, message = "debe ingresar su código de pais")
    @Pattern(regexp = "[0-9]{2}+$", message = "código de pais Invalido")
    private String contrycode;
}
