package com.example.api.drones.controller;

import com.example.api.drones.dto.DroneDTO;
import com.example.api.drones.service.IDroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author manorip
 * @create 1/22/23 11:46 AM
 */

@RestController
@RequestMapping("/v1/api/drones")
public class DroneDispatchController {

    @Autowired
    private IDroneService droneService;

    @PostMapping()
    public ResponseEntity<DroneDTO> addDrone(@RequestBody DroneDTO droneDTO) {
        DroneDTO responseDTO = droneService.create(droneDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
