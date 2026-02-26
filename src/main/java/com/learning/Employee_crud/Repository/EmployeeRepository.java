package com.learning.Employee_crud.Repository;

import com.learning.Employee_crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface  EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByFirstNameIgnoreCaseOrderByFirstNameAsc(String firstName);

    //When creating new Employee
    boolean existsByEmail(String email);

    //When updating existing employee
    boolean existsByEmailAndIdNot(String email, Integer id);

}
