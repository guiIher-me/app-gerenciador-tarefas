package br.com.tarefas.gerenciador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.gerenciador.dto.task.CreateTaskDTO;
import br.com.tarefas.gerenciador.dto.task.GetTaskDTO;
import br.com.tarefas.gerenciador.model.Task;
import br.com.tarefas.gerenciador.repository.TaskRepository;
import br.com.tarefas.gerenciador.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping(value="/", produces="application/json")
    public ResponseEntity<GetTaskDTO> createTask(@NonNull @Validated @RequestBody CreateTaskDTO createTaskDTO) {
        Task savedTask = taskService.createTask(createTaskDTO);
        GetTaskDTO taskDTO = new GetTaskDTO(savedTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDTO);
    }

    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<GetTaskDTO> getTaskById(@NonNull @Validated @PathVariable Long id) {
        Task task = taskService.getOrFail(id);
        GetTaskDTO taskDTO = new GetTaskDTO(task);
        return ResponseEntity.ok().body(taskDTO);
    }

    @SuppressWarnings("null")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") @NonNull Long id) {
        Task task = taskService.getOrFail(id);
        this.taskRepository.delete(task);
        return ResponseEntity.noContent().build();
    }

}