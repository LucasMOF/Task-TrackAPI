package com.API.Task_Tracker.model;

import com.API.Task_Tracker.model.dto.DadosAtualizacaoTarefa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Tarefa")
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public void atualizarInformacoes(DadosAtualizacaoTarefa dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }

        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }

        if (dados.status() != null) {
            this.status = dados.status();
        }
    }
}
