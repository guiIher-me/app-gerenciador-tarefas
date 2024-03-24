package br.com.tarefas.gerenciador.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskDTO {
    @NotNull(message = "Field 'title' is mandatory")
    private String title;

    private String description;

    @NotNull(message = "Field 'userId' is mandatory")
    private Long userId;
    
    @NotNull(message = "Field 'listId' is mandatory")
    private Long listId;

    private String startDate;

    private String endDate;
}
