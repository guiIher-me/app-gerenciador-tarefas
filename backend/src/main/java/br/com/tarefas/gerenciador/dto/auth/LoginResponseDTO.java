package br.com.tarefas.gerenciador.dto.auth;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;

    public LoginResponseDTO() { }

    public LoginResponseDTO(String token) {
        this.token = token;
    }
}
