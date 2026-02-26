package com.learning.Employee_crud.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/showLoginPage").permitAll()
                .requestMatchers("/processLoginForm").permitAll()
                .requestMatchers("/showRegistrationForm/**", "/handelRegistratinForm").permitAll()
                .requestMatchers("/employee/deleteEmployee", "/department/showDepartmentForm").hasRole("ADMIN")
                .requestMatchers("/employee/viewEmployees",
                                            "/employee/editEmployee/**",
                                            "/employee/showEmployeeForm/**",
                                            "/department/showDepartmentTable/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
        ).formLogin(login -> login
                .loginPage("/showLoginPage")
                .loginProcessingUrl("/processLoginForm")
                .defaultSuccessUrl("/employee/home")
        ).logout(logout -> logout.permitAll()
        ).exceptionHandling( ex -> ex
                .accessDeniedPage("/access-denied"));

        return http.build();
    }

}
