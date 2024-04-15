package br.com.tarefas.gerenciador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.tarefas.gerenciador.dto.subtask.CreateSubtaskDTO;
import br.com.tarefas.gerenciador.model.Subtask;
import br.com.tarefas.gerenciador.model.Task;
import br.com.tarefas.gerenciador.repository.SubtaskRepository;

@Service
public class SubtaskService {

    @Autowired
    private SubtaskRepository subtaskRepository;

    @Autowired
    private TaskService taskService;

    public void assertSubtask(boolean condition) throws ResourceNotFoundException {
        if (!condition) throw new ResourceNotFoundException("Subtask not found!");
    }

    public Subtask createSubtask(CreateSubtaskDTO createSubtaskDTO) {
        Subtask subtask = (Subtask) taskService.getTaskByCreateDTO(createSubtaskDTO);
        Task parent = taskService.getOrFail(createSubtaskDTO.getParentTaskId());
        subtask.setParent(parent);

        Subtask savedSubtask = subtaskRepository.save(subtask);
        return savedSubtask;
    }

    public Subtask getOrFail(Long subtaskId) {
        Subtask subtask = subtaskRepository.findById(subtaskId).orElse(null);
        this.assertSubtask(subtask instanceof Subtask);
        return subtask;
    }

    
    
}
