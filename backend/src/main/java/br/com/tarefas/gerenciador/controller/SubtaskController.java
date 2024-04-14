package br.com.tarefas.gerenciador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.gerenciador.dto.subtask.CreateSubtaskDTO;
import br.com.tarefas.gerenciador.dto.subtask.GetSubtaskDTO;
import br.com.tarefas.gerenciador.repository.SubtaskRepository;
import br.com.tarefas.gerenciador.service.SubtaskService;

@RestController
@RequestMapping("/subtask")
public class SubtaskController {

    @Autowired
    private SubtaskRepository subtaskRepository;
   
    @Autowired
    private SubtaskService subtaskService;

    @PostMapping(value="/", produces="application/json")
    public ResponseEntity<GetSubtaskDTO> create(@NonNull @Validated @RequestBody CreateSubtaskDTO createTaskDTO) {
        return ResponseEntity.ok().build();
    }
    
}
