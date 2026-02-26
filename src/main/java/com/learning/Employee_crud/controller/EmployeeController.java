package com.learning.Employee_crud.controller;

import com.learning.Employee_crud.Service.DepartmentService;
import com.learning.Employee_crud.Service.EmployeeService;
import com.learning.Employee_crud.customException.DuplicateEmployeeException;
import com.learning.Employee_crud.entity.Employee;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService){
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, trimmerEditor);
    }

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

    @GetMapping("/viewEmployees")
    public String viewEmployees(Model model){

        model.addAttribute("employees", employeeService.findAllEmployees());
        return "view-employees";
    }

    @GetMapping("/searchEmployee")
    public String searchEmployee(Model model, @RequestParam(value = "searchType", required = false) String searchType,
                                 @RequestParam(value = "value", required = false) String value){

        if(value == null || searchType.isEmpty() || value.trim().isEmpty()){
            model.addAttribute("employees", employeeService.findAllEmployees());
            return "view-employees";
        }

        if(searchType.equals("Id")){
            for(int i = 0; i < value.length(); i++){
                if(value.charAt(i) < 48 || value.charAt(i) > 57){
                    return "view-employees";
                }
            }
            Optional<Employee> employee = employeeService.findById(Integer.parseInt(value));

            if(employee.isPresent()){
                model.addAttribute("employees", employee.get());
                return "view-employees";
            }
        }else{
            model.addAttribute("employees", employeeService.findByFirstName(value));
            return "view-employees";
        }

        return "view-employees";
    }

    @GetMapping("/editEmployee")
    public String editEmployee(Model model, @RequestParam("emplId") int id){

        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        model.addAttribute("employee",employee);
        model.addAttribute("department", departmentService.findAllDepartments());

        return "employee-form";
    }

    @GetMapping("/showEmployeeForm")
    public String showEmployeeForm(Model model){

        model.addAttribute("employee", new Employee());
        model.addAttribute("department", departmentService.findAllDepartments());

        return "employee-form";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes,
                               @RequestParam("departmentId") int id
                               ){

        if (bindingResult.hasErrors()){
            model.addAttribute("department", departmentService.findAllDepartments());
            return "employee-form";
        }

        employee.setDepartment(departmentService.findDepartmentById(id));

        try{
            employeeService.save(employee);
        }catch (DuplicateEmployeeException dee){
            //Marking email field as invalid and attaching error message to it
            bindingResult.rejectValue("email", "duplicate", dee.getMessage());
            model.addAttribute("department", departmentService.findAllDepartments());
            return "employee-form";
        }

        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("message", "Employee data successfully saved");
        return "redirect:/employee/showEmployeeForm";
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("emplId") Integer id){
       employeeService.deleteEmployeeById(id);

        return "redirect:/employee/viewEmployees";
    }
}
