package br.com.tarefas.gerenciador.dto.tasklist;
import br.com.tarefas.gerenciador.model.TaskList;
import lombok.Data;

@Data
public class SimpleTaskListDTO {
    private Long id; 
    private String title;

    public SimpleTaskListDTO(TaskList taskList) {
        this.id = taskList.getId();
        this.title = taskList.getTitle();
    }
}
