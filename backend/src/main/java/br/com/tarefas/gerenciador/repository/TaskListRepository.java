package br.com.tarefas.gerenciador.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import br.com.tarefas.gerenciador.model.TaskList;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    boolean existsByTitle(String title);
    Optional<TaskList> findByTitle(String title);

    @Query("SELECT t FROM TaskList t ORDER BY t.position ASC")
    @NonNull List<TaskList> findAll();

    @Query("SELECT COALESCE(MAX(t.position), 0.0) FROM TaskList t")
    Double findMaxPosition();

    @Query("SELECT t FROM TaskList t WHERE t.position > :position ORDER BY t.position ASC LIMIT 1")
    Optional<TaskList> findFirstByPositionGreaterThan(@Param("position") Double position);
}
