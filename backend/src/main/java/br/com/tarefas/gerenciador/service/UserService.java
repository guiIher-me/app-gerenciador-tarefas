package br.com.tarefas.gerenciador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tarefas.gerenciador.model.User;
import br.com.tarefas.gerenciador.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @SuppressWarnings("null")
    public User getOrFail(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        this.assertUser(user != null);
        return user;
    }

    public void assertUser(boolean condition) throws ResourceNotFoundException {
        if (!condition) throw new ResourceNotFoundException("User not found!");
    }

    public void encodePassword(User user) {
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
    }
    
}
