package com.example.fleet_management.service;

import com.example.fleet_management.entity.Employee;
import com.example.fleet_management.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public List<Employee> getAllDrivers(){
        return employeeRepository.findByRole("driver");
    }

    public List<Employee> getAllMarshalls(){
        return employeeRepository.findByRole("marshall");
    }

    public List<Employee> getAllAdmins(){
        return employeeRepository.findByRole("admin");
    }

    public String createEmployee(Employee employee){
        String email = employee.getEmail();
        if(email.isBlank()||email==null){
            return "Email field cannot be empty";
        }

        Optional<Employee> existing = employeeRepository.findById(email);
        if(existing.isPresent()){
            return "User " + email + " already exists";
        }
        employeeRepository.save(employee);
        return email + " created successfully";
    }

    public String deleteEmployee(String email){
        Optional<Employee> existing = employeeRepository.findById(email);
        if(existing.isEmpty()){
            return "User " + email + " does not exist";
        }
        employeeRepository.deleteById(email);
        return email + " deleted successfully";
    }
}
