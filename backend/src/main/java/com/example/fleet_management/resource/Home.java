package com.example.fleet_management.resource;

import com.example.fleet_management.entity.Matatu;
import com.example.fleet_management.service.MatatuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Home {
    @Autowired
    private MatatuService matatuService;

    @GetMapping("/")
    public String Homepage(){
        return "Waassssuupppp";
    }
}
