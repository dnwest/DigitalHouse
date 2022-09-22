package com.example.exemploException.exceptions;

public class BadRequestException extends Exception{

    public BadRequestException(String message){
        super(message);
    }
}
