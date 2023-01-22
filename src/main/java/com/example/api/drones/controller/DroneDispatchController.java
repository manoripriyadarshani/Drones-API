package com.example.api.drones.controller;

import com.example.api.drones.dto.DroneDTO;
import com.example.api.drones.dto.DroneListDTO;
import com.example.api.drones.dto.PagedDroneListDTO;
import com.example.api.drones.enums.DroneState;
import com.example.api.drones.service.IDroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{serial_number}")
    public ResponseEntity<DroneDTO> getDroneBySerialNumber(@PathVariable("serial_number") String serialNumber) {
        DroneDTO drone = droneService.findBySerialNumber(serialNumber);
        return new ResponseEntity<>(drone, HttpStatus.OK);
    }

    @GetMapping("/states/{state}")
    public ResponseEntity<DroneListDTO> getDronesByStatus(@PathVariable("state") DroneState status) {
        List<DroneDTO> drones = droneService.findByState(status);
        DroneListDTO droneListDTO = new DroneListDTO();
        droneListDTO.setDrones(drones);
        return new ResponseEntity<>(droneListDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PagedDroneListDTO> getAllDrones(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DroneDTO> dronesPage = droneService.findAllDrones(pageable);
        PagedDroneListDTO responseDTO = new PagedDroneListDTO();
        responseDTO.setContent(dronesPage.getContent());
        responseDTO.setTotalElements(dronesPage.getTotalElements());
        responseDTO.setTotalPages(dronesPage.getTotalPages());
        responseDTO.setLast(dronesPage.isLast());
        responseDTO.setPageNo(page);
        responseDTO.setPageSize(size);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
