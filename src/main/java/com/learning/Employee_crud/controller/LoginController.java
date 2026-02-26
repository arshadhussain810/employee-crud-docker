package com.learning.Employee_crud.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping("/showLoginPage")
    public String showLoginPage(){
        return "login-page";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied-page";
    }

}
