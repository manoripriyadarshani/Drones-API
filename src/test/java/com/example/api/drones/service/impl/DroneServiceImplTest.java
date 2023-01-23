package com.example.api.drones.service.impl;

import com.example.api.drones.config.BaseProperties;
import com.example.api.drones.dto.MedicationDTO;
import com.example.api.drones.model.enums.DroneState;
import com.example.api.drones.exception.DroneRunTimeValidationException;
import com.example.api.drones.helper.DTOMapper;
import com.example.api.drones.model.Drone;
import com.example.api.drones.repository.DroneRepository;
import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @Author manorip
 * @create 1/23/23 7:41 PM
 */
public class DroneServiceImplTest {
    @Mock
    DroneRepository droneRepository;
    @Spy
    DTOMapper dtoMapper;
    @Mock
    BaseProperties baseProperties;
    @InjectMocks
    DroneServiceImpl droneServiceImpl;
    @Captor
    ArgumentCaptor<Drone> droneArgumentCaptor;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(expectedExceptions = EntityNotFoundException.class, expectedExceptionsMessageRegExp = "Drone entity not found")
    void testAddMedications_whenDroneIsNotExist_thenThrowException() {

        String serialNumber = "abc-1";
        MedicationDTO medication1 = new MedicationDTO();
        medication1.setName("Paracetamol");
        medication1.setCode("MC-1");
        medication1.setWeight(BigDecimal.valueOf(50.50));

        Optional<Drone> emptyOptional = Optional.empty();
        when(droneRepository.findById(serialNumber)).thenReturn(emptyOptional);

        droneServiceImpl.addMedications(serialNumber, Collections.singletonList(medication1));
    }

    @Test(expectedExceptions = DroneRunTimeValidationException.class, expectedExceptionsMessageRegExp = "Medications are over weighted")
    void testAddMedications_whenMedicationsOverWeight_thenThrowException() {

        String serialNumber = "abc-1";

        MedicationDTO medication1 = new MedicationDTO();
        medication1.setName("Paracetamol");
        medication1.setCode("MC-1");
        medication1.setWeight(BigDecimal.valueOf(250.50));

        MedicationDTO medication2 = new MedicationDTO();
        medication2.setName("VitaminC");
        medication2.setCode("MC-2");
        medication2.setWeight(BigDecimal.valueOf(120.50));

        Drone drone = new Drone();
        drone.setSerialNumber(serialNumber);
        drone.setState(DroneState.IDLE);
        drone.setWeightLimit(BigDecimal.valueOf(300));

        when(droneRepository.findById(serialNumber)).thenReturn(Optional.of(drone));
        droneServiceImpl.addMedications(serialNumber, Arrays.asList(medication1, medication2));
    }

    @Test(expectedExceptions = DroneRunTimeValidationException.class, expectedExceptionsMessageRegExp = "Drone is not idle")
    void testAddMedications_whenDroneIsNotIdle_thenThrowException() {

        String serialNumber = "abc-1";

        MedicationDTO medication1 = new MedicationDTO();
        medication1.setName("Paracetamol");
        medication1.setCode("MC-1");
        medication1.setWeight(BigDecimal.valueOf(50.50));

        MedicationDTO medication2 = new MedicationDTO();
        medication2.setName("VitaminC");
        medication2.setCode("MC-2");
        medication2.setWeight(BigDecimal.valueOf(120.50));

        Drone drone = new Drone();
        drone.setSerialNumber(serialNumber);
        drone.setState(DroneState.LOADED);
        drone.setWeightLimit(BigDecimal.valueOf(300));

        when(droneRepository.findById(serialNumber)).thenReturn(Optional.of(drone));
        droneServiceImpl.addMedications(serialNumber, Arrays.asList(medication1, medication2));
    }

    @Test(expectedExceptions = DroneRunTimeValidationException.class, expectedExceptionsMessageRegExp = "Drone is running out of battery")
    void testAddMedications_whenDroneBatteryIsLow_thenThrowException() {

        String serialNumber = "abc-1";
        Double requiredMinBatteryLevel = 25.00;

        MedicationDTO medication1 = new MedicationDTO();
        medication1.setName("Paracetamol");
        medication1.setCode("MC-1");
        medication1.setWeight(BigDecimal.valueOf(50.50));

        MedicationDTO medication2 = new MedicationDTO();
        medication2.setName("VitaminC");
        medication2.setCode("MC-2");
        medication2.setWeight(BigDecimal.valueOf(120.50));

        Drone drone = new Drone();
        drone.setSerialNumber(serialNumber);
        drone.setState(DroneState.IDLE);
        drone.setWeightLimit(BigDecimal.valueOf(300));
        drone.setBatteryCapacityPercentage(BigDecimal.valueOf(10.0));

        when(droneRepository.findById(serialNumber)).thenReturn(Optional.of(drone));
        when(baseProperties.getDroneRequiredMinBatteryLevel()).thenReturn(requiredMinBatteryLevel);
        droneServiceImpl.addMedications(serialNumber, Arrays.asList(medication1, medication2));
    }


    @Test
    void testAddMedications_whenDroneSuccessfullyLoaded_thenMedicationsAdded() {

        String serialNumber = "abc-1";
        Double requiredMinBatteryLevel = 25.00;

        MedicationDTO medication1 = new MedicationDTO();
        medication1.setName("Paracetamol");
        medication1.setCode("MC-1");
        medication1.setWeight(BigDecimal.valueOf(50.50));

        MedicationDTO medication2 = new MedicationDTO();
        medication2.setName("VitaminC");
        medication2.setCode("MC-2");
        medication2.setWeight(BigDecimal.valueOf(120.50));

        Drone drone = new Drone();
        drone.setSerialNumber(serialNumber);
        drone.setState(DroneState.IDLE);
        drone.setWeightLimit(BigDecimal.valueOf(300));
        drone.setBatteryCapacityPercentage(BigDecimal.valueOf(90.0));

        when(droneRepository.findById(serialNumber)).thenReturn(Optional.of(drone));
        when(droneRepository.save(drone)).thenReturn(drone);
        when(baseProperties.getDroneRequiredMinBatteryLevel()).thenReturn(requiredMinBatteryLevel);

        droneServiceImpl.addMedications(serialNumber, Arrays.asList(medication1, medication2));

        verify(droneRepository).save(droneArgumentCaptor.capture());
        Drone capturedDrone = droneArgumentCaptor.getValue();
        Assert.assertEquals(capturedDrone.getState(), DroneState.LOADED);
        Assert.assertEquals(capturedDrone.getMedications().size(),2);
    }

}


