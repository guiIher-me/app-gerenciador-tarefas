package br.com.tarefas.gerenciador.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tarefas.gerenciador.dto.user.CreateUserDTO;
import br.com.tarefas.gerenciador.dto.user.UpdateUserDTO;
import br.com.tarefas.gerenciador.exception.HttpBadRequestException;
import br.com.tarefas.gerenciador.model.Role;
import br.com.tarefas.gerenciador.model.User;
import br.com.tarefas.gerenciador.repository.RoleRepository;
import br.com.tarefas.gerenciador.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @SuppressWarnings("null")
    public User getOrFail(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        this.assertUser(user instanceof User);
        return user;
    }

    public void assertUser(boolean condition) throws ResourceNotFoundException {
        if (!condition) throw new ResourceNotFoundException("User not found!");
    }

    public void encodePassword(User user) {
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
    }

    public User create(CreateUserDTO createUser) throws HttpBadRequestException {
        if (userRepository.existsByEmail(createUser.getEmail()))
            throw new HttpBadRequestException("E-mail already exists!");

        User user = new User();
        user.setName(createUser.getName());
        user.setEmail(createUser.getEmail());
        user.setPassword(createUser.getPassword());
        encodePassword(user);
        
        List<Role> roles = createUser.getRoleNames().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new ResourceNotFoundException("Role not found with name: " + roleName)))
                .collect(Collectors.toList());

        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @SuppressWarnings("null")
    public User updateById(Long id, UpdateUserDTO user) throws HttpBadRequestException {
        User existingUser = getOrFail(id);
        User userWithEmail = userRepository.findByEmail(user.getEmail());
        
        if (userWithEmail != null && !userWithEmail.getId().equals(id))
            throw new HttpBadRequestException("E-mail already in use by another user!");
        
        if (user.getName() != null)
            existingUser.setName(user.getName());

        if (user.getEmail() != null)
            existingUser.setEmail(user.getEmail());

        if(user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
            encodePassword(existingUser);
        }

        User updatedUser = userRepository.save(existingUser);        
        return updatedUser;
    }

    public void deleteById(Long id) {
        User user = getOrFail(id);
        user.getRoles().clear();
        userRepository.delete(user);
    }
    
}
