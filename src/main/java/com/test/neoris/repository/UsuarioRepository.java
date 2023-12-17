package com.test.neoris.repository;

import com.test.neoris.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    
    public Optional<Usuario> findByEmail(String email);
}
