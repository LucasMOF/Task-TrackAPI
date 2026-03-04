package com.API.Task_Tracker.model.dto;

import com.API.Task_Tracker.model.StatusTarefa;
import com.API.Task_Tracker.model.Tarefa;

import java.time.LocalDateTime;


public record DadosDetalhamentoTarefa(Long id, String titulo, String descricao, StatusTarefa status, LocalDateTime dataCriacao) {

    public DadosDetalhamentoTarefa(Tarefa tarefa){
        this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getStatus(), tarefa.getDataCriacao());
    }
}
