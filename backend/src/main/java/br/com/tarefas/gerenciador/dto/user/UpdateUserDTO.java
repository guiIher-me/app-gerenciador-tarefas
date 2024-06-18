package br.com.tarefas.gerenciador.dto.user;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserDTO {
    private String name;

    String oldPassword;

    @Size(min = 6, message = "{validation.password.length}")
    String newPassword;
}
