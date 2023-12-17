package com.test.neoris.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mensaje = null;
    
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private UsuarioResponse usuario = null;
}
