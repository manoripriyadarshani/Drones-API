package com.example.api.drones.dto;

import java.util.List;

/**
 * @Author manorip
 * @create 1/22/23 12:02 PM
 */
public class DroneListDTO {

    private List<DroneDTO> drones;

    public List<DroneDTO> getDrones() {
        return drones;
    }

    public void setDrones(List<DroneDTO> drones) {
        this.drones = drones;
    }

}
