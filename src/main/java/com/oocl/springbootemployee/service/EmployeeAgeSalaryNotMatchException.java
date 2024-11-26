package com.oocl.springbootemployee.service;

public class EmployeeAgeSalaryNotMatchException extends RuntimeException {
    public EmployeeAgeSalaryNotMatchException() {
        super("Employee age salary not match");
    }
}
