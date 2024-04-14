package br.com.tarefas.gerenciador.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity
@Table(name="subtasks")
@DiscriminatorValue("Subtask")
public class Subtask extends Task {

    @ManyToOne
    @JoinColumn(name = "task_id")
    @Valid
    private Task parent;
    
    public Subtask() { }

    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }

}
