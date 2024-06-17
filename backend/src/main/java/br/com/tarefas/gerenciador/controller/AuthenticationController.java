package br.com.tarefas.gerenciador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.gerenciador.dto.auth.AuthenticationDTO;
import br.com.tarefas.gerenciador.dto.auth.LoginResponseDTO;
import br.com.tarefas.gerenciador.dto.auth.RegisterUserDTO;
import br.com.tarefas.gerenciador.exception.HttpBadRequestException;
import br.com.tarefas.gerenciador.model.User;
import br.com.tarefas.gerenciador.security.TokenService;
import br.com.tarefas.gerenciador.service.UserService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "${frontend.origin}")
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        UsernamePasswordAuthenticationToken usernamePassword = 
        new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());

        try {
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<Void> register(@NonNull @Validated @RequestBody RegisterUserDTO registerUser) throws HttpBadRequestException {
        userService.register(registerUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}