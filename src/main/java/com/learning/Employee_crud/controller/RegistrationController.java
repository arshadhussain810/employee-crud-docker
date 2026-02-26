package com.learning.Employee_crud.controller;

import com.learning.Employee_crud.Service.RoleService;
import com.learning.Employee_crud.Service.UserService;
import com.learning.Employee_crud.customException.DuplicateUserException;
import com.learning.Employee_crud.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, trimmerEditor);
    }

    private RoleService roleService;
    private UserService userService;

    public RegistrationController(RoleService roleService, UserService userService){
        this.roleService = roleService;
        this.userService  = userService;
    }

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model model){
        model.addAttribute("roles", roleService.findAllRoles() );
        model.addAttribute("user", new User());
        return "registration-form";
    }

    @PostMapping("/handelRegistratinForm")
    public String handelRegistrationForm(@Valid @ModelAttribute("user") User user,
                                         BindingResult bindingResult,
                                         Model model,
                                         @RequestParam("roleId") Integer id){

        if (bindingResult.hasErrors()){
            model.addAttribute("roles", roleService.findAllRoles());
            return "registration-form";
        }

        try{
            userService.save(user, id);
        }catch (DuplicateUserException ex){
            bindingResult.rejectValue("username", "duplicate", ex.getMessage());
            model.addAttribute("roles", roleService.findAllRoles());
            return "registration-form";
        }


        return "redirect:/showRegistrationForm";
    }
}
