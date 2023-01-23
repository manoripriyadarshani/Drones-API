package com.example.api.drones.dto;

import com.example.api.drones.enums.DroneModel;
import com.example.api.drones.enums.DroneState;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @Author manorip
 * @create 1/22/23 12:02 PM
 */
public class DroneDTO {

    @Size(max = 100, message = "length of the serialNumber must be less than 100")
    @NotEmpty(message = "serialNumber must not be empty")
    private String serialNumber;
    private DroneModel model;
    @Max(value = 500, message = "weightLimit must be less than 500 ")
    private BigDecimal weightLimit;
    @Max(value = 100, message = "batteryCapacityPercentage must be less than 100 ")
    private BigDecimal batteryCapacityPercentage;
    private DroneState state;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public BigDecimal getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(BigDecimal weightLimit) {
        this.weightLimit = weightLimit;
    }

    public BigDecimal getBatteryCapacityPercentage() {
        return batteryCapacityPercentage;
    }

    public void setBatteryCapacityPercentage(BigDecimal batteryCapacityPercentage) {
        this.batteryCapacityPercentage = batteryCapacityPercentage;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }
}
