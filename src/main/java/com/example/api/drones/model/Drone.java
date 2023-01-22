package com.example.api.drones.model;

import com.example.api.drones.enums.DroneModel;
import com.example.api.drones.enums.DroneState;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author manorip
 * @create 1/21/23 11:19 PM
 */

@Entity
@Table(name = "drone")
public class Drone {

    @Id
    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @Column(name = "weight_limit")
    private BigDecimal weightLimit;

    @Column(name = "battery_capacity")
    private BigDecimal batteryCapacityPercentage;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private DroneState state;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    @JoinColumn(name = "drone_serial_number")
    private List<Medication> medications = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdTime;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedTime;

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

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
