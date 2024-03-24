package br.com.tarefas.gerenciador.dto.user;

import br.com.tarefas.gerenciador.model.User;
import lombok.Data;

@Data
public class SimpleUserDTO {
    Long id;
    String name;

    public SimpleUserDTO() { }  

    public SimpleUserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName(); 
    }
}