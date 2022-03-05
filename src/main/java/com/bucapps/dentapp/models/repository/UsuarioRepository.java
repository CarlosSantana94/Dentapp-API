package com.bucapps.dentapp.models.repository;

import com.bucapps.dentapp.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario getAllByToken(String token);
}
