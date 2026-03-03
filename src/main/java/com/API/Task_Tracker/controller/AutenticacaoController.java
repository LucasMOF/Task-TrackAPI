package com.API.Task_Tracker.controller;

import com.API.Task_Tracker.model.Usuario;
import com.API.Task_Tracker.model.dto.DadosAutenticacao;
import com.API.Task_Tracker.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody DadosAutenticacao dados){

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.username(), dados.senha());

        var authentication = authenticationManager.authenticate(authenticationToken);

        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        String tokenJWT = tokenService.gerarToken(usuarioLogado);

        return ResponseEntity.ok(tokenJWT);
    }
}
