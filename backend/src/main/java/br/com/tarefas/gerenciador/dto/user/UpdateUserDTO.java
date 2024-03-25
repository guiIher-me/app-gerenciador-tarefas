package br.com.tarefas.gerenciador.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserDTO {
    private String name;

    @Email(message = "{validation.email.format}")
    private String email;

    @Size(min = 6, message = "{validation.password.length}")
    private String password;
}
