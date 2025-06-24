package com.example.fleet_management.repositories;

import com.example.fleet_management.entity.Matatu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatatuRepository extends JpaRepository<Matatu, String> {
//    @Override
   Optional<Matatu> findByPlateNumber(String plateNumber);
   Optional<Matatu> deleteByPlateNumber(String plateNumber);

}
