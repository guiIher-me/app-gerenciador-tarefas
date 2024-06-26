package br.com.tarefas.gerenciador.dto.task;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.tarefas.gerenciador.model.Task;
import lombok.Data;

@Data
public class SimpleTaskDTO {
    private Long id;
    private String title;
    private List<String> usersNames;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    public SimpleTaskDTO() { }

    public SimpleTaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();

        this.usersNames = task.getUsers().stream()
            .map(user -> user.getName())
            .collect(Collectors.toList());

        this.startDate = task.getStartDate();
        this.endDate = task.getEndDate();
    }
}
