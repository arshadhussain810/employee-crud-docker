package com.learning.Employee_crud.controller;

import com.learning.Employee_crud.Service.DepartmentService;
import com.learning.Employee_crud.customException.DuplicateDepartmentCodeException;
import com.learning.Employee_crud.entity.Department;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, trimmerEditor);
    }

    @GetMapping("/showDepartmentForm")
    public String showDepartmentForm(Model model){
        model.addAttribute("department", new Department());

        return "department-form";
    }

    @PostMapping("/saveDepartment")
    public String saveDepartment(@Valid @ModelAttribute("department") Department department,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "department-form";
        }

        try{
            departmentService.save(department);
        }catch(DuplicateDepartmentCodeException ex){
            bindingResult.rejectValue("departmentCode", "duplicate", ex.getMessage());
            return "department-form";
        }

        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("message", "Department saved Successfully");

        return "redirect:/department/showDepartmentForm";
    }

    @GetMapping("/editDepartment")
    public String editDepartment(@RequestParam("id") Integer id, Model model){

        model.addAttribute("department", departmentService.findDepartmentById(id));
        return "department-form";
    }

    @GetMapping("/showDepartmentTable")
    public String showDepartmentTable(Model model){
        model.addAttribute("departmentList", departmentService.findAllDepartments());
        return "view-departments";
    }

    @GetMapping("/deleteDepartment")
    public String deleteDepartment(@RequestParam("id") Integer id){
        departmentService.deleteDepartmentById(id);
        return "redirect:/department/showDepartmentTable";
    }
}
