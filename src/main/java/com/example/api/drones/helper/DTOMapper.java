package com.example.api.drones.helper;

import com.example.api.drones.dto.DroneDTO;
import com.example.api.drones.dto.MedicationDTO;
import com.example.api.drones.model.Drone;
import com.example.api.drones.model.Medication;
import org.springframework.stereotype.Component;

/**
 * @Author manorip
 * @create 1/22/23 12:48 PM
 */

@Component
public class DTOMapper {

    public Drone convertToEntity(DroneDTO droneDTO) {
        if (droneDTO != null) {
            Drone drone = new Drone();
            drone.setSerialNumber(droneDTO.getSerialNumber());
            drone.setWeightLimit(droneDTO.getWeightLimit());
            drone.setBatteryCapacityPercentage(droneDTO.getBatteryCapacityPercentage());
            drone.setModel(droneDTO.getModel());
            drone.setState(droneDTO.getState());
            return drone;
        }
        return null;
    }

    public DroneDTO convertToDTO(Drone drone) {
        if (drone != null) {
            DroneDTO droneDTO = new DroneDTO();
            droneDTO.setSerialNumber(drone.getSerialNumber());
            droneDTO.setWeightLimit(drone.getWeightLimit());
            droneDTO.setBatteryCapacityPercentage(drone.getBatteryCapacityPercentage());
            droneDTO.setModel(drone.getModel());
            droneDTO.setState(drone.getState());
            return droneDTO;
        }
        return null;
    }

    public Medication convertToEntity(MedicationDTO medicationDTO) {
        if (medicationDTO != null) {
            Medication medication = new Medication();
            medication.setId(medicationDTO.getId());
            medication.setCode(medicationDTO.getCode());
            medication.setName(medicationDTO.getName());
            medication.setWeight(medicationDTO.getWeight());
            medication.setImage(medicationDTO.getImage());
            return medication;
        }
        return null;
    }

    public MedicationDTO convertToDTO(Medication medication) {
        if (medication != null) {
            MedicationDTO medicationDTO = new MedicationDTO();
            medicationDTO.setId(medication.getId());
            medicationDTO.setCode(medication.getCode());
            medicationDTO.setName(medication.getName());
            medicationDTO.setWeight(medication.getWeight());
            medicationDTO.setImage(medication.getImage());
            return medicationDTO;
        }
        return null;
    }

}
