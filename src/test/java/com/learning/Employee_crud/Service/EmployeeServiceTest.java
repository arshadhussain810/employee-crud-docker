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
import java.util.Arrays;
import java.util.List;
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
    void save_UpdateEmployee_WhenIdNotNullEmailExist() {
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
    void findByFirstName_WhenNamePreset_RestEmployee() {
        String testName = "testName";
        Employee mockEmployee1 = new Employee();
        mockEmployee1.setId(1001);
        mockEmployee1.setFirstName(testName);
        mockEmployee1.setLastName("testname");
        mockEmployee1.setEmail("test@example.com");
        mockEmployee1.setDateOfJoining(LocalDate.now());
        mockEmployee1.setStatus("active");

        Employee mockEmployee2 = new Employee();
        mockEmployee2.setId(1002);
        mockEmployee2.setFirstName(testName);
        mockEmployee2.setLastName("testname");
        mockEmployee2.setEmail("test@example.com");
        mockEmployee2.setDateOfJoining(LocalDate.now());
        mockEmployee2.setStatus("active");

        List<Employee> mockEmployeeList = Arrays.asList(mockEmployee1, mockEmployee2);

        when(employeeRepository.findByFirstNameIgnoreCaseOrderByFirstNameAsc(testName))
                .thenReturn(mockEmployeeList);

        List<Employee> result =  employeeService.findByFirstName(testName);

        assertEquals(2, result.size());
        assertEquals(testName, result.get(1).getFirstName());
        assertEquals(mockEmployee1.getId(), result.get(0).getId());
        assertEquals(mockEmployee2.getId(), result.get(1).getId());

        verify(employeeRepository, times(1))
                .findByFirstNameIgnoreCaseOrderByFirstNameAsc(testName);
    }

    @Test
    void findAllEmployees() {

        Employee mockEmployee1 = new Employee();
        mockEmployee1.setId(1001);
        mockEmployee1.setFirstName("testName");
        mockEmployee1.setLastName("testname");
        mockEmployee1.setEmail("test@example.com");
        mockEmployee1.setDateOfJoining(LocalDate.now());
        mockEmployee1.setStatus("active");

        Employee mockEmployee2 = new Employee();
        mockEmployee2.setId(1002);
        mockEmployee2.setFirstName("testName");
        mockEmployee2.setLastName("testname");
        mockEmployee2.setEmail("test@example.com");
        mockEmployee2.setDateOfJoining(LocalDate.now());
        mockEmployee2.setStatus("active");

        List<Employee> mockEmployeeList = Arrays.asList(mockEmployee1, mockEmployee2);

        when(employeeRepository.findAll()).thenReturn(mockEmployeeList);

        List<Employee> result = employeeService.findAllEmployees();

        assertEquals(2, result.size());



    }
}