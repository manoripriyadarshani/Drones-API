package com.example.api.drones.exception;

/**
 * @Author manorip
 * @create 1/23/23 1:57 PM
 */
public class DroneConstrainViolationException extends BaseDroneException {

    public DroneConstrainViolationException(String message, String developerMessage, String errorCode) {
        super(message, developerMessage, errorCode);
    }
}
