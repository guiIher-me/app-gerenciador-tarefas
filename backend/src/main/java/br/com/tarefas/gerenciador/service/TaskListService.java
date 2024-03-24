package br.com.tarefas.gerenciador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import br.com.tarefas.gerenciador.dto.tasklist.CreateTaskListDTO;
import br.com.tarefas.gerenciador.model.TaskList;
import br.com.tarefas.gerenciador.repository.TaskListRepository;

@Service
public class TaskListService {

    @Autowired
    private TaskListRepository taskListRepository;

    @SuppressWarnings("null")
    public TaskList getOrFail(Long taskListId) {
        TaskList previousTaskList = taskListRepository.findById(taskListId).orElse(null);
        this.assertTaskList(previousTaskList instanceof TaskList);
        return previousTaskList;
    }

    @SuppressWarnings("null")
    public TaskList getOrFail(Long taskListId, String failMessage) {
        TaskList previousTaskList = taskListRepository.findById(taskListId).orElse(null);
        this.assertTaskList(previousTaskList instanceof TaskList, failMessage);
        return previousTaskList;
    }

    public void assertTaskList(boolean condition) throws ResourceNotFoundException {
        this.assertTaskList(condition, "List not found!");
    }

    public void assertTaskList(boolean condition, @NonNull String message) throws ResourceNotFoundException {
        if (!condition) throw new ResourceNotFoundException(message);
    }

    public TaskList createTaskList(CreateTaskListDTO createTasklist) {
        Long previousListId = createTasklist.getPreviousListId();

        TaskList taskList = new TaskList();
        taskList.setTitle(createTasklist.getTitle());

        if (previousListId == null)
            return this.createTaskListAtEnd(taskList);
        
        TaskList previousTaskList = this.getOrFail(previousListId, "Previous List ID not found!");

        Double previousPosition = previousTaskList.getPosition();
        TaskList nextTaskList = taskListRepository.findFirstByPositionGreaterThan(previousPosition).orElse(null);

        if (nextTaskList == null)
            return this.createTaskListAtEnd(taskList, previousPosition);

        Double nextPosition = nextTaskList.getPosition();
        Double middlePosition = (previousPosition + nextPosition) / 2;
        taskList.setPosition(middlePosition);
        return taskListRepository.save(taskList);
    }

    private TaskList createTaskListAtEnd(TaskList taskList) {
        Double lastPosition = taskListRepository.findMaxPosition();
        return createTaskListAtEnd(taskList, lastPosition);
    }

    private TaskList createTaskListAtEnd(TaskList taskList, Double lastPosition) {
        taskList.setPosition(lastPosition + 1);
        return taskListRepository.save(taskList);
    }

    @SuppressWarnings("null")
    public void deleteTaskListById(Long id) {
        TaskList taskList = getOrFail(id);
        taskListRepository.delete(taskList);
    }

}