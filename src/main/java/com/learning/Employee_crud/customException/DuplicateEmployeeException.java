package com.learning.Employee_crud.customException;

public class DuplicateEmployeeException extends RuntimeException{
    public DuplicateEmployeeException(String message){
        super(message);
    }
}
