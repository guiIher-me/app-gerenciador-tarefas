package br.com.tarefas.gerenciador.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

import br.com.tarefas.gerenciador.dto.user.CreateUserDTO;
import br.com.tarefas.gerenciador.exception.ErrorResponse;
import br.com.tarefas.gerenciador.model.Role;
import br.com.tarefas.gerenciador.model.User;
import br.com.tarefas.gerenciador.repository.RoleRepository;
import br.com.tarefas.gerenciador.repository.UserRepository;
import br.com.tarefas.gerenciador.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> create(@NonNull @Validated @RequestBody CreateUserDTO createUser) {
        if (userRepository.existsByEmail(createUser.getEmail())) {
            ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "E-mail already exists!");
            return ResponseEntity.badRequest().body(error);
        }

        User user = new User();
        user.setName(createUser.getName());
        user.setEmail(createUser.getEmail());
        user.setPassword(createUser.getPassword());
        userService.encodePassword(user);
        
        List<Role> roles = createUser.getRoleNames().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new ResourceNotFoundException("Role not found with name: " + roleName)))
                .collect(Collectors.toList());

        user.setRoles(roles);
        User savedUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<User> getById(@PathVariable(value="id") @NonNull Long id) {
        Optional<User> user = userRepository.findById(id);
        userService.assertUser(user.isPresent());
        return ResponseEntity.ok().body(user.get());
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping(value="/users/{id}", produces="application/json")
    public ResponseEntity<?> updateUser(@PathVariable("id") @NonNull Long id, @Validated @RequestBody User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        userService.assertUser(existingUser instanceof User);
        
        User userWithEmail = userRepository.findByEmail(user.getEmail());
        
        if (userWithEmail != null && !userWithEmail.getId().equals(id)) {
            ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "E-mail already in use by another user!");
            return ResponseEntity.badRequest().body(error);
        }

        existingUser.update(user);
        User updatedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") @NonNull Long id) {
        User user = userRepository.findById(id).orElse(null);
        userService.assertUser(user instanceof User);

        user.getRoles().clear();
        userRepository.delete(user);

        return ResponseEntity.noContent().build();
    }

}
