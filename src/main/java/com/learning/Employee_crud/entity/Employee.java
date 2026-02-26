package com.learning.Employee_crud.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
;


@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "*is required")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "*is required")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "*is required")
    @Email(message = "Invalid Email")
    @Column(name = "email")
    private String email;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "*is required")
    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;
    
    @NotBlank(message = "*is required")
    @Column(name = "status")
    private String status;


    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {}

    public Employee(String firstName, String lastName, String email, LocalDate dateOfJoining, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfJoining=" + dateOfJoining +
                ", status='" + status + '\'' +
                ", department=" + department +
                '}';
    }
}
