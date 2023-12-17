package com.test.neoris.controller;

import com.test.neoris.controller.request.UsuarioRequest;
import com.test.neoris.controller.response.Response;
import com.test.neoris.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Usuario", description = "Controlador Servicio Usuario")
public class UsuarioController {
    
    @Autowired
    UsuarioService service;
    
    @Operation(summary = "Registro de Usuario", tags = {"Controlador Servicio Usuario"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Response.class), mediaType = "application/json")}, description = "CREATED" ),
        @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = Response.class), mediaType = "application/json")}, description = "BAD REQUEST" ),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = Response.class), mediaType = "application/json")}, description = "INTERNAL SERVER" )
    })
    @PostMapping(path = "/registro", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> registro(@RequestBody UsuarioRequest request) {
        return service.registro(request);
    }
}
