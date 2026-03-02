package com.API.Task_Tracker.repository;

import com.API.Task_Tracker.model.Tarefa;
import com.API.Task_Tracker.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByUsuario(Usuario usuario);
}
