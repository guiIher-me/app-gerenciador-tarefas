package br.com.tarefas.gerenciador.controller.auth;

// import java.util.Date;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// import br.com.tarefas.gerenciador.repository.UserProjection;
// import br.com.tarefas.gerenciador.repository.UserRepository;
// import br.com.tarefas.gerenciador.security.JWTCreator;
// import br.com.tarefas.gerenciador.security.JWTObject;
// import br.com.tarefas.gerenciador.security.SecurityConfig;

// @RestController
public class LoginController {

    // @Autowired
    // private PasswordEncoder encoder;

    // @Autowired
    // private UserRepository repository;
    
    // @PostMapping("/login")
    // public LoginResponse logar(@RequestBody LoginRequest login) {
    //     UserProjection user = repository.findByEmail(login.getEmail());
    //     if (user == null) throw new RuntimeException("Erro ao tentar fazer login");

    //     boolean matchPassword =  encoder.matches(login.getPassword(), user.getPassword());
    //     if (!matchPassword) throw new RuntimeException("Senha inválida para o login: " + login.getEmail());

    //     Long now = System.currentTimeMillis();
    //     JWTObject jwtObject = new JWTObject();
    //     jwtObject.setIssuedAt(new Date(now));
    //     jwtObject.setExpiration((new Date(now + SecurityConfig.EXPIRATION)));
    //     jwtObject.setRoles(user.getRoleNames());
       
    //     String token = JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject);
    //     LoginResponse response = new LoginResponse(token);

    //     return response;
    // }
}
