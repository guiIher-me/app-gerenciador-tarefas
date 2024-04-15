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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.gerenciador.dto.subtask.CreateSubtaskDTO;
import br.com.tarefas.gerenciador.dto.subtask.GetSubtaskDTO;
import br.com.tarefas.gerenciador.model.Subtask;
import br.com.tarefas.gerenciador.service.SubtaskService;
import br.com.tarefas.gerenciador.service.TaskService;

@RestController
@RequestMapping("/subtask")
public class SubtaskController {
   
    @Autowired
    private SubtaskService subtaskService;

    @Autowired
    private TaskService taskService;

    @PostMapping(value="/", produces="application/json")
    public ResponseEntity<GetSubtaskDTO> create(@NonNull @Validated @RequestBody CreateSubtaskDTO createTaskDTO) {
        Subtask savedSubtask = subtaskService.createSubtask(createTaskDTO);
        GetSubtaskDTO subtaskDTO = new GetSubtaskDTO(savedSubtask);
        return ResponseEntity.status(HttpStatus.CREATED).body(subtaskDTO);
    }

    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<GetSubtaskDTO> getById(@NonNull @Validated @PathVariable Long id) {
        Subtask subtask = subtaskService.getOrFail(id);
        GetSubtaskDTO subtaskDTO = new GetSubtaskDTO(subtask);
        return ResponseEntity.ok().body(subtaskDTO);
    }

    @PutMapping(value="/{taskId}/move-to-list/{listId}", produces="application/json")
    public ResponseEntity<GetSubtaskDTO> moveTaskToList(
        @NonNull @Validated @PathVariable Long subtaskId,
        @NonNull @Validated @PathVariable Long listId) {

        Subtask updatedSubtask = (Subtask) taskService.moveTaskToList(subtaskId, listId);
        GetSubtaskDTO subtaskDTO = new GetSubtaskDTO(updatedSubtask);
        return ResponseEntity.ok().body(subtaskDTO);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteById(@PathVariable("id") @NonNull Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
