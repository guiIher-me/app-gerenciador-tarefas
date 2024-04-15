package br.com.tarefas.gerenciador.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserDTO {
    @NotNull(message = "{validation.name.mandatory}")
    String name;

    @Email(message = "{validation.email.format}")
    @NotNull(message = "{validation.login.mandatory}")
    String login;

    @Size(min = 6, message = "{validation.password.length}")
    @NotNull(message = "{validation.password.mandatory}")
    String password;

    @NotNull(message = "{validation.role.mandatory}")
    String role;
}
