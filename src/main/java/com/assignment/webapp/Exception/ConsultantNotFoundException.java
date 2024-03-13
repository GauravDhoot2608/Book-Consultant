package com.assignment.webapp.Exception;

public class ConsultantNotFoundException extends RuntimeException{

    public ConsultantNotFoundException(String mess){
        super(mess);
    }
}
