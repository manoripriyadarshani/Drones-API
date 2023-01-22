package com.example.api.drones.repository;

import com.example.api.drones.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author manorip
 * @create 1/22/23 11:41 AM
 */

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

}
