package com.example.fleet_management.controller;

import com.example.fleet_management.entity.Employee;
import com.example.fleet_management.entity.Matatu;
import com.example.fleet_management.repositories.EmployeeRepository;
import com.example.fleet_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('marshall')")
    @GetMapping("/drivers")
    public List<Employee> getAllDrivers(){
        return employeeService.getAllDrivers();
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('marshall')")
    @GetMapping("/marshalls")
    public List<Employee> getAllMarshalls(){
        return employeeService.getAllMarshalls();
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/admins")
    public List<Employee> getAllAdmins(){
        return employeeService.getAllAdmins();
    }

    //Get one employee details

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public String createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("{email}")
    public String deleteEmployee(@PathVariable String email){
        return employeeService.deleteEmployee(email);
    }
}
