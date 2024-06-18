package br.com.tarefas.gerenciador.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.gerenciador.dto.tasklist.CreateTaskListDTO;
import br.com.tarefas.gerenciador.dto.tasklist.GetTaskListDTO;
import br.com.tarefas.gerenciador.model.TaskList;
import br.com.tarefas.gerenciador.repository.TaskListRepository;
import br.com.tarefas.gerenciador.service.TaskListService;
import jakarta.validation.constraints.NotBlank;

@RestController
@CrossOrigin(origins = "${frontend.origin}")
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
    public ResponseEntity<GetTaskListDTO> getById(@PathVariable(value="id") @NonNull Long id) {
        TaskList taskList = taskListService.getOrFail(id);
        GetTaskListDTO taskListDTO = new GetTaskListDTO(taskList);
        return ResponseEntity.ok().body(taskListDTO);
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<TaskList>> getAll() {
        List<TaskList> tasklists = this.taskListRepository.findAll();
        return ResponseEntity.ok().body(tasklists);
    }

    @GetMapping(value = "/detailed", produces = "application/json")
    public ResponseEntity<List<GetTaskListDTO>> getAllDetailed() {
        List<TaskList> tasklists = this.taskListRepository.findAll();

        List<GetTaskListDTO> getTaskListDTOs = tasklists.stream()
            .map(taskList -> new GetTaskListDTO(taskList))
            .collect(Collectors.toList());

        return ResponseEntity.ok().body(getTaskListDTOs);
    }

    @PutMapping(value = "/{id}/title/{newTitle}", produces = "application/json")
    public ResponseEntity<TaskList> updateTitle(
        @PathVariable(value="id") @NonNull Long id,
        @PathVariable(value="newTitle") @NotBlank String newTitle) {

        TaskList existingTaskList = taskListService.getOrFail(id);
        existingTaskList.setTitle(newTitle);
        TaskList updatedTaskList = this.taskListRepository.save(existingTaskList);
        return ResponseEntity.ok(updatedTaskList);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteById(@PathVariable(value="id") @NonNull Long id) {
        taskListService.deleteTaskListById(id);
        return ResponseEntity.noContent().build();
    }
    
}
