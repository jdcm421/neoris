package com.test.neoris.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.neoris.controller.request.TelefonoRequest;
import com.test.neoris.controller.request.UsuarioRequest;
import com.test.neoris.controller.response.Response;
import com.test.neoris.controller.response.UsuarioResponse;
import com.test.neoris.entity.Telefono;
import com.test.neoris.entity.Usuario;
import com.test.neoris.repository.UsuarioRepository;
import com.test.neoris.security.config.JwtService;
import com.test.neoris.service.UsuarioService;
import com.test.neoris.util.Constantes;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository repo;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    Constantes constantes;
    
    @Autowired
    private PasswordEncoder encoder; 


    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<Response> registro(UsuarioRequest request) {
        Response res = new Response();
        String token = null;
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<UsuarioRequest>> constraintViolations = validator.validate(request);
            if (constraintViolations.iterator().hasNext()) {
                res.setMensaje(constraintViolations.iterator().next().getMessage());
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            if (repo.findByEmail(request.getEmail()).isPresent()) {
                res.setMensaje(constantes.ERROREMAIL);
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
            
            Usuario userEntity = new Usuario();
            List<Telefono> phone = new ArrayList<>();
            
            userEntity.setId(UUID.randomUUID().toString());
            userEntity.setNombre(request.getName());
            userEntity.setEmail(request.getEmail());
            userEntity.setPassword(encoder.encode(request.getPassword()));
            for(TelefonoRequest tel : request.getPhones()){
                Telefono entityphone = new Telefono();
                entityphone = mapper.convertValue(tel, Telefono.class);
                phone.add(entityphone);
            }
            userEntity.setPhones(phone);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            if (authentication.isAuthenticated()) {
                token = jwtService.generateToken(request.getEmail());
            } else {
                res.setMensaje(constantes.ERRORNOTAUTHORIZED);
                return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
            }
            userEntity.setLastLogin(new Date());
            userEntity.setIsactive(true);
            userEntity = repo.save(userEntity);
            UsuarioResponse userRes = new UsuarioResponse();
            userRes = mapper.convertValue(userEntity, UsuarioResponse.class);
            res.setUsuario(userRes);
        } catch (Exception ex) {
            log.error(constantes.ERROR, ex);
            res.setMensaje(ex.getMessage());
            res.setUsuario(null);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
