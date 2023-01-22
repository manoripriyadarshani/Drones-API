package com.example.api.drones.helpers;

import com.example.api.drones.dto.DroneDTO;
import com.example.api.drones.model.Drone;
import org.springframework.stereotype.Component;

/**
 * @Author manorip
 * @create 1/22/23 12:48 PM
 */

@Component
public class DTOMapper {

    public Drone convertToEntity(DroneDTO droneDTO) {
        Drone drone = new Drone();
        drone.setSerialNumber(droneDTO.getSerialNumber());
        drone.setWeightLimit(droneDTO.getWeightLimit());
        drone.setBatteryCapacityPercentage(droneDTO.getBatteryCapacityPercentage());
        drone.setModel(droneDTO.getModel());
        drone.setState(droneDTO.getState());
        return drone;
    }

    public DroneDTO convertToDTO(Drone drone) {
        DroneDTO droneDTO = new DroneDTO();
        droneDTO.setSerialNumber(drone.getSerialNumber());
        droneDTO.setWeightLimit(drone.getWeightLimit());
        droneDTO.setBatteryCapacityPercentage(drone.getBatteryCapacityPercentage());
        droneDTO.setModel(drone.getModel());
        droneDTO.setState(drone.getState());
        return droneDTO;
    }
}
