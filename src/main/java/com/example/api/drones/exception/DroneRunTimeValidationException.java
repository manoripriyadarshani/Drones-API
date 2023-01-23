package com.example.api.drones.exception;

/**
 * @Author manorip
 * @create 1/23/23 1:36 PM
 */
public class DroneRunTimeValidationException extends BaseDroneException {

    public DroneRunTimeValidationException(String message, String developerMessage, String errorCode) {
        super(message, developerMessage, errorCode);
    }
}
