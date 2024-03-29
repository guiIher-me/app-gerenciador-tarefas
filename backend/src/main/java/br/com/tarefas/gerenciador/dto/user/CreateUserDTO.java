package br.com.tarefas.gerenciador.dto.user;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDTO {
    @NotBlank(message = "{validation.name.mandatory}")
    private String name;

    @Email(message = "{validation.email.format}")
    private String email;

    @NotBlank(message = "{validation.password.mandatory}")
    @Size(min = 6, message = "{validation.password.length}")
    private String password;

    private List<String> roleNames;
}
