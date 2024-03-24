package br.com.tarefas.gerenciador.api.initializer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.tarefas.gerenciador.dto.task.CreateTaskDTO;
import br.com.tarefas.gerenciador.dto.tasklist.CreateTaskListDTO;
import br.com.tarefas.gerenciador.model.Role;
import br.com.tarefas.gerenciador.model.User;
import br.com.tarefas.gerenciador.repository.UserRepository;
import br.com.tarefas.gerenciador.service.TaskListService;
import br.com.tarefas.gerenciador.service.TaskService;
import jakarta.transaction.Transactional;
import br.com.tarefas.gerenciador.repository.RoleRepository;
import br.com.tarefas.gerenciador.repository.TaskListRepository;

@Component
public class DevDataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TaskListService taskListService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TaskListRepository taskListRepository;

    @Override
    public void run(String... args) throws Exception {

        // Add roles
        Role roleManager = findOrCreateRole("MANAGER");
        Role roleUser = findOrCreateRole("USER");

        // Add users
        this.addUser("Mr. Mock", "mock@mock.com", "mock123", Arrays.asList(roleManager));
        this.addUser("Testerson", "test@test.com", "test123", Arrays.asList(roleUser));

        // Add Task Lists
        this.addList("To Do");
        this.addList("Development");
        this.addList("Blocked");
        this.addList("Testing");
        this.addList("Done");

        // Add Cards
        this.addCard("Card 1", "desc card", (long) 2, (long) 1, "24/03/2015", "27/03/2015");

    }

    private Role findOrCreateRole(String roleName) {
        Optional<Role> roleOptional = roleRepository.findByName(roleName);
        return roleOptional.orElseGet(() -> {
            Role newRole = new Role(roleName);
            return roleRepository.save(newRole);
        });
    }

    @Transactional
    private void addUser(String name, String email, String password, List<Role> roles) {
        if (!userRepository.existsByEmail(email)) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(roles);

            userRepository.save(user);
        }
    }

    @Transactional
    private void addList(String title) {
        if (!taskListRepository.existsByTitle(title)) {
            CreateTaskListDTO createtTaskList = new CreateTaskListDTO();
            createtTaskList.setTitle(title);
            taskListService.createTaskList(createtTaskList);
        }
    }

    @Transactional
    private void addCard(String title, String description, Long userId, Long taskListId, String startDate, String endDate) {
        CreateTaskDTO createTaskList = new CreateTaskDTO();
        createTaskList.setTitle(title);
        createTaskList.setDescription(description);
        createTaskList.setUserId(userId);
        createTaskList.setListId(taskListId);
        createTaskList.setStartDate(startDate);
        createTaskList.setEndDate(endDate);

        taskService.createTask(createTaskList);
    }

}