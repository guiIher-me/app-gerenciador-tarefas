package br.com.tarefas.gerenciador.dto.tasklist;

import java.util.List;
import java.util.stream.Collectors;

import br.com.tarefas.gerenciador.dto.task.SimpleTaskDTO;
import br.com.tarefas.gerenciador.model.TaskList;
import lombok.Data;

@Data
public class GetTaskListDTO {
    private Long id; 
    private String title;
    private Double position;
    private List<SimpleTaskDTO> tasks;

    public GetTaskListDTO(TaskList taskList) {
        this.id = taskList.getId();
        this.title = taskList.getTitle();
        this.position = taskList.getPosition();
        this.tasks = taskList.getTasks().stream()
                        .map(task -> new SimpleTaskDTO(task))
                        .collect(Collectors.toList());
    }
}