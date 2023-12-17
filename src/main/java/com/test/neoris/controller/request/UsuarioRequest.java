package com.test.neoris.controller.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {

    @ApiModelProperty(position = 0,dataType = "String", required = true, name = "name", example = "Luis")
    @NotEmpty(message = "Ingrese su nombre")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Nombre invalido")
    private String name;
    
    @ApiModelProperty(position = 1, required = true, name = "email", dataType = "String", example = "email@dominio.com")
    @NotEmpty(message = "Ingrese su correo electronico")
    @Email(message = "correo invalido")
    private String email;
    
    @ApiModelProperty(position = 2, required = true, name = "password", dataType = "String", example = "Lgoptimusg3#")
    @NotEmpty(message = "Ingrese su password")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&.,#])([A-Za-z\\d$@$!%*?&.,#&]|[^ ]){8,15}$", message = "La contraseña de tener una mayuscula  y una minuscula un número y un caracter especial")
    private String password;
    
    @NotNull(message = "Ingrese su phones")
    List<TelefonoRequest> phones;
}
