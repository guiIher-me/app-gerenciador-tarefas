package br.com.tarefas.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tarefas.gerenciador.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    List<User> findAllByIdIn(List<Long> usersIds);
}
