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

        if (previousListId == null)
            return this.createTaskListAtStart(createTasklist);
        
        TaskList previousTaskList = this.getOrFail(previousListId, "Previous List ID not found!");

        Double previousPosition = previousTaskList.getPosition();
        TaskList nextTaskList = taskListRepository.findFirstByPositionGreaterThan(previousPosition).orElse(null);

        if (nextTaskList == null)
            return this.createTaskListAtEnd(previousTaskList, createTasklist);

        return this.createTaskListAtMiddle(previousTaskList, createTasklist, nextTaskList);
    }

    private TaskList createTaskListAtStart(CreateTaskListDTO createTasklist) {
        Double firstPosition = taskListRepository.findMinPosition();
        TaskList taskList = new TaskList(createTasklist.getTitle(), firstPosition);
        return taskListRepository.save(taskList);
    }

    private TaskList createTaskListAtMiddle(TaskList previousTaskList, CreateTaskListDTO createTasklist, TaskList nextTaskList) {
        Double previousPosition = previousTaskList.getPosition();
        Double nextPosition = nextTaskList.getPosition();
        Double middlePosition = (previousPosition + nextPosition) / 2;

        TaskList taskList = new TaskList(createTasklist.getTitle(), middlePosition);
        return taskListRepository.save(taskList);
    }

    private TaskList createTaskListAtEnd(TaskList lastTaskList, CreateTaskListDTO createTasklist) {
        Double lastPosition = lastTaskList.getPosition() + 1.0;
        TaskList taskList = new TaskList(createTasklist.getTitle(), lastPosition);
        return taskListRepository.save(taskList);
    }

    @SuppressWarnings("null")
    public void deleteTaskListById(Long id) {
        TaskList taskList = getOrFail(id);
        taskListRepository.delete(taskList);
    }

}