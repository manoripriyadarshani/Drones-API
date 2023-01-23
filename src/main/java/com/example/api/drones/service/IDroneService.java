package com.example.api.drones.service;

import com.example.api.drones.dto.DroneDTO;
import com.example.api.drones.dto.MedicationDTO;
import com.example.api.drones.enums.DroneState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author manorip
 * @create 1/22/23 11:47 AM
 */

public interface IDroneService {
    DroneDTO create(DroneDTO droneDTO);
    DroneDTO findBySerialNumber(String serialNumber);
    List<DroneDTO> findByState(DroneState state);
    Page<DroneDTO> findAllDrones(Pageable pageable);
    List<MedicationDTO> addMedications(String serialNumber, List<MedicationDTO> medications);
    List<MedicationDTO> getMedications(String serialNumber);
    List<DroneDTO> findAll();
}
