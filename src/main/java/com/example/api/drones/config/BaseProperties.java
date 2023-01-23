package com.example.api.drones.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author manorip
 * @create 1/23/23 4:52 AM
 */

@Configuration
public class BaseProperties {

    @Value("${drone.required.min.battery.level}")
    private Double droneRequiredMinBatteryLevel;

    public Double getDroneRequiredMinBatteryLevel() {
        return droneRequiredMinBatteryLevel;
    }
}
