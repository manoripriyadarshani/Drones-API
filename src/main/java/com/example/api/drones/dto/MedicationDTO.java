package com.example.api.drones.dto;

import java.math.BigDecimal;

/**
 * @Author manorip
 * @create 1/22/23 12:04 PM
 */
public class MedicationDTO {

    private String code;
    private String name;
    private BigDecimal weight;
    private byte[] image;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
