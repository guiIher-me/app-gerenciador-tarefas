package br.com.tarefas.gerenciador.dto.task;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskDTO {
    @NotNull(message = "Field 'title' is mandatory")
    protected String title;

    protected String description;

    @NotNull(message = "Field 'usersIds' is mandatory")
    protected List<Long> usersIds;
    
    @NotNull(message = "Field 'listId' is mandatory")
    protected Long listId;

    protected String startDate;

    protected String endDate;

    public CreateTaskDTO() { }
}
