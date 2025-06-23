package com.example.fleet_management.controller;

import com.example.fleet_management.entity.Stage;
import com.example.fleet_management.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stages")
public class StageController {
    @Autowired
    private StageService stageService;

    @PreAuthorize("hasAuthority('admin') or hasAuthority('marshall')")
    @GetMapping
    public List<Stage> getAllStages(){
        return stageService.getAllStages();
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public String createStage(@RequestBody Stage stage){
        return stageService.createStage(stage);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("{id}")
    public String deleteStage(@PathVariable Long id){
        return stageService.deleteStage(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("{location}/{name}/assign-stage/{email}")
    public String assignMarshallToStage(@PathVariable String name, @PathVariable String email){
        return stageService.assignMarshallToStage(name, email);
    }


}

