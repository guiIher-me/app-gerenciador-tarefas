package br.com.tarefas.gerenciador.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Void> init() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
