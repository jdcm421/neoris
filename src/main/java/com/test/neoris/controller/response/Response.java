package com.test.neoris.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mensaje = null;
    
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private UsuarioResponse usuario = new UsuarioResponse();
}
