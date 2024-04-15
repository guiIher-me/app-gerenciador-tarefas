package br.com.tarefas.gerenciador.dto.subtask;

import br.com.tarefas.gerenciador.dto.task.CreateTaskDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CreateSubtaskDTO extends CreateTaskDTO {
    @NotNull(message = "Field 'parentTaskId' is mandatory")
    private Long parentTaskId;
}
