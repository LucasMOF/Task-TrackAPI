package com.API.Task_Tracker.controller;

import com.API.Task_Tracker.model.Usuario;
import com.API.Task_Tracker.model.dto.DadosCadastroUsuario;
import com.API.Task_Tracker.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody DadosCadastroUsuario dto){
        String senhaCriptografada = passwordEncoder.encode(dto.senha());

        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsername(dto.username());
        novoUsuario.setRole(dto.role());
        novoUsuario.setSenha(senhaCriptografada);

        usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();

    }

}
