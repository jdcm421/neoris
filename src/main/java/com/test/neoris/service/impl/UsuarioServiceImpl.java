package com.test.neoris.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.neoris.controller.request.TelefonoRequest;
import com.test.neoris.controller.request.UsuarioRequest;
import com.test.neoris.controller.response.Response;
import com.test.neoris.controller.response.UsuarioResponse;
import com.test.neoris.entity.Telefono;
import com.test.neoris.entity.Usuario;
import com.test.neoris.repository.UsuarioRepository;
import com.test.neoris.security.config.JwtProvider;
import com.test.neoris.service.UsuarioService;
import com.test.neoris.util.Constantes;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

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
                res.setUsuario(null);
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            if (repo.findByEmail(request.getEmail()).isPresent()) {
                res.setMensaje(constantes.ERROREMAIL);
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
            

            Usuario userEntity = new Usuario();
            List<Telefono> phone = new ArrayList<>();
            for (TelefonoRequest tel : request.getPhones()) {
                ValidatorFactory factorys = Validation.buildDefaultValidatorFactory();
                Validator validators = factorys.getValidator();
                Set<ConstraintViolation<TelefonoRequest>> constraintViolationst = validators.validate(tel);
                if (constraintViolationst.iterator().hasNext()) {
                    res.setMensaje(constraintViolationst.iterator().next().getMessage());
                    res.setUsuario(null);
                    return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
                }
                Telefono entityphone = new Telefono();
                entityphone = mapper.convertValue(tel, Telefono.class);
                entityphone.setUsuario(userEntity);
                phone.add(entityphone);
            }
            userEntity.setId(UUID.randomUUID().toString());
            userEntity.setNombre(request.getName());
            userEntity.setEmail(request.getEmail());
            userEntity.setPassword(encoder.encode(request.getPassword()));
            userEntity.setPhones(phone);
            userEntity.setLastLogin(new Date());
            userEntity.setIsactive(true);
            userEntity = repo.save(userEntity);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtProvider.generateToken(authentication);
            userEntity.setToken(token);
            userEntity = repo.save(userEntity);
            UsuarioResponse userRes = new UsuarioResponse();
            userRes = mapper.convertValue(userEntity, UsuarioResponse.class);
            res.setUsuario(userRes);
        } catch (IllegalArgumentException | AuthenticationException ex) {
            log.error(constantes.ERROR, ex);
            res.setMensaje(ex.getMessage());
            res.setUsuario(null);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Override
    public Optional<Usuario> usuario(String email) {
        return repo.findByEmail(email);
    }

}
