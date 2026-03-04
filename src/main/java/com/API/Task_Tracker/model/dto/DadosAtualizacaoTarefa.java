package com.API.Task_Tracker.model.dto;

import com.API.Task_Tracker.model.StatusTarefa;

public record DadosAtualizacaoTarefa(Long id, String titulo, String descricao, StatusTarefa status) {
}
