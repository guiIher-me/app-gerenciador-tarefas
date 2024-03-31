package br.com.tarefas.gerenciador.model;

import java.util.List;

import org.hibernate.boot.model.source.spi.Sortable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="tasklists")
@JsonIgnoreProperties({"sorted", "comparatorName"})
public class TaskList implements Sortable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Field 'title' is mandatory")
    private String title;

    @NotNull(message = "Field 'position' is mandatory")
    private Double position;

    @OneToMany(mappedBy = "taskList", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Task> tasks;

    public TaskList() { }

    public TaskList(String title, Double position) {
        this.title = title;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(Double position) {
        this.position = position;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean isSorted() {
        return this.position != null;
    }

    @Override
    public String getComparatorName() {
        return "position";
    }

}
