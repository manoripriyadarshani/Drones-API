package com.example.api.drones.dto;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author manorip
 * @create 1/22/23 11:36 PM
 */
public class MedicationListDTO {

    @Valid
    private List<MedicationDTO> medications;

    public List<MedicationDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationDTO> medications) {
        this.medications = medications;
    }
}
