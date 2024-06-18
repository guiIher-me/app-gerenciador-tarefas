package br.com.tarefas.gerenciador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tarefas.gerenciador.dto.auth.RegisterUserDTO;
import br.com.tarefas.gerenciador.dto.user.UpdateUserDTO;
import br.com.tarefas.gerenciador.exception.HttpBadRequestException;
import br.com.tarefas.gerenciador.model.User;
import br.com.tarefas.gerenciador.repository.UserRepository;
import br.com.tarefas.gerenciador.util.UserRoleEnum;

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

    @SuppressWarnings("null")
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

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        String email = userDetails.getUsername();
        User user = userRepository.findByEmail(email);
        
        return user;
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

    @SuppressWarnings("null")
    public User register(RegisterUserDTO registerUser) throws HttpBadRequestException {
        if (userRepository.existsByEmail(registerUser.getLogin()))
            throw new HttpBadRequestException("E-mail already exists!");

        User user = this.registerToUser(registerUser);
        encodePassword(user);

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @SuppressWarnings("null")
    public User updateByToken(UpdateUserDTO user) throws HttpBadRequestException {
        User existingUser = this.getCurrentUser();
        return this.update(user, existingUser);
    }

    @SuppressWarnings("null")
    public User updateById(Long id, UpdateUserDTO user) throws HttpBadRequestException {
        User existingUser = getOrFail(id);
        return this.update(user, existingUser);
    }

    protected User update(UpdateUserDTO user, User existingUser) throws HttpBadRequestException {
        if (user.getName() != null)
            existingUser.setName(user.getName());
            
        if (user.getOldPassword() != null ^ user.getNewPassword() != null) // XOR
            throw new HttpBadRequestException("Fill all password fields!");

        if (user.getOldPassword() != null) {
            this.assertOldPassword(user, existingUser);
            existingUser.setPassword(user.getNewPassword());
            encodePassword(existingUser);
        }

        User updatedUser = userRepository.save(existingUser);        
        return updatedUser;
    }

    protected void assertOldPassword(UpdateUserDTO user, User existingUser) throws HttpBadRequestException {
        System.out.println("Chegou aqui");
        String oldPasswordEncoded = existingUser.getPassword();
        System.out.println(oldPasswordEncoded);

        String oldPassword = user.getOldPassword();
        if ( !encoder.matches(oldPassword, oldPasswordEncoded))
            throw new HttpBadRequestException("Unmatch old password!");
    }


    @SuppressWarnings("null")
    public void deleteById(Long id) {
        User user = getOrFail(id);
        userRepository.delete(user);
    }
    
}
