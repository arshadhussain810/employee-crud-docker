package com.learning.Employee_crud.customException;

public class DuplicateUserException extends RuntimeException{
    public DuplicateUserException(String message){
        super(message);
    }
}
