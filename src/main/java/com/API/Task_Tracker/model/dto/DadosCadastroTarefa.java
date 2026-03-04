package com.API.Task_Tracker.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosCadastroTarefa(

        @NotBlank(message = "O título é obrigatório!")
        String titulo,

        @NotBlank(message = "A descrição não pode ficar em branco!")
        @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
        String descricao
) {
}
