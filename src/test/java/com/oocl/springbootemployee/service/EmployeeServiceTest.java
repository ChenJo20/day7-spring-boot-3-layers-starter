package com.oocl.springbootemployee.service;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.IEmployeeRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    @Test
    void should_return_the_given_employees_when_getAllEmployees() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        when(mockedEmployeeRepository.getAll()).thenReturn(List.of(new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0)));
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        List<Employee> allEmployees = employeeService.getAllEmployees();

        //then
        assertEquals(1, allEmployees.size());
        assertEquals("Lucy", allEmployees.get(0).getName());
    }

    @Test
    void should_return_the_created_employee_when_create_given_a_employee() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        Employee lucy = new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0);
        when(mockedEmployeeRepository.addEmployee(any())).thenReturn(lucy);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Employee createdEmployee = employeeService.creat(lucy);

        //then
        assertEquals("Lucy", createdEmployee.getName());
    }

    @Test
    void should_throw_EmployeeAgeNotValidException_when_create_given_a_employee_aged_17() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        Employee johnson = new Employee(2, "Johnson", 17, Gender.MALE, 8000.0);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        //then
        assertThrows(EmployeeAgeNotValidException.class, () -> employeeService.creat(johnson));
        verify(mockedEmployeeRepository, never()).addEmployee(any());
    }

    @Test
    void should_throw_EmployeeAgeNotValidException_when_create_given_a_employee_aged_66() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        Employee johnson = new Employee(2, "Johnson", 66, Gender.MALE, 8000.0);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        //then
        assertThrows(EmployeeAgeNotValidException.class, () -> employeeService.creat(johnson));
        verify(mockedEmployeeRepository, never()).addEmployee(any());
    }

    @Test
    void should_throw_EmployeeAgeSalaryNotMatchException_when_create_given_a_employee_aged_33_salary_250() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        Employee johnson = new Employee(2, "Johnson", 30, Gender.MALE, 250.0);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        //then
        assertThrows(EmployeeAgeSalaryNotMatchException.class, () -> employeeService.creat(johnson));
        verify(mockedEmployeeRepository, never()).addEmployee(any());
    }
}
