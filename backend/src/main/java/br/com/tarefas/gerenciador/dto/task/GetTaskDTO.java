package br.com.tarefas.gerenciador.dto.task;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.tarefas.gerenciador.dto.tasklist.SimpleTaskListDTO;
import br.com.tarefas.gerenciador.dto.user.SimpleUserDTO;
import br.com.tarefas.gerenciador.model.Task;
import lombok.Data;

@Data
public class GetTaskDTO {
    protected Long id;
    protected String title;
    protected String description;
    protected List<SimpleUserDTO> users;
    protected SimpleTaskListDTO list;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate endDate;

    public GetTaskDTO() { }

    public GetTaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        
        this.users = task.getUsers().stream()
            .map(user -> new SimpleUserDTO(user))
            .collect(Collectors.toList());

        this.list = new SimpleTaskListDTO(task.getTaskList());
        this.startDate = task.getStartDate();
        this.endDate = task.getEndDate();
    }
}