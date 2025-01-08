package com.flynaut.crud.rest;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(String message) {
        super(message); // It will call the super class constructor
    }

//    public StudentNotFoundException(String message, Throwable cause) {
//        super(message, cause);
//    }
//
//    public StudentNotFoundException(Throwable cause) {
//        super(cause);
//    }
}
