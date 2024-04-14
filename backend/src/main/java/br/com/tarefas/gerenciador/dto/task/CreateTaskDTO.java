package br.com.tarefas.gerenciador.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskDTO {
    @NotNull(message = "Field 'title' is mandatory")
    protected String title;

    protected String description;

    @NotNull(message = "Field 'userId' is mandatory")
    protected Long userId;
    
    @NotNull(message = "Field 'listId' is mandatory")
    protected Long listId;

    protected String startDate;

    protected String endDate;

    public CreateTaskDTO() { }
}
