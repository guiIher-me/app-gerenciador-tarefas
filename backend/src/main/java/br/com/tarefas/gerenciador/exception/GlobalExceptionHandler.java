package br.com.tarefas.gerenciador.exception;

import java.security.InvalidParameterException;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
        int status = HttpStatus.NOT_FOUND.value();
        ErrorResponse errorResponse = new ErrorResponse(status, ex.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleResourceNotFoundException() {
        int status = HttpStatus.NOT_FOUND.value();
        String message = "Endpoint not found";

        ErrorResponse errorResponse = new ErrorResponse(status, message);
        return errorResponse;
    }

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorResponse InvalidParameterException(InvalidParameterException ex) {
        int status = HttpStatus.UNPROCESSABLE_ENTITY.value();
        ErrorResponse errorResponse = new ErrorResponse(status, ex.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse MethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        int status = HttpStatus.BAD_REQUEST.value();

        BindingResult result = ex.getBindingResult();
        FieldError fieldError = result.getFieldErrors().get(0);
        String message = fieldError.getDefaultMessage();

        ErrorResponse errorResponse = new ErrorResponse(status, message);
        return errorResponse;
    }
}