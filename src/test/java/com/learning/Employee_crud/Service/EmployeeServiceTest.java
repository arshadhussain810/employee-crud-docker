package com.learning.Employee_crud.Service;

import com.learning.Employee_crud.Repository.EmployeeRepository;
import com.learning.Employee_crud.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void save() {
    }

    @Test
    void findById_WhenIdPresent_EmployeeShouldBeReturned() {
        Employee mockEmployee = new Employee();
        mockEmployee.setId(001);
        mockEmployee.setFirstName("testname");
        mockEmployee.setLastName("testname");
        mockEmployee.setEmail("test@example.com");
        mockEmployee.setDateOfJoining(LocalDate.now());
        mockEmployee.setStatus("active");

        when(employeeRepository.findById(mockEmployee.getId()))
                .thenReturn(Optional.of(mockEmployee));

        Optional<Employee> result = employeeService.findById(mockEmployee.getId());

        assertTrue( result.isPresent());
        assertEquals(001, result.get().getId());

        verify(employeeRepository, times(1)).findById(mockEmployee.getId());
    }

    @Test
    void findByFirstName() {
    }

    @Test
    void findAllEmployees() {
    }
}