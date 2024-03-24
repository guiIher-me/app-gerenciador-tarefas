package br.com.tarefas.gerenciador.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.gerenciador.dto.tasklist.CreateTaskListDTO;
import br.com.tarefas.gerenciador.model.TaskList;
import br.com.tarefas.gerenciador.repository.TaskListRepository;
import br.com.tarefas.gerenciador.service.TaskListService;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping(value="/list")
public class TaskListController {

    @Autowired
    private TaskListRepository taskListRepository;
   
    @Autowired
    private TaskListService taskListService;

    @PostMapping(value="/", produces="application/json")
    public ResponseEntity<TaskList> create(@NonNull @Validated @RequestBody CreateTaskListDTO createTasklist) {
        TaskList savedTaskList = taskListService.createTaskList(createTasklist);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTaskList);
    }

    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<TaskList> getById(@PathVariable(value="id") @NonNull Long id) {
        Optional<TaskList> tasklist = this.taskListRepository.findById(id);
        taskListService.assertTaskList(tasklist.isPresent());
        return ResponseEntity.ok().body(tasklist.get());
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<TaskList>> getAll() {
        List<TaskList> tasklists = this.taskListRepository.findAll();
        return ResponseEntity.ok().body(tasklists);
    }

    @PutMapping(value = "/{id}/title/{newTitle}", produces = "application/json")
    public ResponseEntity<TaskList> updateName(
        @PathVariable(value="id") @NonNull Long id,
        @PathVariable(value="newTitle") @NotBlank String newTitle) {

        TaskList existingTaskList = this.taskListRepository.findById(id).orElse(null);
        taskListService.assertTaskList(existingTaskList instanceof TaskList);

        existingTaskList.setTitle(newTitle);
        TaskList updatedTaskList = this.taskListRepository.save(existingTaskList);
        return ResponseEntity.ok(updatedTaskList);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteById(@PathVariable(value="id") @NonNull Long id) {
        taskListService.assertTaskList(this.taskListRepository.existsById(id));
        this.taskListRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
