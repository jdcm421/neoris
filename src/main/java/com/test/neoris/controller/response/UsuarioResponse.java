package com.test.neoris.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private String id;

    private String created;

    private String modified;

    private String lastlogin;

    private String token;
    
    private boolean isactive;
}
