package br.com.tarefas.gerenciador.service;

import java.security.InvalidParameterException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.tarefas.gerenciador.dto.task.CreateTaskDTO;
import br.com.tarefas.gerenciador.model.Task;
import br.com.tarefas.gerenciador.model.TaskList;
import br.com.tarefas.gerenciador.model.User;
import br.com.tarefas.gerenciador.repository.TaskRepository;
import br.com.tarefas.gerenciador.util.Utils;

@Service
public class TaskService {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskListService taskListService;

    @Autowired
    private TaskRepository taskRepository;

    public void assertTask(boolean condition) throws ResourceNotFoundException {
        if (!condition) throw new ResourceNotFoundException("Task not found!");
    }

    public void assertDatePeriod(LocalDate start, LocalDate end) throws InvalidParameterException {
        if (start == null || end == null) return;
        if (start.isAfter(end)) throw new InvalidParameterException("Start date cannot be later than end date!");
    }

    public Task getOrFail(Long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        this.assertTask(task instanceof Task);
        return task;
    }

    public Task createTask(CreateTaskDTO createTaskDTO) {
        User user = userService.getOrFail(createTaskDTO.getUserId());
        TaskList taskList = taskListService.getOrFail(createTaskDTO.getListId());

        LocalDate start = null;
        if (createTaskDTO.getStartDate() != null)
            start = Utils.stringToDate(createTaskDTO.getStartDate());

        LocalDate end = null;
        if (createTaskDTO.getEndDate() != null)
            end = Utils.stringToDate(createTaskDTO.getEndDate());
        
        this.assertDatePeriod(start, end);
        
        Task task = new Task();
        task.setTitle(createTaskDTO.getTitle());
        task.setDescription(createTaskDTO.getDescription());
        task.setUser(user);
        task.setTaskList(taskList);
        task.setStartDate(start);
        task.setEndDate(end);
        
        Task savedTask = taskRepository.save(task);
        return savedTask;
    }

    public Task moveTaskToList(Long taskId, Long listId) {
        Task task = this.getOrFail(taskId);
        TaskList taskList = taskListService.getOrFail(listId);

        task.setTaskList(taskList);
        Task updatedTask = taskRepository.save(task);
        return updatedTask;
    }

    public void deleteById(Long id) {
        Task task = getOrFail(id);
        this.taskRepository.delete(task);
    }
    
}
