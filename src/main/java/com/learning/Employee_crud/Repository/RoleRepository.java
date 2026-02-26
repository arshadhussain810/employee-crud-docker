package com.learning.Employee_crud.Repository;

import com.learning.Employee_crud.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
