package br.com.tarefas.gerenciador.dto.user;

import br.com.tarefas.gerenciador.model.UserRole;
import lombok.Data;

@Data
public class RegisterUserDTO {
    String login;
    String password;
    UserRole role;
}
