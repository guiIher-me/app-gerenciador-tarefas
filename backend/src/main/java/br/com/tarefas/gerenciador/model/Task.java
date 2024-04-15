package br.com.tarefas.gerenciador.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.tarefas.gerenciador.util.TaskTypeConst;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="tasks")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "task_type")
@DiscriminatorValue(TaskTypeConst.TASK)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @JsonProperty("task_type")
    @Column(name = "task_type", insertable = false, updatable = false)
    private String taskType;

    protected String title;

    protected String description;

    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "tasks_users", 
                uniqueConstraints = @UniqueConstraint (
                    columnNames = {"task_id","user_id"}, 
                    name = "unique_user_task"
                ), 
	            joinColumns = @JoinColumn(name = "task_id", 
                    referencedColumnName = "id", 
                    table = "tasks", 
                    unique = false
                ), 
	            inverseJoinColumns = @JoinColumn (
                    name = "user_id", 
                    referencedColumnName = "id", 
                    table = "users", 
                    unique = false
                )
            )    
    @Valid
    protected List<User> users;

    @ManyToOne
    @JoinColumn(name = "tasklist_id")
    @Valid
    protected TaskList taskList;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
