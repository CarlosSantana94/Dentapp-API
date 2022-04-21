package com.bucapps.dentapp.services;

import com.bucapps.dentapp.models.entity.Usuario;
import com.bucapps.dentapp.models.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario guardarOActualizarUsuario(Usuario usuario) {

        Usuario buscarUsuario = usuarioRepository.getAllByToken(usuario.getToken());
        if (buscarUsuario!=null) {
            usuario.setId(buscarUsuario.getId());
            usuario.setCreatedDate(buscarUsuario.getCreatedDate());
        }

        return usuarioRepository.save(usuario);
    }
}
