package com.example.api.drones.dto;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * @Author manorip
 * @create 1/22/23 12:04 PM
 */
public class MedicationDTO {

    private Long id;
    @Pattern(regexp = "[A-Z0-9_]+", message = "only upper case letters, ‘_’ and numbers are allowed for code")
    private String code;
    @Pattern(regexp = "[A-Za-z0-9_-]+", message = "only letters, numbers, ‘-‘, ‘_’ are allowed for name")
    private String name;
    private BigDecimal weight;
    private byte[] image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
