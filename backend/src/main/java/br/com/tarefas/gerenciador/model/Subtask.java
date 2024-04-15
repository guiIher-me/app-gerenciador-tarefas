package br.com.tarefas.gerenciador.model;

import br.com.tarefas.gerenciador.util.TaskTypeConst;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity
@Table(name="subtasks")
@DiscriminatorValue(TaskTypeConst.SUBTASK)
public class Subtask extends Task {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_task_id")
    @Valid
    private Task parent;
    
    public Subtask() { }

    public static Subtask createFromTask(Task task) {
        Subtask subtask = new Subtask();

        subtask.id = task.id;
        subtask.title = task.title;
        subtask.description = task.description;
        subtask.users = task.users;
        subtask.taskList = task.taskList;
        subtask.startDate = task.startDate;
        subtask.endDate = task.endDate;

        return subtask;
    }

    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }

}
