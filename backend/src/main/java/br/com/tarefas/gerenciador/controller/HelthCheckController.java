package br.com.tarefas.gerenciador.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class HelthCheckController {

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<String> init() {
        return ResponseEntity.ok().body("pong");
    }
    
}
