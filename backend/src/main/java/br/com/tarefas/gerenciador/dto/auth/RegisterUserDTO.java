package br.com.tarefas.gerenciador.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserDTO {
    @NotNull(message = "Field 'name' is mandatory")
    String name;

    @Email(message = "{validation.email.format}")
    @NotNull(message = "Field 'login' is mandatory")
    String login;

    @Size(min = 6, message = "{validation.password.length}")
    @NotNull(message = "Field 'password' is mandatory")
    String password;

    @NotNull(message = "Field 'role' is mandatory")
    String role;
}
