package com.test.neoris.service.impl;

import com.test.neoris.dto.UsuarioPrincipal;
import com.test.neoris.entity.Usuario;
import com.test.neoris.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;
            
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioService.usuario(username).get();
        return UsuarioPrincipal.build(user);
    }
    
}
