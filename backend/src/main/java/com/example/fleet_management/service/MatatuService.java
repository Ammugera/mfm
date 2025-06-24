package com.example.fleet_management.service;

import com.example.fleet_management.entity.Employee;
import com.example.fleet_management.entity.Matatu;
import com.example.fleet_management.repositories.EmployeeRepository;
import com.example.fleet_management.repositories.MatatuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public class MatatuService {
    @Autowired
    private MatatuRepository matatuRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Matatu> getAllMatatus(){
        return matatuRepository.findAll();
    }

    public Matatu getMatatuDetails(String plateNumber){
        //Optional<Matatu> optionalMatatu = matatuRepository.findByPlateNumber(plateNumber);
        Matatu matatu = matatuRepository.findByPlateNumber(plateNumber)
                .orElseThrow(()-> new RuntimeException("Matatu not found"));
        return matatu;
    }

    public String createMatatu(Matatu matatu){
        String plateNumber = matatu.getPlateNumber();

        if(plateNumber==null || plateNumber.isBlank()){
            return "Plate number is required";
        }

        plateNumber = plateNumber.replaceAll("\\s", "".toUpperCase());
        matatu.setPlateNumber(plateNumber);

        Optional<Matatu> existing = matatuRepository.findByPlateNumber(plateNumber);
        if(existing.isPresent()){
            return "Matatu " + plateNumber + " already exists";
        }

        matatuRepository.save(matatu);
        return "Matatu " + plateNumber + " created successfully";
    }

    public String deleteMatatu(String plateNumber){
        plateNumber = plateNumber.replaceAll("\\s", "".toUpperCase());
        Optional<Matatu> existing = matatuRepository.findByPlateNumber(plateNumber);
        if(existing.isEmpty()){
            return "Matatu with number plate " + plateNumber + " does not exist";
        }
        matatuRepository.deleteById(plateNumber);
        return "Matatu with number plate " + plateNumber + " deleted successfully";
    }

    public String assignDriverToMatatu(String plateNumber, String email){
        Optional<Matatu> optionalMatatu= matatuRepository.findByPlateNumber(plateNumber);
        Optional<Employee> optionalEmployee= employeeRepository.findById(email);

        if(optionalMatatu.isEmpty()){return "Matatu not found";}
        if(optionalEmployee.isEmpty()){return "Employee not found";}

        Employee employee= optionalEmployee.get();
        if(!"driver".equalsIgnoreCase(employee.getRole())){
            return "Employee is not a driver";
        }

        Matatu matatu= optionalMatatu.get();
        if(matatu.getDriver()==null){
            matatu.setDriver(employee);
        }else {
            return "Matatu is already assigned";
        }

        matatuRepository.save(matatu);
        return "Driver assigned to Matatu " + plateNumber;
    }

    public String assignStatus(String plateNumber, String status){
        Optional<Matatu> matatuAssignment = matatuRepository.findByPlateNumber(plateNumber);
        Matatu matatu = matatuAssignment.get();
        matatu.setStatus(status);
        matatuRepository.save(matatu);
        return "Matatu saved successfully";
    }

//    public String removeAssignedDriver(String plateNumber, String email){
//        Optional<Matatu> optionalMatatu = matatuRepository.findByPlateNumber(plateNumber);
//        Optional<Employee> optionalEmployee = employeeRepository.findById(email);
//
//        Matatu matatu = optionalMatatu.get();
//        if(matatu.getDriver()==null){
//            return "Matatu is free (not assigned)";
//        }
//        matatu.getDriver()
//    }

}
