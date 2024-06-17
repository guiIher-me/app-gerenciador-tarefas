package br.com.tarefas.gerenciador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.tarefas.gerenciador.dto.user.UpdateUserDTO;
import br.com.tarefas.gerenciador.exception.HttpBadRequestException;
import br.com.tarefas.gerenciador.model.User;
import br.com.tarefas.gerenciador.repository.UserRepository;
import br.com.tarefas.gerenciador.service.UserService;

@RestController
@CrossOrigin(origins = "${frontend.origin}")
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<User> getById(@PathVariable(value="id") @NonNull Long id) {
        User user = userService.getOrFail(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PatchMapping(value="/{id}", produces="application/json")
    public ResponseEntity<User> updateById(@PathVariable("id") @NonNull Long id, @Validated @RequestBody UpdateUserDTO user) throws HttpBadRequestException {
        User updatedUser = userService.updateById(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") @NonNull Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
