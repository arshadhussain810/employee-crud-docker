package com.learning.Employee_crud.Repository;

import com.learning.Employee_crud.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    boolean existsByDepartmentCode(String departmentCode);

    boolean existsByDepartmentCodeAndIdNot(String departmentCode, Integer id);
}
