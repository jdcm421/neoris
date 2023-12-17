package com.test.neoris.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {

    private String id;
    
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone = "America/Lima")
    private Date created;
    
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone = "America/Lima")
    private Date modified;
    
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone = "America/Lima")
    private Date lastlogin;
    
    private String token;
    private boolean isactive;
}
