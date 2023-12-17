package com.test.neoris.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefonoRequest {

    @NotEmpty(message = "Ingrese su número de teléfono")
    @Size(min = 6, max = 9, message = "debe ingresar un número de teléfono")
    @Pattern(regexp = "[0-9]{7,9}+$", message = "número de teléfono Invalido")
    private String number;
    
    @NotEmpty(message = "Ingrese su código de ciudad")
    @Size(min = 1, message = "debe ingresar su código de ciudad")
    @Pattern(regexp = "[0-9]{1}+$", message = "código de ciudad Invalido")
    private String citycode;
    
    @NotEmpty(message = "Ingrese su código de pais")
    @Size(min = 2, message = "debe ingresar su código de pais")
    @Pattern(regexp = "[0-9]{2}+$", message = "código de pais Invalido")
    private String contrycode;
}
