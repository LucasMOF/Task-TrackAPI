package com.API.Task_Tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @GetMapping
    public ResponseEntity<String> olaMundoSeguro() {
        return ResponseEntity.ok("Olá! Se você está lendo isso, seu Token funcionou!");
    }
}
