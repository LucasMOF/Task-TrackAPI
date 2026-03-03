package com.API.Task_Tracker.controller;

import com.API.Task_Tracker.model.StatusTarefa;
import com.API.Task_Tracker.model.Tarefa;
import com.API.Task_Tracker.model.Usuario;
import com.API.Task_Tracker.model.dto.DadosCadastroTarefa;
import com.API.Task_Tracker.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody DadosCadastroTarefa dados) {
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Tarefa novaTarefa = new Tarefa();

        novaTarefa.setTitulo(dados.titulo());
        novaTarefa.setDescricao(dados.descricao());
        novaTarefa.setStatus(StatusTarefa.PENDENTE);
        novaTarefa.setDataCriacao(LocalDateTime.now());
        novaTarefa.setUsuario(usuarioLogado);

        tarefaRepository.save(novaTarefa);

        return ResponseEntity.ok().build();
    }
}
