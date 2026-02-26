package com.learning.Employee_crud.securityConfig;

import com.learning.Employee_crud.Repository.UserRepository;

import com.learning.Employee_crud.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

     private UserRepository userRepository;

     public CustomUserDetailsService(UserRepository userRepository){
         this.userRepository = userRepository;
     }

     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
         User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));


         return org.springframework.security.core.userdetails.User.builder()
                 .username(user.getUsername())
                 .password(user.getPassword()) // already BCrypt in DB
                 .authorities(user.getRole().getName()) // ROLE_ADMIN
                 .build();

     }
}
