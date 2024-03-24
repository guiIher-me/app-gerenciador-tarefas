package br.com.tarefas.gerenciador.dto.tasklist;

import jakarta.validation.constraints.NotNull;

public class CreateTaskListDTO {
    
    @NotNull(message = "Field 'title' is mandatory")
    private String title;
    private Long previousListId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPreviousListId() {
        return previousListId;
    }
    
    public void setPreviousListId(Long previousListId) {
        this.previousListId = previousListId;
    }

}
