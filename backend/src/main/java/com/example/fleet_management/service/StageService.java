package com.example.fleet_management.service;

import com.example.fleet_management.entity.Employee;
import com.example.fleet_management.entity.Matatu;
import com.example.fleet_management.entity.Stage;
import com.example.fleet_management.repositories.StageRepository;
import com.example.fleet_management.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {
    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Stage> getAllStages(){
        return stageRepository.findAll();
    }
//    public List<Stage> getAllStageNamesInLocation(String location){
//        return stageRepository.findAllByLocation(String location);
//    }

    public String createStage(@RequestBody Stage stage){
        String name = stage.getName();
        String location = stage.getLocation();
        if(name.isBlank() || name==null){
            return "Add stage name";
        }
        if(location.isBlank() || location==null){
            return "Add stage location";
        }

        Optional<Stage> existingName = stageRepository.findByName(name);
        Optional<Stage> existingLocation = stageRepository.findByLocation(location);

        if(existingName.isPresent() && existingLocation.isPresent()){
            return "Stage " + name + "-" + location + " already exists";
        }

        stageRepository.save(stage);
        return "Stage " + name + "-" + location +  " created successfully";
    }

    public String deleteStage(@PathVariable Long id){
        Optional<Stage> existing = stageRepository.findById(id);
        if(existing.isEmpty()){
            return "Stage " + id + " does not exist";
        }
        stageRepository.deleteById(id);
        return "Stage with ID " + id + " deleted successfully";
    }

//    public String assignMarshallToStage(Long id, String email){
//        Optional<Stage> optionalStage = stageRepository.findById(id);
//        Optional<Employee> optionalEmployee = employeeRepository.findById(email);
//
//        Employee employee=  optionalEmployee.get();
//        if(!"marshall".equalsIgnoreCase(employee.getRole())){
//            return "Employee is not a marshall";
//        }
//
//        Stage stage= optionalStage.get();
//        if(stage.getMarshall()==null){
//            stage.setMarshall(employee);
//        }else {
//            return "Stage is already assigned";
//        }
//
//        stageRepository.save(stage);
//        return "Stage assigned successfully";
//    }

    public String assignMarshallToStage(String name, String email){
        Optional<Stage> optionalStage = stageRepository.findByName(name);
        Optional<Employee> optionalEmployee = employeeRepository.findById(email);

        Employee employee=  optionalEmployee.get();
        if(!"marshall".equalsIgnoreCase(employee.getRole())){
            return "Employee is not a marshall";
        }

        Stage stage= optionalStage.get();
        if(stage.getMarshall()==null){
            stage.setMarshall(employee);
        }else {
            return "Stage is already assigned";
        }

        stageRepository.save(stage);
        return "Stage assigned successfully";
    }
}
