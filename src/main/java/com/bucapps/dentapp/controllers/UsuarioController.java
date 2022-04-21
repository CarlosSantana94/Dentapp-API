package com.bucapps.dentapp.controllers;

import com.bucapps.dentapp.models.entity.Usuario;
import com.bucapps.dentapp.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@CrossOrigin(maxAge = 3600)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "usuario", produces = "application/json")
    public ResponseEntity<Usuario> guardarOActualizarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.guardarOActualizarUsuario(usuario));
    }
}
