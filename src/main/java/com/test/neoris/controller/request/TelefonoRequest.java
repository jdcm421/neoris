package com.test.neoris.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefonoRequest {

    @NotEmpty(message = "Ingrese su número de teléfono")
    @Size(min = 6, max = 9, message = "debe ingresar un número de teléfono")
    @Pattern(regexp = "\\d{9}", message = "número de teléfono Invalido")
    private String number;
    
    @NotEmpty(message = "Ingrese su código de ciudad")
    @Size(min = 8, max = 8, message = "debe ingresar su código de ciudad")
    @Pattern(regexp = "\\d{1}", message = "código de ciudad Invalido")
    private String citycode;
    
    @NotEmpty(message = "Ingrese su código de pais")
    @Size(min = 8, max = 8, message = "debe ingresar su código de pais")
    @Pattern(regexp = "\\d{2}", message = "código de pais Invalido")
    private String contrycode;
}
