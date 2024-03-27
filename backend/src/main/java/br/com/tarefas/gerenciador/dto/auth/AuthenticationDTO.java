package br.com.tarefas.gerenciador.dto.auth;

import lombok.Data;

@Data
public class AuthenticationDTO {
    private String login;
    private String password; 
}
