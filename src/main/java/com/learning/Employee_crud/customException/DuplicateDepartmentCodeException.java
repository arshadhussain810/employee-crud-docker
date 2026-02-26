package com.learning.Employee_crud.customException;

public class DuplicateDepartmentCodeException extends RuntimeException{

    public DuplicateDepartmentCodeException(String message){
        super(message);
    }

}
