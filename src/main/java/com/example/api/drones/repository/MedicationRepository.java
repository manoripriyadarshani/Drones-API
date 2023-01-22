package com.example.api.drones.repository;

import com.example.api.drones.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author manorip
 * @create 1/22/23 11:43 AM
 */

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String> {
}
