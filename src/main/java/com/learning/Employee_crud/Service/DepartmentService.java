package com.learning.Employee_crud.Service;

import com.learning.Employee_crud.Repository.DepartmentRepository;
import com.learning.Employee_crud.customException.DuplicateDepartmentCodeException;
import com.learning.Employee_crud.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public Department findDepartmentById(int id){
        Optional<Department> result = departmentRepository.findById(id);

        Department department = null;
        if(result.isPresent()){
            department = result.get();
        }else {
            throw new RuntimeException("No such department");
        }

        return department;
    }

    public void save(Department department){
        department.setStatus("active");

        if(department.getId() == null){
            if (departmentRepository.existsByDepartmentCode(department.getDepartmentCode())){
                System.out.println("create error");
                throw new DuplicateDepartmentCodeException("Duplicate department code");
            }
        }else{
            if (departmentRepository.existsByDepartmentCodeAndIdNot(department.getDepartmentCode(), department.getId())){
                System.out.println("Edit error");
                throw new DuplicateDepartmentCodeException("Duplicate department code");
            }
        }

        departmentRepository.save(department);
    }

    public List<Department> findAllDepartments(){
        return departmentRepository.findAll();
    }

    public void deleteDepartmentById(Integer id){
        departmentRepository.deleteById(id);
    }

}
