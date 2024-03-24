package br.com.tarefas.gerenciador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tarefas.gerenciador.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
