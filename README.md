## Employee Management Application
A secure Employee Management web application built using Spring Boot and Spring MVC to manage employees and departments with role-based access control.

## Tech Stack 
Spring MVC | Spring Boot | MySQL | Docker | Thymeleaf | Hibernate | Spring Security

## Features
1. Create, update, delete, and view employees
2. Role-based authentication and authorization using Spring Security
3. Department management (add and delete departments)
4. Many-to-one relationship between Employee and Department
5. Secure login and access control
6. Dockerized for easy deployment

## Run with Docker
1. Run: docker compose up --build
2. Available at: http://localhost:8000/employee/home

## How to use
1. Register a new account
2. Login using your credentials
3. Manage employees and departments based on your role

## Role-based access
1. Admin: Manage employees and departments (Includes delete and access to all pages)
2. User: Manage employees and departments (does not include delete and access to all pages)
