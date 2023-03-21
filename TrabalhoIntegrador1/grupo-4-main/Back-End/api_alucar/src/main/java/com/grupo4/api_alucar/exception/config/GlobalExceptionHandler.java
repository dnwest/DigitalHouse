package com.grupo4.api_alucar.exception.config;

import com.grupo4.api_alucar.exception.entity.DefaultError;
import com.grupo4.api_alucar.exception.handler.BadRequestException;
import com.grupo4.api_alucar.exception.handler.ResourceNotFoundException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DefaultError> resourceNotFoundHandler(Exception e){

        DefaultError error = DefaultError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name())
                .detail(e.getMessage())
                .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<DefaultError> badRequestExceptionHandler(Exception e){
        DefaultError error = DefaultError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .detail(e.getMessage())
                .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
