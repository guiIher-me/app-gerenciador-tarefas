package br.com.tarefas.gerenciador.dto.task;

import br.com.tarefas.gerenciador.model.TaskList;
import lombok.Data;

@Data
public class GetTaskListDTO {
    private Long id;
    private String title;

    public GetTaskListDTO(TaskList taskList) {
        this.id = taskList.getId();
        this.title = taskList.getTitle();
    }
}
