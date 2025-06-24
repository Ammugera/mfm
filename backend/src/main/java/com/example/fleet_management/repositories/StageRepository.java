package com.example.fleet_management.repositories;

import com.example.fleet_management.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StageRepository extends JpaRepository<Stage, Long>{
    //@Override
    Optional<Stage> findByName(String name);
    Optional<Stage> findByLocation(String location);
    Optional<Stage> findAllByLocation(String location);
}
