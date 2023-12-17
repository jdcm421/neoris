package com.test.neoris.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {

    private String id;

    private String created;

    private String modified;

    private String lastlogin;

    private String token;
    private boolean isactive;
}
