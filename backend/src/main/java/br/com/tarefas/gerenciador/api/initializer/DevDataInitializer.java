package br.com.tarefas.gerenciador.api.initializer;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.tarefas.gerenciador.dto.task.CreateTaskDTO;
import br.com.tarefas.gerenciador.dto.tasklist.CreateTaskListDTO;
import br.com.tarefas.gerenciador.model.User;
import br.com.tarefas.gerenciador.repository.UserRepository;
import br.com.tarefas.gerenciador.service.TaskListService;
import br.com.tarefas.gerenciador.service.TaskService;
import br.com.tarefas.gerenciador.util.UserRoleEnum;
import jakarta.transaction.Transactional;
import br.com.tarefas.gerenciador.repository.TaskListRepository;

@Component
public class DevDataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

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

        // Add users
        this.addUser("Mr. Mock", "mock@mock.com", "mock123", UserRoleEnum.ADMIN);
        this.addUser("Testerson", "test@test.com", "test123", UserRoleEnum.USER);

        // Add Task Lists
        this.addList("To Do", null);
        this.addList("Development", 1L);
        this.addList("Blocked", 2L);
        this.addList("Testing", 3L);
        this.addList("Done", 4L);
       
        // Add Cards
        // this.addCard("Card 1", "desc card", (long) 2, (long) 1, "24/03/2015", "27/03/2015");
    }

    @Transactional
    private void addUser(String name, String email, String password, UserRoleEnum role) {
        if (!userRepository.existsByEmail(email)) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);

            userRepository.save(user);
        }
    }

    @Transactional
    private void addList(String title, Long previousListId) {
        if (!taskListRepository.existsByTitle(title)) {
            CreateTaskListDTO createtTaskList = new CreateTaskListDTO();
            createtTaskList.setTitle(title);
            createtTaskList.setPreviousListId(previousListId);
            taskListService.createTaskList(createtTaskList);
        }
    }

    @Transactional
    private void addCard(String title, String description, Long userId, Long taskListId, String startDate, String endDate) {
        CreateTaskDTO createTaskList = new CreateTaskDTO();
        createTaskList.setTitle(title);
        createTaskList.setDescription(description);
        createTaskList.setUsersIds(Arrays.asList(userId));
        createTaskList.setListId(taskListId);
        createTaskList.setStartDate(startDate);
        createTaskList.setEndDate(endDate);

        taskService.createTask(createTaskList);
    }

}