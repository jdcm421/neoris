package com.test.neoris;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.neoris.controller.request.TelefonoRequest;
import com.test.neoris.controller.request.UsuarioRequest;
import com.test.neoris.controller.response.Response;
import com.test.neoris.service.impl.UsuarioServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class UsuarioServiceImplTest {

    private ObjectMapper mapper;
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    public void setup() {
        usuarioService = new UsuarioServiceImpl();
    }

    @DisplayName("Se realiza validacion del nombre")
    @Test
    public void registroValidacionNombreTest(){
        Response esperado = new Response("Nombre invalido", null);
        TelefonoRequest telreq = new TelefonoRequest("1234567", "1", "51");
        List<TelefonoRequest> phone = new ArrayList<>();
        phone.add(telreq);
        UsuarioRequest request1 = new UsuarioRequest("<h1>Ronaldo45", "ronaldo@gmail.com", "Lgoptimusg3$", phone);
        final ResponseEntity<Response> resultado = usuarioService.registro(request1);
        Assertions.assertEquals(esperado, resultado.getBody());
    }

    @DisplayName("Se realiza validacion del correo")
    @Test
    public void registroValidacionEmailTest() {
        Response esperado = new Response("correo invalido", null);
        TelefonoRequest telreq = new TelefonoRequest("1234567", "1", "51");
        List<TelefonoRequest> phone = new ArrayList<>();
        phone.add(telreq);
        UsuarioRequest request1 = new UsuarioRequest("Ronaldo", "juan.org", "Lgoptimusg3#", phone);
        final ResponseEntity<Response> resultado = usuarioService.registro(request1);
        Assertions.assertEquals(esperado, resultado.getBody());
    }

    @DisplayName("Se realiza validacion del password")
    @Test
    public void registroValidacionPasswordTest() {
        Response esperado = new Response("La contraseña de tener una mayuscula  y una minuscula un número y un caracter especial", null);
        TelefonoRequest telreq = new TelefonoRequest("1234567", "1", "51");
        List<TelefonoRequest> phone = new ArrayList<>();
        phone.add(telreq);
        UsuarioRequest request1 = new UsuarioRequest("Ronaldo", "ronaldo@gmail.com", "12345678", phone);
        final ResponseEntity<Response> resultado = usuarioService.registro(request1);
        Assertions.assertEquals(esperado, resultado.getBody());
    }
}
