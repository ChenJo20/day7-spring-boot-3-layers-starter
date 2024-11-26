package com.oocl.springbootemployee.service;

public class NotActiveEmployeeException extends RuntimeException {
    public NotActiveEmployeeException() {
        super("Employee is inactive");
    }
}
