package com.example.fleet_management.controller;

import com.example.fleet_management.entity.Matatu;
import com.example.fleet_management.service.MatatuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matatus")
public class MatatuController{
    @Autowired
    private MatatuService matatuService;

    @PreAuthorize("hasAuthority('admin') or hasAuthority('marshall')")
    @GetMapping
    public List<Matatu> getAllMatatus(){
        return matatuService.getAllMatatus();
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('marshall')")
    @GetMapping("{plateNumber}")
    public Matatu getMatatuDetails(String plateNumber){
        return matatuService.getMatatuDetails(plateNumber);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public String createMatatu(@RequestBody Matatu matatu){
        return matatuService.createMatatu(matatu);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("{plateNumber}")
    public String deleteMatatu(@PathVariable String plateNumber){
        return matatuService.deleteMatatu(plateNumber);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('marshall')")
    @PutMapping("{plateNumber}/assign-driver/{email}")
    public String assignDriver(@PathVariable String plateNumber, @PathVariable String email){
        return matatuService.assignDriverToMatatu(plateNumber, email);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('marshall')")
    @PutMapping("{plateNumber}/assign-status/{status}")
    public String assignStatus(@PathVariable String plateNumber, @PathVariable String status){
        return matatuService.assignStatus(plateNumber, status);
    }

    //assign marshall to stage
}
