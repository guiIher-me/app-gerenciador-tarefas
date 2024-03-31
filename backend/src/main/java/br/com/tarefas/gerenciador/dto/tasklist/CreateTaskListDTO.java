package br.com.tarefas.gerenciador.dto.tasklist;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskListDTO {
    @NotNull(message = "Field 'title' is mandatory")
    private String title;
    private Long previousListId;
}
