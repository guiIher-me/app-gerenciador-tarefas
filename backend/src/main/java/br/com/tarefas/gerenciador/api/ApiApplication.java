package br.com.tarefas.gerenciador.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.tarefas.gerenciador.api.initializer.DevDataInitializer;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EntityScan(basePackages = "br.com.tarefas.gerenciador.model")
@ComponentScan(basePackages = "br.*")
@EnableJpaRepositories(basePackages = "br.com.tarefas.gerenciador.repository")
@EnableTransactionManagement
@EnableWebMvc
@RestController
@AutoConfiguration
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Autowired
    private DevDataInitializer devDataInitializer;

    @PostConstruct
    public void init() throws Exception {
		try {
			devDataInitializer.run();
		} catch (Exception e) {
			e.getStackTrace();
		}
    }

}
