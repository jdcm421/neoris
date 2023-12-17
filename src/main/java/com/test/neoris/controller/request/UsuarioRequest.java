package com.test.neoris.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {

    @NotEmpty(message = "Ingrese su nombre")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Nombre invalido")
    private String name;
    
    @NotEmpty(message = "Ingrese su correo electronico")
    @Email(message = "correo invalido")
    private String email;
    
    @NotEmpty(message = "Ingrese su password")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&.,#])([A-Za-z\\d$@$!%*?&.,#&]|[^ ]){8,15}$", message = "Password invalido")
    private String password;
    
    @NotNull(message = "Ingrese su phones")
    List<TelefonoRequest> phones;
}
