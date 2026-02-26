package com.learning.Employee_crud.Service;

import com.learning.Employee_crud.Repository.UserRepository;
import com.learning.Employee_crud.customException.DuplicateUserException;
import com.learning.Employee_crud.entity.Role;
import com.learning.Employee_crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user, Integer id){

        if (userRepository.existsByUsername(user.getUsername())){
            throw new DuplicateUserException("User already exists");
        }

        Optional<Role> result = roleService.findRoleById(id);
        Role role = null;
        if(result.isPresent()){
            role = result.get();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive("y");
        user.setRole(role);

        userRepository.save(user);
    }

}
