package br.com.tarefas.gerenciador.exception;

public class HttpBadRequestException extends Exception {

    public HttpBadRequestException(String message) {
        super(message);
    }
    
}