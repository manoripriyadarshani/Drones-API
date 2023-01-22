package com.example.api.drones.service.impl;

import com.example.api.drones.dto.DroneDTO;
import com.example.api.drones.enums.DroneState;
import com.example.api.drones.helpers.DTOMapper;
import com.example.api.drones.model.Drone;
import com.example.api.drones.repository.DroneRepository;
import com.example.api.drones.service.IDroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author manorip
 * @create 1/22/23 11:48 AM
 */

@Service
public class DroneServiceImpl implements IDroneService {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DTOMapper dtoMapper;

    @Override
    public DroneDTO create(DroneDTO droneDTO) {
        droneDTO.setState(DroneState.IDLE);
        Drone drone = dtoMapper.convertToEntity(droneDTO);
        Drone saved = droneRepository.save(drone);
        return dtoMapper.convertToDTO(saved);
    }

    @Override
    public DroneDTO findBySerialNumber(String serialNumber){
        Drone drone = droneRepository.findById(serialNumber).orElse(null);
        return dtoMapper.convertToDTO(drone);
    }

    @Override
    public List<DroneDTO> findByState(DroneState state){
        return droneRepository.findByState(state).stream().map(d->dtoMapper.convertToDTO(d)).collect(Collectors.toList());
    }

    @Override
    public Page<DroneDTO> findAllDrones(Pageable pageable){
        return droneRepository.findAll(pageable).map(d->dtoMapper.convertToDTO(d));
    }



}
