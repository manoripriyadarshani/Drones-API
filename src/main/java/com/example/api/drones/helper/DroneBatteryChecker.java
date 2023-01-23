package com.example.api.drones.helper;

import com.example.api.drones.dto.DroneDTO;
import com.example.api.drones.service.IDroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author manorip
 * @create 1/23/23 5:11 AM
 */

@Component
public class DroneBatteryChecker {

    private final Logger logger = LoggerFactory.getLogger(DroneBatteryChecker.class);

    @Autowired
    private IDroneService droneService;

    @Scheduled(cron = "${drone.battery.checker.cron.expression}")
    public void checkDroneBatteryLevel() {
        List<DroneDTO> drones = droneService.findAll();
        for (DroneDTO drone : drones) {
            logger.info("Drone : {} Batter Level:{} ", drone.getSerialNumber(), drone.getBatteryCapacityPercentage());
        }
    }
}
