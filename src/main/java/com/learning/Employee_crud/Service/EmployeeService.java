package com.learning.Employee_crud.Service;

import com.learning.Employee_crud.Repository.EmployeeRepository;
import com.learning.Employee_crud.customException.DuplicateEmployeeException;
import com.learning.Employee_crud.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public void save(Employee employee){
        //For creating new employee data
        if(employee.getId() == null){
            if(employeeRepository.existsByEmail(employee.getEmail())){
                throw new DuplicateEmployeeException("*Duplicate Employee");
            }
        }else{
            //For updating employee
            if (employeeRepository.existsByEmailAndIdNot(employee.getEmail(), employee.getId())){
                throw new DuplicateEmployeeException("*Duplicate Employee");
            }
        }

        employeeRepository.save(employee);
    }

    public Optional<Employee> findById(int id){
        return  employeeRepository.findById(id);
    }

    public List<Employee> findByFirstName(String firstName){
        return employeeRepository.findByFirstNameIgnoreCaseOrderByFirstNameAsc(firstName);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    public void deleteEmployeeById(int id){
        employeeRepository.deleteById(id);
    }


}
