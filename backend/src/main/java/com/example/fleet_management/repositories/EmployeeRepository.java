package com.example.fleet_management.repositories;

import com.example.fleet_management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

//    @Override
//    List<Employee> findAll();

    List<Employee> findByRole(String role);

    //Optional<User> findByEmail(String email);
}
