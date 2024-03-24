package br.com.tarefas.gerenciador.dto.task;

import br.com.tarefas.gerenciador.model.User;
import lombok.Data;

@Data
public class GetUserDTO {
    Long id;
    String name;

    public GetUserDTO() { }  

    public GetUserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName(); 
    }
}