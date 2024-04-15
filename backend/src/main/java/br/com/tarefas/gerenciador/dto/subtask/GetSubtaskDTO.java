package br.com.tarefas.gerenciador.dto.subtask;

import br.com.tarefas.gerenciador.dto.task.GetTaskDTO;
import br.com.tarefas.gerenciador.model.Subtask;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class GetSubtaskDTO extends GetTaskDTO {
    private Long parentTaskId;
    
    public GetSubtaskDTO(Subtask subtask) {
        super(subtask);
        this.parentTaskId = subtask.getParent().getId();
    }
    
}
