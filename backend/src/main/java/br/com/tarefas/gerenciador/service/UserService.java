package br.com.tarefas.gerenciador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tarefas.gerenciador.dto.auth.RegisterUserDTO;
import br.com.tarefas.gerenciador.dto.user.UpdateUserDTO;
import br.com.tarefas.gerenciador.exception.HttpBadRequestException;
import br.com.tarefas.gerenciador.model.User;
import br.com.tarefas.gerenciador.model.UserRoleEnum;
import br.com.tarefas.gerenciador.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<User> getAllOrFail(List<Long> usersIds) {
        List<User> users = userRepository.findAllByIdIn(usersIds);
        assertUser(usersIds.size() == users.size());
        return users;
    }

    public User getOrFail(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        this.assertUser(user instanceof User);
        return user;
    }

    public void assertUser(boolean condition) throws ResourceNotFoundException {
        if (!condition) throw new ResourceNotFoundException("User(s) not found!");
    }

    public UserRoleEnum getUserRole(RegisterUserDTO registerUser) throws HttpBadRequestException {
        try {
            String userRole = registerUser.getRole();
            return UserRoleEnum.valueOf(userRole);
        } catch(Exception e) {
            throw new HttpBadRequestException("Invalid Role value!");
        }
    }

    public void encodePassword(User user) {
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
    }

    public User registerToUser(RegisterUserDTO registerUser) throws HttpBadRequestException {
        User user = new User();
        user.setName(registerUser.getName());
        user.setEmail(registerUser.getLogin());
        user.setPassword(registerUser.getPassword());
        user.setRole(this.getUserRole(registerUser));

        return user;
    }

    public User register(RegisterUserDTO registerUser) throws HttpBadRequestException {
        if (userRepository.existsByEmail(registerUser.getLogin()))
            throw new HttpBadRequestException("E-mail already exists!");

        User user = this.registerToUser(registerUser);
        encodePassword(user);

        User savedUser = userRepository.save(user);
        return savedUser;
    }

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
        userRepository.delete(user);
    }
    
}
