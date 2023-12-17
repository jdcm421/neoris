package com.test.neoris.controller;

import com.test.neoris.controller.request.UsuarioRequest;
import com.test.neoris.controller.response.Response;
import com.test.neoris.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@CrossOrigin
@Api(value = "personas", produces = "application/json", tags = {"Controlador Servicio Usuario"})
public class UsuarioController {
    
    @Autowired
    UsuarioService service;
    
    @ApiOperation(value = "Registro Usuario ", tags = {"Controlador Servicio Usuario"})
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "CREATED", response = Response.class),
        @ApiResponse(code = 400, message = "BADREQUEST",response = Response.class),
        @ApiResponse(code = 401, message = "NOT AUTHERIZATION", response = Response.class),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = Response.class)
    })
    @PostMapping(path = "/registro", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> registro(@RequestBody UsuarioRequest request) {
        return service.registro(request);
    }
}
