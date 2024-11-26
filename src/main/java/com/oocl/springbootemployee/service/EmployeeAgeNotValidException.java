package com.oocl.springbootemployee.service;

public class EmployeeAgeNotValidException extends RuntimeException{
    public EmployeeAgeNotValidException() {
        super("Employee age not valid");
    }
}
