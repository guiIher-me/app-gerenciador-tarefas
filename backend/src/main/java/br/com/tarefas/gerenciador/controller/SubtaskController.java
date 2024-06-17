package br.com.tarefas.gerenciador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.gerenciador.dto.subtask.CreateSubtaskDTO;
import br.com.tarefas.gerenciador.dto.subtask.GetSubtaskDTO;
import br.com.tarefas.gerenciador.model.Subtask;
import br.com.tarefas.gerenciador.service.SubtaskService;

@RestController
@CrossOrigin(origins = "${frontend.origin}")
@RequestMapping("/subtask")
public class SubtaskController {
   
    @Autowired
    private SubtaskService subtaskService;

    @PostMapping(value="/", produces="application/json")
    public ResponseEntity<GetSubtaskDTO> create(@NonNull @Validated @RequestBody CreateSubtaskDTO createSubtaskDTO) {
        Subtask savedSubtask = subtaskService.createSubtask(createSubtaskDTO);
        GetSubtaskDTO subtaskDTO = new GetSubtaskDTO(savedSubtask);
        return ResponseEntity.status(HttpStatus.CREATED).body(subtaskDTO);
    }

    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<GetSubtaskDTO> getById(@NonNull @Validated @PathVariable Long id) {
        Subtask subtask = subtaskService.getOrFail(id);
        GetSubtaskDTO subtaskDTO = new GetSubtaskDTO(subtask);
        return ResponseEntity.ok().body(subtaskDTO);
    }    
}
