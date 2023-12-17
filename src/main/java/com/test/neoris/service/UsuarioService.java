package com.test.neoris.service;

import com.test.neoris.controller.request.UsuarioRequest;
import com.test.neoris.controller.response.Response;
import com.test.neoris.entity.Usuario;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {
    
    public ResponseEntity<Response> registro(UsuarioRequest request);
     public Optional<Usuario> usuario(String email);
}
