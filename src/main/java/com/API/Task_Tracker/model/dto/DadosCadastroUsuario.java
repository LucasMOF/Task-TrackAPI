package com.API.Task_Tracker.model.dto;

import com.API.Task_Tracker.model.UserRole;

public record DadosCadastroUsuario(String username, String senha, UserRole role) {
}
