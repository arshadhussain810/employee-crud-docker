package com.learning.Employee_crud.Service;

import com.learning.Employee_crud.Repository.RoleRepository;
import com.learning.Employee_crud.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }

    public Optional<Role> findRoleById(Integer id){
        return roleRepository.findById(id);
    }

}
