package com.API.Task_Tracker.controller;

import com.API.Task_Tracker.model.StatusTarefa;
import com.API.Task_Tracker.model.Tarefa;
import com.API.Task_Tracker.model.Usuario;
import com.API.Task_Tracker.model.dto.DadosAtualizacaoTarefa;
import com.API.Task_Tracker.model.dto.DadosCadastroTarefa;
import com.API.Task_Tracker.model.dto.DadosDetalhamentoTarefa;
import com.API.Task_Tracker.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoTarefa>> listarTarefas() {
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var tarefas = tarefaRepository.findByUsuario(usuarioLogado);

        var dtoList = tarefas.stream()
                .map(DadosDetalhamentoTarefa::new)
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarinformacao(@RequestBody DadosAtualizacaoTarefa dados) {
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var tarefa = tarefaRepository.getReferenceById(dados.id());

        if (!tarefa.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new RuntimeException("Acesso negado");
        }
        tarefa.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTarefa(tarefa));
    }
}
