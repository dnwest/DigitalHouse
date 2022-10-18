package com.example.exemploException.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // vai fazer o tratamento de todos os erros
public class GlobalExceptions {
    //para funcionar no spring boot temos que implementar um método que vai
    //pegar o erro e tornar ele como responseEntity
    @ExceptionHandler // vai utilizar essa configuração para fazer o tratamento do erro
    public ResponseEntity<String> processarErrorNotFound(RessourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()+ " Global...");
    }

}
