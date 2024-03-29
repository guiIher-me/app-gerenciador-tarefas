package br.com.tarefas.gerenciador.dto.task;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.tarefas.gerenciador.dto.tasklist.SimpleTaskListDTO;
import br.com.tarefas.gerenciador.dto.user.SimpleUserDTO;
import br.com.tarefas.gerenciador.model.Task;
import lombok.Data;

@Data
public class GetTaskDTO {
    private Long id;
    private String title;
    private String description;
    private SimpleUserDTO user;
    private SimpleTaskListDTO list;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    public GetTaskDTO() { }

    public GetTaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.user = new SimpleUserDTO(task.getUser());
        this.list = new SimpleTaskListDTO(task.getTaskList());
        this.startDate = task.getStartDate();
        this.endDate = task.getEndDate();
    }
}