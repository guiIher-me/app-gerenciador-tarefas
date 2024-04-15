package br.com.tarefas.gerenciador.dto.task;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskDTO {
    @NotNull(message = "{validation.title.mandatory}")
    protected String title;

    protected String description;

    @NotNull(message = "{validation.usersIds.mandatory}")
    protected List<Long> usersIds;
    
    @NotNull(message = "{validation.listId.mandatory}")
    protected Long listId;

    protected String startDate;

    protected String endDate;

    public CreateTaskDTO() { }
}
