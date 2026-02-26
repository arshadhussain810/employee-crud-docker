package com.learning.Employee_crud.Repository;

import com.learning.Employee_crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
