package com.example.api.drones.service.impl;

import com.example.api.drones.config.BaseProperties;
import com.example.api.drones.dto.DroneDTO;
import com.example.api.drones.dto.MedicationDTO;
import com.example.api.drones.model.enums.DroneState;
import com.example.api.drones.exception.DroneConstrainViolationException;
import com.example.api.drones.exception.DroneRunTimeValidationException;
import com.example.api.drones.helper.DTOMapper;
import com.example.api.drones.model.Drone;
import com.example.api.drones.model.Medication;
import com.example.api.drones.repository.DroneRepository;
import com.example.api.drones.service.IDroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private BaseProperties baseProperties;

    @Override
    public DroneDTO create(DroneDTO droneDTO) {
        droneDTO.setState(DroneState.IDLE);
        Drone drone = dtoMapper.convertToEntity(droneDTO);
        if (droneRepository.findById(droneDTO.getSerialNumber()).isPresent()) {
            throw new DroneConstrainViolationException("Drone is already exist", "Drone entity already exist for given serial-number ", "ERR-9");
        }
        Drone saved = droneRepository.save(drone);
        return dtoMapper.convertToDTO(saved);
    }

    @Override
    public DroneDTO findBySerialNumber(String serialNumber) {
        Drone drone = droneRepository.findById(serialNumber).orElse(null);
        return dtoMapper.convertToDTO(drone);
    }

    @Override
    public List<DroneDTO> findByState(DroneState state) {
        return droneRepository.findByState(state).stream().map(d -> dtoMapper.convertToDTO(d)).collect(Collectors.toList());
    }

    @Override
    public Page<DroneDTO> findAllDrones(Pageable pageable) {
        return droneRepository.findAll(pageable).map(d -> dtoMapper.convertToDTO(d));
    }

    @Override
    @Transactional
    public List<MedicationDTO> addMedications(String serialNumber, List<MedicationDTO> medicationDTOs) {
        List<Medication> medications = medicationDTOs.stream().map(d -> dtoMapper.convertToEntity(d)).collect(Collectors.toList());
        Optional<Drone> byId = droneRepository.findById(serialNumber);
        if (byId.isPresent()) {
            Drone drone = byId.get();
            double weightSum = medications.stream().filter(m -> m.getWeight() != null).mapToDouble(m -> m.getWeight().doubleValue()).sum();
            if (weightSum > drone.getWeightLimit().doubleValue()) {
                throw new DroneRunTimeValidationException("Medications are over weighted", "Drone runtime validation failed", "ERR-10");
            } else if (drone.getState() != DroneState.IDLE) {
                throw new DroneRunTimeValidationException("Drone is not idle", "Drone runtime validation failed", "ERR-11");
            } else if (drone.getBatteryCapacityPercentage().doubleValue() < baseProperties.getDroneRequiredMinBatteryLevel()) {
                throw new DroneRunTimeValidationException("Drone is running out of battery", "Drone runtime validation failed", "ERR-12");
            }
            drone.getMedications().addAll(medications);
            drone.setState(DroneState.LOADED);
            Drone saved = droneRepository.save(drone);
            return saved.getMedications().stream().map(d -> dtoMapper.convertToDTO(d)).collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException("Drone entity not found");
        }
    }

    @Override
    public List<MedicationDTO> getMedications(String serialNumber) {
        Optional<Drone> droneOptional = droneRepository.findById(serialNumber);
        if (droneOptional.isPresent()) {
            return droneOptional.get().getMedications().stream().map(d -> dtoMapper.convertToDTO(d)).collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException("Drone entity not found");
        }
    }

    @Override
    public List<DroneDTO> findAll() {
        return droneRepository.findAll().stream().map(d -> dtoMapper.convertToDTO(d)).collect(Collectors.toList());
    }

}
