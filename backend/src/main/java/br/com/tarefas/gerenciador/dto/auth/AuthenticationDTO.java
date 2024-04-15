package br.com.tarefas.gerenciador.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthenticationDTO {
    @Email(message = "{validation.email.format}")
    @NotNull(message = "{validation.login.mandatory}")
    private String login;

    @NotNull(message = "{validation.password.mandatory}")
    private String password;
}
