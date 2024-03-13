package com.assignment.webapp.Exception;

public class ConsultantRequestNotFoundException extends RuntimeException{

    public ConsultantRequestNotFoundException(String message){
        super(message);
    }
}
