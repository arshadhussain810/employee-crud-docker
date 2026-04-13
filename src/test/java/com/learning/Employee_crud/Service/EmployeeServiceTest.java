package com.learning.Employee_crud.Service;

import com.learning.Employee_crud.Repository.EmployeeRepository;
import com.learning.Employee_crud.customException.DuplicateEmployeeException;
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
    void save_ThrowException_WhenIdNullEmailExists() {
        Employee mockEmployee = new Employee();
        mockEmployee.setFirstName("testname");
        mockEmployee.setLastName("testname");
        mockEmployee.setEmail("test@example.com");
        mockEmployee.setDateOfJoining(LocalDate.now());
        mockEmployee.setStatus("active");

        when(employeeRepository.existsByEmail(mockEmployee.getEmail()))
                .thenReturn(true);

        assertThrows(DuplicateEmployeeException.class, ()->{
            employeeService.save(mockEmployee);
        });

        verify(employeeRepository, never()).save(mockEmployee);
    }

    @Test
    void save_SaveEmployee_WhenIdNullEmailDontExist() {
        Employee mockEmployee = new Employee();
        mockEmployee.setFirstName("testname");
        mockEmployee.setLastName("testname");
        mockEmployee.setEmail("test@example.com");
        mockEmployee.setDateOfJoining(LocalDate.now());
        mockEmployee.setStatus("active");

        when(employeeRepository.existsByEmail(mockEmployee.getEmail()))
                .thenReturn(false);

        employeeService.save(mockEmployee);
        verify(employeeRepository, times(1)).save(mockEmployee);

    }

    @Test
    void save_UpdateEmployee_WhenIdNotNullEmaiExist() {
        Employee mockEmployee = new Employee();
        mockEmployee.setId(1001);
        mockEmployee.setFirstName("testname");
        mockEmployee.setLastName("testname");
        mockEmployee.setEmail("test@example.com");
        mockEmployee.setDateOfJoining(LocalDate.now());
        mockEmployee.setStatus("active");

        when(employeeRepository.existsByEmailAndIdNot(mockEmployee.getEmail(), mockEmployee.getId()))
                .thenReturn(false);

        employeeService.save(mockEmployee);
        verify(employeeRepository, times(1)).save(mockEmployee);

    }

    @Test
    void findById_WhenIdPresent_EmployeeShouldBeReturned() {
        Employee mockEmployee = new Employee();
        mockEmployee.setId(1001);
        mockEmployee.setFirstName("testname");
        mockEmployee.setLastName("testname");
        mockEmployee.setEmail("test@example.com");
        mockEmployee.setDateOfJoining(LocalDate.now());
        mockEmployee.setStatus("active");

        when(employeeRepository.findById(mockEmployee.getId()))
                .thenReturn(Optional.of(mockEmployee));

        Optional<Employee> result = employeeService.findById(mockEmployee.getId());

        assertTrue( result.isPresent());
        assertEquals(1001, result.get().getId());

        verify(employeeRepository, times(1)).findById(mockEmployee.getId());
    }

    @Test
    void findByFirstName() {
    }

    @Test
    void findAllEmployees() {
    }
}